package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscWhite extends BlankDisc {

	public BlankDiscWhite() {
		super("Blank White Obsidyisc");
	}

	@Override
	public int getColor() {
		return DiscColor.WHITE;
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_white.png";
	}

}
