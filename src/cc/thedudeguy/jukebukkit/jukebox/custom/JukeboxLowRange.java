package cc.thedudeguy.jukebukkit.jukebox.custom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.CustomsManager;
import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.jukebox.JukeboxBlock;

public class JukeboxLowRange extends JukeboxBlock {
	
	public final String name = "Low Range Jukebox";
	private final int range = 11;
	
	private JukeBukkit plugin;
	
	public JukeboxLowRange(JukeBukkit plugin)
	{
		super(
			plugin, 
			"Low Range Jukebox",
			new GenericCubeBlockDesign(
				plugin, 
				plugin.getCustomsManager().getCustomBlockTexture(), 
				new int[] { 0, 5, 5, 5, 5, 1 }
			)
		);
		//ints are { bottom, north, ?, ?, ?, top }
		this.plugin = plugin;
	}
	public JukeboxLowRange(JukeBukkit plugin, Texture texture)
	{
		super(
			plugin, 
			"Low Range Jukebox",
			new GenericCubeBlockDesign(
				plugin, 
				texture, 
				new int[] { 0, 5, 5, 5, 5, 1 }
			)
		);
		//ints are { bottom, north, ?, ?, ?, top }
		this.plugin = plugin;
	}
	
	@Override
	public void playMusic(String url, Location location, SpoutPlayer player) {
		/** get players in radius of the jukebox and start it for only those players **/
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)range) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					try {
						SpoutManager.getSoundManager().playCustomMusic(plugin, sp, url, true, location, range);
					} catch (Exception e) {
						//the disc has an error.
						player.sendMessage(e.getMessage());
						SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
					}
				}
			}
		}
	}
	@Override
	public void playMusic(String url, Location location) {
		/** get players in radius of the jukebox and start it for only those players **/
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)range) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					try {
						SpoutManager.getSoundManager().playCustomMusic(plugin, sp, url, true, location, range);
					} catch (Exception e) {
						//the disc has an error.
						//player.sendMessage(e.getMessage());
						SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
					}
				}
			}
		}
	}
	@Override
	public void stopMusic(Location location) {
		/** get players in radius of the jukebox and start it for only those players **/
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)range) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					SpoutManager.getSoundManager().stopMusic(sp);
				}
			}
		}
	}
	@Override
	public boolean canRedstoneActivate() {
		return true;
	}

}
