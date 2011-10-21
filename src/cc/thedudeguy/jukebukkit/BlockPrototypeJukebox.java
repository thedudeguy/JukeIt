/**
 * Copyright (C) 2011  Chris Churchwell
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
 **/
package cc.thedudeguy.jukebukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.items.ItemBurnedObsidyisc;

/**
 * Prototype Jukebox. The Base Custom Disc player. Short range. A model in which I can build from.
 * @author Chris Churchwell
 *
 */
public class BlockPrototypeJukebox extends GenericCubeCustomBlock
{

	public JukeBukkit plugin;
	private int range = 10;
	
	public BlockPrototypeJukebox(JukeBukkit plugin)
	{
		super(
			plugin, 
			"Prototype Jukebox rev. A",
			new GenericCubeBlockDesign(
				plugin, 
				plugin.getCustomsManager().customBlockTexture, 
				new int[] { 0, 0, 0, 0, 0, 1 }
			)
		);
		//ints are { bottom, north, ?, ?, ?, top }
		this.plugin = plugin;
	}
	public BlockPrototypeJukebox(JukeBukkit plugin, Texture texture)
	{
		super(
			plugin, 
			"Prototype Jukebox rev. A",
			new GenericCubeBlockDesign(
				plugin, 
				texture, 
				new int[] { 0, 0, 0, 0, 0, 1 }
			)
		);
		//ints are { bottom, north, ?, ?, ?, top }
		this.plugin = plugin;
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		//construct our location
		Location location = new Location(world, (double)x, (double)y, (double)z);
		String locationKey = plugin.getJukeBoxManager().createLocationKey(location);
		//aka right clicked - load or eject disc.
		//check if it has a disc.
		if (!plugin.getJukeBoxManager().hasDisc(locationKey))
		{
			//jukebox is empty, load
			ItemStack inHand = player.getItemInHand();
			
			if (SpoutManager.getMaterialManager().isCustomItem(inHand))
			{
				//we know its a custom item, go ahaed and remove 1 from the hand.
				if (inHand.getAmount()<2)
				{
					player.setItemInHand(null);
				} else {
					player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount()-1);
				}
				
				CustomItem custom = SpoutManager.getMaterialManager().getCustomItem(inHand);
				//see if its a burned disc.
				if (plugin.getDiscsManager().hasDiscId(custom.getCustomId()))
				{
					
					plugin.getJukeBoxManager().insertDisc(locationKey, custom.getCustomId());
					SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_START, false, location, 3);
				
					/** start the music **/
					int discId = plugin.getJukeBoxManager().getDisc(locationKey);
					if (plugin.getDiscsManager().hasDiscId(discId))
					{
						String url = plugin.getDiscsManager().getUrl(discId);
						/** get players in radius of the jukebox and start it for only those players **/
						for(Player p:location.getWorld().getPlayers())
						{
							double distance = location.toVector().distance(p.getLocation().toVector());
							if (distance<=(double)range)
							{
								SpoutPlayer sp = SpoutManager.getPlayer(p);
								if (sp.isSpoutCraftEnabled()) {
									
									try {
										SpoutManager.getSoundManager().playCustomMusic(plugin, sp, url, true, location, range);
										
									} catch (Exception e) {
										//the disc has an error.
										player.sendMessage(e.getMessage());
										SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
									}
								}
							}
						}
						return true;
					}
					
					
				} else {
					//eject it its bad whatever it is! lolza!1!!
					ItemStack e = inHand.clone();
					e.setAmount(1);
					location.setY(location.getY()+1);
					location.getWorld().dropItem(location, e);
				}
			}
			
			SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
			return true;
		} else {
			//has disc, eject
			//create the disc from the id.
			int discId = plugin.getJukeBoxManager().getDisc(locationKey);
			
			//spawn disc
			ItemBurnedObsidyisc disc = new ItemBurnedObsidyisc(plugin, plugin.getDiscsManager().getKey(discId), plugin.getDiscsManager().getTitle(discId), plugin.getDiscsManager().getColor(discId));
			ItemStack iss = SpoutManager.getMaterialManager().getCustomItemStack(disc, 1);
			location.setY(location.getY()+1);
			location.getWorld().dropItem(location, iss);
			
			//remove disc from ff
			plugin.getJukeBoxManager().removeDisc(locationKey);
			
			//stop playing music for people around this box.
			/** get players in radius of the jukebox and start it for only those players **/
			for(Player p:location.getWorld().getPlayers()) {
				double distance = location.toVector().distance(p.getLocation().toVector());
				if (distance<=(double)range)
				{
					SpoutPlayer sp = SpoutManager.getPlayer(p);
					if (sp.isSpoutCraftEnabled()) {
						SpoutManager.getSoundManager().stopMusic(sp);
					}
				}
			}
			
		}
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
		return true;
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player) {
		//construct our location
		Location location = new Location(world, (double)x, (double)y, (double)z);
		String locationKey = plugin.getJukeBoxManager().createLocationKey(location);
		
		//player.sendMessage("loc::: " + world.toString() + " -- " + String.valueOf(x) + " -- " + String.valueOf(y) + " -- " + String.valueOf(z));
		//aka left clicked - play disc
		if (plugin.getJukeBoxManager().hasDisc(locationKey))
		{
			int discId = plugin.getJukeBoxManager().getDisc(locationKey);
			if (plugin.getDiscsManager().hasDiscId(discId))
			{
				String url = plugin.getDiscsManager().getUrl(discId);
				/** get players in radius of the jukebox and start it for only those players **/
				for(Player p:location.getWorld().getPlayers())
				{
					double distance = location.toVector().distance(p.getLocation().toVector());
					if (distance<=(double)range)
					{
						SpoutPlayer sp = SpoutManager.getPlayer(p);
						if (sp.isSpoutCraftEnabled()) {
							SpoutManager.getSoundManager().playCustomMusic(plugin, sp, url, true, location, range);
						}
					}
				}
				return;
			}
		}
		
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
		return;
	}
	
	@Override
	public void onBlockDestroyed(World world, int x, int y, int z) {
		//when the block is destroyed if theres a disc in it the disc should be freed from its jail.
		Location location = new Location(world, (double)x, (double)y, (double)z);
		String locationKey = plugin.getJukeBoxManager().createLocationKey(location);
		if (plugin.getJukeBoxManager().hasDisc(locationKey))
		{
			int discId = plugin.getJukeBoxManager().getDisc(locationKey);
			
			ItemBurnedObsidyisc disc = new ItemBurnedObsidyisc(plugin, plugin.getDiscsManager().getKey(discId), plugin.getDiscsManager().getTitle(discId));
			ItemStack iss = SpoutManager.getMaterialManager().getCustomItemStack(disc, 1);
			location.getWorld().dropItem(location, iss);
			
			//stop playing music for people around this box.
			/** get players in radius of the jukebox and start it for only those players **/
			for(Player p:location.getWorld().getPlayers()) {
				double distance = location.toVector().distance(p.getLocation().toVector());
				if (distance<=(double)range)
				{
					SpoutPlayer sp = SpoutManager.getPlayer(p);
					if (sp.isSpoutCraftEnabled()) {
						SpoutManager.getSoundManager().stopMusic(sp);
					}
				}
			}
		}
		
		//remove disc from ff
		plugin.getJukeBoxManager().removeDisc(locationKey);
	}
	
	@Override
	public boolean canPlaceBlockAt(World arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		//We set this to true so the block can be placed anywhere
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World arg0, int arg1, int arg2, int arg3,
			BlockFace arg4) {
		// TODO Auto-generated method stub
		//We set this to true so the block can be placed anywhere
		return true;
	}

	@Override
	public boolean isIndirectlyProdivingPowerTo(World arg0, int arg1, int arg2, int arg3, BlockFace arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProvidingPowerTo(World arg0, int arg1, int arg2, int arg3, BlockFace arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onBlockPlace(World arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockPlace(World arg0, int arg1, int arg2, int arg3, LivingEntity arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityMoveAt(World arg0, int arg1, int arg2, int arg3, Entity arg4) {
		// TODO Auto-generated method stub
		//plugin.log.info("on Entity Mova At");
		
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int changedId) {
		Location location = new Location(world, (double)x, (double)y, (double)z);
		
		if (location.getBlock().isBlockPowered())
		{
			
			if (this.getCustomMetaData() == 0)
			{
				//block is currently not powered. //set to powered
				this.setCustomMetaData(1);
				
				//if the jukebox has a disc in it, go ahead and play it.
				String locationKey = plugin.getJukeBoxManager().createLocationKey(location);
				if (plugin.getJukeBoxManager().hasDisc(locationKey))
				{
					//play disc.
					int discId = plugin.getJukeBoxManager().getDisc(locationKey);
					if (plugin.getDiscsManager().hasDiscId(discId))
					{
						String url = plugin.getDiscsManager().getUrl(discId);
						/** get players in radius of the jukebox and start it for only those players **/
						for(Player p:location.getWorld().getPlayers())
						{
							double distance = location.toVector().distance(p.getLocation().toVector());
							if (distance<=(double)range)
							{
								SpoutPlayer sp = SpoutManager.getPlayer(p);
								if (sp.isSpoutCraftEnabled()) {
									SpoutManager.getSoundManager().playCustomMusic(plugin, sp, url, true, location, range);
								}
							}
						}
						return;
					}
				}
			} else {
				//block is currently powered. do nothing.
				//set power setting back to unpowered
				this.setCustomMetaData(0);
			}
		}
	}


}
