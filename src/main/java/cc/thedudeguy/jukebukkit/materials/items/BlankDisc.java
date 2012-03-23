package cc.thedudeguy.jukebukkit.materials.items;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public abstract class BlankDisc extends GenericCustomItem implements DiscColor {
	
	private String color;
	
	public BlankDisc(String name) {
		super(JukeBukkit.instance, name);
		
		this.setTexture(getTextureFileName());
	}

	public abstract String getTextureFileName();
	
	//ublic abstract int getColor();

}
