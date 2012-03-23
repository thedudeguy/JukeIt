package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscGreen extends BlankDisc {

	public BlankDiscGreen() {
		super("Blank Green Obsidyisc");
	}

	@Override
	public int getColor() {
		return DiscColor.GREEN;
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_green.png";
	}

}
