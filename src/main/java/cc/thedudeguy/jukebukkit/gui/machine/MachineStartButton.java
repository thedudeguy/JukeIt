/**
 * This file is part of JukeBukkit
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
package cc.thedudeguy.jukebukkit.gui.machine;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.event.MachineStartEvent;

public class MachineStartButton extends GenericButton{
	
	protected SpoutBlock block;
	
	protected GenericSlot primarySlot;
	protected GenericSlot additiveSlot;
	
	private Player player;
	
	public MachineStartButton(SpoutPlayer player, SpoutBlock block, GenericSlot primary, GenericSlot secondary) {
		this("Start!", player, block, primary, secondary);
		this.player = player;
	}
	
	public MachineStartButton(String label, SpoutPlayer player, SpoutBlock block, GenericSlot primary, GenericSlot secondary) {
		super(label);
		this.block = block;
		this.primarySlot = primary;
		this.additiveSlot = secondary;
		this.player = player;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		
		if (player.getItemOnCursor() != null && !player.getItemOnCursor().getType().equals(Material.AIR) ) {
			tossItem((SpoutPlayer) player, player.getItemOnCursor());
			player.setItemOnCursor(new ItemStack(Material.AIR));
		}
		
		event.getPlayer().getMainScreen().getActivePopup().close();
		
		MachineStartEvent startEvent = new MachineStartEvent(block, primarySlot.getItem(), additiveSlot.getItem(), null);
		Bukkit.getServer().getPluginManager().callEvent(startEvent);
		
		
	}
	
	private void tossItem(SpoutPlayer player, ItemStack dropItem) {
		Location loc = player.getLocation();
        loc.setY(loc.getY() + 1);
        
        Item item = loc.getWorld().dropItem(loc, dropItem);
        Vector v = loc.getDirection().multiply(0.2);
        v.setY(0.2);
        item.setVelocity(v);
	}
}
