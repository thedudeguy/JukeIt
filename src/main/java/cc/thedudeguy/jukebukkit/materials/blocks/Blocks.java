package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.DiscBurnerDesign;


public class Blocks {
	
	public static final Texture RecordPlayerTexture = new Texture(JukeBukkit.instance, "recordplayer.png", 256, 256, 16);
	public static final Texture blocksTexture = new Texture(JukeBukkit.instance, "blocks_deprecated.png", 256, 256, 16);
	
	public static RecordPlayer recordPlayer;
	public static JukeboxBasic jukeboxBasic;
	public static JukeboxLowRange jukeboxLowRange;
	public static JukeboxMidRange jukeboxMidRange;
	public static JukeboxLongRange jukeboxLongRange;
	public static JukeboxMaxRange jukeboxMaxRange;
	
	public static DiscBurner discBurner;
	public static DiscBurner discBurnerSouth;
	public static DiscBurner discBurnerNorth;
	public static DiscBurner discBurnerEast;
	public static DiscBurner discBurnerWest;
	
	public static Speaker speaker;
	
	public Blocks() {
		
		recordPlayer = new RecordPlayer();
		
		discBurner = new DiscBurner();
		discBurnerSouth = new DiscBurner(DiscBurnerDesign.SOUTH);
		discBurnerNorth = new DiscBurner(DiscBurnerDesign.NORTH);
		discBurnerEast = new DiscBurner(DiscBurnerDesign.EAST);
		discBurnerWest = new DiscBurner(DiscBurnerDesign.WEST);
		
		jukeboxBasic = new JukeboxBasic();
		jukeboxLowRange = new JukeboxLowRange();
		jukeboxMidRange = new JukeboxMidRange();
		jukeboxLongRange = new JukeboxLongRange();
		jukeboxMaxRange = new JukeboxMaxRange();
		
		speaker = new Speaker();
	}
	
}
