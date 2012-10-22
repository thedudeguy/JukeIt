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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.SpoutManager;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.sound.SoundEffect;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

public class ResourceManager {
	
	public static final List<String> music = Arrays.asList(
			"battle_jesus_vs_cyborg_hitlers.ogg"
			);
	
	public static final List<String> web = Arrays.asList(
			"index.html",
			"favicon.png",
			
			"css/bootstrap.css",
			"css/bootstrap.min.css",
			"css/bootstrap-responsive.css",
			"css/bootstrap-responsive.min.css",
			"css/uploadify.css",
			
			"img/glyphicons-halflings.png",
			"img/glyphicons-halflings-white.png",
			"img/uploadify-cancel.png",
			"img/jb32logo.png",
			
			"js/bootstrap.js",
			"js/bootstrap.min.js",
			"js/bootstrap-collapse.js",
			"js/bootstrap-tooltip.js",
			"js/bootstrap-popover.js",
			"js/jquery.js",
			"js/jquery.uploadify.min.js",
			"js/uploadify.swf"
			);
	
	public static void copyResources() {
		for (TextureFile texture : TextureFile.values()) {
			doCopy(texture.getFile(), "textures/default");
		}
		
		for (SoundEffect sound : SoundEffect.values()) {
			doCopy(sound.getSoundFileName(), "sounds");
		}
		
		for (String m : music) {
			doCopy(m, "music");
		}
		
		for (String w : web) {
			doCopy(w, "web");
		}
	}
	
	private static void doCopy(String filename, String pathInJar) {
		
		File dir = new File(JukeBukkit.getInstance().getDataFolder(), pathInJar);
		
		if (!dir.exists()) dir.mkdirs();
		if (!dir.canWrite()) Bukkit.getLogger().log(Level.WARNING, "The path "+ dir.getPath() +" is not writable");
		if (!dir.isDirectory()) Bukkit.getLogger().log(Level.WARNING, "The path "+ dir.getPath() +" is not a directory");
		
		String fileCopyRelPath = new File(pathInJar, filename).getPath();
		
		//File fileCopy = new File(JukeBukkit.instance.getDataFolder(), fileCopyRelPath);
		
		JukeBukkit.getInstance().saveResource(fileCopyRelPath, true);
	}
	
	public static void preLoginCache() {
		for (TextureFile texture : TextureFile.values()) {
			String pack = JukeBukkit.getInstance().getConfig().getString("texturePack", "default");
			File toCache = new File(JukeBukkit.getInstance().getDataFolder(), new File("textures/"+pack, texture.getFile()).getPath());
			if (toCache.exists()) {
				SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.getInstance(), toCache);
			} else {
				SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.getInstance(), new File(JukeBukkit.getInstance().getDataFolder(), new File("textures/default", texture.getFile()).getPath()));
			}
		}
		for (SoundEffect sound : SoundEffect.values()) {
			SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.getInstance(), new File(JukeBukkit.getInstance().getDataFolder(), new File("sounds", sound.getSoundFileName()).getPath()));
		}
	}
	
	public static void clearCache() {
		List<String> textures = new ArrayList<String>();
		for (TextureFile texture : TextureFile.values()) {
			textures.add(texture.getFile());
		}
		SpoutManager.getFileManager().removeFromCache(JukeBukkit.getInstance(), textures);
	}
	
	public static void addCache() {
		for (TextureFile texture : TextureFile.values()) {
			String pack = JukeBukkit.getInstance().getConfig().getString("texturePack", "default");
			File toCache = new File(JukeBukkit.getInstance().getDataFolder(), new File("textures/"+pack, texture.getFile()).getPath());
			if (toCache.exists()) {
				SpoutManager.getFileManager().addToCache(JukeBukkit.getInstance(), toCache);
			} else {
				SpoutManager.getFileManager().addToCache(JukeBukkit.getInstance(), new File(JukeBukkit.getInstance().getDataFolder(), new File("textures/default", texture.getFile()).getPath()));
			}
		}
		
		for (SoundEffect sound : SoundEffect.values()) {
			SpoutManager.getFileManager().addToCache(JukeBukkit.getInstance(), new File(JukeBukkit.getInstance().getDataFolder(), new File("sounds", sound.getSoundFileName()).getPath()));
		}
		
	}
	
	public static void resetCache() {
		clearCache();
		addCache();
	}
}
