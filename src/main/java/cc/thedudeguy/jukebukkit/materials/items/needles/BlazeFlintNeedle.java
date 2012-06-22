package cc.thedudeguy.jukebukkit.materials.items.needles;

import java.io.File;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RPNeedle;
import cc.thedudeguy.jukebukkit.permission.CraftPermission;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;

public class BlazeFlintNeedle extends GenericCustomItem implements Needle, CraftPermissible {

	public BlazeFlintNeedle() {
		super(JukeBukkit.instance, "Blaze Needle");
		setTexture(new File(JukeBukkit.instance.getDataFolder(), new File("textures", "needle_blaze-flint.png").getPath()));
	}

	@Override
	public RPNeedle getNeedleType() {
		return RPNeedle.BLAZE_FLINT;
	}

	@Override
	public CraftPermission getPermission() {
		return new CraftPermission("jukebukkit.craft.needle");
	}
	
}
