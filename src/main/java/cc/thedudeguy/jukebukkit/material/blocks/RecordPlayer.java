/**
 * This file is part of JukeBukkit
 *
 * Copyright (C) 2011-2012  Chris Churchwell
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cc.thedudeguy.jukebukkit.material.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.particle.Particle;
import org.getspout.spoutapi.particle.Particle.ParticleType;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.database.RecordPlayerData;
import cc.thedudeguy.jukebukkit.gui.recordplayer.RecordPlayerGUI;
import cc.thedudeguy.jukebukkit.material.Blocks;
import cc.thedudeguy.jukebukkit.material.Items;
import cc.thedudeguy.jukebukkit.material.blocks.designs.RPDisc;
import cc.thedudeguy.jukebukkit.material.blocks.designs.RPIndicator;
import cc.thedudeguy.jukebukkit.material.blocks.designs.RPNeedle;
import cc.thedudeguy.jukebukkit.material.blocks.designs.RecordPlayerDesign;
import cc.thedudeguy.jukebukkit.material.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.material.items.needles.Needle;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.permission.UsePermissible;
import cc.thedudeguy.jukebukkit.sound.Sound;
import cc.thedudeguy.jukebukkit.sound.SoundEffect;
import cc.thedudeguy.jukebukkit.util.Debug;

//TODO This needs to be cleaned up a LOT

public class RecordPlayer extends GenericCustomBlock implements WireConnector, CraftPermissible, UsePermissible {
	
	public static HashMap<String, Integer> designIds = new HashMap<String, Integer>();
	
	public RecordPlayer() {
		super(JukeBukkit.instance, "Record Player", 5);
		
		//load custom designs.
		int n = 0;
		for (RPDisc disc : RPDisc.values()) {
			for (RPNeedle needle : RPNeedle.values()) {
				for (RPIndicator indicator : RPIndicator.values()) {
					RecordPlayerDesign d = new RecordPlayerDesign(needle, disc, indicator);
					designIds.put(d.getDesignTypeId(), n);
					this.setBlockDesign(d, n);
					n++;
				}
			}
		}
		
		//load recipes
		setRecipe();
	}
	
	public static void updateBlockDesign(SpoutBlock block, RecordPlayerData data) {
		
		Debug.debug("Updating Block Design");
		
		RPDisc disc;
		RPIndicator indicator;
		RPNeedle needle;
		
		//get disc info
		if (data.hasDisc()) {
			//get the disc color.
			DiscData discData = JukeBukkit.instance.getDatabase().find(DiscData.class)
					.where()
						.ieq("nameKey", data.getDiscKey())
					.findUnique();
			if (discData == null) {
				Bukkit.getLogger().log(Level.WARNING, "Disc Key is missing from discs table");
				disc = RPDisc.NONE;
			} else {
				disc = RPDisc.getByColor(discData.getColor());
			}
		} else {
			disc = RPDisc.NONE;
		}
		
		//get indicator info
		if (!RPNeedle.getById(data.getNeedleType()).equals(RPNeedle.NONE) && !disc.equals(RPDisc.NONE))
		{
			indicator = RPIndicator.GREEN;
		} else {
			indicator = RPIndicator.RED;
		}
		
		//get needle info
		needle = RPNeedle.getById(data.getNeedleType());
		
		//get design id
		String designName = RecordPlayerDesign.getDesignTypeId(needle, disc, indicator);
		int designId = designIds.get(designName);
		
		SpoutManager.getMaterialManager().overrideBlock(block, Blocks.recordPlayer, (byte) designId);
		
	}
	
	/**
	 * Event fired when a player right clicks on a block.
	 * Lots of things to do here. if theres a disc in it, the disc needs to be ejected, and if the player
	 * is holding the right items in their hand, then those select items may be taken by this block.
	 */
	public boolean onBlockInteract(org.bukkit.World world, int x, int y, int z, SpoutPlayer player) {
		
		player.getMainScreen().attachPopupScreen(new RecordPlayerGUI(player, (SpoutBlock)world.getBlockAt(x, y, z)));
		
		return false;
	}
	
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player) {
		
		if (player != null) {
			if (!player.hasPermission(getUsePermission())) {
				player.sendMessage("You do not have permission to perform this action.");
				player.sendMessage("("+getUsePermission()+")");
				return;
			}
		}
		
		//when the block is placed we need to make sure to get data set up for it.
		RecordPlayerData rpd = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
				.where()
					.eq("x", (double)x)
					.eq("y", (double)y)
					.eq("z", (double)z)
					.ieq("worldName", world.getName())
				.findUnique();
		if (rpd == null) {
			rpd = new RecordPlayerData();
			rpd.setDiscKey(null);
			rpd.setNeedleType(RPNeedle.NONE);
			rpd.setX((double)x);
			rpd.setY((double)y);
			rpd.setZ((double)z);
			rpd.setWorldName(world.getName());
			JukeBukkit.instance.getDatabase().save(rpd);
		}
		
		if (rpd.hasDisc() && !RPNeedle.getById(rpd.getNeedleType()).equals(RPNeedle.NONE)) {
			DiscData discData = JukeBukkit.instance.getDatabase().find(DiscData.class)
					.where()
						.ieq("nameKey", rpd.getDiscKey())
					.findUnique();
			if (discData == null) {
				Bukkit.getLogger().log(Level.WARNING, "Disc Key is missing from discs table");
			} else {
				Location location = new Location(world, (double)x, (double)y, (double)z);
				playMusic(discData.getUrl(), location, RPNeedle.getById(rpd.getNeedleType()));
			}
		}
	}
	
	/**
	 * Event fires when a neighboring block updates, like a Neighboring redstone becomes powered.
	 * We can use this to detemind if this block is now powered.
	 */
	public void onNeighborBlockChange(org.bukkit.World world, int x, int y, int z, int changedId) {
		Debug.debug("RecordPlayer: Neighboring Block Change Event. changedId=", changedId);
		
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		if (
				(
						block.getData("recordplayer.powered") == null ||
						(Integer)block.getData("recordplayer.powered") == 0
				) &&
				block.isBlockPowered() == true
				) {
			block.setData("recordplayer.powered", 1);
			Debug.debug("RecordPlayer: Redstone Activated");
			
			onBlockClicked(world, x, y, z, null);
			
		} else if (
				block.getData("recordplayer.powered") != null &&
				(Integer)block.getData("recordplayer.powered") == 1 &&
				block.isBlockPowered() == true
				) {
			Debug.debug("RecordPlayer: New Redstone Power source, but block is already powered.");
		
		} else if (
				block.getData("recordplayer.powered") != null &&
				(Integer)block.getData("recordplayer.powered") == 1 &&
				block.isBlockPowered() == false
				) {
			block.setData("recordplayer.powered", 0);
			Debug.debug("RecordPlayer: Lost Redstone Power.");
			
		} else {
			block.setData("recordplayer.powered", 0);
			Debug.debug("RecordPlayer: Not Powered, and not powering");
		}
		
	}
	
	/**
	 * Event Fired when this block is placed.
	 * Update/Insert the data into the DB for this block, so we can keep an eye on it.
	 */
	public void onBlockPlace(org.bukkit.World world, int x, int y, int z) {
		//when the block is placed we need to make sure to get data set up for it.
		RecordPlayerData rpd = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
				.where()
					.eq("x", (double)x)
					.eq("y", (double)y)
					.eq("z", (double)z)
					.ieq("worldName", world.getName())
				.findUnique();
		if (rpd == null) {
			rpd = new RecordPlayerData();
			rpd.setDiscKey(null);
			rpd.setNeedleType(RPNeedle.NONE);
			rpd.setX((double)x);
			rpd.setY((double)y);
			rpd.setZ((double)z);
			rpd.setWorldName(world.getName());
			JukeBukkit.instance.getDatabase().save(rpd);
		}
		/* If its still set, well go ahead and leave it, because it could be an blockplace even from setting the custom block to a different subblock for this location */
	}
	
	/**
	 * Event Fired when this block is destroyed.
	 * Firstly, if this player has any items in it, like a record, or a needle, then those items need
	 * to be spawned into the world.
	 * lastly, remove the block data we have saved in the database to keep it nice and tidy.
	 */
	public void onBlockDestroyed(org.bukkit.World world, int x, int y, int z) {
		
		//Bukkit.getLogger().log(Level.INFO, "Block Destroyed.");
		
		Location location = new Location(world, (double)x, (double)y, (double)z);
		Location spawnLoc = location;
		spawnLoc.setY(spawnLoc.getY()+1);
		
		//if theres junk in this block we need to make sure it drops too
		RecordPlayerData rpd = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
				.where()
					.eq("x", (double)x)
					.eq("y", (double)y)
					.eq("z", (double)z)
					.ieq("worldName", world.getName())
				.findUnique();
		if (rpd != null) {
			if (!RPNeedle.getById(rpd.getNeedleType()).equals(RPNeedle.NONE)) {
				world.dropItem(spawnLoc, new SpoutItemStack(RPNeedle.getById(rpd.getNeedleType()).getItem(), 1));
			}
			
			if (rpd.hasDisc()) {
				//get disc.
				DiscData discData = JukeBukkit.instance.getDatabase().find(DiscData.class)
						.where()
							.ieq("nameKey", rpd.getDiscKey())
						.findUnique();
				if (discData == null) {
					Bukkit.getLogger().log(Level.WARNING, "Disc Key is missing from discs table");
				} else {
					//create disc to spawn
					BurnedDisc disc = new BurnedDisc(discData);
					ItemStack iss = new SpoutItemStack(disc, 1);
					spawnLoc.getWorld().dropItem(spawnLoc, iss);
				}
				
				//just in case there was a disc
				stopMusic(location, RPNeedle.getById(rpd.getNeedleType()));
			}
		}
		
		//delete ALL data associated to this location, just incase somehow multiples got into the database this will take care of that.
		List<RecordPlayerData> rpdall = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
				.where()
					.eq("x", (double)x)
					.eq("y", (double)y)
					.eq("z", (double)z)
					.ieq("worldName", world.getName())
				.findList();
		if (!rpdall.isEmpty()) {
			JukeBukkit.instance.getDatabase().delete(rpdall);
		}
	}
	
	public int getRange() {
		return 10;
	}
	
	public int getRange(Location location, RPNeedle needle) {
		
		int range = getRange();
		HashMap<BlockFace,Speaker> blocks = getConnectedBlocks(location);
		
		if (blocks.size() == 1) {
			range = range + 20;
		} else if (blocks.size() > 1) {
			range = range + 40;
		}
		
		Debug.debug("Needle modifier is: ", needle.rangeModifier());
		range = range + ((int)Math.floor((double)range * needle.rangeModifier()));
		
		return range;
	}
	
	public void playMusic(String url, Location location, RPNeedle needle) {
		
		url = JukeBukkit.finishIncompleteURL(url);
		
		Particle particle = new Particle(ParticleType.NOTE, location, new Vector(0, 10, 0));
		particle.setMaxAge(10);
		particle.setGravity(1F);
		particle.spawn();
		
		int range = getRange(location, needle);
		Debug.debug("Playing audio with range: ", range);
		
		new Sound(SoundEffect.RECORD_PLAYER_START, location, 8).play();
		
		//get players in radius of the jukebox and start it for only those players
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)range) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					try {
						SpoutManager.getSoundManager().playCustomMusic(JukeBukkit.instance, sp, url, true, location, range);
					} catch (Exception e) {
						//the disc has an error.
						SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, "jb_error.wav", false, location, 8);
					}
				}
			}
		}
	}
	
	public void stopMusic(Location location, RPNeedle needle) {
		int range = getRange(location, needle);
		Debug.debug("Stopping audio with range: ", range);
		//get players in radius of the jukebox and start it for only those players
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)range) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					SpoutManager.getSoundManager().stopMusic(sp);
				}
			}
		}
		new Sound(SoundEffect.RECORD_PLAYER_STOP, location, 8).play();
	}
	
	public HashMap<BlockFace, Speaker> getConnectedBlocks(Location location) {
		
		HashMap<BlockFace, Speaker> blocks = new HashMap<BlockFace, Speaker>();
		
		Block block = location.getBlock();
		Block check;
		
		//nothing can be on top
		//check = block.getRelative(BlockFace.UP);
		//if ( ((SpoutBlock)check).isCustomBlock() && ((SpoutBlock)check).getCustomBlock() instanceof Speaker ) {
		//	blocks.put(BlockFace.UP, (Speaker)((SpoutBlock)check).getCustomBlock());
		//}
		
		check = block.getRelative(BlockFace.DOWN);
		if ( ((SpoutBlock)check).getCustomBlock() != null && ((SpoutBlock)check).getCustomBlock() instanceof Speaker ) {
			blocks.put(BlockFace.DOWN, (Speaker)((SpoutBlock)check).getCustomBlock());
		}
		
		check = block.getRelative(BlockFace.NORTH);
		if ( ((SpoutBlock)check).getCustomBlock() != null && ((SpoutBlock)check).getCustomBlock() instanceof Speaker ) {
			blocks.put(BlockFace.NORTH, (Speaker)((SpoutBlock)check).getCustomBlock());
		}
		
		check = block.getRelative(BlockFace.SOUTH);
		if ( ((SpoutBlock)check).getCustomBlock() != null && ((SpoutBlock)check).getCustomBlock() instanceof Speaker ) {
			blocks.put(BlockFace.SOUTH, (Speaker)((SpoutBlock)check).getCustomBlock());
		}
		
		check = block.getRelative(BlockFace.EAST);
		if ( ((SpoutBlock)check).getCustomBlock() != null && ((SpoutBlock)check).getCustomBlock() instanceof Speaker ) {
			blocks.put(BlockFace.EAST, (Speaker)((SpoutBlock)check).getCustomBlock());
		}
		
		check = block.getRelative(BlockFace.WEST);
		if ( ((SpoutBlock)check).getCustomBlock() != null && ((SpoutBlock)check).getCustomBlock() instanceof Speaker ) {
			blocks.put(BlockFace.WEST, (Speaker)((SpoutBlock)check).getCustomBlock());
		}
		
		return blocks;
		
	}

	@Override
	public String getCraftPermission() {
		return "jukebukkit.craft.recordplayer";
	}
	
	@Override
	public String getUsePermission() {
		return "jukebukkit.use.recordplayer";
	}
	
	public void setRecipe() {
		
		SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
		r.shape("sps", "njn", "www");
		r.setIngredient('s', MaterialData.oakWoodSlab);
		r.setIngredient('p', MaterialData.stonePressurePlate);
		r.setIngredient('n', Blocks.speaker);
		r.setIngredient('j', MaterialData.jukebox);
		
		r.setIngredient('w', MaterialData.wood);
		SpoutManager.getMaterialManager().registerSpoutRecipe(r);
		
		
		
	}
}
