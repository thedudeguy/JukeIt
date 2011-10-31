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

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Handles the Flatfile storage, loading, and saving of persistant data representing jukeboxes placed in the world
 * and whether or not they have discs loaded in them.
 * @author Chris Churchwell
 *
 */
public class JukeBoxManager {
	
	//private JukeBukkit plugin;
	private FileConfiguration jukeConfig;
	private File configFile;
	
	public JukeBoxManager(JukeBukkit plugin)
	{
		//this.plugin = plugin;
		
		//load the file
		configFile = new File(plugin.getDataFolder(), "jukeboxes.yml");
				
		//load the config.
		jukeConfig = YamlConfiguration.loadConfiguration(configFile);
	}
	
	public void save()
	{
		try {
			jukeConfig.save(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block - error writing to file
			e.printStackTrace();
		}
	}
	
	public String createLocationKey(Location location)
	{
		return String.valueOf( location.toString().hashCode() );
	}
	
	public Boolean hasDisc(String locationKey)
	{
		if (jukeConfig.contains(locationKey))
		{
			if (getDisc(locationKey) == 0) return false;
			return true;
		}
		return false;
	}
	
	public Boolean hasDisc(Location location)
	{
		String locationKey = createLocationKey(location);
		if (jukeConfig.contains(locationKey))
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
		jukeConfig.set(locationKey, discId);
		save();
	}
	
	public void removeDisc(String locationKey)
	{
		//set to null could still be bugged in 1337? it was fixed at some point, could have been after 1337.
		jukeConfig.set(locationKey, null);
		save();
	}
	
}
