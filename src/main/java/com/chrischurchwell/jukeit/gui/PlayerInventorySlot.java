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
package com.chrischurchwell.jukeit.gui;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.GenericSlot;

import com.chrischurchwell.jukeit.util.Debug;


public class PlayerInventorySlot extends GenericSlot {

	int index;
	Player player;
	
	/**
	 * 
	 * @param index - the index of the player inventory item slot that this slot represents
	 */
	public PlayerInventorySlot(Player player, int index) {
		this.index = index;
		this.player = player;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean onItemExchange(org.bukkit.inventory.ItemStack current, org.bukkit.inventory.ItemStack cursor) {
		
		Debug.debug("onItemExchange: current ", current, " : cursor ", cursor);
		player.getInventory().setItem(index, cursor);
		
		return true;
	}
	
	public boolean onItemPut(org.bukkit.inventory.ItemStack item) {
		Debug.debug("onItemPut: ", item);
		player.getInventory().setItem(index, item);
		return true;
	}
	
	public boolean onItemTake(org.bukkit.inventory.ItemStack item) {
		Debug.debug("onItemTake: ", item);
		player.getInventory().setItem(index, null);
		return true;
	}
}
