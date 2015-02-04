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
package com.chrischurchwell.jukeit.gui.recordplayer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import com.chrischurchwell.jukeit.material.items.BurnedDisc;
import com.chrischurchwell.jukeit.sound.Sound;
import com.chrischurchwell.jukeit.sound.SoundEffect;
import com.chrischurchwell.jukeit.util.Debug;


public class RecordSlot extends GenericSlot {
	
	private RecordPlayerGUI parent;
	
	public RecordSlot(RecordPlayerGUI parent) {
		this.parent = parent;
	}
	
	public boolean onItemPut(org.bukkit.inventory.ItemStack item) {
		if (!isBurnedDisc(item)) {
			return false;
		}
		if (parent.needleSlot.getItem()== null || parent.needleSlot.getItem().getType().equals(Material.AIR)) {
			new Sound(SoundEffect.RECORD_PLAYER_LOAD, parent.block, 8).play();
		} else {
			new Sound(SoundEffect.RECORD_PLAYER_START, parent.block, 8).play();
		}
		
		parent.saveRecord(item);
		return true;
	}
	
	public boolean onItemExchange(org.bukkit.inventory.ItemStack current, org.bukkit.inventory.ItemStack cursor) {
		if (!isBurnedDisc(cursor)) {
			return false;
		}
		
		if (parent.needleSlot.getItem()== null || parent.needleSlot.getItem().getType().equals(Material.AIR)) {
			new Sound(SoundEffect.RECORD_PLAYER_LOAD, parent.block, 8).play();
		} else {
			new Sound(SoundEffect.RECORD_PLAYER_START, parent.block, 8).play();
		}
		
		parent.saveRecord(cursor);
		return true;
	}
	
	public boolean onItemTake(org.bukkit.inventory.ItemStack item) {
		if (parent.needleSlot.getItem()== null || parent.needleSlot.getItem().getType().equals(Material.AIR)) {
			Debug.debug("No Needle");
			new Sound(SoundEffect.RECORD_PLAYER_EJECT, parent.block, 8).play();
		} else {
			new Sound(SoundEffect.RECORD_PLAYER_STOP, parent.block, 8).play();
		}
		
		parent.saveRecord(null);
		return true;
	}
	
	private boolean isBurnedDisc(ItemStack item) {
		SpoutItemStack sItem = new SpoutItemStack(item);
		
		if (!sItem.isCustomItem()) {
			return false;
		}
		
		if (sItem.getMaterial() instanceof BurnedDisc) {
			return true;
		}
		return false;
	}
	
}
