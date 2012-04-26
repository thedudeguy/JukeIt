package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.block.design.SubTexture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;

public class SpeakerWireStraightDesign extends GenericBlockDesign {

	public SpeakerWireStraightDesign(int degrees) {

		setTexture(JukeBukkit.instance, Blocks.speakerwireTexture);
		setMinBrightness(0F);
		setMaxBrightness(1F);

		SubTexture wireInside = getTexture().getSubTexture(0);
		SubTexture wireOutside = getTexture().getSubTexture(1);

		/* apparently setBoundingBox doesn't work with SpoutPlugin ??? */
		setBoundingBox(0F, 0F, 0F, 1F, 0.0625F, 1F);

		setQuadNumber(6);

		//main wire
		///////////////////////////////////////

		RotatableQuad wireFront = new RotatableQuad(0, wireOutside, degrees);
		wireFront.addVertex(0, 1F, 0.0625F, 0.5313F);
		wireFront.addVertex(1, 0F, 0.0625F, 0.5313F);
		wireFront.addVertex(2, 0F, 0F, 0.5313F);
		wireFront.addVertex(3, 1F, 0F, 0.5313F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(wireFront);

		RotatableQuad wireBottom = new RotatableQuad(1, wireOutside, degrees);
		wireBottom.addVertex(0, 1F, 0F, 0.5313F);
		wireBottom.addVertex(1, 0F, 0F, 0.5313F);
		wireBottom.addVertex(2, 0F, 0F, 0.5313F-0.0625F);
		wireBottom.addVertex(3, 1F, 0F, 0.5313F-0.0625F);
		setLightSource(1, 0, 1, 0);
		this.setQuad(wireBottom);

		RotatableQuad wireRear = new RotatableQuad(2, wireOutside, degrees);
		wireRear.addVertex(0, 1F, 0F, 0.5313F-0.0625F);
		wireRear.addVertex(1, 0F, 0F, 0.5313F-0.0625F);
		wireRear.addVertex(2, 0F, 0.0625F, 0.5313F-0.0625F);
		wireRear.addVertex(3, 1F, 0.0625F, 0.5313F-0.0625F);
		setLightSource(2, 0, 1, 0);
		this.setQuad(wireRear);

		RotatableQuad wireTop = new RotatableQuad(3, wireOutside, degrees);
		wireTop.addVertex(0, 1F, 0.0625F, 0.5313F-0.0625F);
		wireTop.addVertex(1, 0F, 0.0625F, 0.5313F-0.0625F);
		wireTop.addVertex(2, 0F, 0.0625F, 0.5313F);
		wireTop.addVertex(3, 1F, 0.0625F, 0.5313F);
		setLightSource(3, 0, 1, 0);
		this.setQuad(wireTop);

		RotatableQuad wireRight = new RotatableQuad(4, wireInside, degrees);
		wireRight.addVertex(0, 1F, 0.0625F, 0.5313F);
		wireRight.addVertex(1, 1F, 0F, 0.5313F);
		wireRight.addVertex(2, 1F, 0F, 0.5313F-0.0625F);
		wireRight.addVertex(3, 1F, 0.0625F, 0.5313F-0.0625F);
		setLightSource(4, 0, 1, 0);
		this.setQuad(wireRight);

		RotatableQuad wireLeft = new RotatableQuad(5, wireInside, degrees);
		wireLeft.addVertex(0, 0F, 0.0625F, 0.5313F);
		wireLeft.addVertex(1, 0F, 0.0625F, 0.5313F-0.0625F);
		wireLeft.addVertex(2, 0F, 0F, 0.5313F-0.0625F);
		wireLeft.addVertex(3, 0F, 0F, 0.5313F);
		setLightSource(5, 0, 1, 0);
		this.setQuad(wireLeft);
		
		
		/*
		//right nub
		///////////////////////////////////////

		RotatableQuad rightNubFront = new RotatableQuad(6, wireInside, degrees);
		rightNubFront.addVertex(0, 0.9062F+0.0312F, 0.0525F, 0.5213F);
		rightNubFront.addVertex(1, 0.9062F, 0.0525F, 0.5213F);
		rightNubFront.addVertex(2, 0.9062F, 0.0525F-0.0425F, 0.5213F);
		rightNubFront.addVertex(3, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F);
		setLightSource(6, 0, 1, 0);
		this.setQuad(rightNubFront);

		RotatableQuad rightNubBottom = new RotatableQuad(7, wireInside, degrees);
		rightNubBottom.addVertex(0, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F);
		rightNubBottom.addVertex(1, 0.9062F, 0.0525F-0.0425F, 0.5213F);
		rightNubBottom.addVertex(2, 0.9062F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		rightNubBottom.addVertex(3, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		setLightSource(7, 0, 1, 0);
		this.setQuad(rightNubBottom);

		RotatableQuad rightNubRear = new RotatableQuad(8, wireInside, degrees);
		rightNubRear.addVertex(0, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		rightNubRear.addVertex(1, 0.9062F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		rightNubRear.addVertex(2, 0.9062F, 0.0525F, 0.5213F-0.0425F);
		rightNubRear.addVertex(3, 0.9062F+0.0312F, 0.0525F, 0.5213F-0.0425F);
		setLightSource(8, 0, 1, 0);
		this.setQuad(rightNubRear);

		RotatableQuad rightNubTop = new RotatableQuad(9, wireInside, degrees);
		rightNubTop.addVertex(0, 0.9062F+0.0312F, 0.0525F, 0.5213F-0.0425F);
		rightNubTop.addVertex(1, 0.9062F, 0.0525F, 0.5213F-0.0425F);
		rightNubTop.addVertex(2, 0.9062F, 0.0525F, 0.5213F);
		rightNubTop.addVertex(3, 0.9062F+0.0312F, 0.0525F, 0.5213F);
		setLightSource(9, 0, 1, 0);
		this.setQuad(rightNubTop);

		RotatableQuad rightNubSide = new RotatableQuad(10, wireInside, degrees);
		rightNubSide.addVertex(0, 0.9062F+0.0312F, 0.0525F, 0.5213F);
		rightNubSide.addVertex(1, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F);
		rightNubSide.addVertex(2, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		rightNubSide.addVertex(3, 0.9062F+0.0312F, 0.0525F, 0.5213F-0.0425F);
		setLightSource(10, 0, 1, 0);
		this.setQuad(rightNubSide);

		//left nub
		///////////////////////////////////////

		RotatableQuad leftNubFront = new RotatableQuad(11, wireInside, degrees);
		leftNubFront.addVertex(0, 0.0937F, 0.0525F, 0.5213F);
		leftNubFront.addVertex(1, 0.0937F-0.0312F, 0.0525F, 0.5213F);
		leftNubFront.addVertex(2, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F);
		leftNubFront.addVertex(3, 0.0937F, 0.0525F-0.0425F, 0.5213F);
		setLightSource(11, 0, 1, 0);
		this.setQuad(leftNubFront);

		RotatableQuad leftNubBottom = new RotatableQuad(12, wireInside, degrees);
		leftNubBottom.addVertex(0, 0.0937F, 0.0525F-0.0425F, 0.5213F);
		leftNubBottom.addVertex(1, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F);
		leftNubBottom.addVertex(2, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		leftNubBottom.addVertex(3, 0.0937F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		setLightSource(12, 0, 1, 0);
		this.setQuad(leftNubBottom);

		RotatableQuad leftNubRear = new RotatableQuad(13, wireInside, degrees);
		leftNubRear.addVertex(0, 0.0937F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		leftNubRear.addVertex(1, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		leftNubRear.addVertex(2, 0.0937F-0.0312F, 0.0525F, 0.5213F-0.0425F);
		leftNubRear.addVertex(3, 0.0937F, 0.0525F, 0.5213F-0.0425F);
		setLightSource(13, 0, 1, 0);
		this.setQuad(leftNubRear);

		RotatableQuad leftNubTop = new RotatableQuad(14, wireInside, degrees);
		leftNubTop.addVertex(0, 0.0937F, 0.0525F, 0.5213F-0.0425F);
		leftNubTop.addVertex(1, 0.0937F-0.0312F, 0.0525F, 0.5213F-0.0425F);
		leftNubTop.addVertex(2, 0.0937F-0.0312F, 0.0525F, 0.5213F);
		leftNubTop.addVertex(3, 0.0937F, 0.0525F, 0.5213F);
		setLightSource(14, 0, 1, 0);
		this.setQuad(leftNubTop);

		RotatableQuad leftNubSide = new RotatableQuad(15, wireInside, degrees);
		leftNubSide.addVertex(0, 0.0937F-0.0312F, 0.0525F, 0.5213F);
		leftNubSide.addVertex(1, 0.0937F-0.0312F, 0.0525F, 0.5213F-0.0425F);
		leftNubSide.addVertex(2, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		leftNubSide.addVertex(3, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F);
		setLightSource(15, 0, 1, 0);
		this.setQuad(leftNubSide);
		*/
	}

}