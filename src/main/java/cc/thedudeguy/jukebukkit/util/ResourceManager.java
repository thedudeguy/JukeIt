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
			doCopy(texture.getFile(), "textures");
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
		
		File dir = new File(JukeBukkit.instance.getDataFolder(), pathInJar);
		
		if (!dir.exists()) dir.mkdirs();
		if (!dir.canWrite()) Bukkit.getLogger().log(Level.WARNING, "The path "+ dir.getPath() +" is not writable");
		if (!dir.isDirectory()) Bukkit.getLogger().log(Level.WARNING, "The path "+ dir.getPath() +" is not a directory");
		
		String fileCopyRelPath = new File(pathInJar, filename).getPath();
		
		//File fileCopy = new File(JukeBukkit.instance.getDataFolder(), fileCopyRelPath);
		
		JukeBukkit.instance.saveResource(fileCopyRelPath, true);
	}
	
	public static void preLoginCache() {
		for (TextureFile texture : TextureFile.values()) {
			SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), new File("textures", texture.getFile()).getPath()));
		}
		for (SoundEffect sound : SoundEffect.values()) {
			SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), new File("sounds", sound.getSoundFileName()).getPath()));
		}
	}
	
	public static void clearCache() {
		List<String> textures = new ArrayList<String>();
		for (TextureFile texture : TextureFile.values()) {
			textures.add(texture.getFile());
		}
		SpoutManager.getFileManager().removeFromCache(JukeBukkit.instance, textures);
	}
	
	public static void addCache() {
		for (TextureFile texture : TextureFile.values()) {
			SpoutManager.getFileManager().addToCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), new File("textures", texture.getFile()).getPath()));
		}
		
		for (SoundEffect sound : SoundEffect.values()) {
			SpoutManager.getFileManager().addToCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), new File("sounds", sound.getSoundFileName()).getPath()));
		}
		
	}
	
	public static void cacheSingle(String folder, String file) {
		SpoutManager.getFileManager().addToCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), new File(folder, file).getPath()));
	}
	
	public static void resetCache() {
		clearCache();
		addCache();
	}
}
