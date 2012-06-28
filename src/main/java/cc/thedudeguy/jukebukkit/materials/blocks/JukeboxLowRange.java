package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.List;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.Blocks;
import cc.thedudeguy.jukebukkit.util.Recipies;

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
						for (org.getspout.spoutapi.material.Material mat5 : woods) {
							for (org.getspout.spoutapi.material.Material mat6 : woods) {
								for (org.getspout.spoutapi.material.Material mat7 : woods) {
									for (org.getspout.spoutapi.material.Material mat8 : woods) {
										
										SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
										r.shape(
												Recipies.buildWoodRefString(mat1, mat2, mat3), 
												Recipies.buildWoodRefString(mat4, 'j', mat5), 
												Recipies.buildWoodRefString(mat6, mat7, mat8));
										r.setIngredient('j', Blocks.jukeboxBasic);
										
										for (org.getspout.spoutapi.material.Material umat : Recipies.getWoodUniqueMats(mat1, mat2, mat3, mat4, mat5, mat6, mat7, mat8) ) {
											r.setIngredient(Recipies.getWoodMatRefLetter(umat), umat);
										}
										
										SpoutManager.getMaterialManager().registerSpoutRecipe(r);
										
										
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
