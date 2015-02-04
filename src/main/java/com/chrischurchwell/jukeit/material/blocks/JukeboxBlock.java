/**
 * This file is part of JukeIt
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
package com.chrischurchwell.jukeit.material.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.database.RPStorageData;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;
import com.chrischurchwell.jukeit.permission.CraftPermissible;
import com.chrischurchwell.jukeit.permission.UsePermissible;
import com.chrischurchwell.jukeit.sound.Sound;
import com.chrischurchwell.jukeit.sound.SoundEffect;
import com.chrischurchwell.jukeit.util.Debug;
import com.chrischurchwell.jukeit.util.DiscUtil;


public abstract class JukeboxBlock extends GenericCustomBlock implements CraftPermissible, UsePermissible {

	public JukeboxBlock(String name) {
		super(JukeIt.getInstance(), name);
		setBlockDesign(getCustomBlockDesign());
		setRecipe();
	}
	
	public abstract void setRecipe();
	
	public abstract int getRange();
	
	public abstract boolean canRedstoneActivate();
	
	public abstract GenericCubeBlockDesign getCustomBlockDesign();
	
	@Override
	public String getCraftPermission() {
		return "jukeit.craft.jukebox";
	}
	
	@Override
	public String getUsePermission() {
		return "jukeit.use.jukebox";
	}
	
	public boolean canPlaceBlockAt(World arg0, int arg1, int arg2, int arg3) {
		//block is placeable.
		return true;
	}
	
	public boolean canPlaceBlockAt(World arg0, int arg1, int arg2, int arg3, BlockFace arg4) {
		//placeable anywhere
		return true;
	}
	
	public boolean isIndirectlyProdivingPowerTo(World arg0, int arg1, int arg2, int arg3, BlockFace arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isProvidingPowerTo(World arg0, int arg1, int arg2, int arg3,
			BlockFace arg4) {
		// TODO Auto-generated method stub
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
		
		Block block = world.getBlockAt(x, y, z);
		
		RPStorageData rpdata = RPStorageData.getOrCreateEntry(block);
		
		if (rpdata.hasDisc() && !rpdata.getUrl().isEmpty()) {
			this.playMusic(rpdata.getUrl(), block.getLocation());
			
		}
	}
	
	public void onBlockDestroyed(World world, int x, int y, int z) {
		
		Block block = world.getBlockAt(x, y, z);
		
		RPStorageData rpdata = RPStorageData.getOrCreateEntry(block);
		
		if (rpdata.hasDisc()) {
			//create disc to spawn
			ItemStack disc = DiscUtil.createDisc(rpdata);
			Location spawnLoc = block.getLocation();
			spawnLoc.setY(spawnLoc.getY()+1);
			spawnLoc.getWorld().dropItem(spawnLoc, disc);
			
			stopMusic(block.getLocation());
		}
		
		RPStorageData.deleteEntries(block);
	}

	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		if (!player.hasPermission(getUsePermission())) {
			player.sendMessage("You do not have permission to perform this action.");
			player.sendMessage("("+getUsePermission()+")");
			return true;
		}
		
		Block block = world.getBlockAt(x, y, z);
		
		RPStorageData rpdata = RPStorageData.getOrCreateEntry(block);
		
		if (rpdata.hasDisc()) {
			
			if (!player.hasPermission("jukeit.use.burneddisc")) {
				player.sendMessage("You do not have permission to perform this action.");
				player.sendMessage("(jukeit.use.burneddisc)");
				return false;
			}
			
			//get and eject disc.
			//create disc to spawn
			ItemStack disc = DiscUtil.createDisc(rpdata);
			Location spawnLoc = block.getLocation();
			spawnLoc.setY(spawnLoc.getY()+1);
			spawnLoc.getWorld().dropItem(spawnLoc, disc);
			
			RPStorageData.removeDisc(block);
			
			stopMusic(block.getLocation());
			
			return true;
		}
		
		ItemStack inHand = player.getItemInHand().clone();
		
		if ((new SpoutItemStack(inHand)).getMaterial() instanceof BurnedDisc) {
			
			if (!player.hasPermission("jukeit.use.burneddisc")) {
				player.sendMessage("You do not have permission to perform this action.");
				player.sendMessage("(jukeit.use.burneddisc)");
				return false;
			}
			
			//we know its a custom item, go ahaed and remove 1 from the hand.
			if (inHand.getAmount()<2) {
				player.setItemInHand(new ItemStack(Material.AIR));
			} else {
				player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount()-1);
			}
			
			RPStorageData.setDisc(block, inHand);
			
			new Sound(SoundEffect.JUKEBOX_START, world.getBlockAt(x,y,z), 8).play();
			
			//start the music
			String url = DiscUtil.decodeDisc(inHand);
			playMusic(url, block.getLocation());
			
			Debug.debug("Attempting to start jukebox with url ", url);
			
			return true;
		}
		
		new Sound(SoundEffect.JUKEBOX_STOP, world.getBlockAt(x,y,z), 8).play();
		return false;
	}
	
	/**
	 * Event fires when a neighboring block updates, like a Neighboring redstone becomes powered.
	 * We can use this to detemind if this block is now powered.
	 */
	@Override
	public void onNeighborBlockChange(org.bukkit.World world, int x, int y, int z, int changedId) {
		Debug.debug("JukeboxBlock: Neighboring Block Change Event. changedId=", changedId);
		
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		if (
				(
						block.getData("jukeboxblock.powered") == null ||
						(Integer)block.getData("jukeboxblock.powered") == 0
				) &&
				block.isBlockPowered() == true
				) {
			block.setData("jukeboxblock.powered", 1);
			Debug.debug("JukeboxBlock: Redstone Activated");
			
			onBlockClicked(world, x, y, z, null);
			
		} else if (
				block.getData("jukeboxblock.powered") != null &&
				(Integer)block.getData("jukeboxblock.powered") == 1 &&
				block.isBlockPowered() == true
				) {
			Debug.debug("JukeboxBlock: New Redstone Power source, but block is already powered.");
		
		} else if (
				block.getData("jukeboxblock.powered") != null &&
				(Integer)block.getData("jukeboxblock.powered") == 1 &&
				block.isBlockPowered() == false
				) {
			block.setData("jukeboxblock.powered", 0);
			Debug.debug("JukeboxBlock: Lost Redstone Power.");
			
		} else {
			block.setData("jukeboxblock.powered", 0);
			Debug.debug("JukeboxBlock: Not Powered, and not powering");
		}
		
	}

	public void playMusic(String url, Location location) {
		
		url = DiscUtil.finishIncompleteURL(url);
		
		//get players in radius of the jukebox and start it for only those players
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)getRange()) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					try {
						SpoutManager.getSoundManager().playCustomMusic(JukeIt.getInstance(), sp, url, true, location, getRange());
					} catch (Exception e) {
						new Sound(SoundEffect.JUKEBOX_STOP, location, 8).play();
					}
				}
			}
		}
		
	}
	
	public void stopMusic(Location location) {
		//get players in radius of the jukebox and start it for only those players
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)getRange()) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					SpoutManager.getSoundManager().stopMusic(sp);
				}
			}
		}
	}
}
