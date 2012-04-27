package cc.thedudeguy.jukebukkit.materials.blocks.speakerwire;

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.SpeakerWireStraightDesign;

public class SpeakerWireNorthSouth extends SpeakerWireBlock {

	private int rotationY = 0;
	
	public SpeakerWireNorthSouth() {
		super(SpeakerWireBlock.NORTHtoSOUTH);
		
		this.setBlockDesign(new SpeakerWireStraightDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.SOUTH)) return true;
		return false;
	}
	
}
