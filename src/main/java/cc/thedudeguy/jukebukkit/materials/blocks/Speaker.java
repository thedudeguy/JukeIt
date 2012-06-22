package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.permission.CraftPermission;

public class Speaker extends GenericCubeCustomBlock implements WireConnector, CraftPermissible {

	public Speaker() {
		super(JukeBukkit.instance, "Universal Speaker", 5, "speaker.png", 16);		
	}

	@Override
	public CraftPermission getPermission() {
		return new CraftPermission("jukebukkit.craft.speaker");
	}
}
