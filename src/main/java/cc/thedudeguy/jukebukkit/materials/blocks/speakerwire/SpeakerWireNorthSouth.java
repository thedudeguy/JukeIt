package cc.thedudeguy.jukebukkit.materials.blocks.speakerwire;

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
import cc.thedudeguy.jukebukkit.util.Debug;

public class SpeakerWireNorthSouth extends SpeakerWireBlock {

	public SpeakerWireNorthSouth() {
		super(SpeakerWireBlock.NORTHtoSOUTH);
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		
		if (!this.isFaceConnected(block, BlockFace.SOUTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		return false;
	}
	
}
