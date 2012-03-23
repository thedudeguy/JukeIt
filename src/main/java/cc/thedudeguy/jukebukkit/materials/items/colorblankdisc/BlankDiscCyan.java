package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscCyan extends BlankDisc {

	public BlankDiscCyan() {
		super("Blank Cyan Obsidyisc");
	}

	@Override
	public int getColor() {
		return DiscColor.CYAN;
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_cyan.png";
	}

}
