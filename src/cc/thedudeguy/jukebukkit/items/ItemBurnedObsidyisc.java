/**
 * Copyright (C) 2011  Chris Churchwell
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
 **/
package cc.thedudeguy.jukebukkit.items;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.CustomsManager;
import cc.thedudeguy.jukebukkit.DiscsManager;
import cc.thedudeguy.jukebukkit.JukeBukkit;

/**
 * Custom Item representing a Music Disc that has already been burned.
 * @author Chris Churchwell
 *
 */
public class ItemBurnedObsidyisc extends GenericCustomItem {
	public ItemBurnedObsidyisc(JukeBukkit plugin)
	{
		super(plugin, "Burned Obsidyisc", CustomsManager.TEXTURE_URL_WHITE_DISC_BURNED);
	}
	public ItemBurnedObsidyisc(JukeBukkit plugin, String key)
	{
		super(plugin, key, CustomsManager.TEXTURE_URL_WHITE_DISC_BURNED);
		this.setName("Burned Obsidyisc");
	}
	public ItemBurnedObsidyisc(JukeBukkit plugin, String key, String name)
	{
		super(plugin, key, CustomsManager.TEXTURE_URL_WHITE_DISC_BURNED);
		this.setName(name);
	}
	
	public ItemBurnedObsidyisc(JukeBukkit plugin, String key, String name, int color)
	{
		super(plugin, key);
		this.setName(name);
		
		if (color == DiscsManager.BLACK)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_BLACK_DISC_BURNED);
		} 
		else if (color == DiscsManager.RED) {
			this.setTexture(CustomsManager.TEXTURE_URL_RED_DISC_BURNED);
		} 
		else if (color == DiscsManager.GREEN)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_GREEN_DISC_BURNED);
		} 
		else if (color == DiscsManager.BROWN)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_BROWN_DISC_BURNED);
		} 
		else if (color == DiscsManager.BLUE)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_BLUE_DISC_BURNED);
		} 
		else if (color == DiscsManager.PURPLE)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_PURPLE_DISC_BURNED);
		} 
		else if (color == DiscsManager.CYAN)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_CYAN_DISC_BURNED);
		} 
		else if (color == DiscsManager.LIGHTGRAY)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_LIGHTGRAY_DISC_BURNED);
		} 
		else if (color == DiscsManager.GRAY)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_GRAY_DISC_BURNED);
		} 
		else if (color == DiscsManager.PINK)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_PINK_DISC_BURNED);
		} 
		else if (color == DiscsManager.LIME)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_LIME_DISC_BURNED);
		} 
		else if (color == DiscsManager.YELLOW)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_YELLOW_DISC_BURNED);
		} 
		else if (color == DiscsManager.LIGHTBLUE)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_LIGHTBLUE_DISC_BURNED);
		} 
		else if (color == DiscsManager.MAGENTA)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_MAGENTA_DISC_BURNED);
		} 
		else if (color == DiscsManager.ORANGE)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_ORANGE_DISC_BURNED);
		} 
		else {
			this.setTexture(CustomsManager.TEXTURE_URL_WHITE_DISC_BURNED);
		}
		
	}
	
	public ItemBurnedObsidyisc(JukeBukkit plugin, String key, int color)
	{
		super(plugin, key);
		this.setName("Burned Obsidyisc");
		
		if (color == DiscsManager.BLACK)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_BLACK_DISC_BURNED);
		} 
		else if (color == DiscsManager.RED) {
			this.setTexture(CustomsManager.TEXTURE_URL_RED_DISC_BURNED);
		} 
		else if (color == DiscsManager.GREEN)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_GREEN_DISC_BURNED);
		} 
		else if (color == DiscsManager.BROWN)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_BROWN_DISC_BURNED);
		} 
		else if (color == DiscsManager.BLUE)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_BLUE_DISC_BURNED);
		} 
		else if (color == DiscsManager.PURPLE)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_PURPLE_DISC_BURNED);
		} 
		else if (color == DiscsManager.CYAN)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_CYAN_DISC_BURNED);
		} 
		else if (color == DiscsManager.LIGHTGRAY)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_LIGHTGRAY_DISC_BURNED);
		} 
		else if (color == DiscsManager.GRAY)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_GRAY_DISC_BURNED);
		} 
		else if (color == DiscsManager.PINK)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_PINK_DISC_BURNED);
		} 
		else if (color == DiscsManager.LIME)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_LIME_DISC_BURNED);
		} 
		else if (color == DiscsManager.YELLOW)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_YELLOW_DISC_BURNED);
		} 
		else if (color == DiscsManager.LIGHTBLUE)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_LIGHTBLUE_DISC_BURNED);
		} 
		else if (color == DiscsManager.MAGENTA)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_MAGENTA_DISC_BURNED);
		} 
		else if (color == DiscsManager.ORANGE)
		{
			this.setTexture(CustomsManager.TEXTURE_URL_ORANGE_DISC_BURNED);
		} 
		else {
			this.setTexture(CustomsManager.TEXTURE_URL_WHITE_DISC_BURNED);
		}
	}
	
}
