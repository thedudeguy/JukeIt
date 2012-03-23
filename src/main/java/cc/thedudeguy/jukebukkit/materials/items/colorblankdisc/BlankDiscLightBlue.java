package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscLightBlue extends BlankDisc {

	public BlankDiscLightBlue() {
		super("Blank Light Blue Obsidyisc");
	}

	@Override
	public int getColor() {
		return DiscColor.LIGHTBLUE;
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_lightblue.png";
	}

}
