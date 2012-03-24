package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;


public class Blocks {
	
	public static final Texture RecordPlayerTexture = new Texture(JukeBukkit.instance, "recordplayer.png", 256, 256, 16);
	public static final Texture blocksTexture = new Texture(JukeBukkit.instance, "blocks_deprecated.png", 256, 256, 16);
	
	public static RecordPlayer recordPlayer;
	public static DiscBurner discBurner;
	public static JukeboxBasic jukeboxBasic;
	
	public Blocks() {
		
		recordPlayer = new RecordPlayer();
		discBurner = new DiscBurner();
		
		jukeboxBasic = new JukeboxBasic();
	}
	
}
