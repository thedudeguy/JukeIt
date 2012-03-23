package cc.thedudeguy.jukebukkit.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
			"blank_disc_lime.png",
			"blank_disc_magenta.png",
			"blank_disc_orange.png",
			"blank_disc_pink.png",
			"blank_disc_purple.png",
			"blank_disc_red.png",
			"blank_disc_white.png",
			"blank_disc_yellow.png",
			"blank_disc_lightgray.png",
			"needle_stick-flint.png",
			"recordplayer.png",
			"blocks_deprecated.png"
			);
	
	public static final List<String> sounds = Arrays.asList(
			"jb_error.wav",
			"jb_startup.wav"
			);
	
	public static void copyResources() {
		for (String texture : textures) {
			JukeBukkit.instance.saveResource(new File("textures", texture).getPath(), false);
		}
		for (String sound : sounds) {
			JukeBukkit.instance.saveResource(new File("sounds", sound).getPath(), false);
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
}
