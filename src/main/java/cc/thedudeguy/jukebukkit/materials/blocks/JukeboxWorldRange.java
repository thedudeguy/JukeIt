package cc.thedudeguy.jukebukkit.materials.blocks;

import org.bukkit.Location;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.Blocks;

public class JukeboxWorldRange extends JukeboxBlock {
	
	public JukeboxWorldRange()
	{
		super("World Range Jukebox");
	}
	
	@Override
	public GenericCubeBlockDesign getCustomBlockDesign() {
		
		return new GenericCubeBlockDesign(
				JukeBukkit.instance, 
				Blocks.blocksTexture, 
				new int[] { 0, 9, 9, 9, 9, 1 }
			);
	}
	
	@Override
	public String getPermission() {
		return "jukebukkit.player.World";
	}
	
	@Override
	public boolean canRedstoneActivate() {
		return true;
	}
	
	@Override
	public void playMusic(String url, Location location)
	
	{
		 url = JukeBukkit.finishIncompleteURL(url);
	SpoutManager.getSoundManager().playGlobalCustomMusic(JukeBukkit.instance, url, true);
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 0;
	}
}
