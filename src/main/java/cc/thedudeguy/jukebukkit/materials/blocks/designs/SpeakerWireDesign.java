package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.block.design.Quad;
import org.getspout.spoutapi.block.design.SubTexture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;

/*
 * new_x = old_x * cos(angle) + old_y * sin(angle)
 * new_z = old_z * cos(angle) - old_z * sin(angle)
 */
public class SpeakerWireDesign extends GenericBlockDesign {
	
	public class SimpleVertex {
		public float x;
		public float y;
		public float z;
		public int angle;
		
		public SimpleVertex(float x, float y, float z, int degrees) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.angle = degrees;
		}
		
		/** conjugation? **/ 
		
		public float getX() {
			return (float)( ((x - 0.5F) * Math.cos(Math.toRadians(angle))) - ((z - 0.5F) * Math.sin(Math.toRadians(angle))) ) + 0.5F;
		}
		public float getY() {
			return y;
		}
		public float getZ() {
			return (float)( ((x - 0.5F) * Math.sin(Math.toRadians(angle))) + ((z - 0.5F) * Math.cos(Math.toRadians(angle))) ) + 0.5F;
		}
	}
	 
	public SpeakerWireDesign(int deg) {
		
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
		SimpleVertex p1 = new SimpleVertex(0.9062F			, 0.0625F	, 0.5313F, deg);
		SimpleVertex p2 = new SimpleVertex(0.9062F-0.8125F	, 0.0625F	, 0.5313F, deg);
		SimpleVertex p3 = new SimpleVertex(0.9062F-0.8125F	, 0F		, 0.5313F, deg);
		SimpleVertex p4 = new SimpleVertex(0.9062F			, 0F		, 0.5313F, deg);
		wireFront.addVertex(0, p1.getX(), p1.getY(), p1.getZ() );
		wireFront.addVertex(1, p2.getX(), p2.getY(), p2.getZ());
		wireFront.addVertex(2, p3.getX(), p3.getY(), p3.getZ());
		wireFront.addVertex(3, p4.getX(), p4.getY(), p4.getZ());
		setLightSource(0, 0, 1, 0);
		this.setQuad(wireFront);
		
		Quad wireBottom = new Quad(1, wireOutside);
		p1 = new SimpleVertex(0.9062F			, 0F	, 0.5313F			, deg);
		p2 = new SimpleVertex(0.9062F-0.8125F	, 0F	, 0.5313F			, deg);
		p3 = new SimpleVertex(0.9062F-0.8125F	, 0F	, 0.5313F-0.0625F	, deg);
		p4 = new SimpleVertex(0.9062F			, 0F	, 0.5313F-0.0625F	, deg);
		wireBottom.addVertex(0, p1.getX(), p1.getY(), p1.getZ() );
		wireBottom.addVertex(1, p2.getX(), p2.getY(), p2.getZ());
		wireBottom.addVertex(2, p3.getX(), p3.getY(), p3.getZ());
		wireBottom.addVertex(3, p4.getX(), p4.getY(), p4.getZ());
		setLightSource(1, 0, 1, 0);
		this.setQuad(wireBottom);
		
		Quad wireRear = new Quad(2, wireOutside);
		p1 = new SimpleVertex(0.9062F			, 0F		, 0.5313F-0.0625F , deg);
		p2 = new SimpleVertex(0.9062F-0.8125F	, 0F		, 0.5313F-0.0625F , deg);
		p3 = new SimpleVertex(0.9062F-0.8125F	, 0.0625F	, 0.5313F-0.0625F , deg);
		p4 = new SimpleVertex(0.9062F			, 0.0625F	, 0.5313F-0.0625F , deg);
		wireRear.addVertex(0, p1.getX(), p1.getY(), p1.getZ() );
		wireRear.addVertex(1, p2.getX(), p2.getY(), p2.getZ());
		wireRear.addVertex(2, p3.getX(), p3.getY(), p3.getZ());
		wireRear.addVertex(3, p4.getX(), p4.getY(), p4.getZ());
		setLightSource(2, 0, 1, 0);
		this.setQuad(wireRear);
		
		Quad wireTop = new Quad(3, wireOutside);
		p1 = new SimpleVertex(0.9062F			, 0.0625F	, 0.5313F-0.0625F	, deg);
		p2 = new SimpleVertex(0.9062F-0.8125F	, 0.0625F	, 0.5313F-0.0625F	, deg);
		p3 = new SimpleVertex(0.9062F-0.8125F	, 0.0625F	, 0.5313F			, deg);
		p4 = new SimpleVertex(0.9062F			, 0.0625F	, 0.5313F			, deg);
		wireTop.addVertex(0, p1.getX(), p1.getY(), p1.getZ() );
		wireTop.addVertex(1, p2.getX(), p2.getY(), p2.getZ());
		wireTop.addVertex(2, p3.getX(), p3.getY(), p3.getZ());
		wireTop.addVertex(3, p4.getX(), p4.getY(), p4.getZ());
		setLightSource(3, 0, 1, 0);
		this.setQuad(wireTop);
		
		Quad wireRight = new Quad(4, wireInside); //setting to wire inside so i can see were it moves.
		p1 = new SimpleVertex(0.9062F			, 0.0625F	, 0.5313F			, deg);
		p2 = new SimpleVertex(0.9062F			, 0F		, 0.5313F			, deg);
		p3 = new SimpleVertex(0.9062F			, 0F		, 0.5313F-0.0625F	, deg);
		p4 = new SimpleVertex(0.9062F			, 0.0625F	, 0.5313F-0.0625F	, deg);
		wireRight.addVertex(0, p1.getX(), p1.getY(), p1.getZ() );
		wireRight.addVertex(1, p2.getX(), p2.getY(), p2.getZ());
		wireRight.addVertex(2, p3.getX(), p3.getY(), p3.getZ());
		wireRight.addVertex(3, p4.getX(), p4.getY(), p4.getZ());
		setLightSource(4, 0, 1, 0);
		this.setQuad(wireRight);
		
		Quad wireLeft = new Quad(5, wireOutside);
		p1 = new SimpleVertex(0.9062F-0.8125F	, 0.0625F	, 0.5313F			, deg);
		p2 = new SimpleVertex(0.9062F-0.8125F	, 0.0625F	, 0.5313F-0.0625F	, deg);
		p3 = new SimpleVertex(0.9062F-0.8125F	, 0F		, 0.5313F-0.0625F	, deg);
		p4 = new SimpleVertex(0.9062F-0.8125F	, 0F		, 0.5313F			, deg);
		wireLeft.addVertex(0, p1.getX(), p1.getY(), p1.getZ() );
		wireLeft.addVertex(1, p2.getX(), p2.getY(), p2.getZ());
		wireLeft.addVertex(2, p3.getX(), p3.getY(), p3.getZ());
		wireLeft.addVertex(3, p4.getX(), p4.getY(), p4.getZ());
		setLightSource(5, 0, 1, 0);
		this.setQuad(wireLeft);
		
		/*
		
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
		
		*/
		
	}
	
	
}
