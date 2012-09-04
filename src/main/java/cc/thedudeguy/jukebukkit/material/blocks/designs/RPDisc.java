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
package cc.thedudeguy.jukebukkit.material.blocks.designs;

import cc.thedudeguy.jukebukkit.material.items.DiscColor;

public enum RPDisc {
	NONE		(0,		0),
	WHITE		(16,	DiscColor.WHITE),
	RED			(17,	DiscColor.RED),
	PURPLE		(18,	DiscColor.PURPLE),
	PINK		(19,	DiscColor.PINK),
	ORANGE		(20,	DiscColor.ORANGE),
	MAGENTA		(21,	DiscColor.MAGENTA),
	LIME		(22,	DiscColor.LIME),
	LIGHT_GRAY	(23,	DiscColor.LIGHTGRAY),
	LIGHT_BLUE	(24,	DiscColor.LIGHTBLUE),
	GREEN		(25,	DiscColor.GREEN),
	GRAY		(26,	DiscColor.GRAY),
	CYAN		(27,	DiscColor.CYAN),
	BROWN		(28,	DiscColor.BROWN),
	BLUE		(29,	DiscColor.BLUE),
	BLACK		(30,	DiscColor.BLACK),
	YELLOW		(31,	DiscColor.YELLOW);
	
	private final Integer textureId;
	private final int discColor;
	
	RPDisc(Integer textureId, int discColor) {
		this.textureId = textureId;
		this.discColor = discColor;
	}
	
	public Integer textureId() {
		return this.textureId;
	}
	
	public int discColor() {
		return discColor;
	}
	
	public int id() {
		return this.ordinal();
	}
	
	public static RPDisc getById(int id) {
		for (RPDisc d : RPDisc.values()) {
			if (d.id() == id) return d;
		}
		
		//throw exception instead?
		return RPDisc.NONE;
	}
	
	public static RPDisc getByColor(int discColor) {
		for (RPDisc d : RPDisc.values()) {
			if (d.discColor() == discColor) return d;
		}
		
		//throw exception instead?
		return RPDisc.NONE;
	}
}
