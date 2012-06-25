package cc.thedudeguy.jukebukkit.events;

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
