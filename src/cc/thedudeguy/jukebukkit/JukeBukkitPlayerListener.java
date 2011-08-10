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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class JukeBukkitPlayerListener extends PlayerListener {
	
	public static JukeBukkit plugin; 
	
	public JukeBukkitPlayerListener(JukeBukkit instance) {
        plugin = instance;
	}
	
	private void stopMusic()
	{
		//since this plugin is marked as depend sprout, no need to check if sprout is loaded...
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            SpoutPlayer sp = SpoutManager.getPlayer(p);
            if (sp.isSpoutCraftEnabled() == true)
            {
            	plugin.sm.stopMusic(sp);
            }
		}
	}
	
	private void playDisc(short discId, Location location) throws UnsupportedOperationException
	{
		Disc disc = plugin.discs.get(discId);
		playDisc(disc, location);
	}
	
	private void playDisc(Disc disc, Location location) throws UnsupportedOperationException
	{
		String url = disc.getUrl();
		//plugin.sm.playGlobalCustomSoundEffect(plugin, url, true, block.getLocation());
		plugin.sm.playGlobalCustomMusic(plugin, url, true, location);
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = event.getClickedBlock();
			if(block.getType() == Material.JUKEBOX) {
				
				//check if the jukebox has an original disc loaded.
				if (block.getData() != 0x0)
				{
					//jukebox has an original minecraft disc in it. run the default actiom
					return;
				}
				
				//check if the jukebox has a disk in it already...
				String locationString = block.getLocation().toString();
				if (plugin.jukeboxes.containsKey(locationString))
				{
					//eject the disc...
					ItemStack newDisc = new ItemStack(Material.GOLD_RECORD, 1);
					newDisc.setDurability(plugin.jukeboxes.get(locationString));
					Location spawnLoc = block.getLocation();
					spawnLoc.setY(spawnLoc.getY()+1);
					block.getWorld().dropItem(spawnLoc, newDisc);
					//remove the reference
					plugin.jukeboxes.remove(locationString);
					
					//stop playing the music...
					stopMusic();
					
					event.setCancelled(true);
					return;
				}
				
				Player player = event.getPlayer();
				ItemStack inHand = player.getItemInHand();
				if (inHand.getType() == Material.GOLD_RECORD) {
					short discId = inHand.getDurability();
					
					if (discId < 1)
					{
						//original minecraft disc is in hand, run the default action
						return;
					}
				
					//otherwise, put the disc into the jukebox...
					plugin.jukeboxes.put(locationString, discId);
					player.setItemInHand(null);
					
					
					if (!plugin.discs.containsKey(discId))
					{
						player.sendMessage("This disc seems to be broken. Maybe it's too scratched up?");
					} else {
						try {
							playDisc(discId, block.getLocation());
						} catch (Exception e) {
							plugin.log.severe("[JukeBukkit] " + e.getMessage());
							player.sendMessage(e.getMessage());
						}
						
					}
					
					event.setCancelled(true);
					return;
					
					
				}
			}
		} else if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			Block block = event.getClickedBlock();
			if(block.getType() == Material.JUKEBOX) {
				Player player = event.getPlayer();
				//check if the jukebox has an original disc loaded.
				if (block.getData() != 0x0)
				{
					//jukebox has an original minecraft disc in it. run the default actiom
					player.sendMessage("This Jukebox has an original Music Disc (Blank Disc) in it!");
					return;
				}
				
				//check if the jukebox has a disk in it already...
				String locationString = block.getLocation().toString();
				if (plugin.jukeboxes.containsKey(locationString))
				{
					//replay the song...
					short discId = plugin.jukeboxes.get(locationString);
					if (!plugin.discs.containsKey(discId))
					{
						player.sendMessage("This disc seems to be broken. Maybe it's too scratched up?");
					} else {
						try {
							playDisc(discId, block.getLocation());
						} catch (Exception e) {
							plugin.log.severe("[JukeBukkit] " + e.getMessage());
							player.sendMessage(e.getMessage());
						}
					}
				}
			}
		}
		
	}
}
