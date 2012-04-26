package cc.thedudeguy.jukebukkit.materials.blocks.speakerwire;

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
import cc.thedudeguy.jukebukkit.util.Debug;

public class SpeakerWireEastWest extends SpeakerWireBlock {

	public SpeakerWireEastWest() {
		super(SpeakerWireBlock.EASTtoWEST);
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		
		Debug.debug("Checking for open End East");
		
		if (
				((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock() != null &&
				((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock() instanceof SpeakerWireBlock
				) {
			Debug.debug("Speaker wire on east face");
			
			if ( 
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() != SpeakerWireBlock.EASTtoWEST 
					) {
				Debug.debug("east face wire is not connected. true");
				return true;
			} else {
				Debug.debug("east wire connected.");
			}
		} else {
			Debug.debug("nothing at east end. true");
			return true;
		}
		
		Debug.debug("Checking for open End West");
		if (
				((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock() != null &&
				((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock() instanceof SpeakerWireBlock
				) {
			Debug.debug("Speaker wire on west face");
			
			if ( 
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() != SpeakerWireBlock.EASTtoWEST 
					) {
				Debug.debug("west west wire is not connected. true");
				return true;
			} else {
				Debug.debug("west wire connected.");
			}
		} else {
			Debug.debug("nothing at west end. true");
			return true;
		}
		
		
		return false;
		
	}
	
	
}
