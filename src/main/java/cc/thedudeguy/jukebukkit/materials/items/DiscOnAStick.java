package cc.thedudeguy.jukebukkit.materials.items;

import org.bukkit.Material;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.MachineRecipe;
import cc.thedudeguy.jukebukkit.materials.blocks.MachineRecipe.RecipeDiscType;

public class DiscOnAStick extends GenericCustomItem {

	/* maybe one day this will be a crappy weapon, but for now its just a useless item */
	public DiscOnAStick() {
		super(JukeBukkit.instance, "Disc on a Stick", "disconastick.png");
		MachineRecipe.addMachineRecipe(new MachineRecipe(RecipeDiscType.ANY, Material.STICK, new SpoutItemStack(this)));
		
	}
}
