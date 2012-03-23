package cc.thedudeguy.jukebukkit.materials.items;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class Needle extends GenericCustomItem {

	public Needle() {
		super(JukeBukkit.instance, "Simple Needle");
		this.setTexture("needle_stick-flint.png");
	}

}
