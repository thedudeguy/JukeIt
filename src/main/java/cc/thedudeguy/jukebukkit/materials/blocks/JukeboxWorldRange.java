package cc.thedudeguy.jukebukkit.materials.blocks;

import org.bukkit.Location;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

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
	public String getCraftPermission() {
		return "jukebukkit.craft.jukebox.world";
	}
	
	@Override
	public String getUsePermission() {
		return "jukebukkit.use.jukebox.world";
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

	@Override
	public void setRecipe() {
		SpoutManager.getMaterialManager().registerSpoutRecipe(
		new SpoutShapedRecipe( new SpoutItemStack(this, 1) )
		.shape("njn", "jdj", "njn")
		.setIngredient('j', Blocks.jukeboxMaxRange)
		.setIngredient('n', MaterialData.noteblock)
		.setIngredient('d', MaterialData.diamondBlock)
		);
	}
}
