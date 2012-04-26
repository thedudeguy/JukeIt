package cc.thedudeguy.jukebukkit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.block.SpoutBlock;

/**
 * A custom event for when SpeakerWire is placed into the world
 * @author Chris
 *
 */
public class SpeakerWirePlaceEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	private SpoutBlock block;
	private Player player;
	
	/**
	 * @param player the player who placed the speakerwire
	 * @param block where the player is attempting to place the wire
	 */
	public SpeakerWirePlaceEvent(Player player, SpoutBlock block) {
		this.block = block;
		this.player = player;
	}
	
	/**
	 * Gets the block (speakerwire block) that was placed.
	 * @return
	 */
	public SpoutBlock getBlock() {
		return block;
	}
	
	/**
	 * Gets the player who placed the block
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
}
