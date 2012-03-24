package cc.thedudeguy.jukebukkit.materials.items;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.database.RecordPlayerBlockDesigns;
import cc.thedudeguy.jukebukkit.materials.blocks.RecordPlayer.RecordPlayerSubBlock;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerDesign;
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

public class Items {

	public static Needle needle;
	
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
	
	public Items() {
		
		//Init custom items.
		needle = new Needle();
		
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
		
		//initialize burned discs
		List<DiscData> discDataList = JukeBukkit.instance.getDatabase().find(DiscData.class).findList();
		if (discDataList.isEmpty()) {
			 Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] No Burned Discs to load.");
		} else {
			int count = 0;
			for (DiscData discData : discDataList) {
				new BurnedDisc(discData);
				count++;
			}
			Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Initialized "+ String.valueOf(count) +" Burned Discs.");
		}
		
	}
}
