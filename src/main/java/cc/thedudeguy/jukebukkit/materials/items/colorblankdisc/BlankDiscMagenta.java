package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscMagenta extends BlankDisc {

	public BlankDiscMagenta() {
		super("Blank Magenta Obsidyisc");
		setColor(DiscColor.MAGENTA);
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_magenta.png";
	}

}
