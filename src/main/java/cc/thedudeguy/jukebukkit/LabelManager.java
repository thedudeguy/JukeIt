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
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import cc.thedudeguy.jukebukkit.items.ItemLabel;

/**
 * Handles Flatfile data storage pertaining to labels, in order to maintain persistance.
 * @author Chris Churchwell
 *
 */
public class LabelManager {

	JukeBukkit plugin;
	private FileConfiguration labelConfig;
	private File configFile;
	
	public LabelManager(JukeBukkit plugin)
	{
		this.plugin = plugin;
		
		//load the file
		configFile = new File(plugin.getDataFolder(), "labels.yml");
						
		//load the config.
		labelConfig = YamlConfiguration.loadConfiguration(configFile);
				
		init();
	}
	
	/**
	 * Re Initializes the Custom items, to fix a bug with spout where a custom item loses its custom item 
	 * data and becomes unflagged as custom after renaming the item and reloading the server.
	 * Doing this permits persistance across restarts and reloads
	 */
	public void init()
	{
		for(String key : labelConfig.getKeys(false)) {
		    // Get element
		    new ItemLabel(plugin, labelConfig.getString(key));
		}
	}
	
	public void save()
	{
		try {
			labelConfig.save(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block - error writing to file
			e.printStackTrace();
		}
	}
	
	public ItemStack create(String label)
	{
		ItemLabel newLabel = new ItemLabel(plugin, label);
		int labelId = newLabel.getCustomId();
		
		set(labelId, label);
		
		return new SpoutItemStack(newLabel, 1);
	}
	
	public void set(int labelId, String label)
	{
		labelConfig.set(String.valueOf(labelId), label);
		save();
	}
	public String get(int labelId)
	{
		return labelConfig.getString(String.valueOf(labelId), "");
	}
	public void remove(int labelId)
	{
		//possible bukkit bug with set to null?
		labelConfig.set(String.valueOf(labelId), null);
		save();
	}
	public Boolean hasLabel(int labelId)
	{
		return (labelConfig.contains(String.valueOf(labelId)));
	}
}
