package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;


public class Blocks {
	
	public static final Texture RecordPlayerTexture = new Texture(JukeBukkit.instance, "recordplayer.png", 256, 256, 16);
	
	public static RecordPlayer recordPlayer;
	
	public Blocks() {
		
		recordPlayer = new RecordPlayer();
		
	}
	
}
