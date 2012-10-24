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
package com.chrischurchwell.jukeit.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;

public abstract class MachineEvent extends Event {

	private SpoutBlock block;
	private ItemStack primaryItem;
	private ItemStack additionItem;
	private String label;
	
	public MachineEvent(SpoutBlock block, ItemStack primaryItem, ItemStack addItem) {
		this(block, primaryItem, addItem, null);
	}
	
	public MachineEvent(SpoutBlock block, ItemStack primaryItem, ItemStack addItem, String label) {
		this.block = block;
		this.primaryItem = primaryItem;
		this.additionItem = addItem;
		this.label = label;
	}
	
	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public SpoutBlock getBlock() {
		return block;
	}
	
	public ItemStack getPrimaryItem() {
		return this.primaryItem;
	}

	public void setPrimaryItem(ItemStack item) {
		this.primaryItem = item;
	}
	
	public ItemStack getAdditionItem() {
		return this.additionItem;
	}

	public void setAdditionItem(ItemStack item) {
		this.additionItem = item;
	}

	public void setBlock(SpoutBlock block) {
		this.block = block;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public boolean hasLabel() {
		if (this.label == null) return false;
		return true;
	}
}
