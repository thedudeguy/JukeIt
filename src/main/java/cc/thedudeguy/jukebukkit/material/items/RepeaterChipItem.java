package cc.thedudeguy.jukebukkit.material.items;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

public class RepeaterChipItem extends GenericCustomItem {
	
	public RepeaterChipItem() {
		super(JukeBukkit.instance, "Repeater Chip Item", TextureFile.ITEM_REPEATER_CHIP.getFile());
		this.setName("Repeater Chip");
	}
}
