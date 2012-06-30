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
