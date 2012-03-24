package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class JukeboxLowRange extends JukeboxBlock {
	
	public JukeboxLowRange()
	{
		super("Low Range Jukebox");
	}
	
	@Override
	public GenericCubeBlockDesign getCustomBlockDesign() {
		
		return new GenericCubeBlockDesign(
				JukeBukkit.instance, 
				Blocks.blocksTexture, 
				new int[] { 0, 5, 5, 5, 5, 1 }
			);
	}
	
	@Override
	public int getRange()
	{
		return 15;
	}
	
	@Override
	public String getPermission() {
		return "jukebukkit.player.low";
	}
	
	@Override
	public boolean canRedstoneActivate() {
		return true;
	}
}
