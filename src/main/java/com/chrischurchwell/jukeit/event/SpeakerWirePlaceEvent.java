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
package com.chrischurchwell.jukeit.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.block.SpoutBlock;

/**
 * A custom event for when SpeakerWire is placed into the world
 * @author Chris Churchwell
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
