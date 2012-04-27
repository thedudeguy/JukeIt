package cc.thedudeguy.jukebukkit.materials.blocks.speakerwire;

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.SpeakerWireTurnDesign;

public class SpeakerWireNorthDown extends SpeakerWireBlock {
	
	private int rotationX = 90;
	private int rotationY = 180;
	private int rotationZ = 0;
	
	private float moveX = 0;
	private float moveY = -0.46875F;
	private float moveZ = 0.46875F;;
	
	public SpeakerWireNorthDown() {
		super(SpeakerWireBlock.NORTHtoDOWN);
		this.setBlockDesign(new SpeakerWireTurnDesign(rotationX, rotationY, rotationZ, moveX, moveY, moveZ));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.DOWN)) return true;
		return false;
	}

}
