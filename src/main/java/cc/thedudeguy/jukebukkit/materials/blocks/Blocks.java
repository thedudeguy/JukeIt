package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class Blocks {

	public static RecordPlayer recordPlayer;
	public static String recordPlayerTextureName = "recordplayer.png";
	public static Texture recordPlayerTexture = new Texture(JukeBukkit.instance, recordPlayerTextureName, 256, 256, 16);;
	
	public Blocks() {
		
		//load any textures.
		SpoutManager.getFileManager().addToPreLoginCache(JukeBukkit.instance, JukeBukkit.instance.getResource(recordPlayerTextureName), recordPlayerTextureName);
		
		recordPlayer = new RecordPlayer();
		
	}
	
}
