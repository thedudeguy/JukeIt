/**
 * This file is part of JukeBukkit
 *
 * Copyright (C) 2011-2012  Chris Churchwell
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cc.thedudeguy.jukebukkit.material;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.material.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.material.items.DiscOnAStick;
import cc.thedudeguy.jukebukkit.material.items.MachineBottom;
import cc.thedudeguy.jukebukkit.material.items.MachineTop;
import cc.thedudeguy.jukebukkit.material.items.RepeaterChipItem;
import cc.thedudeguy.jukebukkit.material.items.SpeakerWire;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscBlack;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscBlue;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscBrown;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscCyan;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscGray;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscGreen;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscLightBlue;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscLightGray;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscLime;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscMagenta;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscOrange;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscPink;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscPurple;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscRed;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscWhite;
import cc.thedudeguy.jukebukkit.material.items.colorblankdisc.BlankDiscYellow;
import cc.thedudeguy.jukebukkit.material.items.needles.BlazeFlintNeedle;
import cc.thedudeguy.jukebukkit.material.items.needles.WoodFlintNeedle;

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
	
	public static RepeaterChipItem repeaterChipItem;
	
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
		
		repeaterChipItem = new RepeaterChipItem();
		
		//TODO: Detect if any burned disc does NOT exist in the world anymore, and remove them from the DB
		
		//reference disc ONLY.
		burnedDisc = new BurnedDisc();
		
		//initialize burned discs
		burnedDiscs = new HashMap<String, BurnedDisc>();
		List<DiscData> discDataList = JukeBukkit.getInstance().getDatabase().find(DiscData.class).findList();
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
