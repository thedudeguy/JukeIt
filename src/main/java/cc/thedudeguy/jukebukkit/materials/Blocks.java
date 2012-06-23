package cc.thedudeguy.jukebukkit.materials;

import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.DiscBurner;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxBasic;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxLongRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxLowRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxMaxRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxMidRange;
import cc.thedudeguy.jukebukkit.materials.blocks.LabelerBlock;
import cc.thedudeguy.jukebukkit.materials.blocks.RecordPlayer;
import cc.thedudeguy.jukebukkit.materials.blocks.Speaker;
import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
import cc.thedudeguy.jukebukkit.model.Model;


public class Blocks {
	
	public static final Texture recordPlayerTexture = new Texture(JukeBukkit.instance, "recordplayer.png", 256, 256, 16);
	public static final Texture blocksTexture = new Texture(JukeBukkit.instance, "blocks_deprecated.png", 256, 256, 16);
	public static final Texture speakerwireTexture = new Texture(JukeBukkit.instance, "speakerwireblock.png", 256, 256, 16);
	public static final Texture labelerBlockTexture = new Texture(JukeBukkit.instance, "labelerblock.png", 64, 64, 64);
	
	public static Model labelerBlockModel;
	
	public static RecordPlayer recordPlayer;
	public static JukeboxBasic jukeboxBasic;
	public static JukeboxLowRange jukeboxLowRange;
	public static JukeboxMidRange jukeboxMidRange;
	public static JukeboxLongRange jukeboxLongRange;
	public static JukeboxMaxRange jukeboxMaxRange;
	
	public static LabelerBlock discLabeler;
	
	public static DiscBurner discBurner;
	
	public static Speaker speaker;
	
	public static SpeakerWireBlock speakerWireBlock;
	
	public Blocks() {
		
		labelerBlockModel = new Model(JukeBukkit.instance.getResource("models/labelerBlock.obj"), labelerBlockTexture);
		
		recordPlayer = new RecordPlayer();
		
		discBurner = new DiscBurner();
		
		discLabeler = new LabelerBlock();
		
		jukeboxBasic = new JukeboxBasic();
		jukeboxLowRange = new JukeboxLowRange();
		jukeboxMidRange = new JukeboxMidRange();
		jukeboxLongRange = new JukeboxLongRange();
		jukeboxMaxRange = new JukeboxMaxRange();
		
		speaker = new Speaker();
		
		speakerWireBlock = new SpeakerWireBlock();
	}
	
}
