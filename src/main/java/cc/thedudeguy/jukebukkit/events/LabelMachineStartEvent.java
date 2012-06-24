package cc.thedudeguy.jukebukkit.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.block.SpoutBlock;

public class LabelMachineStartEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	
	private SpoutBlock block;
	
	public LabelMachineStartEvent(SpoutBlock block) {
		this.block = block;
	}
	
	public SpoutBlock getBlock() {
		return block;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
