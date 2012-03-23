package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscPurple extends BlankDisc {

	public BlankDiscPurple() {
		super("Blank Purple Obsidyisc");
		setColor(DiscColor.PURPLE);
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_purple.png";
	}

}
