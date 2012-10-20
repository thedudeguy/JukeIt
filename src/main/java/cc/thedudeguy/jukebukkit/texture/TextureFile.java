/**
 * This file is part of JukeBukkit
 *
 * Copyright (C) 2011-2012  Chris Churchwell
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cc.thedudeguy.jukebukkit.texture;

import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public enum TextureFile {
	/* items */
	BLANK_DISC_BLACK(		"blank_disc_black.png"),
	BLANK_DISC_BLUE(		"blank_disc_blue.png"),
	BLANK_DISC_BROWN(		"blank_disc_brown.png"),
	BLANK_DISC_CYAN(		"blank_disc_cyan.png"),
	BLANK_DISC_GRAY(		"blank_disc_gray.png"),
	BLANK_DISC_GREEN(		"blank_disc_green.png"),
	BLANK_DISC_LIGHT_BLUE(	"blank_disc_lightblue.png"),
	BLANK_DISC_LIGHT_GRAY(	"blank_disc_lightgray.png"),
	BLANK_DISC_LIME(		"blank_disc_lime.png"),
	BLANK_DISC_MAGENTA(		"blank_disc_magenta.png"),
	BLANK_DISC_ORANGE(		"blank_disc_orange.png"),
	BLANK_DISC_PINK(		"blank_disc_pink.png"),
	BLANK_DISC_PURPLE(		"blank_disc_purple.png"),
	BLANK_DISC_RED(			"blank_disc_red.png"),
	BLANK_DISC_WHITE(		"blank_disc_white.png"),
	BLANK_DISC_YELLOW(		"blank_disc_yellow.png"),
	
	BURNED_DISC_BLACK(		"burned_disc_black.png"),
	BURNED_DISC_BLUE(		"burned_disc_blue.png"),
	BURNED_DISC_BROWN(		"burned_disc_brown.png"),
	BURNED_DISC_CYAN(		"burned_disc_cyan.png"),
	BURNED_DISC_GRAY(		"burned_disc_gray.png"),
	BURNED_DISC_GREEN(		"burned_disc_green.png"),
	BURNED_DISC_LIGHT_BLUE(	"burned_disc_lightblue.png"),
	BURNED_DISC_LIGHT_GRAY(	"burned_disc_lightgray.png"),
	BURNED_DISC_LIME(		"burned_disc_lime.png"),
	BURNED_DISC_MAGENTA(	"burned_disc_magenta.png"),
	BURNED_DISC_ORANGE(		"burned_disc_orange.png"),
	BURNED_DISC_PINK(		"burned_disc_pink.png"),
	BURNED_DISC_PURPLE(		"burned_disc_purple.png"),
	BURNED_DISC_RED(		"burned_disc_red.png"),
	BURNED_DISC_WHITE(		"burned_disc_white.png"),
	BURNED_DISC_YELLOW(		"burned_disc_yellow.png"),
	
	NEEDLE_BLAZE_FLINT(		"needle_blaze-flint.png"),
	NEEDLE_STICK_FLINT(		"needle_stick-flint.png"),
	
	ITEM_DISC_ON_A_STICK(	"disconastick.png"),
	ITEM_SPEAKER_WIRE(		"speakerwire.png"),
	ITEM_MACHINE_TOP(		"machineitemtop.png"),
	ITEM_MACHINE_BOTTOM(	"machineitembot.png"),
	
	ITEM_REPEATER_CHIP(		"repeater_item.png"),
	
	/* gui */
	GUI_BG_BLUE(			"borderblue.png"),
	GUI_BG_MACHINE(			"machinegui.png"),
	GUI_BG_LABEL_WRITER(	"paper.png"),
	
	/* blocks */
	BLOCK_MACHINE(			"machineblock.png", 64, 64, 64),
	BLOCK_RECORD_PLAYER(	"recordplayer.png", 128, 128, 16),
	BLOCK_SPEAKER(			"speaker.png", 16, 16, 16),
	BLOCK_SPEAKER_WIRE(		"speakerwireblock.png", 32, 16, 16),
	BLOCK_BURNER(			"discburner.png", 64, 16, 16),
	
	BLOCK_JUKEBOX_BASIC(	"jukebox_basic.png", 32, 16, 16),
	BLOCK_JUKEBOX_LOW(		"jukebox_low.png", 64, 16, 16),
	BLOCK_JUKEBOX_MID(		"jukebox_mid.png", 64, 16, 16),
	BLOCK_JUKEBOX_LONG(		"jukebox_long.png", 64, 16, 16),
	BLOCK_JUKEBOX_MAX(		"jukebox_max.png", 64, 16, 16),
	BLOCK_JUKEBOX_WORLD(	"jukebox_world.png", 64, 16, 16),
	
	BLOCK_REPEATER_CHIP(	"repeater.png", 32, 32, 32);
	
	private String file;
	private int width;
	private int height;
	private int size;
	
	TextureFile(String file) {
		this(file, 16, 16, 16);
	}
	
	TextureFile(String file, int width, int height, int size) {
		this.file = file;
		this.width = width;
		this.height = height;
		this.size = size;
	}
	
	public String getFile() {
		return file;
	}
	
	public Texture getTexture() {
		
		// since the texture class actually uses the width/height/size parameters to calculate u-v coordinates
		// its safe to just use the default texture size, and any custom textures with higher resolutions
		// will still work fine assuming the custom texture was scaled properly. (in powers of 2 and maintains size ratio)
		return new Texture(JukeBukkit.instance, this.file, this.width, this.height, this.size);
		
	}
}
