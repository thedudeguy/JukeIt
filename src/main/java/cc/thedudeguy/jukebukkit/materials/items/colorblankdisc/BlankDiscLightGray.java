package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscLightGray extends BlankDisc {

	public BlankDiscLightGray() {
		super("Blank Light Gray Obsidyisc");
	}

	@Override
	public int getColor() {
		return DiscColor.LIGHTGRAY;
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_lightgray.png";
	}

}
