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

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.config.Configuration;
import org.getspout.spoutapi.SpoutManager;

import cc.thedudeguy.jukebukkit.items.ItemLabel;

/**
 * Handles Flatfile data storage pertaining to labels, in order to maintain persistance.
 * @author Chris Churchwell
 *
 */
public class LabelManager {

	JukeBukkit plugin;
	private Configuration labelConfig;
	
	public LabelManager(JukeBukkit plugin)
	{
		this.plugin = plugin;
		
		//load the config.
		labelConfig = new Configuration(new File(plugin.getDataFolder(), "labels.yml"));
		load();
		
		init();
	}
	
	/**
	 * Re Initializes the Custom items, to fix a bug with spout where a custom item loses its custom item 
	 * data and becomes unflagged as custom after renaming the item and reloading the server.
	 * Doing this permits persistance across restarts and reloads
	 */
	public void init()
	{
		List<String> keys = labelConfig.getKeys();
		for(int i = 0, n = keys.size(); i < n; i++) {
	        String key = keys.get(i);
	        
	        String label = labelConfig.getString(key);
	        new ItemLabel(plugin, label);
	    }
	}
	
	public void load()
	{
		labelConfig.load();
	}
	
	public void save()
	{
		labelConfig.save();
	}
	
	public ItemStack create(String label)
	{
		ItemLabel newLabel = new ItemLabel(plugin, label);
		int labelId = newLabel.getCustomId();
		
		set(labelId, label);
		
		ItemStack item = SpoutManager.getMaterialManager().getCustomItemStack(newLabel, 1);
		return item;
	}
	
	public void set(int labelId, String label)
	{
		labelConfig.setProperty(String.valueOf(labelId), label);
		save();
	}
	public String get(int labelId)
	{
		return labelConfig.getString(String.valueOf(labelId), "");
	}
	public void remove(int labelId)
	{
		labelConfig.removeProperty(String.valueOf(labelId));
		save();
	}
	public Boolean hasLabel(int labelId)
	{
		if (labelConfig.getKeys().contains(String.valueOf(labelId))) 
		{
			return true;
		}
		return false;
	}
}
