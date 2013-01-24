/**
 * This file is part of JukeIt
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
package com.chrischurchwell.jukeit.material.blocks.designs;

import org.getspout.spoutapi.material.CustomItem;

import com.chrischurchwell.jukeit.material.Items;


public enum RPNeedle {
	NONE		(0,		0, null),
	WOOD_FLINT	(32,	0, Items.woodflintNeedle);
	
	private final int textureId;
	private final double rangeModifier;
	private final CustomItem needleItemRef;
	
	RPNeedle(int textureId, double rangeModifier, CustomItem needleItemRef) {
		this.textureId = textureId;
		this.needleItemRef = needleItemRef;
		this.rangeModifier = rangeModifier;
	}
	
	public int textureId() {
		return this.textureId;
	}
	
	public int id() {
		return this.ordinal();
	}
	
	public double rangeModifier() {
		return this.rangeModifier;
	}
	
	public CustomItem getItem() {
		return this.needleItemRef;
	}
	
	public static RPNeedle getById(int id) {
		for (RPNeedle d : RPNeedle.values()) {
			if (d.id() == id) return d;
		}
		
		//throw exception instead?
		return null;
	}
}
