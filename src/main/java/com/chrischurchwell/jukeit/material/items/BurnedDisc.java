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
package com.chrischurchwell.jukeit.material.items;

import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.material.DiscColor;
import com.chrischurchwell.jukeit.util.Debug;
import com.chrischurchwell.jukeit.util.DiscUtil;


public class BurnedDisc extends GenericCustomItem implements DiscColorable {
	
	private DiscColor color = DiscColor.WHITE; //white is the default color
	
	public BurnedDisc(DiscColor color) {
		super(JukeIt.getInstance(), color.toString());
		this.color = color;
		setName("Ruined Burned Disc");
		setTexture(color.burnedTexture().getFile());
	}
	
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, org.bukkit.block.BlockFace face) {
		Debug.debug(player, "Disc URL ", DiscUtil.decodeDisc(player.getItemInHand()));
		
		return true;
	}
	
	public DiscColor getColor() {
		return color;
	}
}
