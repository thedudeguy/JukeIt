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
package cc.thedudeguy.jukebukkit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.util.Debug;

public class GeneralListener implements Listener {
	
	@EventHandler
	public void onCraftItem(CraftItemEvent event) {
		Debug.debug("CraftItemEvent");
		SpoutItemStack craftedStack = new SpoutItemStack(event.getRecipe().getResult());
		
		if (craftedStack.getMaterial() != null && craftedStack.getMaterial() instanceof CraftPermissible) {
			Debug.debug("crafted item has permission attached");
			
			String permission = ((CraftPermissible)craftedStack.getMaterial()).getCraftPermission();
			
			if (!event.getWhoClicked().hasPermission(permission)) {
				event.setResult(Result.DENY);
				((Player)event.getWhoClicked()).sendMessage("You do not have permission to craft this item.");
				((Player)event.getWhoClicked()).sendMessage("("+permission+")");
				event.setCancelled(true);
				return;
			}
			
		}
	}
}
