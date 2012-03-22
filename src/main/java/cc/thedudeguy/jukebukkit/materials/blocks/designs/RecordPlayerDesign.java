package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.block.design.Quad;
import org.getspout.spoutapi.block.design.SubTexture;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;

public class RecordPlayerDesign extends GenericBlockDesign {
	
	public static final int DISC_NONE 		= 0;
	public static final int DISC_WHITE 		= 16;
	public static final int DISC_RED 		= 17;
	public static final int DISC_PURPLE 	= 18;
	public static final int DISC_PINK 		= 19;
	public static final int DISC_ORANGE 	= 20;
	public static final int DISC_MAGENTA	= 21;
	public static final int DISC_LIME 		= 22;
	public static final int DISC_LGRAY 		= 23;
	public static final int DISC_LBLUE 		= 24;
	public static final int DISC_GREEN 		= 25;
	public static final int DISC_GRAY 		= 26;
	public static final int DISC_CYAN 		= 27;
	public static final int DISC_BROWN 		= 28;
	public static final int DISC_BLUE		= 29;
	public static final int DISC_BLACK 		= 30;
	public static final int DISC_YELLOW 	= 31;
	
	public static final int NEEDLE_NONE 		= 0;
	public static final int NEEDLE_WOOD_FLINT 	= 32;
	
	public static final int INDICATOR_RED = 7;
	public static final int INDICATOR_GREEN = 8;
	
	private int discColor;
	private int needleType;
	private int indicatorColor;
	
	public RecordPlayerDesign() {
		this(NEEDLE_NONE, DISC_NONE, INDICATOR_RED);
	}
	public RecordPlayerDesign(int needle, int disc, int indicator) {
		
		discColor = disc;
		needleType = needle;
		indicatorColor = indicator;
		
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
		SubTexture needleBase = texture.getSubTexture(6);
		
		SubTexture indicatorST = texture.getSubTexture(indicatorColor);
		SubTexture recordST = texture.getSubTexture(discColor);
		SubTexture needleST = texture.getSubTexture(needleType);
		
		setBoundingBox(0, 0, 0, 1, 1, 1);
		setQuadNumber(26);
		
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
		
		// INDICATOR LIGHT
		
		Quad indicatorLeft = new Quad(14, indicatorST);
		indicatorLeft.addVertex(0, 0.5F, 0.9675F, 0.8125F);
		indicatorLeft.addVertex(1, 0.5F, 0.9675F-0.03F, 0.8125F);
		indicatorLeft.addVertex(2, 0.5F, 0.9675F-0.03F, 0.8125F + 0.1250F);
		indicatorLeft.addVertex(3, 0.5F, 0.9675F, 0.8125F + 0.1250F);
		this.setQuad(indicatorLeft);
		
		Quad indicatorRear = new Quad(15, indicatorST);
		indicatorRear.addVertex(0, 0.5F, 0.9675F, 0.8125F);
		indicatorRear.addVertex(1, 0.5F+0.0625F, 0.9675F, 0.8125F);
		indicatorRear.addVertex(2, 0.5F+0.0625F, 0.9675F-0.03F, 0.8125F);
		indicatorRear.addVertex(3, 0.5F, 0.9675F-0.03F, 0.8125F);
		this.setQuad(indicatorRear);
		
		Quad indicatorRight = new Quad(16, indicatorST);
		indicatorRight.addVertex(0, 0.5F+0.0625F, 0.9675F, 0.8125F);
		indicatorRight.addVertex(1, 0.5F+0.0625F, 0.9675F, 0.8125F+0.125F);
		indicatorRight.addVertex(2, 0.5F+0.0625F, 0.9675F-0.03F, 0.8125F+0.125F);
		indicatorRight.addVertex(3, 0.5F+0.0625F, 0.9675F-0.03F, 0.8125F);
		this.setQuad(indicatorRight);
		
		Quad indicatorTop = new Quad(17, indicatorST);
		indicatorTop.addVertex(0, 0.5F, 0.9675F, 0.8125F);
		indicatorTop.addVertex(1, 0.5F, 0.9675F, 0.8125F+0.125F);
		indicatorTop.addVertex(2, 0.5F+0.0625F, 0.9675F, 0.8125F+0.125F);
		indicatorTop.addVertex(3, 0.5F+0.0625F, 0.9675F, 0.8125F);
		this.setQuad(indicatorTop);
		
		// NEEDLE BASE
		
		Quad needleBaseFront = new Quad(18, needleBase);
		needleBaseFront.addVertex(0, 0.75F, 0.980F, 0.75F+0.0625F);
		needleBaseFront.addVertex(1, 0.75F, 0.998F-0.0605F, 0.75F+0.0625F);
		needleBaseFront.addVertex(2, 0.75F+0.0625F, 0.998F-0.0605F, 0.75F+0.0625F);
		needleBaseFront.addVertex(3, 0.75F+0.0625F, 0.998F, 0.75F+0.0625F);
		this.setQuad(needleBaseFront);
		
		Quad needleBaseLeft = new Quad(19, needleBase);
		needleBaseLeft.addVertex(0, 0.75F, 0.980F, 0.75F);
		needleBaseLeft.addVertex(1, 0.75F, 0.998F-0.0605F, 0.75F);
		needleBaseLeft.addVertex(2, 0.75F, 0.998F-0.0605F, 0.75F+0.0625F);
		needleBaseLeft.addVertex(3, 0.75F, 0.980F, 0.75F+0.0625F);
		this.setQuad(needleBaseLeft);
		
		Quad needleBaseRight = new Quad(20, needleBase);
		needleBaseRight.addVertex(0, 0.75F+0.0625F, 0.998F, 0.75F+0.0625F);
		needleBaseRight.addVertex(1, 0.75F+0.0625F, 0.998F-0.0605F, 0.75F+0.0625F);
		needleBaseRight.addVertex(2, 0.75F+0.0625F, 0.998F-0.0605F, 0.75F);
		needleBaseRight.addVertex(3, 0.75F+0.0625F, 0.980F, 0.75F);
		this.setQuad(needleBaseRight);
		
		Quad needleBaseRear = new Quad(21, needleBase);
		needleBaseRear.addVertex(0, 0.75F+0.0625F, 0.980F, 0.75F);
		needleBaseRear.addVertex(1, 0.75F+0.0625F, 0.998F-0.0605F, 0.75F);
		needleBaseRear.addVertex(2, 0.75F, 0.998F-0.0605F, 0.75F);
		needleBaseRear.addVertex(3, 0.75F, 0.980F, 0.75F);
		this.setQuad(needleBaseRear);
		
		Quad needleBaseTop = new Quad(22, needleBase);
		needleBaseTop.addVertex(0, 0.75F, 0.980F, 0.75F);
		needleBaseTop.addVertex(1, 0.75F, 0.980F, 0.8125F);
		needleBaseTop.addVertex(2, 0.8125F, 0.998F, 0.8125F);
		needleBaseTop.addVertex(3, 0.8125F, 0.998F, 0.8125F);
		this.setQuad(needleBaseTop);
		
		Quad needleBaseTop2 = new Quad(23, needleBase);
		needleBaseTop2.addVertex(0, 0.8125F, 0.998F, 0.8125F);
		needleBaseTop2.addVertex(1, 0.8125F, 0.980F, 0.75F);
		needleBaseTop2.addVertex(2, 0.75F, 0.980F, 0.75F);
		needleBaseTop2.addVertex(3, 0.75F, 0.980F, 0.75F);
		this.setQuad(needleBaseTop2);
		
		// RECORD.
		
		if (discColor != DISC_NONE) {
			
			Quad record = new Quad(25, recordST);
			record.addVertex(0, 1F, 0.9675F, 0F);
			record.addVertex(1, 0F, 0.9675F, 0F);
			record.addVertex(2, 0F, 0.9675F, 1F);
			record.addVertex(3, 1F, 0.9675F, 1F);
			this.setQuad(record);
		}
		
		// NEEDLE
		if (needleType != NEEDLE_NONE) {
			
			if (discColor == DISC_NONE) {
				Quad needleQuad = new Quad(24, needleST);
				needleQuad.addVertex(2, 0.8125F, 1F, 0.75F);
				needleQuad.addVertex(3, 0.3125F, 1F, 0.75F);
				needleQuad.addVertex(0, 0.3125F, 1F, 0.7812F);
				needleQuad.addVertex(1, 0.8125F, 1F, 0.8125F);
				this.setQuad(needleQuad);
			
			} else {
				Quad needleQuad = new Quad(24, needleST);
				needleQuad.addVertex(0, 0.3308F, 0.9675F, 0.6505F);
				needleQuad.addVertex(1, 0.8025F, 1F, 0.8192F);
				needleQuad.addVertex(2, 0.8198F, 1F, 0.7591F);
				needleQuad.addVertex(3, 0.3395F, 0.9675F, 0.6205F);
				this.setQuad(needleQuad);
				
			}	
		}
	}
	
	public int getIndicator() {
		return indicatorColor;
	}
	
	public int getDisc() {
		return discColor;
	}
	
	public int getNeedle() {
		return needleType;
	}
	
	public String getDesignTypeId()
	{
		return "jb_" + String.valueOf(needleType) + "_" + String.valueOf(discColor) + "_" + String.valueOf(indicatorColor);
	}
	
}