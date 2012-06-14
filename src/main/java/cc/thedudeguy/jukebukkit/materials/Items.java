package cc.thedudeguy.jukebukkit.materials;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.database.LabelData;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscLabel;
import cc.thedudeguy.jukebukkit.materials.items.Needle;
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
import cc.thedudeguy.jukebukkit.materials.items.needles.BlazeNeedle;

public class Items {

	public static Needle needle;
	public static BlazeNeedle blazeNeedle;
	
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
	public static DiscLabel discLabel;
	
	public static SpeakerWire speakerWire;
	
	public Items() {
		
		//Init custom items.
		needle = new Needle();
		blazeNeedle = new BlazeNeedle();
		
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
		
		//TODO: Detect if any burned disc does NOT exist in the world anymore, and remove them from the DB
		
		//reference disc ONLY.
		burnedDisc = new BurnedDisc();
		discLabel = new DiscLabel();
		
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
		
		//initialize labels that have been created
		List<LabelData> labelDataList = JukeBukkit.instance.getDatabase().find(LabelData.class).findList();
		if (labelDataList.isEmpty()) {
			 Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] No created labels to load.");
		} else {
			int count = 0;
			for (LabelData labelData : labelDataList) {
				new DiscLabel(labelData);
				count++;
			}
			Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Initialized "+ String.valueOf(count) +" labels.");
		}
		
	}
}
