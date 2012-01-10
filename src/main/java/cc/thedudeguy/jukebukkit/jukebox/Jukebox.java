package cc.thedudeguy.jukebukkit.jukebox;

import org.bukkit.Location;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface Jukebox {
	
	/**
	 * Plays music
	 * @param the url of the music that should play
	 * @param the location of the jukebox that is playing the music
	 * @param the player who initiated the jukebox
	 */
	void playMusic(String url, Location location, SpoutPlayer player);
	
	/**
	 * Plays music
	 * @param the url of the music that should play
	 * @param the location of the jukebox that is playing the music
	 */
	void playMusic(String url, Location location);
	
	/**
	 * stops the music
	 * @param the location of the jukebox needing to stop
	 */
	void stopMusic(Location location);
	
	/**
	 * returns true if the block is allowed to be activated with redstone
	 * @return
	 */
	boolean canRedstoneActivate();
}
