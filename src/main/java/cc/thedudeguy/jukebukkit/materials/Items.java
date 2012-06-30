package cc.thedudeguy.jukebukkit.materials;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscOnAStick;
import cc.thedudeguy.jukebukkit.materials.items.MachineBottom;
import cc.thedudeguy.jukebukkit.materials.items.MachineTop;
import cc.thedudeguy.jukebukkit.materials.items.SpeakerWire;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscBlack;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscBlue;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscBrown;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscCyan;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscGray;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscGreen;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscLightBlue;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscLightGray;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscLime;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscMagenta;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscOrange;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscPink;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscPurple;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscRed;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscWhite;
import cc.thedudeguy.jukebukkit.materials.items.colorblankdisc.BlankDiscYellow;
import cc.thedudeguy.jukebukkit.materials.items.needles.BlazeFlintNeedle;
import cc.thedudeguy.jukebukkit.materials.items.needles.WoodFlintNeedle;

public class Items {

	public static MachineTop machineTop;
	public static MachineBottom machineBottom;
	
	public static WoodFlintNeedle woodflintNeedle;
	public static BlazeFlintNeedle blazeflintNeedle;
	
	public static BlankDiscBlack blankDiscBlack;
	public static BlankDiscBlue blankDiscBlue;
	public static BlankDiscBrown blankDiscBrown;
	public static BlankDiscCyan blankDiscCyan;
	public static BlankDiscGray blankDiscGray;
	public static BlankDiscGreen blankDiscGreen;
	public static BlankDiscLightBlue blankDiscLightBlue;
	public static BlankDiscLightGray blankDiscLightGray;
	public static BlankDiscLime blankDiscLime;
	public static BlankDiscMagenta blankDiscMagenta;
	public static BlankDiscOrange blankDiscOrange;
	public static BlankDiscPink blankDiscPink;
	public static BlankDiscPurple blankDiscPurple;
	public static BlankDiscRed blankDiscRed;
	public static BlankDiscWhite blankDiscWhite;
	public static BlankDiscYellow blankDiscYellow;
	
	public static BurnedDisc burnedDisc;
	public static HashMap<String, BurnedDisc> burnedDiscs;
	
	public static SpeakerWire speakerWire;
	
	public static DiscOnAStick discOnAStick;
	public Items() {
		
		machineTop = new MachineTop();
		machineBottom = new MachineBottom();
		
		//Init custom items.
		woodflintNeedle = new WoodFlintNeedle();
		blazeflintNeedle = new BlazeFlintNeedle();
		
		speakerWire = new SpeakerWire();
		
		blankDiscBlack = new BlankDiscBlack();
		blankDiscBlue = new BlankDiscBlue();
		blankDiscBrown = new BlankDiscBrown();
		blankDiscCyan = new BlankDiscCyan();
		blankDiscGray = new BlankDiscGray();
		blankDiscGreen = new BlankDiscGreen();
		blankDiscLightBlue = new BlankDiscLightBlue();
		blankDiscLightGray = new BlankDiscLightGray();
		blankDiscLime = new BlankDiscLime();
		blankDiscMagenta = new BlankDiscMagenta();
		blankDiscOrange = new BlankDiscOrange();
		blankDiscPink = new BlankDiscPink();
		blankDiscPurple = new BlankDiscPurple();
		blankDiscRed = new BlankDiscRed();
		blankDiscWhite = new BlankDiscWhite();
		blankDiscYellow = new BlankDiscYellow();
		
		discOnAStick = new DiscOnAStick();
		
		//TODO: Detect if any burned disc does NOT exist in the world anymore, and remove them from the DB
		
		//reference disc ONLY.
		burnedDisc = new BurnedDisc();
		
		//initialize burned discs
		burnedDiscs = new HashMap<String, BurnedDisc>();
		List<DiscData> discDataList = JukeBukkit.instance.getDatabase().find(DiscData.class).findList();
		if (discDataList.isEmpty()) {
			 Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] No Burned Discs to load.");
		} else {
			int count = 0;
			for (DiscData discData : discDataList) {
				BurnedDisc d = new BurnedDisc(discData);
				burnedDiscs.put(d.getKey(), d);
				count++;
			}
			Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Initialized "+ String.valueOf(count) +" Burned Discs.");
		}
		
	}
}
