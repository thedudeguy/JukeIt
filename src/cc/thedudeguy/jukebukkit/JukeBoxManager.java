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

import org.bukkit.Location;
import org.bukkit.util.config.Configuration;

/**
 * Handles the Flatfile storage, loading, and saving of persistant data representing jukeboxes placed in the world
 * and whether or not they have discs loaded in them.
 * @author Chris Churchwell
 *
 */
public class JukeBoxManager {
	
	private JukeBukkit plugin;
	private Configuration jukeConfig;
	
	public JukeBoxManager(JukeBukkit plugin)
	{
		this.plugin = plugin;
		jukeConfig = new Configuration(new File(plugin.getDataFolder(), "jukeboxes.yml"));
		load();
	}
	
	public void load()
	{
		jukeConfig.load();
	}
	
	public void save()
	{
		jukeConfig.save();
	}
	
	public String createLocationKey(Location location)
	{
		return String.valueOf( location.toString().hashCode() );
	}
	
	public Boolean hasDisc(String locationKey)
	{
		if (jukeConfig.getKeys().contains(locationKey))
		{
			if (getDisc(locationKey) == 0) return false;
			return true;
		}
		return false;
	}
	
	public int getDisc(String locationKey)
	{
		return jukeConfig.getInt(locationKey, 0);
	}
	
	public void insertDisc(String locationKey, int discId)
	{
		jukeConfig.setProperty(locationKey, discId);
		save();
	}
	
	public void removeDisc(String locationKey)
	{
		jukeConfig.removeProperty(locationKey);
		save();
	}
	
}
