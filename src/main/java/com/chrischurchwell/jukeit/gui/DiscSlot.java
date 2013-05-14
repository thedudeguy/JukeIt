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
package com.chrischurchwell.jukeit.gui;

import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import com.chrischurchwell.jukeit.material.items.BlankDisc;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;


public class DiscSlot extends GenericSlot {

	public boolean onItemPut(org.bukkit.inventory.ItemStack item) {
		SpoutItemStack sItem = new SpoutItemStack(item);
		
		if (
				!(sItem.isCustomItem() && sItem.getMaterial() instanceof BlankDisc) &&
				!(sItem.isCustomItem() && sItem.getMaterial() instanceof BurnedDisc)
				) {
			return false;
		}
		
		return true;
	}
	
	public boolean onItemExchange(org.bukkit.inventory.ItemStack current, org.bukkit.inventory.ItemStack cursor) {
		
		SpoutItemStack sItem = new SpoutItemStack(cursor);
		if (
				!(sItem.isCustomItem() && sItem.getMaterial() instanceof BlankDisc) &&
				!(sItem.isCustomItem() && sItem.getMaterial() instanceof BurnedDisc)
				) {
			return false;
		}
		return true;
	}
	
}
