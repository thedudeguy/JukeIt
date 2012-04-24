package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.RecordPlayerBlockDesigns;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.DiscBurnerDesign;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerDesign;


public class Blocks {
	
	public static final Texture recordPlayerTexture = new Texture(JukeBukkit.instance, "recordplayer.png", 256, 256, 16);
	public static final Texture blocksTexture = new Texture(JukeBukkit.instance, "blocks_deprecated.png", 256, 256, 16);
	public static final Texture speakerwireTexture = new Texture(JukeBukkit.instance, "speakerwireblock.png", 256, 256, 16);
	
	public static HashMap<String, RecordPlayer> subBlocks = new HashMap<String, RecordPlayer>();
	
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
	
	public static SpeakerWireBlock speakerWireBlock;
	
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
		
		speakerWireBlock = new SpeakerWireBlock(0);
		
		
		//init the subblocks
		List<RecordPlayerBlockDesigns> rpbd = JukeBukkit.instance.getDatabase().find(RecordPlayerBlockDesigns.class).findList();
		 
		 if (rpbd.isEmpty()) {
			 Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] No RecordPlayer Designs to load.");
		 } else {
			 int count = 0;
			 for (RecordPlayerBlockDesigns record : rpbd) {
				 RecordPlayerDesign rpDesign = new RecordPlayerDesign(record.getNeedle(), record.getDisc(), record.getIndicator());
				 Blocks.subBlocks.put(rpDesign.getDesignTypeId(), new RecordPlayerSubBlock(rpDesign));
				 count++;
	         }
			 Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Initialized "+ String.valueOf(count) +" RecordPlayer Designs.");
		 }
	}
	
}
