package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class JukeboxMidRange extends JukeboxBlock {
	
	public JukeboxMidRange()
	{
		super("Mid Range Jukebox");
	}
	
	@Override
	public GenericCubeBlockDesign getCustomBlockDesign() {
		
		return new GenericCubeBlockDesign(
				JukeBukkit.instance, 
				Blocks.blocksTexture, 
				new int[] { 0, 6, 6, 6, 6, 1 }
			);
	}
	
	@Override
	public String getPermission() {
		return "jukebukkit.player.mid";
	}
	
	@Override
	public boolean canRedstoneActivate() {
		return true;
	}
	
	@Override
	public int getRange()
	{
		return 30;
	}
}
