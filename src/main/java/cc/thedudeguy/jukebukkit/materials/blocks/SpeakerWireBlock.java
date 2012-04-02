package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.SpeakerWireDesign;
import cc.thedudeguy.jukebukkit.materials.items.Items;

public class SpeakerWireBlock extends GenericCustomBlock {

	public SpeakerWireBlock() {
		super(JukeBukkit.instance, "speakerwireblock", 55, new SpeakerWireDesign());
		
		this.setName("Speaker Wire Block (DO NOT USE)");
		this.setItemDrop(new SpoutItemStack(Items.speakerWire));
	}
	
}
