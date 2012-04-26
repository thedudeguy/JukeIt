package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.RecordPlayerBlockDesigns;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerDesign;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireEastWest;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireNorthSouth;


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
	
	public static DiscBurner discBurnerSouth;
	public static DiscBurner discBurnerNorth;
	public static DiscBurner discBurnerEast;
	public static DiscBurner discBurnerWest;
	
	public static Speaker speaker;
	
	public static SpeakerWireBlock speakerWireBlockEastWest;
	public static SpeakerWireBlock speakerWireBlockNorthSouth;
	
	public Blocks() {
		
		recordPlayer = new RecordPlayer();
		
		discBurnerSouth = new DiscBurner(DiscBurner.SOUTH);
		discBurnerNorth = new DiscBurner(DiscBurner.NORTH);
		discBurnerEast = new DiscBurner(DiscBurner.EAST);
		discBurnerWest = new DiscBurner(DiscBurner.WEST);
		
		jukeboxBasic = new JukeboxBasic();
		jukeboxLowRange = new JukeboxLowRange();
		jukeboxMidRange = new JukeboxMidRange();
		jukeboxLongRange = new JukeboxLongRange();
		jukeboxMaxRange = new JukeboxMaxRange();
		
		speaker = new Speaker();
		
		speakerWireBlockEastWest = new SpeakerWireEastWest();
		speakerWireBlockNorthSouth = new SpeakerWireNorthSouth();
		
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
