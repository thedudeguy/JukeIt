package cc.thedudeguy.jukebukkit.materials.items.colorblankdisc;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;


public class BlankDiscLime extends BlankDisc {

	public BlankDiscLime() {
		super("Blank Lime Obsidyisc");
		setColor(DiscColor.LIME);
	}

	@Override
	public String getTextureFileName() {
		return "blank_disc_lime.png";
	}

}
