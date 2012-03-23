package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscRed extends BlankDisc {

	public BlankDiscRed() {
		super("Blank Red Obsidyisc");
		setColor(DiscColor.RED);
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_red.png";
	}

}
