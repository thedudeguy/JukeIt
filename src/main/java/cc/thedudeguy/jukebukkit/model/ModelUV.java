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
package cc.thedudeguy.jukebukkit.model;

public class ModelUV {

	private float u;
	private float v;
	
	public ModelUV(float u, float v) {
		this.setU(u);
		this.setV(v);
	}

	public float getU() {
		return u;
	}

	public void setU(float u) {
		this.u = u;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}
	
	public int getTx(int textureWidth) {
		return Math.round(u * (float)textureWidth);
	}
	
	public int getTy(int textureHeight) {
		return Math.round(v * (float)textureHeight);
	}
}
