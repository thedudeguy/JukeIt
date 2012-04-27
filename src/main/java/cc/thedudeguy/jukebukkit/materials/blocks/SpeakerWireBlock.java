package cc.thedudeguy.jukebukkit.materials.blocks;

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.items.Items;

public abstract class SpeakerWireBlock extends GenericCustomBlock {
	
	public static final int EASTtoWEST = 0;
	public static final int WESTtoEAST = 0;
	public static final int NORTHtoSOUTH = 1;
	public static final int SOUTHtoNORTH = 1;
	
	public static final int NORTHtoEAST = 2;
	public static final int EASTtoNORTH = 2;
	
	public static final int EASTtoSOUTH = 3;
	public static final int SOUTHtoEAST = 3;
	
	public static final int SOUTHtoWEST = 4;
	public static final int WESTtoSOUTH = 4;
	
	public static final int WESTtoNORTH = 5;
	public static final int NORTHtoWEST = 5;
	
	public static final int UPtoDOWN = 6;
	public static final int DOWNtoUP = 6;
	
	public static final int EASTtoUP = 7;
	public static final int UPtoEAST = 7;
	
	public static final int WESTtoUP = 8;
	public static final int UPtoWEST = 8;
	
	public static final int NORTHtoUP = 9;
	public static final int UPtoNORTH = 9;
	
	public static final int SOUTHtoUP = 10;
	public static final int UPtoSOUTH = 10;
	
	public static final int WESTtoDOWN = 11;
	public static final int DOWNtoWEST = 11;
	
	public static final int EASTtoDOWN = 12;
	public static final int DOWNtoEAST = 12;
	
	public static final int NORTHtoDOWN = 13;
	public static final int DOWNtoNORTH = 13;
	
	public static final int SOUTHtoDOWN = 14;
	public static final int DOWNtoSOUTH = 14;
	
	protected int type;
	
	public SpeakerWireBlock(int type) {
		super(JukeBukkit.instance, "speakerwireblock_"+String.valueOf(type), 70);
		
		setType(type);
		
		this.setName("Speaker Wire Block "+ String.valueOf(type) + " (DO NOT USE)");
		this.setItemDrop(new SpoutItemStack(Items.speakerWire));
		this.setHardness(0.1F);
	}
	
	private void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean isFaceConnected(SpoutBlock block, BlockFace face) {
		
		if (
				((SpeakerWireBlock)((SpoutBlock)block.getRelative(face)).getCustomBlock()) == null
				) {
			return false;
		}
		
		if (face.equals(BlockFace.NORTH)) {
			if (
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == SpeakerWireBlock.SOUTHtoNORTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == SpeakerWireBlock.SOUTHtoEAST ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == SpeakerWireBlock.SOUTHtoWEST ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == SpeakerWireBlock.SOUTHtoDOWN ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == SpeakerWireBlock.SOUTHtoUP
					) {
				return true;
			}
			
		}
		
		if (face.equals(BlockFace.EAST)) {
			if (
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == SpeakerWireBlock.WESTtoEAST ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == SpeakerWireBlock.WESTtoNORTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == SpeakerWireBlock.WESTtoSOUTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == SpeakerWireBlock.WESTtoUP ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == SpeakerWireBlock.WESTtoDOWN
					) {
				return true;
			}
			
		} 
		
		if (face.equals(BlockFace.SOUTH)) {
			if (
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == SpeakerWireBlock.NORTHtoEAST ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == SpeakerWireBlock.NORTHtoSOUTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == SpeakerWireBlock.NORTHtoWEST ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == SpeakerWireBlock.NORTHtoDOWN ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == SpeakerWireBlock.NORTHtoUP
					) {
				return true;
			}
			
		} 
		
		if (face.equals(BlockFace.WEST)) {
			if (
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == SpeakerWireBlock.EASTtoNORTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == SpeakerWireBlock.EASTtoSOUTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == SpeakerWireBlock.EASTtoWEST ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == SpeakerWireBlock.EASTtoDOWN ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == SpeakerWireBlock.EASTtoUP
					) {
				return true;
			}
			
		}
		
		if (face.equals(BlockFace.UP)) {
			if (
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == SpeakerWireBlock.DOWNtoEAST ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == SpeakerWireBlock.DOWNtoNORTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == SpeakerWireBlock.DOWNtoSOUTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == SpeakerWireBlock.DOWNtoUP ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == SpeakerWireBlock.DOWNtoWEST
					) {
				return true;
			}
			
		}
		
		if (face.equals(BlockFace.DOWN)) {
			if (
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == SpeakerWireBlock.UPtoDOWN ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == SpeakerWireBlock.UPtoEAST ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == SpeakerWireBlock.UPtoNORTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == SpeakerWireBlock.UPtoSOUTH ||
					((SpeakerWireBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == SpeakerWireBlock.UPtoWEST
					) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public abstract boolean hasOpenEnd(SpoutBlock block);
	
}
