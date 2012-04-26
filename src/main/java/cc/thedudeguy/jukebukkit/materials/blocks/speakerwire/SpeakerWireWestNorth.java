package cc.thedudeguy.jukebukkit.materials.blocks.speakerwire;

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;

public class SpeakerWireWestNorth extends SpeakerWireBlock {

	public SpeakerWireWestNorth() {
		super(SpeakerWireBlock.WESTtoNORTH);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		
		if (!this.isFaceConnected(block, BlockFace.WEST)) return true;
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		return false;
	}

}
