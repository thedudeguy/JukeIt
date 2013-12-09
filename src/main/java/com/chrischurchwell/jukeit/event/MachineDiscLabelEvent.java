package com.chrischurchwell.jukeit.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class MachineDiscLabelEvent extends Event {
	
	private ItemStack disc;
	private String label;
	private boolean cancel = false;
	
	public MachineDiscLabelEvent(ItemStack disc, String label) {
		this.disc = disc;
		this.label = label;
	}
	
	public ItemStack getDisc() {
		return disc;
	}
	
	public void setDisc(ItemStack disc) {
		this.disc = disc;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
	
	public boolean isCancelled() {
		return cancel;
	}
	
	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
