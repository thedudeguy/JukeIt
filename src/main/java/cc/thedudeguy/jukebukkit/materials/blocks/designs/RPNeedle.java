package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import org.getspout.spoutapi.material.CustomItem;

import cc.thedudeguy.jukebukkit.materials.Items;

public enum RPNeedle {
	NONE		(0,		0, null),
	WOOD_FLINT	(32,	0, Items.woodflintNeedle),
	BLAZE_FLINT	(33,	.30, Items.blazeflintNeedle);
	
	private final int textureId;
	private final double rangeModifier;
	private final CustomItem needleItemRef;
	
	RPNeedle(int textureId, double rangeModifier, CustomItem needleItemRef) {
		this.textureId = textureId;
		this.needleItemRef = needleItemRef;
		this.rangeModifier = rangeModifier;
	}
	
	public int textureId() {
		return this.textureId;
	}
	
	public int id() {
		return this.ordinal();
	}
	
	public double rangeModifier() {
		return this.rangeModifier;
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
