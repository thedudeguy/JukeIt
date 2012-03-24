package cc.thedudeguy.jukebukkit.jukebox.custom;

import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxBlock;

public class JukeboxMaxRange extends JukeboxBlock {
	public JukeboxMaxRange(JukeBukkit plugin)
	{
		this(plugin, plugin.getCustomsManager().getCustomBlockTexture());
	}
	public JukeboxMaxRange(JukeBukkit plugin, Texture texture)
	{
		super(
			plugin, 
			"Max Range Jukebox",
			new GenericCubeBlockDesign(
				plugin, 
				texture, 
				new int[] { 0, 8, 8, 8, 8, 1 }
			),
			"jukebukkit.player.max"
		);
		//ints are { bottom, north, ?, ?, ?, top }
	}

	@Override
	public int getRange()
	{
		return 100;
	}
}
