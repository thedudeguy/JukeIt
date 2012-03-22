package cc.thedudeguy.jukebukkit.materials.items;

import java.io.File;

import org.getspout.spoutapi.SpoutManager;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class Items {

	public static Needle needle;
	
	public Items() {
		
		//copy resources into the data directory, this will allow for overrides.
		JukeBukkit.instance.saveResource("textures/needle_stick-flint.png", false);
		
		//precache the textures.
		SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.instance, new File(JukeBukkit.instance.getDataFolder(), "textures/needle_stick-flint.png"));
		
		//Init custom items.
		needle = new Needle();
		
	}
}
