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
