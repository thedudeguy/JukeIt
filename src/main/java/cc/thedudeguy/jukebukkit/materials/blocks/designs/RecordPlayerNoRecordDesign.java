package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.block.design.Quad;
import org.getspout.spoutapi.block.design.SubTexture;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;

public class RecordPlayerNoRecordDesign extends GenericBlockDesign {
	
	public RecordPlayerNoRecordDesign() {
		JukeBukkit plugin = JukeBukkit.instance;
		Texture texture = Blocks.recordPlayerTexture;
		
		setTexture(plugin, texture.getTexture());
		setMinBrightness(1F);
		setMaxBrightness(1F);
		
		SubTexture textureSide = texture.getSubTexture(0);
		SubTexture textureTop = texture.getSubTexture(1);
		SubTexture edge1 = texture.getSubTexture(2);
		SubTexture edge2 = texture.getSubTexture(3);
		SubTexture insideEdge1 = texture.getSubTexture(4);
		SubTexture insideEdge2 = texture.getSubTexture(5);
		
		setBoundingBox(0, 0, 0, 1, 1, 1);
		setQuadNumber(14);
		
		//main body
		///////////////////////////////////////
		
		Quad frontFace = new Quad(2, textureSide);
		frontFace.addVertex(0, 1F, 0F, 1F);
		frontFace.addVertex(1, 1F, 1F, 1F);
		frontFace.addVertex(2, 0F, 1F, 1F);
		frontFace.addVertex(3, 0F, 0F, 1F);
		this.setQuad(frontFace);
		
		Quad rightFace = new Quad(1, textureSide);
		rightFace.addVertex(0, 1F, 0F, 0F);
		rightFace.addVertex(1, 1F, 1F, 0F);
		rightFace.addVertex(2, 1F, 1F, 1F);
		rightFace.addVertex(3, 1F, 0F, 1F);
		this.setQuad(rightFace);
		
		Quad rearFace = new Quad(0, textureSide);
		rearFace.addVertex(0, 0F, 0F, 0F);
		rearFace.addVertex(1, 0F, 1F, 0F);
		rearFace.addVertex(2, 1F, 1F, 0F);
		rearFace.addVertex(3, 1F, 0F, 0F);
		this.setQuad(rearFace);
		
		Quad leftFace = new Quad(3, textureSide);
		leftFace.addVertex(0, 0F, 0F, 1F);
		leftFace.addVertex(1, 0F, 1F, 1F);
		leftFace.addVertex(2, 0F, 1F, 0F);
		leftFace.addVertex(3, 0F, 0F, 0F);
		this.setQuad(leftFace);
		
		Quad bottomFace = new Quad(4, textureSide);
		bottomFace.addVertex(0, 1F, 0F, 1F);
		bottomFace.addVertex(1, 0F, 0F, 1F);
		bottomFace.addVertex(2, 0F, 0F, 0F);
		bottomFace.addVertex(3, 1F, 0F, 0F);
		this.setQuad(bottomFace);
		
		// Very top face, base
		////////////////////////////////////////////
		
		Quad topFace1 = new Quad(5, textureTop);
		topFace1.addVertex(0, 0F, 0.9375F, 0F);
		topFace1.addVertex(1, 0F, 0.9375F, 1F);
		topFace1.addVertex(2, 1F, 0.9375F, 1F);
		topFace1.addVertex(3, 1F, 0.9375F, 0F);
		this.setQuad(topFace1);
		
		//4 slivers across the top for the padding box like effect
		///////////////////////////////////////////////////////////
		
		Quad topFaceEdge1 = new Quad(6, edge1);
		topFaceEdge1.addVertex(0, 0F, 1F, 0F);
		topFaceEdge1.addVertex(1, 0F, 1F, 1F);
		topFaceEdge1.addVertex(2, 0.0625F, 1F, 1F);
		topFaceEdge1.addVertex(3, 0.0625F, 1F, 0F);
		this.setQuad(topFaceEdge1);
		
		Quad topFaceEdge2 = new Quad(7, edge1);
		topFaceEdge2.addVertex(0, 0.9375F, 1F, 0F);
		topFaceEdge2.addVertex(1, 0.9375F, 1F, 1F);
		topFaceEdge2.addVertex(2, 1F, 1F, 1F);
		topFaceEdge2.addVertex(3, 1F, 1F, 0F);
		this.setQuad(topFaceEdge2);
		
		Quad topFaceEdge3 = new Quad(8, edge2);
		topFaceEdge3.addVertex(0, 0.0625F, 1F, 0.9375F);
		topFaceEdge3.addVertex(1, 0.0625F, 1F, 1F);
		topFaceEdge3.addVertex(2, 0.9375F, 1F, 1F);
		topFaceEdge3.addVertex(3, 0.9375F, 1F, 0.9375F);
		this.setQuad(topFaceEdge3);
		
		Quad topFaceEdge4 = new Quad(9, edge2);
		topFaceEdge4.addVertex(0, 0.0625F, 1F, 0F);
		topFaceEdge4.addVertex(1, 0.0625F, 1F, 0.0625F);
		topFaceEdge4.addVertex(2, 0.9375F, 1F, 0.0625F);
		topFaceEdge4.addVertex(3, 0.9375F, 1F, 0F);
		this.setQuad(topFaceEdge4);
		
		//inside edges
		///////////////
		
		Quad insideEdgeq1 = new Quad(10, insideEdge1);
		insideEdgeq1.addVertex(0, 0.0625F, 1F, 0.0625F);
		insideEdgeq1.addVertex(1, 0.0625F, 1F, 0.9375F);
		insideEdgeq1.addVertex(2, 0.0625F, 0.9375F, 0.9375F);
		insideEdgeq1.addVertex(3, 0.0625F, 0.0625F, 0.0625F);
		this.setQuad(insideEdgeq1);
		
		Quad insideEdgeq2 = new Quad(11, insideEdge1);
		insideEdgeq2.addVertex(0, 0.9375F, 0.9375F, 0.0625F);
		insideEdgeq2.addVertex(1, 0.9375F, 0.9375F, 0.9375F);
		insideEdgeq2.addVertex(2, 0.9375F, 1F, 0.9375F);
		insideEdgeq2.addVertex(3, 0.9375F, 1F, 0.0625F);
		this.setQuad(insideEdgeq2);
		
		Quad insideEdgeq3 = new Quad(12, insideEdge2);
		insideEdgeq3.addVertex(0, 0.0625F, 1F, 0.0625F);
		insideEdgeq3.addVertex(1, 0.0625F, 0.0625F, 0.0625F);
		insideEdgeq3.addVertex(2, 0.9375F, 0.9375F, 0.0625F);
		insideEdgeq3.addVertex(3, 0.9375F, 1F, 0.0625F);
		this.setQuad(insideEdgeq3);
		
		Quad insideEdgeq4 = new Quad(13, insideEdge2);
		insideEdgeq4.addVertex(0, 0.9375F, 1F, 0.9375F);
		insideEdgeq4.addVertex(1, 0.9375F, 0.9375F, 0.9375F);
		insideEdgeq4.addVertex(2, 0.0625F, 0.9375F, 0.9375F);
		insideEdgeq4.addVertex(3, 0.0625F, 1F, 0.9375F);
		this.setQuad(insideEdgeq4);
		
		
		
		
	}
	
}