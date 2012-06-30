package cc.thedudeguy.jukebukkit.materials.items;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

public class MachineBottom extends GenericCustomItem implements CraftPermissible {

	public MachineBottom() {
		super(JukeBukkit.instance, "Machine Bottom Half", TextureFile.ITEM_MACHINE_BOTTOM.getFile());
		this.setName("Machine Part");
		setRecipe();
	}
	
	@Override
	public String getCraftPermission() {
		return "jukebukkit.craft.machinepart";
	}
	
	private void setRecipe() {
		SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
		r.shape("sss", "sls","scs");
		r.setIngredient('s', MaterialData.cobblestone);
		r.setIngredient('l', MaterialData.lavaBucket);
		r.setIngredient('c', MaterialData.compass);
		
		SpoutManager.getMaterialManager().registerSpoutRecipe(r);
	}
}
