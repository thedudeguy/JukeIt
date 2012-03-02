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
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
	private FileConfiguration  discsConfig;
	private File configFile;
	
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
		
		//load the file
		configFile = new File(plugin.getDataFolder(), "discs.yml");
		
		//load the config.
		discsConfig = YamlConfiguration.loadConfiguration(configFile);
	}
	
	/**
	 * Re Initializes the Burned Music discs, to fix a bug with spout where a custom item loses its custom item data and becomes unflagged as custom after renaming the item and reloading the server.
	 */
	public void reInitDiscs()
	{
		for(String key : discsConfig.getKeys(false)) {
		    // Get element
		    String dkey = discsConfig.getString(key+".key", "");
		    String dtitle = discsConfig.getString(key+".title", "");
		    int dcolor = discsConfig.getInt(key+".color", 0);
		    new ItemBurnedObsidyisc(plugin, dkey, dtitle, dcolor);
		}
	}
	
	
	public void save()
	{
		try {
			discsConfig.save(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block - error while saving to file
			e.printStackTrace();
		}
	}
	
	public void add(int discId)
	{
		discsConfig.set(String.valueOf(discId)+".url", "");
		discsConfig.set(String.valueOf(discId)+".title", "");
	}
	public void setUrl(int discId, String url)
	{
		discsConfig.set(String.valueOf(discId)+".url", url);
	}
	public void setTitle(int discId, String title)
	{
		discsConfig.set(String.valueOf(discId)+".title", title);
	}
	public void setKey(int discId, String key)
	{
		discsConfig.set(String.valueOf(discId)+".key", key);
	}
	public void setColor(int discId, int color)
	{
		discsConfig.set(String.valueOf(discId)+".color", color);
	}
	
	public boolean hasDiscId(int discId)
	{
		return (discsConfig.contains(String.valueOf(discId)));
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
