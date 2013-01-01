package com.chrischurchwell.jukeit.material;

import com.chrischurchwell.jukeit.texture.TextureFile;

public enum DiscColor {
	BLACK(		1,	TextureFile.BLANK_DISC_BLACK,		TextureFile.BURNED_DISC_BLACK,	30),
	RED(		2,	TextureFile.BLANK_DISC_RED,			TextureFile.BURNED_DISC_RED,	17),
	GREEN(		3,	TextureFile.BLANK_DISC_GREEN,		TextureFile.BURNED_DISC_GREEN,	25),
	BROWN(		4,	TextureFile.BLANK_DISC_BROWN,		TextureFile.BURNED_DISC_BROWN,	28),
	BLUE(		5,	TextureFile.BLANK_DISC_BLUE,		TextureFile.BURNED_DISC_BLUE,	29),
	PURPLE(		6,	TextureFile.BLANK_DISC_PURPLE,		TextureFile.BURNED_DISC_PURPLE,	18),
	CYAN(		7,	TextureFile.BLANK_DISC_CYAN,		TextureFile.BURNED_DISC_CYAN,	27),
	LIGHTGRAY(	8,	TextureFile.BLANK_DISC_LIGHT_GRAY,	TextureFile.BURNED_DISC_LIGHT_GRAY, 23),
	GRAY(		9,	TextureFile.BLANK_DISC_GRAY,		TextureFile.BURNED_DISC_GRAY,	26),
	PINK(		10,	TextureFile.BLANK_DISC_PINK,		TextureFile.BURNED_DISC_PINK,	19),
	LIME(		11,	TextureFile.BLANK_DISC_LIME,		TextureFile.BURNED_DISC_LIME,	22),
	YELLOW(		12,	TextureFile.BLANK_DISC_YELLOW,		TextureFile.BURNED_DISC_YELLOW,	31),
	LIGHTBLUE(	13,	TextureFile.BLANK_DISC_LIGHT_BLUE,	TextureFile.BURNED_DISC_LIGHT_BLUE, 24),
	MAGENTA(	14,	TextureFile.BLANK_DISC_MAGENTA,		TextureFile.BURNED_DISC_MAGENTA,21),
	ORANGE(		15,	TextureFile.BLANK_DISC_ORANGE,		TextureFile.BURNED_DISC_ORANGE,	20),
	WHITE(		16,	TextureFile.BLANK_DISC_WHITE,		TextureFile.BURNED_DISC_WHITE,	16),
	NONE(		0,	null,								null,							0);
	
	/**
	 * identifier is a unique hardcoded int to identify this color by int.
	 */
	private int identifier;
	
	/**
	 * The texture file representing the blank disc of this color
	 */
	private TextureFile blankTexture;
	
	/**
	 * The texture file representing the burned disc of this color.
	 */
	private TextureFile burnedTexture;
	
	/**
	 * rpTextureId reference to sprite id of the rp-disc colors on the recordplayer sprite texture sheet.
	 */
	private int rpTextureId;
	
	DiscColor(int identifier, TextureFile blankTexture, TextureFile burnedTexture, int rpTextureId) {
		this.identifier = identifier;
		this.blankTexture = blankTexture;
		this.burnedTexture = burnedTexture;
		this.rpTextureId = rpTextureId;
	}
	
	public int identifier() {
		return identifier;
	}
	
	public int rpTextureId() {
		return rpTextureId;
	}
	
	public TextureFile blankTexture() {
		return blankTexture;
	}
	
	public TextureFile burnedTexture() {
		return burnedTexture;
	}
	
	public static DiscColor getByIdentifier(int id) {
		for (DiscColor d : DiscColor.values()) {
			if (d.identifier == id) return d;
		}
		return DiscColor.NONE;
	}
}
