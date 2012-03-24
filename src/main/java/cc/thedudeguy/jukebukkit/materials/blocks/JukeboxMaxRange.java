package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class JukeboxMaxRange extends JukeboxBlock {
	
	public JukeboxMaxRange()
	{
		super("Max Range Jukebox");
	}
	
	@Override
	public GenericCubeBlockDesign getCustomBlockDesign() {
		
		return new GenericCubeBlockDesign(
				JukeBukkit.instance, 
				Blocks.blocksTexture, 
				new int[] { 0, 8, 8, 8, 8, 1 }
			);
	}
	
	@Override
	public String getPermission() {
		return "jukebukkit.player.max";
	}
	
	@Override
	public boolean canRedstoneActivate() {
		return true;
	}
	
	@Override
	public int getRange()
	{
		return 100;
	}
}
