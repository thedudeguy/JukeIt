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

import org.getspout.spoutapi.block.design.Quad;
import org.getspout.spoutapi.block.design.SubTexture;

public class RotatableQuad extends Quad {
	
	private int rotationX = 0;
	private int rotationY = 0;
	private int rotationZ = 0;
	private float moveX = 0;
	private float moveY = 0;
	private float moveZ = 0;
	
	public RotatableQuad(int index, SubTexture texture) {
		super(index, texture);
	}
	
	/**
	 * Rotate the quad from the very center of the block on the X Axis. Make sure to call this BEFORE addVertex functions
	 * @param degrees
	 */
	public RotatableQuad rotateX(int degrees) {
		rotationX = degrees;
		return this;
	}
	/**
	 * Rotate the quad from the very center of the block on the Y Axis. Make sure to call this BEFORE addVertex functions
	 * @param degrees
	 */
	public RotatableQuad rotateY(int degrees) {
		rotationY = degrees;
		return this;
	}
	/**
	 * Rotate the quad from the very center of the block on the Z Axis. Make sure to call this BEFORE addVertex functions
	 * @param degrees
	 */
	public RotatableQuad rotateZ(int degrees) {
		rotationZ = degrees;
		return this;
	}
	
	public RotatableQuad moveX(float amount) {
		moveX = amount;
		return this;
	}
	public RotatableQuad moveY(float amount) {
		moveY = amount;
		return this;
	}
	public RotatableQuad moveZ(float amount) {
		moveZ = amount;
		return this;
	}
	
	@Override
	public Quad addVertex(int index, float x, float y, float z) {
		
		//rotate on x axis
		float x0 = x;
		float y0 = (float)( ((z - 0.5F) * Math.sin(Math.toRadians(rotationX))) + ((y - 0.5F) * Math.cos(Math.toRadians(rotationX))) ) + 0.5F;
		float z0 = (float)( ((z - 0.5F) * Math.cos(Math.toRadians(rotationX))) - ((y - 0.5F) * Math.sin(Math.toRadians(rotationX))) ) + 0.5F;
		
		//rotate on y axis
		float x1 = (float)( ((x0 - 0.5F) * Math.cos(Math.toRadians(rotationY))) - ((z0 - 0.5F) * Math.sin(Math.toRadians(rotationY))) ) + 0.5F;
		float y1 = y0;
		float z1 = (float)( ((x0 - 0.5F) * Math.sin(Math.toRadians(rotationY))) + ((z0 - 0.5F) * Math.cos(Math.toRadians(rotationY))) ) + 0.5F;
		
		//rotate on z axis
		float x2 = (float)( ((x1 - 0.5F) * Math.cos(Math.toRadians(rotationZ))) - ((y1 - 0.5F) * Math.sin(Math.toRadians(rotationZ))) ) + 0.5F;
		float y2 = (float)( ((x1 - 0.5F) * Math.sin(Math.toRadians(rotationZ))) + ((y1 - 0.5F) * Math.cos(Math.toRadians(rotationZ))) ) + 0.5F;
		float z2 = z1;
		
		//move on x axis
		float x3 = x2 + moveX;
		
		//move on y axis
		float y3 = y2 + moveY;
		
		//move on z axis
		float z3 = z2 + moveZ;
		
		return super.addVertex(index, x3, y3, z3);
	}
}
