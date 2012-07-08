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
package cc.thedudeguy.jukebukkit.sound;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.getspout.spoutapi.SpoutManager;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class Sound {
	
	private SoundEffect sound;
	private Location location;
	private int range = -1;
	private int volume = 100;
	private boolean notify = false;
	
	public Sound(SoundEffect sound) {
		this.sound = sound;
	}
	
	public Sound(SoundEffect sound, Block block, int range) {
		this.sound = sound;
		this.range = range;
		setLocation(block);
	}
	
	public Sound(SoundEffect sound, Location location, int range) {
		this.sound = sound;
		this.range = range;
		this.location = location;
	}
	
	public void setLocation(Block block) {
		Location loc = block.getLocation();
		loc.setX(loc.getX() + 0.5);
		loc.setY(loc.getY() + 0.5);
		loc.setZ(loc.getZ() + 0.5);
		location = loc;
	}
	
	public void play() {
		if ( location == null || range <= 0) {
			SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, sound.getSoundFileName(), notify);
		} else {
			play(location);
		}
	}
	
	public void play(Location location) {
		
		//Bukkit.getLogger().log(Level.INFO, "Playing Sound");
		//Bukkit.getLogger().log(Level.INFO, getFileName());
		
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, sound.getSoundFileName(), notify, location, range, volume);
		
	}
	
}
