package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;

public class DiscBurnerDesign extends GenericCubeBlockDesign {

	public static final int[] SOUTH = { 2, 3, 3, 4, 3, 2 };
	public static final int[] NORTH = { 2, 4, 3, 3, 3, 2 };
	public static final int[] EAST = { 2, 3, 4, 3, 3, 2 };
	public static final int[] WEST = { 2, 3, 3, 3, 4, 2 };
	
	public DiscBurnerDesign(int[] direction) {
		super(JukeBukkit.instance, Blocks.blocksTexture, direction);
	}

}
