package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.List;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.util.Recipies;

public class Speaker extends GenericCubeCustomBlock implements WireConnector, CraftPermissible {

	public Speaker() {
		super(JukeBukkit.instance, "Universal Speaker", 5, "speaker.png", 16);		
		setRecipe();
	}

	@Override
	public String getCraftPermission() {
		return "jukebukkit.craft.speaker";
	}
	
	public void setRecipe() {
		
		//stupid wood
		
		List<org.getspout.spoutapi.material.Material> woods = Recipies.getWoods();
		
		for (org.getspout.spoutapi.material.Material mat1 : woods) {
			for (org.getspout.spoutapi.material.Material mat2 : woods) {
				for (org.getspout.spoutapi.material.Material mat3 : woods) {
					for (org.getspout.spoutapi.material.Material mat4 : woods) {
						for (org.getspout.spoutapi.material.Material mat5 : woods) {
							for (org.getspout.spoutapi.material.Material mat6 : woods) {
								
								SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
								r.shape(Recipies.buildWoodRefString(mat1, mat2, mat3), "cnc",Recipies.buildWoodRefString(mat4, mat5, mat6));
								r.setIngredient('c', MaterialData.whiteWool);
								r.setIngredient('n', MaterialData.noteblock);
								
								for (org.getspout.spoutapi.material.Material umat : Recipies.getWoodUniqueMats(mat1, mat2, mat3, mat4, mat5, mat6) ) {
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
