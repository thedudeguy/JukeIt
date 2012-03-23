package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscPink extends BlankDisc {

	public BlankDiscPink() {
		super("Blank Pink Obsidyisc");
	}

	@Override
	public int getColor() {
		return DiscColor.PINK;
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_pink.png";
	}

}
