package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerNoRecordDesign;

public class RecordPlayer extends GenericCustomBlock {
	
	public RecordPlayer() {
		super(JukeBukkit.instance, "Record Player", 3);
		
		this.setBlockDesign(new RecordPlayerNoRecordDesign());
		
		this.setHardness(MaterialData.wood.getHardness());
		this.setLightLevel(MaterialData.glowstoneBlock.getLightLevel());
	}
	
}
