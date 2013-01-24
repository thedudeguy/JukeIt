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

import com.chrischurchwell.jukeit.material.items.BlankDisc;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;
import com.chrischurchwell.jukeit.material.items.DiscOnAStick;
import com.chrischurchwell.jukeit.material.items.MachineBottom;
import com.chrischurchwell.jukeit.material.items.MachineTop;
import com.chrischurchwell.jukeit.material.items.RepeaterChipItem;
import com.chrischurchwell.jukeit.material.items.SpeakerWire;
import com.chrischurchwell.jukeit.material.items.needles.BlazeFlintNeedle;
import com.chrischurchwell.jukeit.material.items.needles.WoodFlintNeedle;


public class Items {

	public static MachineTop machineTop;
	public static MachineBottom machineBottom;
	
	public static WoodFlintNeedle woodflintNeedle;
	public static BlazeFlintNeedle blazeflintNeedle;
	
	public static BlankDisc blankDiscBlack;
	public static BlankDisc blankDiscBlue;
	public static BlankDisc blankDiscBrown;
	public static BlankDisc blankDiscCyan;
	public static BlankDisc blankDiscGray;
	public static BlankDisc blankDiscGreen;
	public static BlankDisc blankDiscLightBlue;
	public static BlankDisc blankDiscLightGray;
	public static BlankDisc blankDiscLime;
	public static BlankDisc blankDiscMagenta;
	public static BlankDisc blankDiscOrange;
	public static BlankDisc blankDiscPink;
	public static BlankDisc blankDiscPurple;
	public static BlankDisc blankDiscRed;
	public static BlankDisc blankDiscWhite;
	public static BlankDisc blankDiscYellow;
	
	public static BurnedDisc burnedDiscBlack;
	public static BurnedDisc burnedDiscBlue;
	public static BurnedDisc burnedDiscBrown;
	public static BurnedDisc burnedDiscCyan;
	public static BurnedDisc burnedDiscGray;
	public static BurnedDisc burnedDiscGreen;
	public static BurnedDisc burnedDiscLightBlue;
	public static BurnedDisc burnedDiscLightGray;
	public static BurnedDisc burnedDiscLime;
	public static BurnedDisc burnedDiscMagenta;
	public static BurnedDisc burnedDiscOrange;
	public static BurnedDisc burnedDiscPink;
	public static BurnedDisc burnedDiscPurple;
	public static BurnedDisc burnedDiscRed;
	public static BurnedDisc burnedDiscWhite;
	public static BurnedDisc burnedDiscYellow;
	
	public static BurnedDisc burnedDisc;
	public static HashMap<String, BurnedDisc> burnedDiscs;
	
	public static SpeakerWire speakerWire;
	
	public static DiscOnAStick discOnAStick;
	
	public static RepeaterChipItem repeaterChipItem;
	
	public enum Disc {
		BLACK(		blankDiscBlack, burnedDiscBlack, DiscColor.BLACK),
		RED(		blankDiscRed, burnedDiscRed, DiscColor.RED),
		GREEN(		blankDiscGreen, burnedDiscGreen, DiscColor.GREEN),
		BROWN(		blankDiscBrown, burnedDiscBrown, DiscColor.BROWN),
		BLUE(		blankDiscBlue, burnedDiscBlue, DiscColor.BLUE),
		PURPLE(		blankDiscPurple, burnedDiscPurple, DiscColor.PURPLE),
		CYAN(		blankDiscCyan, burnedDiscCyan, DiscColor.CYAN),
		LIGHTGRAY(	blankDiscLightGray, burnedDiscLightGray, DiscColor.LIGHTGRAY),
		GRAY(		blankDiscGray, burnedDiscGray, DiscColor.GRAY),
		PINK(		blankDiscPink, burnedDiscPink, DiscColor.PINK),
		LIME(		blankDiscLime, burnedDiscLime, DiscColor.LIME),
		YELLOW(		blankDiscYellow, burnedDiscYellow, DiscColor.YELLOW),
		LIGHTBLUE(	blankDiscLightBlue, burnedDiscLightBlue, DiscColor.LIGHTBLUE),
		MAGENTA(	blankDiscMagenta, burnedDiscMagenta, DiscColor.MAGENTA),
		ORANGE(		blankDiscOrange, burnedDiscOrange, DiscColor.ORANGE),
		WHITE(		blankDiscWhite, burnedDiscWhite, DiscColor.WHITE);
		
		private BlankDisc blank;
		private BurnedDisc burned;
		private DiscColor color;
		
		private Disc(BlankDisc blank, BurnedDisc burned, DiscColor color) {
			this.blank = blank;
			this.burned = burned;
			this.color = color;
		}
		
		public BlankDisc blank() {
			return blank;
		}
		
		public BurnedDisc burned() {
			return burned;
		}
		
		public DiscColor color() {
			return color;
		}
		
		public static Disc getDiscByColor(DiscColor color) {
			for(Disc disc : Disc.values()) {
				if (disc.color.equals(color)) {
					return disc;
				}
			}
			return null;
		}
	}
	
	public Items() {
		
		machineTop = new MachineTop();
		machineBottom = new MachineBottom();
		
		//Init custom items.
		woodflintNeedle = new WoodFlintNeedle();
		blazeflintNeedle = new BlazeFlintNeedle();
		
		speakerWire = new SpeakerWire();
		
		blankDiscBlack = new BlankDisc("Blank Black Obsidyisc", DiscColor.BLACK);
		blankDiscBlue = new BlankDisc("Blank Blue Obsidyisc", DiscColor.BLUE);
		blankDiscBrown = new BlankDisc("Blank Brown Obsidyisc", DiscColor.BROWN);
		blankDiscCyan = new BlankDisc("Blank Cyan Obsidyisc", DiscColor.CYAN);
		blankDiscGray = new BlankDisc("Blank Gray Obsidyisc", DiscColor.GRAY);
		blankDiscGreen = new BlankDisc("Blank Green Obsidyisc", DiscColor.GREEN);
		blankDiscLightBlue = new BlankDisc("Blank Light Blue Obsidyisc", DiscColor.LIGHTBLUE);
		blankDiscLightGray = new BlankDisc("Blank Light Gray Obsidyisc", DiscColor.LIGHTGRAY);
		blankDiscLime = new BlankDisc("Blank Lime Obsidyisc", DiscColor.LIME);
		blankDiscMagenta = new BlankDisc("Blank Magenta Obsidyisc", DiscColor.MAGENTA);
		blankDiscOrange = new BlankDisc("Blank Orange Obsidyisc", DiscColor.ORANGE);
		blankDiscPink = new BlankDisc("Blank Pink Obsidyisc", DiscColor.PINK);
		blankDiscPurple = new BlankDisc("Blank Purple Obsidyisc", DiscColor.PURPLE);
		blankDiscRed = new BlankDisc("Blank Red Obsidyisc", DiscColor.RED);
		blankDiscWhite = new BlankDisc("Blank White Obsidyisc", DiscColor.WHITE);
		blankDiscYellow = new BlankDisc("Blank Yellow Obsidyisc", DiscColor.YELLOW);
		
		burnedDiscBlack = new BurnedDisc(DiscColor.BLACK);
		burnedDiscBlue = new BurnedDisc(DiscColor.BLUE);
		burnedDiscBrown = new BurnedDisc(DiscColor.BROWN);
		burnedDiscCyan = new BurnedDisc(DiscColor.CYAN);
		burnedDiscGray = new BurnedDisc(DiscColor.GRAY);
		burnedDiscGreen = new BurnedDisc(DiscColor.GREEN);
		burnedDiscLightBlue = new BurnedDisc(DiscColor.LIGHTBLUE);
		burnedDiscLightGray = new BurnedDisc(DiscColor.LIGHTGRAY);
		burnedDiscLime = new BurnedDisc(DiscColor.LIME);
		burnedDiscMagenta = new BurnedDisc(DiscColor.MAGENTA);
		burnedDiscOrange = new BurnedDisc(DiscColor.ORANGE);
		burnedDiscPink = new BurnedDisc(DiscColor.PINK);
		burnedDiscPurple = new BurnedDisc(DiscColor.PURPLE);
		burnedDiscRed = new BurnedDisc(DiscColor.RED);
		burnedDiscWhite = new BurnedDisc(DiscColor.WHITE);
		burnedDiscYellow = new BurnedDisc(DiscColor.YELLOW);
		
		discOnAStick = new DiscOnAStick();
		
		repeaterChipItem = new RepeaterChipItem();
		
	}
}
