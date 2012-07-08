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
package cc.thedudeguy.jukebukkit.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.materials.items.DiscColor;

public class DiscImporter {
	
	public static boolean discsFileExists() {
		
		File discsFile = new File(JukeBukkit.instance.getDataFolder(), "discs.yml");
		return discsFile.exists();
	}
	
	public static void checkForOldDiscsImport() {
		
		if (!discsFileExists()) return;
		
		//if it exists, than lets import them.
		Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Old Discs.yml found, attempting to import...");
		
		Map<Integer, Integer> oldColorToNewColor = new HashMap<Integer, Integer>();
		oldColorToNewColor.put(15,DiscColor.BLACK);
		oldColorToNewColor.put(1, DiscColor.RED);
		oldColorToNewColor.put(2, DiscColor.GREEN);
		oldColorToNewColor.put(3, DiscColor.BROWN);
		oldColorToNewColor.put(4, DiscColor.BLUE);
		oldColorToNewColor.put(5, DiscColor.PURPLE);
		oldColorToNewColor.put(6, DiscColor.CYAN);
		oldColorToNewColor.put(7, DiscColor.LIGHTGRAY);
		oldColorToNewColor.put(8, DiscColor.GRAY);
		oldColorToNewColor.put(9, DiscColor.PINK);
		oldColorToNewColor.put(10,DiscColor.LIME);
		oldColorToNewColor.put(11,DiscColor.YELLOW);
		oldColorToNewColor.put(12,DiscColor.LIGHTBLUE);
		oldColorToNewColor.put(13,DiscColor.MAGENTA);
		oldColorToNewColor.put(14,DiscColor.ORANGE);
		oldColorToNewColor.put(0, DiscColor.WHITE);
		
		File discsFile = new File(JukeBukkit.instance.getDataFolder(), "discs.yml");
		FileConfiguration  discsConfig = YamlConfiguration.loadConfiguration(discsFile);
		
		int count = 0;
		
		//loop through the config.
		Set<String> keys = discsConfig.getKeys(false);
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
		    // Get element
		    String discId = it.next();
		    String key = discsConfig.getString(discId+".key", "");
		    String title = discsConfig.getString(key+".title", "");
		    String url = discsConfig.getString(key+".url", "");
		    int color = discsConfig.getInt(key+".color", 0);
		    int newColor;
		    if (oldColorToNewColor.containsKey(color)) {
		    	newColor = oldColorToNewColor.get(color);
		    } else {
		    	newColor = DiscColor.WHITE;
		    }
		    
		    addOldDisc(key, url, title, newColor);
		    count++;
		}
		
		Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] "+String.valueOf(count)+" Discs converted. Removing discs.yml.");
		
		//when its all said and done... delete that old file.
		discsFile.delete();
		
		return;
		
	}
	
	public static void addOldDisc(String key, String url, String title, int color) {
		DiscData discData = JukeBukkit.instance.getDatabase().find(DiscData.class)
				.where()
					.ieq("nameKey", key)
				.findUnique();
		if (discData == null) discData = new DiscData();
		
		discData.setNameKey(key);
		discData.setUrl(url);
		discData.setLabel(title);
		discData.setColor(color);
		JukeBukkit.instance.getDatabase().save(discData);
		
	}
	
}
