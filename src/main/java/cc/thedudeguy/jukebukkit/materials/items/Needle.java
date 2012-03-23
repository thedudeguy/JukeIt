package cc.thedudeguy.jukebukkit.materials.items;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class Needle extends GenericCustomItem {

	public Needle() {
		super(JukeBukkit.instance, "Simple Needle", "needle_stick-flint.png");
		
		//TODO: Limti max item stack to 1.
	}

}
