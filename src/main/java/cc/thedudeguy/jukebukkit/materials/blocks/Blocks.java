package cc.thedudeguy.jukebukkit.materials.blocks;

import java.io.File;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class Blocks {

	public static RecordPlayer recordPlayer;
	public static String recordPlayerTextureName = "recordplayer.png";
	public static Texture recordPlayerTexture = new Texture(JukeBukkit.instance, recordPlayerTextureName, 256, 256, 16);;
	
	public Blocks() {
		
		//extract the resources from the jar and into a file.
		JukeBukkit.instance.saveResource("textures/"+recordPlayerTextureName, false);
		
		File recordPlayerTextureFile = new File(JukeBukkit.instance.getDataFolder(), "textures/"+recordPlayerTextureName);
		
		//load any textures.
		SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.instance, recordPlayerTextureFile);
		
		recordPlayer = new RecordPlayer();
		
	}
	
}
