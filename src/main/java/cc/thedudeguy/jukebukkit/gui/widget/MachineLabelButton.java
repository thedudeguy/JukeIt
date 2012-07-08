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
package cc.thedudeguy.jukebukkit.gui.widget;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.events.MachineStartEvent;

public class MachineLabelButton extends MachineStartButton {

	GenericTextField labelInput;
	
	public MachineLabelButton(SpoutPlayer player, SpoutBlock block, GenericSlot primary, GenericSlot secondary, GenericTextField labelInput) {
		super("Label Disc!", player, block, primary, secondary);
		this.labelInput = labelInput;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().getMainScreen().getActivePopup().close();
		
		MachineStartEvent startEvent = new MachineStartEvent(block, primarySlot.getItem(), additiveSlot.getItem(), labelInput.getText() );
		Bukkit.getServer().getPluginManager().callEvent(startEvent);
		
		
	}

}
