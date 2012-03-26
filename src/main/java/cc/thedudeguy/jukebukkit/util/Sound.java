package cc.thedudeguy.jukebukkit.util;

import java.net.URL;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.getspout.spoutapi.SpoutManager;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class Sound {
	
	private String sound;
	private Location location;
	private int range = -1;
	private int volume = 100;
	private boolean notify = false;
	private URL url;
	
	public Sound(String sound) {
		setSound(sound);
	}
	
	public Sound(URL soundUrl) {
		setUrl(soundUrl);
	}
	
	public void play() {
		if ( location == null ) {
			SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, getFileName(), isNotify());
		} else {
			play(location);
		}
	}
	
	public void play(Location location) {
		
		Bukkit.getLogger().log(Level.INFO, "Playing Sound");
		Bukkit.getLogger().log(Level.INFO, getFileName());
		
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, getFileName(), isNotify(), location, getRange(), getVolume());
		
	}
	
	private String getFileName() {
		if (sound != null) {
			return sound;
		}
		return url.toString();
	}
	
	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
	
}
