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

/**
 * Handles the loading and saving of Burned discs in order to provide persistance.
 * @author Chris Churchwell
 *
 */
public class DiscsManager {
	
	private JukeBukkit plugin;
	private Configuration discsConfig;
	
	public static final int WHITE = 0;
	public static final int RED = 1;
	
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
	        
	        String dkey = discsConfig.getString(key+".key");
	        String dtitle = discsConfig.getString(key+".title");
	        new ItemBurnedObsidyisc(plugin, dkey, dtitle);
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
	
	
	public int findDiscColor(CustomItem discItem)
	{
		
		if (discItem instanceof ItemBlankRedObsidyisc) {
			return RED;
		} else {
			return WHITE;
		}
	}
	
	public String getBurnedColorDiscUrl(int color)
	{
		if (color == RED) {
			return CustomsManager.TEXTURE_URL_RED_DISC_BURNED;
		}
		
		return CustomsManager.TEXTURE_URL_WHITE_DISC_BURNED;
	}
	
}
