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
package com.chrischurchwell.jukeit.material.items.needles;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.material.blocks.designs.RPNeedle;
import com.chrischurchwell.jukeit.permission.CraftPermissible;
import com.chrischurchwell.jukeit.texture.TextureFile;


public class WoodFlintNeedle extends GenericCustomItem implements Needle, CraftPermissible {

	public WoodFlintNeedle() {
		super(JukeIt.getInstance(), "Simple Needle");
		setTexture(TextureFile.NEEDLE_STICK_FLINT.getFile());
	}

	@Override
	public RPNeedle getNeedleType() {
		return RPNeedle.WOOD_FLINT;
	}
	
	@Override
	public String getCraftPermission() {
		return "jukeit.craft.needle";
	}
}
