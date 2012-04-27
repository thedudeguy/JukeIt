package cc.thedudeguy.jukebukkit.materials.blocks.speakerwire;

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.SpeakerWireStraightDesign;

public class SpeakerWireEastWest extends SpeakerWireBlock {

	private int rotationY = 90;
	
	public SpeakerWireEastWest() {
		super(SpeakerWireBlock.EASTtoWEST);
		
		this.setBlockDesign(new SpeakerWireStraightDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.EAST)) return true;
		if (!this.isFaceConnected(block, BlockFace.WEST)) return true;
		return false;
		
	}
	
	
}
