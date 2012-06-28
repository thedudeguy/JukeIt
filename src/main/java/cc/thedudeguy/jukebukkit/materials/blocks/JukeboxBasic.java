package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.Blocks;

public class JukeboxBasic extends JukeboxBlock {
	
	public JukeboxBasic()
	{
		super("Basic Jukebox");
	}
	
	@Override
	public int getRange()
	{
		return 7;
	}
	
	@Override
	public boolean canRedstoneActivate() {
		return true;
	}

	@Override
	public GenericCubeBlockDesign getCustomBlockDesign() {
		
		return new GenericCubeBlockDesign(
				JukeBukkit.instance, 
				Blocks.blocksTexture, 
				new int[] { 0, 0, 0, 0, 0, 1 }
			);
	}
	
	@Override
	public void setRecipe() {
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(this, 1) )
				.shape("jn")
				.setIngredient('j', MaterialData.jukebox)
				.setIngredient('n', MaterialData.noteblock)
				);
			
	}
}
