package cc.thedudeguy.jukebukkit.materials.items.needles;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class BlazeNeedle extends GenericCustomItem {

	public BlazeNeedle() {
		super(JukeBukkit.instance, "Blaze Needle");
		this.setTexture("needle_blaze-flint.png");
	}
	
}
