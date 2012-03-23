package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscGray extends BlankDisc {

	public BlankDiscGray() {
		super("Blank Gray Obsidyisc");
		setColor(DiscColor.GRAY);
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_gray.png";
	}

}
