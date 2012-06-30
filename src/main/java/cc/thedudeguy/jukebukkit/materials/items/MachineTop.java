package cc.thedudeguy.jukebukkit.materials.items;

import java.util.List;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.texture.TextureFile;
import cc.thedudeguy.jukebukkit.util.Recipies;

public class MachineTop extends GenericCustomItem implements CraftPermissible {
	
	public MachineTop() {
		super(JukeBukkit.instance, "Machine Top Half", TextureFile.ITEM_MACHINE_TOP.getFile());
		this.setName("Machine Part");
		setRecipe();
	}
	
	@Override
	public String getCraftPermission() {
		return "jukebukkit.craft.machinepart";
	}
	
	private void setRecipe() {
		List<org.getspout.spoutapi.material.Material> woods = Recipies.getWoods();
		for (org.getspout.spoutapi.material.Material mat1 : woods) {
			for (org.getspout.spoutapi.material.Material mat2 : woods) {
				for (org.getspout.spoutapi.material.Material mat3 : woods) {
					SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
					r.shape(Recipies.buildWoodRefString(mat1, mat2, mat3), "ipi","i i" );
					r.setIngredient('i', MaterialData.ironIngot);
					r.setIngredient('p', MaterialData.pistonBase);
					
					for (org.getspout.spoutapi.material.Material umat : Recipies.getWoodUniqueMats(mat1, mat2, mat3) ) {
						r.setIngredient(Recipies.getWoodMatRefLetter(umat), umat);
					}
					
					SpoutManager.getMaterialManager().registerSpoutRecipe(r);
				}
			}
		}
	}
}
