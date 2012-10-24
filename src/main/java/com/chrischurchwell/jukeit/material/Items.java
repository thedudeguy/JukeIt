/**
 * This file is part of JukeIt
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
package com.chrischurchwell.jukeit.material;

import java.util.HashMap;
import java.util.List;

import javax.persistence.PersistenceException;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.database.DiscData;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;
import com.chrischurchwell.jukeit.material.items.DiscOnAStick;
import com.chrischurchwell.jukeit.material.items.MachineBottom;
import com.chrischurchwell.jukeit.material.items.MachineTop;
import com.chrischurchwell.jukeit.material.items.RepeaterChipItem;
import com.chrischurchwell.jukeit.material.items.SpeakerWire;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscBlack;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscBlue;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscBrown;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscCyan;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscGray;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscGreen;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscLightBlue;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscLightGray;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscLime;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscMagenta;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscOrange;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscPink;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscPurple;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscRed;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscWhite;
import com.chrischurchwell.jukeit.material.items.colorblankdisc.BlankDiscYellow;
import com.chrischurchwell.jukeit.material.items.needles.BlazeFlintNeedle;
import com.chrischurchwell.jukeit.material.items.needles.WoodFlintNeedle;


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
		List<DiscData> discDataList;
		
		try {
			discDataList = JukeIt.getInstance().getDatabase().find(DiscData.class).findList();
			
			if (discDataList.isEmpty()) {
				 JukeIt.info("No Burned Discs to load.");
			} else {
				int count = 0;
				for (DiscData discData : discDataList) {
					BurnedDisc d = new BurnedDisc(discData);
					burnedDiscs.put(d.getKey(), d);
					count++;
				}
				JukeIt.info("Initialized "+ String.valueOf(count) +" Burned Discs.");
			}
		} catch (PersistenceException e) {
			JukeIt.warn("Unable to load discs. (Either the database hasnt been created yet or it hasnt been loaded yet)");
		}
		
	}
}
