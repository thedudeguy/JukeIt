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
package cc.thedudeguy.jukebukkit.materials.blocks.designs;

public enum RPIndicator {
	RED		(7),
	GREEN	(8);
	
	private final Integer textureId;
	
	RPIndicator(Integer textureId) {
		this.textureId = textureId;
	}
	
	public Integer textureId() {
		return this.textureId;
	}
	
	public int id() {
		return this.ordinal();
	}
	
	public static RPIndicator getById(int id) {
		for (RPIndicator d : RPIndicator.values()) {
			if (d.id() == id) return d;
		}
		
		//throw exception instead?
		return null;
	}
}
