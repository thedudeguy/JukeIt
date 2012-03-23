package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscBlack extends BlankDisc {

	public BlankDiscBlack() {
		super("Blank Black Obsidyisc");
		setColor(DiscColor.BLACK);
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_black.png";
	}

}
