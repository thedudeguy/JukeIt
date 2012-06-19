package cc.thedudeguy.jukebukkit.materials.items.needles;

import java.io.File;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RPNeedle;

public class WoodFlintNeedle extends GenericCustomItem implements Needle {

	public WoodFlintNeedle() {
		super(JukeBukkit.instance, "Simple Needle");
		setTexture(new File(JukeBukkit.instance.getDataFolder(), new File("textures", "needle_stick-flint.png").getPath()));
	}

	@Override
	public RPNeedle getNeedleType() {
		return RPNeedle.WOOD_FLINT;
	}

}
