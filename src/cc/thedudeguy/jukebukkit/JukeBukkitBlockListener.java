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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class JukeBukkitBlockListener extends BlockListener {
	
	public static JukeBukkit plugin; 
	
	public JukeBukkitBlockListener(JukeBukkit instance) {
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
				stopMusic();
				return;
			}
		}
	}
}
