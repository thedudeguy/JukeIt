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
package com.chrischurchwell.jukeit.runnable;

import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.getspout.spoutapi.block.SpoutBlock;

public abstract class MachineRunnable extends BukkitRunnable {
	
	private SpoutBlock block;
	private ItemStack primaryItem;
	private ItemStack additionItem;
	private String label;
	
	public MachineRunnable(SpoutBlock block, ItemStack primaryItem, ItemStack additionItem, String label) {
		this.block = block;
		this.primaryItem = primaryItem;
		this.additionItem = additionItem;
		this.label = label;
	}
	public MachineRunnable(SpoutBlock block) {
		this.block = block;
	}
	
	public SpoutBlock getBlock() {
		return block;
	}
	
	public ItemStack getPrimaryItem() {
		return primaryItem;
	}
	
	public ItemStack getAdditionalItem() {
		return additionItem;
	}
	
	public String getLabel() {
		return label;
	}
}
