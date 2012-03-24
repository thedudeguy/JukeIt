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

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.Block;

import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxBlock;

/**
 * Player Listener. Handles writing of labels and opening the label maker gui.
 * @author Chris Churchwell
 *
 */
public class JukeBukkitPlayerListener implements Listener {
	
	public static JukeBukkit plugin; 
	
	public JukeBukkitPlayerListener(JukeBukkit instance) {
        plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	

	@EventHandler
	public void onBlockPlaced(BlockPlaceEvent event) {
		event.setBuild(true); //MEW!

		final Player ply = event.getPlayer();
		final Block block = ((SpoutBlock)event.getBlock()).getCustomBlock();
		if(!(block instanceof JukeboxBlock)) return;
		final JukeboxBlock jukeboxBlock = (JukeboxBlock)block;
		String permission = jukeboxBlock.getPermission();
		if(permission == null) return;
		if(!ply.hasPermission(permission)) {
			event.setBuild(false);
			event.setCancelled(true);
		}
	}
	
	/*
	@EventHandler
	public void onPlayerCraft(CraftItemEvent event) {
		final Player ply = event.getPlayer();
                final ItemStack st = event.getResult();
                if (st==null) return;
		final org.getspout.spoutapi.material.Material block = new SpoutItemStack(st).getMaterial();
		if(!(block instanceof JukeboxBlock)) return;
		final JukeboxBlock jukeboxBlock = (JukeboxBlock)block;
		String permission = jukeboxBlock.getPermission();
		if(permission == null) return;
		if(!ply.hasPermission(permission)) {
			event.setResult(null);
			event.setCancelled(true);
		}
	}
	*/
	

	
}
