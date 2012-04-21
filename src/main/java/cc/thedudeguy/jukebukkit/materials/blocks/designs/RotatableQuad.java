package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.block.design.Quad;
import org.getspout.spoutapi.block.design.SubTexture;

public class RotatableQuad extends Quad {
	
	private int rotation;
	
	public RotatableQuad(int index, SubTexture texture, int rotation) {
		super(index, texture);
		this.rotation = rotation;
	}
	
	@Override
	public Quad addVertex(int index, float x, float y, float z) {
		
		float newx = (float)( ((x - 0.5F) * Math.cos(Math.toRadians(rotation))) - ((z - 0.5F) * Math.sin(Math.toRadians(rotation))) ) + 0.5F;
		float newz = (float)( ((x - 0.5F) * Math.sin(Math.toRadians(rotation))) + ((z - 0.5F) * Math.cos(Math.toRadians(rotation))) ) + 0.5F;
		
		return super.addVertex(index, newx, y, newz);
	}
}
