package cc.thedudeguy.jukebukkit.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.SpoutManager;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class ResourceManager {

	public static final List<String> textures = Arrays.asList(
			"blank_disc_black.png",
			"blank_disc_blue.png",
			"blank_disc_brown.png",
			"blank_disc_cyan.png",
			"blank_disc_gray.png",
			"blank_disc_green.png",
			"blank_disc_lightblue.png",
			"blank_disc_lightgray.png",
			"blank_disc_lime.png",
			"blank_disc_magenta.png",
			"blank_disc_orange.png",
			"blank_disc_pink.png",
			"blank_disc_purple.png",
			"blank_disc_red.png",
			"blank_disc_white.png",
			"blank_disc_yellow.png",
			"burned_disc_lightgray.png",
			"burned_disc_black.png",
			"burned_disc_blue.png",
			"burned_disc_brown.png",
			"burned_disc_cyan.png",
			"burned_disc_gray.png",
			"burned_disc_green.png",
			"burned_disc_lightblue.png",
			"burned_disc_lime.png",
			"burned_disc_magenta.png",
			"burned_disc_orange.png",
			"burned_disc_pink.png",
			"burned_disc_purple.png",
			"burned_disc_red.png",
			"burned_disc_white.png",
			"burned_disc_yellow.png",
			"needle_stick-flint.png",
			"recordplayer.png",
			"blocks_deprecated.png",
			"label.png",
			"speaker.png",
			"speakerwire.png",
			"speakerwireblock.png",
			"borderblue.png"
			);
	
	public static final List<String> sounds = Arrays.asList(
			"jb_error.wav",
			"jb_startup.wav",
			"disc_load.wav",
			"disc_eject.wav",
			"disc_start.wav",
			"disc_stop.wav",
			"needle_attach.wav",
			"needle_eject.wav"
			);
	
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
		for (String texture : textures) {
			doCopy(texture, "textures");
		}
		for (String sound : sounds) {
			doCopy(sound, "sounds");
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
		
		File fileCopy = new File(JukeBukkit.instance.getDataFolder(), fileCopyRelPath);
		
		if (!fileCopy.exists()) {
			
			JukeBukkit.instance.saveResource(fileCopyRelPath, true);
			//fileCopy.setLastModified(new Date().getTime());
		}
	}
	
	public static void preLoginCache() {
		for (String texture : textures) {
			SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), new File("textures", texture).getPath()));
		}
		for (String sound : sounds) {
			SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), new File("sounds", sound).getPath()));
		}
	}
	
	public static void clearCache() {
		SpoutManager.getFileManager().removeFromCache(JukeBukkit.instance, textures);
	}
	
	public static void addCache() {
		for (String texture : textures) {
			SpoutManager.getFileManager().addToCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), new File("textures", texture).getPath()));
		}
		for (String sound : sounds) {
			SpoutManager.getFileManager().addToCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), new File("sounds", sound).getPath()));
		}
	}
	
	public static void resetCache() {
		clearCache();
		addCache();
	}
}
