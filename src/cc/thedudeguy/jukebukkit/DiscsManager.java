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
package cc.thedudeguy.jukebukkit;

import java.io.File;
import java.util.List;

import org.bukkit.util.config.Configuration;
import org.getspout.spoutapi.material.CustomItem;

import cc.thedudeguy.jukebukkit.items.ItemBurnedObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankBlackObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankBlueObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankBrownObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankCyanObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankGrayObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankGreenObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankLightBlueObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankLightGrayObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankLimeObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankMagentaObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankOrangeObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankPinkObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankPurpleObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankRedObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankYellowObsidyisc;

/**
 * Handles the loading and saving of Burned discs in order to provide persistance.
 * @author Chris Churchwell
 *
 */
public class DiscsManager {
	
	private JukeBukkit plugin;
	private Configuration discsConfig;
	
	public static final int BLACK = 15;
	public static final int RED = 1;
	public static final int GREEN = 2;
	public static final int BROWN = 3;
	public static final int BLUE = 4;
	public static final int PURPLE = 5;
	public static final int CYAN = 6;
	public static final int LIGHTGRAY = 7;
	public static final int GRAY = 8;
	public static final int PINK = 9;
	public static final int LIME = 10;
	public static final int YELLOW = 11;
	public static final int LIGHTBLUE = 12;
	public static final int MAGENTA = 13;
	public static final int ORANGE = 14;
	public static final int WHITE = 0;
	
	public DiscsManager(JukeBukkit plugin)
	{
		this.plugin = plugin;
		
		//load the config.
		discsConfig = new Configuration(new File(plugin.getDataFolder(), "discs.yml"));
		
		load();
	}
	
	/**
	 * Re Initializes the Burned Music discs, to fix a bug with spout where a custom item loses its custom item data and becomes unflagged as custom after renaming the item and reloading the server.
	 */
	public void reInitDiscs()
	{
		List<String> keys = discsConfig.getKeys();
		for(int i = 0, n = keys.size(); i < n; i++) {
	        String key = keys.get(i);
	        
	        String dkey = discsConfig.getString(key+".key", "");
	        String dtitle = discsConfig.getString(key+".title", "");
	        int dcolor = discsConfig.getInt(key+".color", 0);
	        new ItemBurnedObsidyisc(plugin, dkey, dtitle, dcolor);
	    }
	}
	
	public void load()
	{
		discsConfig.load();
	}
	
	public void save()
	{
		discsConfig.save();
	}
	
	public void add(int discId)
	{
		discsConfig.setProperty(String.valueOf(discId)+".url", "");
		discsConfig.setProperty(String.valueOf(discId)+".title", "");
	}
	public void setUrl(int discId, String url)
	{
		discsConfig.setProperty(String.valueOf(discId)+".url", url);
	}
	public void setTitle(int discId, String title)
	{
		discsConfig.setProperty(String.valueOf(discId)+".title", title);
	}
	public void setKey(int discId, String key)
	{
		discsConfig.setProperty(String.valueOf(discId)+".key", key);
	}
	public void setColor(int discId, int color)
	{
		discsConfig.setProperty(String.valueOf(discId)+".color", color);
	}
	
	public Boolean hasDiscId(int discId)
	{
		if (discsConfig.getKeys().contains(String.valueOf(discId))) 
		{
			return true;
		}
		return false;
	}
	
	public String getUrl(int discId)
	{
		return discsConfig.getString(String.valueOf(discId)+".url", "");
	}
	public String getTitle(int discId)
	{
		return discsConfig.getString(String.valueOf(discId)+".title", "");
	}
	public String getKey(int discId)
	{
		return discsConfig.getString(String.valueOf(discId)+".key", "");
	}
	public int getColor(int discId)
	{
		return discsConfig.getInt(String.valueOf(discId)+".color", 0);
	}
	
	public int findDiscColor(CustomItem discItem)
	{
		
		if (discItem instanceof ItemBlankBlackObsidyisc) {
			return BLACK;
		} else if (discItem instanceof ItemBlankRedObsidyisc) {
			return RED;
		} else if (discItem instanceof ItemBlankGreenObsidyisc) {
			return GREEN;
		} else if (discItem instanceof ItemBlankBrownObsidyisc) {
			return BROWN;
		} else if (discItem instanceof ItemBlankBlueObsidyisc) {
			return BLUE;
		} else if (discItem instanceof ItemBlankPurpleObsidyisc) {
			return PURPLE;
		} else if (discItem instanceof ItemBlankCyanObsidyisc) {
			return CYAN;
		} else if (discItem instanceof ItemBlankLightGrayObsidyisc) {
			return LIGHTGRAY;
		} else if (discItem instanceof ItemBlankGrayObsidyisc) {
			return GRAY;
		} else if (discItem instanceof ItemBlankPinkObsidyisc) {
			return PINK;
		} else if (discItem instanceof ItemBlankLimeObsidyisc) {
			return LIME;
		} else if (discItem instanceof ItemBlankYellowObsidyisc) {
			return YELLOW;
		} else if (discItem instanceof ItemBlankLightBlueObsidyisc) {
			return LIGHTBLUE;
		} else if (discItem instanceof ItemBlankMagentaObsidyisc) {
			return MAGENTA;
		} else if (discItem instanceof ItemBlankOrangeObsidyisc) {
			return ORANGE;
		} else {
			return WHITE;
		}
	}
	
}
