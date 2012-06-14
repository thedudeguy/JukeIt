package cc.thedudeguy.jukebukkit.materials.items.needles;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RPNeedle;

public class BlazeFlintNeedle extends GenericCustomItem implements Needle {

	public BlazeFlintNeedle() {
		super(JukeBukkit.instance, "Blaze Needle");
		this.setTexture("needle_blaze-flint.png");
	}

	@Override
	public RPNeedle getNeedleType() {
		return RPNeedle.BLAZE_FLINT;
	}
	
}
