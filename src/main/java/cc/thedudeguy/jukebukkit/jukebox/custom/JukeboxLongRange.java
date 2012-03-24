package cc.thedudeguy.jukebukkit.jukebox.custom;

import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxBlock;

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
