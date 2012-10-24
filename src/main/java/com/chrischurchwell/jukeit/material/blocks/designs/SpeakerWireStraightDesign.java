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

import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.block.design.SubTexture;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.texture.TextureFile;


public class SpeakerWireStraightDesign extends GenericBlockDesign {

	public SpeakerWireStraightDesign(int degreesY) {
		this(0, degreesY, 0, 0, 0, 0);
	}
	
	/**
	 * 
	 * @param degreesY the amount to rotate the entire design on the y axis
	 * @param degreesX the amount to rotate the entire design on the x axis
	 */
	public SpeakerWireStraightDesign(int degreesX, int degreesY, int degreesZ, float moveX, float moveY, float moveZ) {

		setTexture(JukeIt.getInstance(), TextureFile.BLOCK_SPEAKER_WIRE.getTexture());
		setMinBrightness(0F);
		setMaxBrightness(1F);

		SubTexture wireInside = getTexture().getSubTexture(0);
		SubTexture wireOutside = getTexture().getSubTexture(1);

		/* apparently setBoundingBox doesn't work with SpoutPlugin ??? */
		setBoundingBox(0F, 0F, 0F, 1F, 0.0625F, 1F);

		setQuadNumber(6);

		//main wire
		///////////////////////////////////////

		RotatableQuad wireFront = new RotatableQuad(0, wireOutside);
		wireFront.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		wireFront.addVertex(0, 1F, 0.0625F, 0.5313F);
		wireFront.addVertex(1, 0F, 0.0625F, 0.5313F);
		wireFront.addVertex(2, 0F, 0F, 0.5313F);
		wireFront.addVertex(3, 1F, 0F, 0.5313F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(wireFront);

		RotatableQuad wireBottom = new RotatableQuad(1, wireOutside);
		wireBottom.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		wireBottom.addVertex(0, 1F, 0F, 0.5313F);
		wireBottom.addVertex(1, 0F, 0F, 0.5313F);
		wireBottom.addVertex(2, 0F, 0F, 0.5313F-0.0625F);
		wireBottom.addVertex(3, 1F, 0F, 0.5313F-0.0625F);
		setLightSource(1, 0, 1, 0);
		this.setQuad(wireBottom);

		RotatableQuad wireRear = new RotatableQuad(2, wireOutside);
		wireRear.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		wireRear.addVertex(0, 1F, 0F, 0.5313F-0.0625F);
		wireRear.addVertex(1, 0F, 0F, 0.5313F-0.0625F);
		wireRear.addVertex(2, 0F, 0.0625F, 0.5313F-0.0625F);
		wireRear.addVertex(3, 1F, 0.0625F, 0.5313F-0.0625F);
		setLightSource(2, 0, 1, 0);
		this.setQuad(wireRear);

		RotatableQuad wireTop = new RotatableQuad(3, wireOutside);
		wireTop.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		wireTop.addVertex(0, 1F, 0.0625F, 0.5313F-0.0625F);
		wireTop.addVertex(1, 0F, 0.0625F, 0.5313F-0.0625F);
		wireTop.addVertex(2, 0F, 0.0625F, 0.5313F);
		wireTop.addVertex(3, 1F, 0.0625F, 0.5313F);
		setLightSource(3, 0, 1, 0);
		this.setQuad(wireTop);

		RotatableQuad wireRight = new RotatableQuad(4, wireInside);
		wireRight.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		wireRight.addVertex(0, 1F, 0.0625F, 0.5313F);
		wireRight.addVertex(1, 1F, 0F, 0.5313F);
		wireRight.addVertex(2, 1F, 0F, 0.5313F-0.0625F);
		wireRight.addVertex(3, 1F, 0.0625F, 0.5313F-0.0625F);
		setLightSource(4, 0, 1, 0);
		this.setQuad(wireRight);

		RotatableQuad wireLeft = new RotatableQuad(5, wireInside);
		wireLeft.rotateX(degreesX).rotateY(degreesY).rotateZ(degreesZ).moveX(moveX).moveY(moveY).moveZ(moveZ);
		wireLeft.addVertex(0, 0F, 0.0625F, 0.5313F);
		wireLeft.addVertex(1, 0F, 0.0625F, 0.5313F-0.0625F);
		wireLeft.addVertex(2, 0F, 0F, 0.5313F-0.0625F);
		wireLeft.addVertex(3, 0F, 0F, 0.5313F);
		setLightSource(5, 0, 1, 0);
		this.setQuad(wireLeft);
		
	}

}