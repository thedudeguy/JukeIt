package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.SpeakerWireDesign;
import cc.thedudeguy.jukebukkit.materials.items.Items;

public abstract class SpeakerWireBlock extends GenericCustomBlock {
	
	public static final int EASTtoWEST = 0;
	public static final int NORTHtoSOUTH = 1;
	
	protected int type;
	
	public SpeakerWireBlock(int type) {
		super(JukeBukkit.instance, "speakerwireblock_"+String.valueOf(type), 70);
		
		setType(type);
		
		this.setBlockDesign(new SpeakerWireDesign(getRotation(type)));
		this.setName("Speaker Wire Block "+ String.valueOf(type) + " (DO NOT USE)");
		this.setItemDrop(new SpoutItemStack(Items.speakerWire));
	}
	
	public int getRotation(int type) {
		switch(type) {
		case EASTtoWEST:
			return 90;
		case NORTHtoSOUTH:
			return 0;
		default:
				return 90;
		}
	}
	
	private void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public abstract boolean hasOpenEnd(SpoutBlock block);
	
	/*
	public boolean hasAvailableConnection(SpoutBlock block) {
		switch(type) {
		case EASTtoWEST:
			if (
					block.getRelative(BlockFace.EAST) instanceof SpeakerWireBlock &&
					block.getRelative(BlockFace.WEST) instanceof SpeakerWireBlock) {
				return false;
			}
			break;
		}
		
		return true;
	}
	*/
	
	/*
	public void onNeighborBlockChange(org.bukkit.World world, int x, int y, int z, int changedId) {
		
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		if (
				(
						block.getData("wire.powered") == null ||
						(Integer)block.getData("wire.powered") == 0
				) &&
				block.isBlockPowered() == true
				) {
			block.setData("wire.powered", 1);
			//Debug.debug("RecordPlayer: Redstone Activated");
			
			if (block.getData("wire.rotation") == null) {
				block.setData("wire.rotation", 0);
			}
			
			int rotate = (Integer)block.getData("wire.rotation");
			rotate += 10;
			if (rotate >= 360) rotate = 0;
			JukeBukkit.instance.getServer().broadcastMessage("rotating to "+String.valueOf(rotate));
			block.setData("wire.rotation", rotate);
			
			this.setBlockDesign(new SpeakerWireDesign(rotate));
			
		} else if (
				block.getData("wire.powered") != null &&
				(Integer)block.getData("wire.powered") == 1 &&
				block.isBlockPowered() == true
				) {
			//Debug.debug("RecordPlayer: New Redstone Power source, but block is already powered.");
		
		} else if (
				block.getData("wire.powered") != null &&
				(Integer)block.getData("wire.powered") == 1 &&
				block.isBlockPowered() == false
				) {
			block.setData("wire.powered", 0);
			//Debug.debug("RecordPlayer: Lost Redstone Power.");
			
		} else {
			block.setData("wire.powered", 0);
			//Debug.debug("RecordPlayer: Not Powered, and not powering");
		}
		
	}
	*/
}
