package cc.thedudeguy.jukebukkit.materials.items;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.permission.CraftPermission;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;

public abstract class BlankDisc extends GenericCustomItem implements DiscColor, CraftPermissible {

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
	
	@Override
	public CraftPermission getPermission() {
		return new CraftPermission("jukebukkit.craft.blankdisc");
	}
}