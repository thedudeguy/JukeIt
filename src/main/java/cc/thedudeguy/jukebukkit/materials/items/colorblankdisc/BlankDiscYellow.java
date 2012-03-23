package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscYellow extends BlankDisc {

	public BlankDiscYellow() {
		super("Blank Yellow Obsidyisc");
	}

	@Override
	public int getColor() {
		return DiscColor.YELLOW;
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_yellow.png";
	}

}
