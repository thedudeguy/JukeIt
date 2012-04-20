package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.block.design.Quad;
import org.getspout.spoutapi.block.design.SubTexture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;

public class SpeakerWireDesign extends GenericBlockDesign {
	
	public SpeakerWireDesign() {
		
		setTexture(JukeBukkit.instance, Blocks.speakerwireTexture);
		setMinBrightness(0F);
		setMaxBrightness(1F);
		
		SubTexture wireInside = getTexture().getSubTexture(0);
		SubTexture wireOutside = getTexture().getSubTexture(1);
		
		/* apparently setBoundingBox doesn't work with SpoutPlugin ??? */
		setBoundingBox(0F, 0F, 0F, 1F, 0.0625F, 1F);
		
		setQuadNumber(17);
		
		//main wire
		///////////////////////////////////////
		
		Quad wireFront = new Quad(0, wireOutside);
		wireFront.addVertex(0, 0.9062F, 0.0625F, 0.5313F);
		wireFront.addVertex(1, 0.9062F-0.8125F, 0.0625F, 0.5313F);
		wireFront.addVertex(2, 0.9062F-0.8125F, 0F, 0.5313F);
		wireFront.addVertex(3, 0.9062F, 0F, 0.5313F);
		setLightSource(0, 0, 1, 0);
		this.setQuad(wireFront);
		
		Quad wireBottom = new Quad(1, wireOutside);
		wireBottom.addVertex(0, 0.9062F, 0F, 0.5313F);
		wireBottom.addVertex(1, 0.9062F-0.8125F, 0F, 0.5313F);
		wireBottom.addVertex(2, 0.9062F-0.8125F, 0F, 0.5313F-0.0625F);
		wireBottom.addVertex(3, 0.9062F, 0F, 0.5313F-0.0625F);
		setLightSource(1, 0, 1, 0);
		this.setQuad(wireBottom);
		
		Quad wireRear = new Quad(2, wireOutside);
		wireRear.addVertex(0, 0.9062F, 0F, 0.5313F-0.0625F);
		wireRear.addVertex(1, 0.9062F-0.8125F, 0F, 0.5313F-0.0625F);
		wireRear.addVertex(2, 0.9062F-0.8125F, 0.0625F, 0.5313F-0.0625F);
		wireRear.addVertex(3, 0.9062F, 0.0625F, 0.5313F-0.0625F);
		setLightSource(2, 0, 1, 0);
		this.setQuad(wireRear);
		
		Quad wireTop = new Quad(3, wireOutside);
		wireTop.addVertex(0, 0.9062F, 0.0625F, 0.5313F-0.0625F);
		wireTop.addVertex(1, 0.9062F-0.8125F, 0.0625F, 0.5313F-0.0625F);
		wireTop.addVertex(2, 0.9062F-0.8125F, 0.0625F, 0.5313F);
		wireTop.addVertex(3, 0.9062F, 0.0625F, 0.5313F);
		setLightSource(3, 0, 1, 0);
		this.setQuad(wireTop);
		
		Quad wireRight = new Quad(4, wireOutside);
		wireRight.addVertex(0, 0.9062F, 0.0625F, 0.5313F);
		wireRight.addVertex(1, 0.9062F, 0F, 0.5313F);
		wireRight.addVertex(2, 0.9062F, 0F, 0.5313F-0.0625F);
		wireRight.addVertex(3, 0.9062F, 0.0625F, 0.5313F-0.0625F);
		setLightSource(4, 0, 1, 0);
		this.setQuad(wireRight);
		
		Quad wireLeft = new Quad(5, wireOutside);
		wireLeft.addVertex(0, 0.9062F-0.8125F, 0.0625F, 0.5313F);
		wireLeft.addVertex(1, 0.9062F-0.8125F, 0.0625F, 0.5313F-0.0625F);
		wireLeft.addVertex(2, 0.9062F-0.8125F, 0F, 0.5313F-0.0625F);
		wireLeft.addVertex(3, 0.9062F-0.8125F, 0F, 0.5313F);
		setLightSource(5, 0, 1, 0);
		this.setQuad(wireLeft);
		
		//right nub
		///////////////////////////////////////
		
		Quad rightNubFront = new Quad(6, wireInside);
		rightNubFront.addVertex(0, 0.9062F+0.0312F, 0.0525F, 0.5213F);
		rightNubFront.addVertex(1, 0.9062F, 0.0525F, 0.5213F);
		rightNubFront.addVertex(2, 0.9062F, 0.0525F-0.0425F, 0.5213F);
		rightNubFront.addVertex(3, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F);
		setLightSource(6, 0, 1, 0);
		this.setQuad(rightNubFront);
		
		Quad rightNubBottom = new Quad(7, wireInside);
		rightNubBottom.addVertex(0, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F);
		rightNubBottom.addVertex(1, 0.9062F, 0.0525F-0.0425F, 0.5213F);
		rightNubBottom.addVertex(2, 0.9062F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		rightNubBottom.addVertex(3, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		setLightSource(7, 0, 1, 0);
		this.setQuad(rightNubBottom);
		
		Quad rightNubRear = new Quad(8, wireInside);
		rightNubRear.addVertex(0, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		rightNubRear.addVertex(1, 0.9062F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		rightNubRear.addVertex(2, 0.9062F, 0.0525F, 0.5213F-0.0425F);
		rightNubRear.addVertex(3, 0.9062F+0.0312F, 0.0525F, 0.5213F-0.0425F);
		setLightSource(8, 0, 1, 0);
		this.setQuad(rightNubRear);
		
		Quad rightNubTop = new Quad(9, wireInside);
		rightNubTop.addVertex(0, 0.9062F+0.0312F, 0.0525F, 0.5213F-0.0425F);
		rightNubTop.addVertex(1, 0.9062F, 0.0525F, 0.5213F-0.0425F);
		rightNubTop.addVertex(2, 0.9062F, 0.0525F, 0.5213F);
		rightNubTop.addVertex(3, 0.9062F+0.0312F, 0.0525F, 0.5213F);
		setLightSource(9, 0, 1, 0);
		this.setQuad(rightNubTop);
		
		Quad rightNubSide = new Quad(10, wireInside);
		rightNubSide.addVertex(0, 0.9062F+0.0312F, 0.0525F, 0.5213F);
		rightNubSide.addVertex(1, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F);
		rightNubSide.addVertex(2, 0.9062F+0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		rightNubSide.addVertex(3, 0.9062F+0.0312F, 0.0525F, 0.5213F-0.0425F);
		setLightSource(10, 0, 1, 0);
		this.setQuad(rightNubSide);
		
		//left nub
		///////////////////////////////////////
		
		Quad leftNubFront = new Quad(11, wireInside);
		leftNubFront.addVertex(0, 0.0937F, 0.0525F, 0.5213F);
		leftNubFront.addVertex(1, 0.0937F-0.0312F, 0.0525F, 0.5213F);
		leftNubFront.addVertex(2, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F);
		leftNubFront.addVertex(3, 0.0937F, 0.0525F-0.0425F, 0.5213F);
		setLightSource(11, 0, 1, 0);
		this.setQuad(leftNubFront);
		
		Quad leftNubBottom = new Quad(12, wireInside);
		leftNubBottom.addVertex(0, 0.0937F, 0.0525F-0.0425F, 0.5213F);
		leftNubBottom.addVertex(1, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F);
		leftNubBottom.addVertex(2, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		leftNubBottom.addVertex(3, 0.0937F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		setLightSource(12, 0, 1, 0);
		this.setQuad(leftNubBottom);
		
		Quad leftNubRear = new Quad(13, wireInside);
		leftNubRear.addVertex(0, 0.0937F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		leftNubRear.addVertex(1, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		leftNubRear.addVertex(2, 0.0937F-0.0312F, 0.0525F, 0.5213F-0.0425F);
		leftNubRear.addVertex(3, 0.0937F, 0.0525F, 0.5213F-0.0425F);
		setLightSource(13, 0, 1, 0);
		this.setQuad(leftNubRear);
		
		Quad leftNubTop = new Quad(14, wireInside);
		leftNubTop.addVertex(0, 0.0937F, 0.0525F, 0.5213F-0.0425F);
		leftNubTop.addVertex(1, 0.0937F-0.0312F, 0.0525F, 0.5213F-0.0425F);
		leftNubTop.addVertex(2, 0.0937F-0.0312F, 0.0525F, 0.5213F);
		leftNubTop.addVertex(3, 0.0937F, 0.0525F, 0.5213F);
		setLightSource(14, 0, 1, 0);
		this.setQuad(leftNubTop);
		
		Quad leftNubSide = new Quad(15, wireInside);
		leftNubSide.addVertex(0, 0.0937F-0.0312F, 0.0525F, 0.5213F);
		leftNubSide.addVertex(1, 0.0937F-0.0312F, 0.0525F, 0.5213F-0.0425F);
		leftNubSide.addVertex(2, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F-0.0425F);
		leftNubSide.addVertex(3, 0.0937F-0.0312F, 0.0525F-0.0425F, 0.5213F);
		setLightSource(15, 0, 1, 0);
		this.setQuad(leftNubSide);
		
	}

}
