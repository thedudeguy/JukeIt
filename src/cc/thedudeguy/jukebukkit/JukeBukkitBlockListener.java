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
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.inventory.ItemStack;

public class JukeBukkitBlockListener extends BlockListener {
	
	public static JukeBukkit plugin; 
	
	public JukeBukkitBlockListener(JukeBukkit instance) {
        plugin = instance;
	}
	
	public void onBlockPhysics(BlockPhysicsEvent event)
	{
		Block block = event.getBlock();
		if (block.getType() == Material.JUKEBOX)
		{
			if (block.isBlockPowered())
			{
				
				if (block.getData() != 0x0)
				{
					//jukebox has an original minecraft disc in it. run the default actiom
					return;
				}
				
				//check if the jukebox has a disk in it already...
				String locationString = block.getLocation().toString();
				if (plugin.jukeboxes.containsKey(locationString))
				{
					//stop any music first :: this seems like it might cause more problems, commenting out for now
					//plugin.stopMusic();
					
					//replay the song...
					short discId = plugin.jukeboxes.get(locationString);
					if (!plugin.discs.containsKey(discId))
					{
						//player.sendMessage("This disc seems to be broken. Maybe it's too scratched up?");
					} else {
						try {
							plugin.playDisc(discId, block.getLocation());
						} catch (Exception e) {
							plugin.log.severe("[JukeBukkit] " + e.getMessage());
							//player.sendMessage(e.getMessage());
						}
					}
				}
				
				
			}
		}
		return;
	}
	
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		if (block.getType() == Material.JUKEBOX) 
		{
			
			//check if the jukebox has a disk in it already...
			String locationString = block.getLocation().toString();
			if (plugin.jukeboxes.containsKey(locationString))
			{
				//eject the disc...
				ItemStack newDisc = new ItemStack(Material.GOLD_RECORD, 1);
				newDisc.setDurability(plugin.jukeboxes.get(locationString));
				Location spawnLoc = block.getLocation();
				block.getWorld().dropItem(spawnLoc, newDisc);
				//remove the reference
				plugin.jukeboxes.remove(locationString);
				
				//stop playing the music...
				plugin.stopMusic(block.getLocation());
				return;
			}
			
		}
		return;
	}
}
