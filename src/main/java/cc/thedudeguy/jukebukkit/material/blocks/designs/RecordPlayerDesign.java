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

import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.block.design.Quad;
import org.getspout.spoutapi.block.design.SubTexture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

public class RecordPlayerDesign extends GenericBlockDesign {
	
	private RPDisc disc;
	private RPNeedle needle;
	private RPIndicator indicator;
	
	public RecordPlayerDesign() {
		this(RPNeedle.NONE, RPDisc.NONE, RPIndicator.RED);
	}
	public RecordPlayerDesign(RPNeedle needle, RPDisc disc, RPIndicator indicator) {
		this.disc = disc;
		this.needle = needle;
		this.indicator = indicator;
		
		setTexture(JukeBukkit.instance, TextureFile.BLOCK_RECORD_PLAYER.getTexture());
		setMinBrightness(1.0F);
		setMaxBrightness(1.0F);
		
		SubTexture textureSide = getTexture().getSubTexture(0);
		SubTexture textureTop = getTexture().getSubTexture(1);
		SubTexture edge1 = getTexture().getSubTexture(2);
		SubTexture edge2 = getTexture().getSubTexture(3);
		SubTexture insideEdge1 = getTexture().getSubTexture(4);
		SubTexture insideEdge2 = getTexture().getSubTexture(5);
		SubTexture needleBase = getTexture().getSubTexture(6);
		
		SubTexture indicatorST = getTexture().getSubTexture(indicator.textureId());
		SubTexture recordST = getTexture().getSubTexture(disc.textureId());
		SubTexture needleST = getTexture().getSubTexture(needle.textureId());
		
		setBoundingBox(0, 0, 0, 1, 1, 1);
		setQuadNumber(26);
		
		//main body
		///////////////////////////////////////
		
		Quad frontFace = new Quad(2, textureSide);
		frontFace.addVertex(0, 1F, 0F, 1F);
		frontFace.addVertex(1, 1F, 1F, 1F);
		frontFace.addVertex(2, 0F, 1F, 1F);
		frontFace.addVertex(3, 0F, 0F, 1F);
		setLightSource(2, 0, 0, 1);
		this.setQuad(frontFace);
		
		Quad rightFace = new Quad(1, textureSide);
		rightFace.addVertex(0, 1F, 0F, 0F);
		rightFace.addVertex(1, 1F, 1F, 0F);
		rightFace.addVertex(2, 1F, 1F, 1F);
		rightFace.addVertex(3, 1F, 0F, 1F);
		setLightSource(1, 1, 0, 0);
		this.setQuad(rightFace);
		
		Quad rearFace = new Quad(0, textureSide);
		rearFace.addVertex(0, 0F, 0F, 0F);
		rearFace.addVertex(1, 0F, 1F, 0F);
		rearFace.addVertex(2, 1F, 1F, 0F);
		rearFace.addVertex(3, 1F, 0F, 0F);
		setLightSource(0, 0, 0, -1);
		this.setQuad(rearFace);
		
		Quad leftFace = new Quad(3, textureSide);
		leftFace.addVertex(0, 0F, 0F, 1F);
		leftFace.addVertex(1, 0F, 1F, 1F);
		leftFace.addVertex(2, 0F, 1F, 0F);
		leftFace.addVertex(3, 0F, 0F, 0F);
		setLightSource(3, -1, 0, 0);
		this.setQuad(leftFace);
		
		Quad bottomFace = new Quad(4, textureSide);
		bottomFace.addVertex(0, 1F, 0F, 1F);
		bottomFace.addVertex(1, 0F, 0F, 1F);
		bottomFace.addVertex(2, 0F, 0F, 0F);
		bottomFace.addVertex(3, 1F, 0F, 0F);
		setLightSource(4, 0, -1, 0);
		this.setQuad(bottomFace);
		
		// Very top face, base
		////////////////////////////////////////////
		
		Quad topFace1 = new Quad(5, textureTop);
		topFace1.addVertex(0, 0F, 0.9375F, 0F);
		topFace1.addVertex(1, 0F, 0.9375F, 1F);
		topFace1.addVertex(2, 1F, 0.9375F, 1F);
		topFace1.addVertex(3, 1F, 0.9375F, 0F);
		setLightSource(5, 0, 1, 0);
		this.setQuad(topFace1);
		
		//4 slivers across the top for the padding box like effect
		///////////////////////////////////////////////////////////
		
		Quad topFaceEdge1 = new Quad(6, edge1);
		topFaceEdge1.addVertex(0, 0F, 1F, 0F);
		topFaceEdge1.addVertex(1, 0F, 1F, 1F);
		topFaceEdge1.addVertex(2, 0.0625F, 1F, 1F);
		topFaceEdge1.addVertex(3, 0.0625F, 1F, 0F);
		setLightSource(6, 0, 1, 0);
		this.setQuad(topFaceEdge1);
		
		Quad topFaceEdge2 = new Quad(7, edge1);
		topFaceEdge2.addVertex(0, 0.9375F, 1F, 0F);
		topFaceEdge2.addVertex(1, 0.9375F, 1F, 1F);
		topFaceEdge2.addVertex(2, 1F, 1F, 1F);
		topFaceEdge2.addVertex(3, 1F, 1F, 0F);
		setLightSource(7, 0, 1, 0);
		this.setQuad(topFaceEdge2);
		
		Quad topFaceEdge3 = new Quad(8, edge2);
		topFaceEdge3.addVertex(0, 0.0625F, 1F, 0.9375F);
		topFaceEdge3.addVertex(1, 0.0625F, 1F, 1F);
		topFaceEdge3.addVertex(2, 0.9375F, 1F, 1F);
		topFaceEdge3.addVertex(3, 0.9375F, 1F, 0.9375F);
		setLightSource(8, 0, 1, 0);
		this.setQuad(topFaceEdge3);
		
		Quad topFaceEdge4 = new Quad(9, edge2);
		topFaceEdge4.addVertex(0, 0.0625F, 1F, 0F);
		topFaceEdge4.addVertex(1, 0.0625F, 1F, 0.0625F);
		topFaceEdge4.addVertex(2, 0.9375F, 1F, 0.0625F);
		topFaceEdge4.addVertex(3, 0.9375F, 1F, 0F);
		setLightSource(9, 0, 1, 0);
		this.setQuad(topFaceEdge4);
		
		//inside edges
		///////////////
		
		Quad insideEdgeq1 = new Quad(10, insideEdge1);
		insideEdgeq1.addVertex(0, 0.0625F, 1F, 0.0625F);
		insideEdgeq1.addVertex(1, 0.0625F, 1F, 0.9375F);
		insideEdgeq1.addVertex(2, 0.0625F, 0.9375F, 0.9375F);
		insideEdgeq1.addVertex(3, 0.0625F, 0.0625F, 0.0625F);
		setLightSource(10, 0, 1, 0);
		this.setQuad(insideEdgeq1);
		
		Quad insideEdgeq2 = new Quad(11, insideEdge1);
		insideEdgeq2.addVertex(0, 0.9375F, 0.9375F, 0.0625F);
		insideEdgeq2.addVertex(1, 0.9375F, 0.9375F, 0.9375F);
		insideEdgeq2.addVertex(2, 0.9375F, 1F, 0.9375F);
		insideEdgeq2.addVertex(3, 0.9375F, 1F, 0.0625F);
		setLightSource(11, 0, 1, 0);
		this.setQuad(insideEdgeq2);
		
		Quad insideEdgeq3 = new Quad(12, insideEdge2);
		insideEdgeq3.addVertex(0, 0.0625F, 1F, 0.0625F);
		insideEdgeq3.addVertex(1, 0.0625F, 0.0625F, 0.0625F);
		insideEdgeq3.addVertex(2, 0.9375F, 0.9375F, 0.0625F);
		insideEdgeq3.addVertex(3, 0.9375F, 1F, 0.0625F);
		setLightSource(12, 0, 1, 0);
		this.setQuad(insideEdgeq3);
		
		Quad insideEdgeq4 = new Quad(13, insideEdge2);
		insideEdgeq4.addVertex(0, 0.9375F, 1F, 0.9375F);
		insideEdgeq4.addVertex(1, 0.9375F, 0.9375F, 0.9375F);
		insideEdgeq4.addVertex(2, 0.0625F, 0.9375F, 0.9375F);
		insideEdgeq4.addVertex(3, 0.0625F, 1F, 0.9375F);
		setLightSource(13, 0, 1, 0);
		this.setQuad(insideEdgeq4);
		
		// INDICATOR LIGHT
		
		Quad indicatorLeft = new Quad(14, indicatorST);
		indicatorLeft.addVertex(0, 0.5F, 0.9675F, 0.8125F);
		indicatorLeft.addVertex(1, 0.5F, 0.9675F-0.03F, 0.8125F);
		indicatorLeft.addVertex(2, 0.5F, 0.9675F-0.03F, 0.8125F + 0.1250F);
		indicatorLeft.addVertex(3, 0.5F, 0.9675F, 0.8125F + 0.1250F);
		setLightSource(14, 0, 1, 0);
		this.setQuad(indicatorLeft);
		
		Quad indicatorRear = new Quad(15, indicatorST);
		indicatorRear.addVertex(0, 0.5F, 0.9675F, 0.8125F);
		indicatorRear.addVertex(1, 0.5F+0.0625F, 0.9675F, 0.8125F);
		indicatorRear.addVertex(2, 0.5F+0.0625F, 0.9675F-0.03F, 0.8125F);
		indicatorRear.addVertex(3, 0.5F, 0.9675F-0.03F, 0.8125F);
		setLightSource(15, 0, 1, 0);
		this.setQuad(indicatorRear);
		
		Quad indicatorRight = new Quad(16, indicatorST);
		indicatorRight.addVertex(0, 0.5F+0.0625F, 0.9675F, 0.8125F);
		indicatorRight.addVertex(1, 0.5F+0.0625F, 0.9675F, 0.8125F+0.125F);
		indicatorRight.addVertex(2, 0.5F+0.0625F, 0.9675F-0.03F, 0.8125F+0.125F);
		indicatorRight.addVertex(3, 0.5F+0.0625F, 0.9675F-0.03F, 0.8125F);
		setLightSource(16, 0, 1, 0);
		this.setQuad(indicatorRight);
		
		Quad indicatorTop = new Quad(17, indicatorST);
		indicatorTop.addVertex(0, 0.5F, 0.9675F, 0.8125F);
		indicatorTop.addVertex(1, 0.5F, 0.9675F, 0.8125F+0.125F);
		indicatorTop.addVertex(2, 0.5F+0.0625F, 0.9675F, 0.8125F+0.125F);
		indicatorTop.addVertex(3, 0.5F+0.0625F, 0.9675F, 0.8125F);
		setLightSource(17, 0, 1, 0);
		this.setQuad(indicatorTop);
		
		// NEEDLE BASE
		
		Quad needleBaseFront = new Quad(18, needleBase);
		needleBaseFront.addVertex(0, 0.75F, 0.980F, 0.75F+0.0625F);
		needleBaseFront.addVertex(1, 0.75F, 0.998F-0.0605F, 0.75F+0.0625F);
		needleBaseFront.addVertex(2, 0.75F+0.0625F, 0.998F-0.0605F, 0.75F+0.0625F);
		needleBaseFront.addVertex(3, 0.75F+0.0625F, 0.998F, 0.75F+0.0625F);
		setLightSource(18, 0, 1, 0);
		this.setQuad(needleBaseFront);
		
		Quad needleBaseLeft = new Quad(19, needleBase);
		needleBaseLeft.addVertex(0, 0.75F, 0.980F, 0.75F);
		needleBaseLeft.addVertex(1, 0.75F, 0.998F-0.0605F, 0.75F);
		needleBaseLeft.addVertex(2, 0.75F, 0.998F-0.0605F, 0.75F+0.0625F);
		needleBaseLeft.addVertex(3, 0.75F, 0.980F, 0.75F+0.0625F);
		setLightSource(19, 0, 1, 0);
		this.setQuad(needleBaseLeft);
		
		Quad needleBaseRight = new Quad(20, needleBase);
		needleBaseRight.addVertex(0, 0.75F+0.0625F, 0.998F, 0.75F+0.0625F);
		needleBaseRight.addVertex(1, 0.75F+0.0625F, 0.998F-0.0605F, 0.75F+0.0625F);
		needleBaseRight.addVertex(2, 0.75F+0.0625F, 0.998F-0.0605F, 0.75F);
		needleBaseRight.addVertex(3, 0.75F+0.0625F, 0.980F, 0.75F);
		setLightSource(20, 0, 1, 0);
		this.setQuad(needleBaseRight);
		
		Quad needleBaseRear = new Quad(21, needleBase);
		needleBaseRear.addVertex(0, 0.75F+0.0625F, 0.980F, 0.75F);
		needleBaseRear.addVertex(1, 0.75F+0.0625F, 0.998F-0.0605F, 0.75F);
		needleBaseRear.addVertex(2, 0.75F, 0.998F-0.0605F, 0.75F);
		needleBaseRear.addVertex(3, 0.75F, 0.980F, 0.75F);
		setLightSource(21, 0, 1, 0);
		this.setQuad(needleBaseRear);
		
		Quad needleBaseTop = new Quad(22, needleBase);
		needleBaseTop.addVertex(0, 0.75F, 0.980F, 0.75F);
		needleBaseTop.addVertex(1, 0.75F, 0.980F, 0.8125F);
		needleBaseTop.addVertex(2, 0.8125F, 0.998F, 0.8125F);
		needleBaseTop.addVertex(3, 0.8125F, 0.998F, 0.8125F);
		setLightSource(22, 0, 1, 0);
		this.setQuad(needleBaseTop);
		
		Quad needleBaseTop2 = new Quad(23, needleBase);
		needleBaseTop2.addVertex(0, 0.8125F, 0.998F, 0.8125F);
		needleBaseTop2.addVertex(1, 0.8125F, 0.980F, 0.75F);
		needleBaseTop2.addVertex(2, 0.75F, 0.980F, 0.75F);
		needleBaseTop2.addVertex(3, 0.75F, 0.980F, 0.75F);
		setLightSource(23, 0, 1, 0);
		this.setQuad(needleBaseTop2);
		
		// RECORD.
		
		if (!disc.equals(RPDisc.NONE)) {
			
			Quad record = new Quad(25, recordST);
			record.addVertex(0, 1F, 0.9675F, 0F);
			record.addVertex(1, 0F, 0.9675F, 0F);
			record.addVertex(2, 0F, 0.9675F, 1F);
			record.addVertex(3, 1F, 0.9675F, 1F);
			setLightSource(25, 0, 1, 0);
			this.setQuad(record);
		}
		
		// NEEDLE
		if (!needle.equals(RPNeedle.NONE)) {
			
			if (disc.equals(RPDisc.NONE)) {
				Quad needleQuad = new Quad(24, needleST);
				needleQuad.addVertex(2, 0.8125F, 1F, 0.75F);
				needleQuad.addVertex(3, 0.3125F, 1F, 0.75F);
				needleQuad.addVertex(0, 0.3125F, 1F, 0.7812F);
				needleQuad.addVertex(1, 0.8125F, 1F, 0.8125F);
				setLightSource(24, 0, 1, 0);
				this.setQuad(needleQuad);
			
			} else {
				Quad needleQuad = new Quad(24, needleST);
				needleQuad.addVertex(0, 0.3308F, 0.9675F, 0.6505F);
				needleQuad.addVertex(1, 0.8025F, 1F, 0.8192F);
				needleQuad.addVertex(2, 0.8198F, 1F, 0.7591F);
				needleQuad.addVertex(3, 0.3395F, 0.9675F, 0.6205F);
				setLightSource(24, 0, 1, 0);
				this.setQuad(needleQuad);
				
			}	
		}
	}
	
	public RPIndicator getIndicator() {
		return indicator;
	}
	
	public RPDisc getDisc() {
		return disc;
	}
	
	public RPNeedle getNeedle() {
		return needle;
	}
	
	public String getDesignTypeId()
	{
		return String.valueOf(needle.id()) + "_" + String.valueOf(disc.id()) + "_" + String.valueOf(indicator.id());
	}
	
	public static String getDesignTypeId(RPNeedle needle, RPDisc disc, RPIndicator indicator) {
		return String.valueOf(needle.id()) + "_" + String.valueOf(disc.id()) + "_" + String.valueOf(indicator.id());
	}
}