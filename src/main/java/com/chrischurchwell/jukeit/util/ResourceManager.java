/**
 * This file is part of JukeIt
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
package com.chrischurchwell.jukeit.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.SpoutManager;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.sound.SoundEffect;
import com.chrischurchwell.jukeit.texture.TextureFile;


public class ResourceManager {
	
	public static void copyResources() {
		for (TextureFile texture : TextureFile.values()) {
			doCopy(texture.getFile(), "textures/default");
		}
		
		for (SoundEffect sound : SoundEffect.values()) {
			doCopy(sound.getSoundFileName(), "sounds");
		}
	}
	
	private static void doCopy(String filename, String pathInJar) {
		
		File dir = new File(JukeIt.getInstance().getDataFolder(), pathInJar);
		
		if (!dir.exists()) dir.mkdirs();
		if (!dir.canWrite()) Bukkit.getLogger().log(Level.WARNING, "The path "+ dir.getPath() +" is not writable");
		if (!dir.isDirectory()) Bukkit.getLogger().log(Level.WARNING, "The path "+ dir.getPath() +" is not a directory");
		
		String fileCopyRelPath = new File(pathInJar, filename).getPath();
		
		JukeIt.getInstance().saveResource(fileCopyRelPath, true);
	}
	
	public static void preLoginCache() {
		for (TextureFile texture : TextureFile.values()) {
			String pack = JukeIt.getInstance().getConfig().getString("texturePack", "default");
			File toCache = new File(JukeIt.getInstance().getDataFolder(), new File("textures/"+pack, texture.getFile()).getPath());
			if (toCache.exists()) {
				SpoutManager.getFileManager().addToPreLoginCache(JukeIt.getInstance(), toCache);
			} else {
				SpoutManager.getFileManager().addToPreLoginCache(JukeIt.getInstance(), new File(JukeIt.getInstance().getDataFolder(), new File("textures/default", texture.getFile()).getPath()));
			}
		}
		for (SoundEffect sound : SoundEffect.values()) {
			SpoutManager.getFileManager().addToPreLoginCache(JukeIt.getInstance(), new File(JukeIt.getInstance().getDataFolder(), new File("sounds", sound.getSoundFileName()).getPath()));
		}
	}
	
	public static void clearCache() {
		List<String> textures = new ArrayList<String>();
		for (TextureFile texture : TextureFile.values()) {
			textures.add(texture.getFile());
		}
		SpoutManager.getFileManager().removeFromCache(JukeIt.getInstance(), textures);
	}
	
	public static void addCache() {
		for (TextureFile texture : TextureFile.values()) {
			String pack = JukeIt.getInstance().getConfig().getString("texturePack", "default");
			File toCache = new File(JukeIt.getInstance().getDataFolder(), new File("textures/"+pack, texture.getFile()).getPath());
			if (toCache.exists()) {
				SpoutManager.getFileManager().addToCache(JukeIt.getInstance(), toCache);
			} else {
				SpoutManager.getFileManager().addToCache(JukeIt.getInstance(), new File(JukeIt.getInstance().getDataFolder(), new File("textures/default", texture.getFile()).getPath()));
			}
		}
		
		for (SoundEffect sound : SoundEffect.values()) {
			SpoutManager.getFileManager().addToCache(JukeIt.getInstance(), new File(JukeIt.getInstance().getDataFolder(), new File("sounds", sound.getSoundFileName()).getPath()));
		}
		
	}
	
	public static void resetCache() {
		clearCache();
		addCache();
	}
}
