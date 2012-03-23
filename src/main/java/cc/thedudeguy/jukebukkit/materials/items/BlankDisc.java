package cc.thedudeguy.jukebukkit.materials.items;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public abstract class BlankDisc extends GenericCustomItem implements DiscColor {

	private int color = DiscColor.WHITE; //white is the default
	
	public BlankDisc(String name) {
		super(JukeBukkit.instance, name);
		
		setTexture(getTextureFileName());
		
	}

	public abstract String getTextureFileName();
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
}
