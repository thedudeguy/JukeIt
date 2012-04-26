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
		
		Debug.debug("Checking for open End North");
		
		if (
				((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock() != null &&
				((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock() instanceof SpeakerWireBlock
				) {
			Debug.debug("Speaker wire on east face");
			
			if ( 
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() != SpeakerWireBlock.EASTtoWEST 
					) {
				Debug.debug("NORTH face wire is not connected. true");
				return true;
			} else {
				Debug.debug("NORTH wire connected.");
			}
		} else {
			Debug.debug("nothing at NORTH end. true");
			return true;
		}
		
		Debug.debug("Checking for open End SOUTH");
		if (
				((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock() != null &&
				((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock() instanceof SpeakerWireBlock
				) {
			Debug.debug("Speaker wire on SOUTH face");
			
			if ( 
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() != SpeakerWireBlock.EASTtoWEST 
					) {
				Debug.debug("SOUTH west wire is not connected. true");
				return true;
			} else {
				Debug.debug("SOUTH wire connected.");
			}
		} else {
			Debug.debug("nothing at SOUTH end. true");
			return true;
		}
		
		
		return false;
	}
	
}
