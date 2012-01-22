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

public class JukeboxLongRange extends JukeboxBlock {
	public JukeboxLongRange(JukeBukkit plugin)
	{
		this(plugin, plugin.getCustomsManager().getCustomBlockTexture());
	}
	public JukeboxLongRange(JukeBukkit plugin, Texture texture)
	{
		super(
			plugin, 
			"Long Range Jukebox",
			new GenericCubeBlockDesign(
				plugin, 
				texture, 
				new int[] { 0, 7, 7, 7, 7, 1 }
			),
			"jukebukkit.player.long"
		);
		//ints are { bottom, north, ?, ?, ?, top }
	}
	
	@Override
	public int getRange()
	{
		return 60;
	}
}
