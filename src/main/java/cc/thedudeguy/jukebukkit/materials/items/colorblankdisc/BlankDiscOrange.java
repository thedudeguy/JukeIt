package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscOrange extends BlankDisc {

	public BlankDiscOrange() {
		super("Blank Orange Obsidyisc");
		setColor(DiscColor.ORANGE);
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_orange.png";
	}

}
