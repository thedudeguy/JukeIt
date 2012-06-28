package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.List;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.Blocks;
import cc.thedudeguy.jukebukkit.util.Recipies;

public class JukeboxLongRange extends JukeboxBlock {
	
	public JukeboxLongRange()
	{
		super("Long Range Jukebox");
	}
	
	@Override
	public GenericCubeBlockDesign getCustomBlockDesign() {
		
		return new GenericCubeBlockDesign(
				JukeBukkit.instance, 
				Blocks.blocksTexture, 
				new int[] { 0, 7, 7, 7, 7, 1 }
			);
	}
	
	@Override
	public int getRange()
	{
		return 60;
	}
	
	@Override
	public boolean canRedstoneActivate() {
		return true;
	}

	@Override
	public void setRecipe() {
		List<org.getspout.spoutapi.material.Material> woods = Recipies.getWoods();

		for (org.getspout.spoutapi.material.Material mat1 : woods) {
			for (org.getspout.spoutapi.material.Material mat2 : woods) {
				for (org.getspout.spoutapi.material.Material mat3 : woods) {
					for (org.getspout.spoutapi.material.Material mat4 : woods) {
						
						SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
						r.shape(Recipies.buildWoodRefString(mat1, 'n', mat2), "njn",Recipies.buildWoodRefString(mat3, 'n', mat4));
						r.setIngredient('j', Blocks.jukeboxMidRange);
						r.setIngredient('n', MaterialData.noteblock);
						
						for (org.getspout.spoutapi.material.Material umat : Recipies.getWoodUniqueMats(mat1, mat2, mat3, mat4) ) {
							r.setIngredient(Recipies.getWoodMatRefLetter(umat), umat);
						}
						
						SpoutManager.getMaterialManager().registerSpoutRecipe(r);
					}
				}
			}
		}
	}
}
