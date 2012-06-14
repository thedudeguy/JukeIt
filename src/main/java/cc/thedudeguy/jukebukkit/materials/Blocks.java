package cc.thedudeguy.jukebukkit.materials;

import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.DiscBurner;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxBasic;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxLongRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxLowRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxMaxRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxMidRange;
import cc.thedudeguy.jukebukkit.materials.blocks.RecordPlayer;
import cc.thedudeguy.jukebukkit.materials.blocks.Speaker;
import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
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
		
	}
	
}
