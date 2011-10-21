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
import cc.thedudeguy.jukebukkit.JukeBukkit;

/**
 * Custom Item to represend discs that have not been "burned"
 * @author Chris Churchwell
 *
 */
public class ItemBlankObsidyisc extends GenericCustomItem {
	
	public ItemBlankObsidyisc(JukeBukkit plugin)
	{
		super(plugin, "Blank Obsidyisc", CustomsManager.TEXTURE_URL_WHITE_DISC);
	}
	
	public ItemBlankObsidyisc(JukeBukkit plugin, String name, String texture_url)
	{
		super(plugin, name, texture_url);
	}
}
