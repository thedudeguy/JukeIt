package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscBlue extends BlankDisc {

	public BlankDiscBlue() {
		super("Blank Blue Obsidyisc");
		setColor(DiscColor.BLUE);
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_blue.png";
	}

}
