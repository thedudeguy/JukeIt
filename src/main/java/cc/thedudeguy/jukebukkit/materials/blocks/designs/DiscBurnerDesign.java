package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;

public class DiscBurnerDesign extends GenericCubeBlockDesign {

	
	
	public DiscBurnerDesign(int[] direction) {
		super(JukeBukkit.instance, Blocks.blocksTexture, direction);
	}

}
