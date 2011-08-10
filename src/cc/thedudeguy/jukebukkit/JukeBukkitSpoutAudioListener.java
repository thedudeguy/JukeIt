/**
 * Copyright (C) 2011  Chris Churchwell
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
 **/
package cc.thedudeguy.jukebukkit;

import org.getspout.spoutapi.event.sound.AudioListener;
import org.getspout.spoutapi.event.sound.BackgroundMusicEvent;

public class JukeBukkitSpoutAudioListener extends AudioListener {
	
	public static JukeBukkit plugin; 
	
	public JukeBukkitSpoutAudioListener(JukeBukkit instance) {
        plugin = instance;
	}
	
	public void onBackgroundMusicChange(BackgroundMusicEvent event)
	{
		plugin.log.info("[Jukebukkit] Recieved BackgroundMusicEvent");
	}
	
	/*
	public void onCustomEvent(Event event)
	{
		plugin.log.info("[Jukebukkit] Recieved Custom Event");
	}
	*/
	
}
