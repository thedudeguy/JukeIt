package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.RecordPlayerBlockDesigns;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerDesign;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireEastDown;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireEastSouth;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireEastUp;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireEastWest;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireNorthDown;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireNorthEast;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireNorthSouth;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireNorthUp;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireSouthDown;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireSouthUp;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireSouthWest;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireUpDown;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireWestDown;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireWestNorth;
import cc.thedudeguy.jukebukkit.materials.blocks.speakerwire.SpeakerWireWestUp;


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
	
	public static Speaker speaker;
	
	public static SpeakerWireBlock speakerWireBlockEastWest;
	public static SpeakerWireBlock speakerWireBlockNorthSouth;
	
	public static SpeakerWireBlock speakerWireBlockNorthEast;
	public static SpeakerWireBlock speakerWireBlockEastSouth;
	public static SpeakerWireBlock speakerWireBlockSouthWest;
	public static SpeakerWireBlock speakerWireBlockWestNorth;
	
	public static SpeakerWireBlock speakerWireBlockUpDown;
	
	public static SpeakerWireBlock speakerWireBlockEastUp;
	public static SpeakerWireBlock speakerWireBlockWestUp;
	public static SpeakerWireBlock speakerWireBlockNorthUp;
	public static SpeakerWireBlock speakerWireBlockSouthUp;
	
	public static SpeakerWireBlock speakerWireBlockEastDown;
	public static SpeakerWireBlock speakerWireBlockWestDown;
	public static SpeakerWireBlock speakerWireBlockNorthDown;
	public static SpeakerWireBlock speakerWireBlockSouthDown;
	
	public Blocks() {
		
		recordPlayer = new RecordPlayer();
		
		discBurner = new DiscBurner();
		
		jukeboxBasic = new JukeboxBasic();
		jukeboxLowRange = new JukeboxLowRange();
		jukeboxMidRange = new JukeboxMidRange();
		jukeboxLongRange = new JukeboxLongRange();
		jukeboxMaxRange = new JukeboxMaxRange();
		
		speaker = new Speaker();
		
		speakerWireBlockEastWest = new SpeakerWireEastWest();
		speakerWireBlockNorthSouth = new SpeakerWireNorthSouth();
		
		speakerWireBlockNorthEast = new SpeakerWireNorthEast();
		speakerWireBlockEastSouth = new SpeakerWireEastSouth();
		speakerWireBlockSouthWest = new SpeakerWireSouthWest();
		speakerWireBlockWestNorth = new SpeakerWireWestNorth();
		
		speakerWireBlockUpDown = new SpeakerWireUpDown();
		
		speakerWireBlockEastUp = new SpeakerWireEastUp();
		speakerWireBlockWestUp = new SpeakerWireWestUp();
		speakerWireBlockNorthUp = new SpeakerWireNorthUp();
		speakerWireBlockSouthUp = new SpeakerWireSouthUp();
		
		speakerWireBlockEastDown = new SpeakerWireEastDown();
		speakerWireBlockWestDown = new SpeakerWireWestDown();
		speakerWireBlockNorthDown = new SpeakerWireNorthDown();
		speakerWireBlockSouthDown = new SpeakerWireSouthDown();
		
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
