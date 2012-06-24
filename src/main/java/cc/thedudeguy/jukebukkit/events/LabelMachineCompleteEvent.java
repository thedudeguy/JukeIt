package cc.thedudeguy.jukebukkit.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.block.SpoutBlock;

public class LabelMachineCompleteEvent extends Event {

	private SpoutBlock block;
	
	public LabelMachineCompleteEvent(SpoutBlock block) {
		this.block = block;
	}
	
	public SpoutBlock getBlock() {
		return block;
	}
	
	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
