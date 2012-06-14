package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.material.CustomItem;

import cc.thedudeguy.jukebukkit.materials.Items;

public enum RPNeedle {
	NONE		(0,		null),
	WOOD_FLINT	(32,	Items.woodflintNeedle),
	BLAZE_FLINT	(33,	Items.blazeflintNeedle);
	
	private final Integer textureId;
	private final CustomItem needleItemRef;
	
	RPNeedle(Integer textureId, CustomItem needleItemRef) {
		this.textureId = textureId;
		this.needleItemRef = needleItemRef;
	}
	
	public Integer textureId() {
		return this.textureId;
	}
	
	public int id() {
		return this.ordinal();
	}
	
	public CustomItem getItem() {
		return this.needleItemRef;
	}
	
	public static RPNeedle getById(int id) {
		for (RPNeedle d : RPNeedle.values()) {
			if (d.id() == id) return d;
		}
		
		//throw exception instead?
		return null;
	}
}
