package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class Speaker extends GenericCubeCustomBlock implements WireConnector {

	public Speaker() {
		super(JukeBukkit.instance, "Universal Speaker", 5, "speaker.png", 16);		
	}
}
