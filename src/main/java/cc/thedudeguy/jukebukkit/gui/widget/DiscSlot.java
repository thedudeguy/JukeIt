package cc.thedudeguy.jukebukkit.gui.widget;

import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;

public class DiscSlot extends GenericSlot {

	public boolean onItemPut(org.bukkit.inventory.ItemStack item) {
		SpoutItemStack sItem = new SpoutItemStack(item);
		
		if (
				!(sItem.isCustomItem() && sItem.getMaterial() instanceof BlankDisc) &&
				!(sItem.isCustomItem() && sItem.getMaterial() instanceof BurnedDisc)
				) {
			return false;
		}
		
		return true;
	}
	
	public boolean onItemExchange(org.bukkit.inventory.ItemStack current, org.bukkit.inventory.ItemStack cursor) {
		
		SpoutItemStack sItem = new SpoutItemStack(cursor);
		if (
				!(sItem.isCustomItem() && sItem.getMaterial() instanceof BlankDisc) &&
				!(sItem.isCustomItem() && sItem.getMaterial() instanceof BurnedDisc)
				) {
			return false;
		}
		return true;
	}
	
}
