package cc.thedudeguy.jukebukkit.materials.blocks.speakerwire;

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;

public class SpeakerWireEastWest extends SpeakerWireBlock {

	public SpeakerWireEastWest() {
		super(SpeakerWireBlock.EASTtoWEST);
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		
		if (!this.isFaceConnected(block, BlockFace.WEST)) return true;
		if (!this.isFaceConnected(block, BlockFace.EAST)) return true;
		return false;
		
	}
	
	
}
