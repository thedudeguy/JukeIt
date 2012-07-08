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

import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.block.design.SubTexture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.Blocks;

public class SpeakerWireTurnDesign extends GenericBlockDesign {
	
	public SpeakerWireTurnDesign(int degreesY) {
		this(0, degreesY, 0, 0, 0, 0);
	}
	
	public SpeakerWireTurnDesign(int degreesX, int degreesY, int degreesZ, float moveX, float moveY, float moveZ) {
		
		setTexture(JukeBukkit.instance, Blocks.speakerwireTexture);
		setMinBrightness(0F);
		setMaxBrightness(1F);
		
		SubTexture wireInside = getTexture().getSubTexture(0);
		SubTexture wireOutside = getTexture().getSubTexture(1);
		
		/* apparently setBoundingBox doesn't work with SpoutPlugin ??? */
		setBoundingBox(0F, 0F, 0F, 1F, 0.0625F, 1F);
		
		setQuadNumber(14);
		
		// Top 
		///////////////////////////////////////////
		
		RotatableQuad top1 = new RotatableQuad(0, wireOutside);
		top1.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		top1.addVertex(0, 0.5313F, 0.0625F, 0F);
		top1.addVertex(1, 0.4688F, 0.0625F, 0F);
		top1.addVertex(2, 0.4688F, 0.0625F, 0.3125F);
		top1.addVertex(3, 0.5313F, 0.0625F, 0.3750F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(top1);
		
		RotatableQuad top2 = new RotatableQuad(1, wireOutside);
		top2.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		top2.addVertex(0, 0.6875F, 0.0625F, 0.5312F);
		top2.addVertex(1, 0.4688F, 0.0625F, 0.3125F);
		top2.addVertex(2, 0.4688F, 0.0625F, 0.4062F);
		top2.addVertex(3, 0.5938F, 0.0625F, 0.5312F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(top2);
		
		RotatableQuad top3 = new RotatableQuad(2, wireOutside);
		top3.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		top3.addVertex(0, 1F, 0.0625F, 0.4687F);
		top3.addVertex(1, 0.6250F, 0.0625F, 0.4687F);
		top3.addVertex(2, 0.6875F, 0.0625F, 0.5312F);
		top3.addVertex(3, 1F, 0.0625F, 0.5312F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(top3);
		
		// Bottom 
		///////////////////////////////////////////
		
		RotatableQuad bottom1 = new RotatableQuad(3, wireOutside);
		bottom1.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		bottom1.addVertex(3, 0.5313F, 0.0F, 0F);
		bottom1.addVertex(2, 0.4688F, 0.0F, 0F);
		bottom1.addVertex(1, 0.4688F, 0.0F, 0.3125F);
		bottom1.addVertex(0, 0.5313F, 0.0F, 0.3750F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(bottom1);
		
		RotatableQuad bottom2 = new RotatableQuad(4, wireOutside);
		bottom2.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		bottom2.addVertex(3, 0.6875F, 0.0F, 0.5312F);
		bottom2.addVertex(2, 0.4688F, 0.0F, 0.3125F);
		bottom2.addVertex(1, 0.4688F, 0.0F, 0.4062F);
		bottom2.addVertex(0, 0.5938F, 0.0F, 0.5312F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(bottom2);
		
		RotatableQuad bottom3 = new RotatableQuad(5, wireOutside);
		bottom3.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		bottom3.addVertex(3, 1F, 0.0F, 0.4687F);
		bottom3.addVertex(2, 0.6250F, 0.0F, 0.4687F);
		bottom3.addVertex(1, 0.6875F, 0.0F, 0.5312F);
		bottom3.addVertex(0, 1F, 0.0F, 0.5312F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(bottom3);
		
		//inside
		///////////////////////////////////
		
		RotatableQuad inside1 = new RotatableQuad(6, wireOutside);
		inside1.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		inside1.addVertex(0, 0.5313F, 0.0F, 0.3750F);
		inside1.addVertex(1, 0.5313F, 0.0F, 0.0F);
		inside1.addVertex(2, 0.5313F, 0.0625F, 0.0F);
		inside1.addVertex(3, 0.5313F, 0.0625F, 0.3750F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(inside1);
		
		RotatableQuad inside2 = new RotatableQuad(7, wireOutside);
		inside2.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		inside2.addVertex(0, 0.6250F, 0.0F, 0.4687F);
		inside2.addVertex(1, 0.5313F, 0.0F, 0.3750F);
		inside2.addVertex(2, 0.5313F, 0.0625F, 0.3750F);
		inside2.addVertex(3, 0.6250F, 0.0625F, 0.4687F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(inside2);
		
		RotatableQuad inside3 = new RotatableQuad(8, wireOutside);
		inside3.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		inside3.addVertex(0, 1F, 0.0F, 0.4687F);
		inside3.addVertex(1, 0.6250F, 0.0F, 0.4687F);
		inside3.addVertex(2, 0.6250F, 0.0625F, 0.4687F);
		inside3.addVertex(3, 1F, 0.0625F, 0.4687F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(inside3);
		
		//outside
		
		RotatableQuad outside1 = new RotatableQuad(9, wireOutside);
		outside1.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		outside1.addVertex(0, 0.4688F, 0.0F, 0.0F);
		outside1.addVertex(1, 0.4688F, 0.0F, 0.4063F);
		outside1.addVertex(2, 0.4688F, 0.0625F, 0.4063F);
		outside1.addVertex(3, 0.4688F, 0.0625F, 0.0F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(outside1);
		
		RotatableQuad outside2 = new RotatableQuad(10, wireOutside);
		outside2.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		outside2.addVertex(0, 0.4688F, 0.0F, 0.4063F);
		outside2.addVertex(1, 0.5938F, 0.0F, 0.5312F);
		outside2.addVertex(2, 0.5938F, 0.0625F, 0.5312F);
		outside2.addVertex(3, 0.4688F, 0.0625F, 0.4063F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(outside2);
		
		RotatableQuad outside3 = new RotatableQuad(11, wireOutside);
		outside3.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		outside3.addVertex(0, 0.5938F, 0.0F, 0.5312F);
		outside3.addVertex(1, 1F, 0.0F, 0.5312F);
		outside3.addVertex(2, 1F, 0.0625F, 0.5312F);
		outside3.addVertex(3, 0.5938F, 0.0625F, 0.5312F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(outside3);
		
		//caps
		//////////////////////////
		
		RotatableQuad cap1 = new RotatableQuad(12, wireInside);
		cap1.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		cap1.addVertex(0, 0.5313F, 0.0F, 0.0F);
		cap1.addVertex(1, 0.4688F, 0.0F, 0.0F);
		cap1.addVertex(2, 0.4688F, 0.0625F, 0.0F);
		cap1.addVertex(3, 0.5313F, 0.0625F, 0.0F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(cap1);
		
		RotatableQuad cap2 = new RotatableQuad(13, wireInside);
		cap2.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		cap2.addVertex(0, 1F, 0.0F, 0.5312F);
		cap2.addVertex(1, 1F, 0.0F, 0.4687F);
		cap2.addVertex(2,1F, 0.0625F, 0.4687F);
		cap2.addVertex(3,1F, 0.0625F, 0.5312F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(cap2);
		
	}
	
}
