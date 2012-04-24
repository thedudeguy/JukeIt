package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.inventory.SpoutItemStack;

import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerDesign;

public class RecordPlayerSubBlock extends RecordPlayer {
	
	public RecordPlayerSubBlock(RecordPlayerDesign rpDesign) {
		super(rpDesign.getDesignTypeId());
		
		this.setName("Record Player SubBlock (Do Not Use)");
		this.setBlockDesign(rpDesign);
		
		SpoutItemStack dropItem = new SpoutItemStack(getSubBlock(RecordPlayerDesign.NEEDLE_NONE, RecordPlayerDesign.DISC_NONE, RecordPlayerDesign.INDICATOR_RED), 1);
		
		this.setItemDrop(dropItem);
	}
}