/**
 * This file is part of JukeIt-Free
 *
 * Copyright (C) 2011-2013  Chris Churchwell
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
/**
 * This file is part of JukeIt
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
package com.chrischurchwell.jukeit.material.blocks;

import java.util.HashMap;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.particle.Particle;
import org.getspout.spoutapi.particle.Particle.ParticleType;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.database.RPStorageData;
import com.chrischurchwell.jukeit.gui.recordplayer.RecordPlayerGUI;
import com.chrischurchwell.jukeit.material.Blocks;
import com.chrischurchwell.jukeit.material.DiscColor;
import com.chrischurchwell.jukeit.material.blocks.designs.RPIndicator;
import com.chrischurchwell.jukeit.material.blocks.designs.RPNeedle;
import com.chrischurchwell.jukeit.material.blocks.designs.RecordPlayerDesign;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;
import com.chrischurchwell.jukeit.permission.CraftPermissible;
import com.chrischurchwell.jukeit.permission.UsePermissible;
import com.chrischurchwell.jukeit.sound.Sound;
import com.chrischurchwell.jukeit.sound.SoundEffect;
import com.chrischurchwell.jukeit.util.Debug;


//TODO This needs to be cleaned up a LOT

public class RecordPlayer extends GenericCustomBlock implements WireConnector, CraftPermissible, UsePermissible {
	
	public static HashMap<String, Integer> designIds = new HashMap<String, Integer>();
	
	public RecordPlayer() {
		super(JukeIt.getInstance(), "Record Player", 5);
		
		//load custom designs.
		int n = 0;
		
		//reverse the disc color values so that NONE is the first in the array
		DiscColor[] discs = DiscColor.values();
		ArrayUtils.reverse(discs);
		
		for (DiscColor disc : discs) {
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
	
	/**
	 * updates a block design in the world by grabbing the rp data
	 * @param block
	 */
	public static void updateBlockDesign(SpoutBlock block) {
		updateBlockDesign(block, RPStorageData.getOrCreateEntry(block));
	}
	
	/**
	 * updates a block design in the world with manual rp data
	 * @param block
	 */
	public static void updateBlockDesign(SpoutBlock block, RPStorageData data) {
		
		Debug.debug("Updating Block Design");
		
		DiscColor disc;
		RPIndicator indicator;
		RPNeedle needle;
		
		if (data.hasDisc()) {
			disc = DiscColor.getByIdentifier(data.getColor());
		} else {
			disc = DiscColor.NONE;
		}
		
		//get indicator info
		if (isPoweredUp(block))
		{
			indicator = RPIndicator.GREEN;
		} else {
			indicator = RPIndicator.RED;
		}
		
		//get needle info
		needle = RPNeedle.getById(data.getNeedle());
		
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
		
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		
		if (player != null) {
			if (!player.hasPermission(getUsePermission())) {
				player.sendMessage("You do not have permission to perform this action.");
				player.sendMessage("("+getUsePermission()+")");
				return;
			}
		}
		
		RPStorageData rpdata = RPStorageData.getOrCreateEntry(block);
		
		if (rpdata.hasDisc() && !RPNeedle.getById(rpdata.getNeedle()).equals(RPNeedle.NONE)) {
			playMusic(rpdata.getUrl(), block.getLocation(), RPNeedle.getById(rpdata.getNeedle()));
		}
	}
	
	public static boolean isPoweredUp(SpoutBlock block) {
		
		if (
				block.getData("recordplayer.powered") != null &&
				((Integer)block.getData("recordplayer.powered")) == 1
				) {
			return true;
		 }
		return false;
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
			
			updateBlockDesign(block);
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
			updateBlockDesign(block);
		} else {
			block.setData("recordplayer.powered", 0);
			Debug.debug("RecordPlayer: Not Powered, and not powering");
		}
		
	}
	
	/**
	 * Event Fired when this block is destroyed.
	 * Firstly, if this player has any items in it, like a record, or a needle, then those items need
	 * to be spawned into the world.
	 * lastly, remove the block data we have saved in the database to keep it nice and tidy.
	 */
	public void onBlockDestroyed(org.bukkit.World world, int x, int y, int z) {
		
		Block block = world.getBlockAt(x, y, z);
		Location spawnLoc = block.getLocation();
		spawnLoc.setY(spawnLoc.getY()+1);
		
		//get data
		RPStorageData rpdata = RPStorageData.getOrCreateEntry(block);
		
		//drop needle if there is one
		if (!RPNeedle.getById(rpdata.getNeedle()).equals(RPNeedle.NONE)) {
			world.dropItem(spawnLoc, new SpoutItemStack(RPNeedle.getById(rpdata.getNeedle()).getItem(), 1));
		}
		
		//drop disc if there is a disc.
		if (rpdata.hasDisc()) {
			ItemStack disc = BurnedDisc.createDisc(rpdata);
			spawnLoc.getWorld().dropItem(spawnLoc, disc);
			
			stopMusic(block.getLocation(), RPNeedle.getById(rpdata.getNeedle()));
		}
		
		RPStorageData.deleteEntries(block);
	}
	
	public static int getRange() {
		return 10;
	}
	
	public static int getRange(Location location, RPNeedle needle) {
		
		int range = getRange();
		
		Debug.debug("Needle modifier is: ", needle.rangeModifier());
		range = range + ((int)Math.floor((double)range * needle.rangeModifier()));
		
		return range;
	}
	
	public void playMusic(String url, Location location, RPNeedle needle) {
		
		url = JukeIt.finishIncompleteURL(url);
		
		Particle particle = new Particle(ParticleType.NOTE, location, new Vector(0, 10, 0));
		particle.setMaxAge(10);
		particle.setGravity(1F);
		particle.spawn();
		
		int range = getRange(location, needle);
		Debug.debug("Playing audio with range: ", range);
		
		if (!isPoweredUp((SpoutBlock)location.getBlock())) {
			new Sound(SoundEffect.RECORD_PLAYER_START, location, 8).play();
		}
		
		//get players in radius of the jukebox and start it for only those players
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)range) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					try {
						SpoutManager.getSoundManager().playCustomMusic(JukeIt.getInstance(), sp, url, true, location, range);
						sp.sendMessage("You are listening to JukeIt (Free). Tell your server admin to upgrade to pro to get more features and remove this message.");
					} catch (Exception e) {
						//the disc has an error.
						SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeIt.getInstance(), "jb_error.wav", false, location, 8);
					}
				}
			}
		}
	}
	
	public static void stopMusic(Location location, RPNeedle needle) {
		stopMusic(location, needle, true);
	}
	
	public static void stopMusic(Location location, RPNeedle needle, boolean playSoundEffect) {
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
		if(playSoundEffect) {
			new Sound(SoundEffect.RECORD_PLAYER_STOP, location, 8).play();
		}
	}
	
	@Override
	public String getCraftPermission() {
		return "jukeit.craft.recordplayer";
	}
	
	@Override
	public String getUsePermission() {
		return "jukeit.use.recordplayer";
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
