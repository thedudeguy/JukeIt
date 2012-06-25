package cc.thedudeguy.jukebukkit.gui.widget;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.GenericSlot;

import cc.thedudeguy.jukebukkit.util.Debug;

public class PlayerInventorySlot extends GenericSlot {

	int index;
	Player player;
	
	/**
	 * 
	 * @param index - the index of the player inventory item slot that this slot represents
	 */
	public PlayerInventorySlot(Player player, int index) {
		this.index = index;
		this.player = player;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean onItemExchange(org.bukkit.inventory.ItemStack current, org.bukkit.inventory.ItemStack cursor) {
		
		Debug.debug("onItemExchange: current ", current, " : cursor ", cursor);
		player.getInventory().setItem(index, cursor);
		
		return true;
	}
	
	public boolean onItemPut(org.bukkit.inventory.ItemStack item) {
		Debug.debug("onItemPut: ", item);
		player.getInventory().setItem(index, item);
		return true;
	}
	
	public boolean onItemTake(org.bukkit.inventory.ItemStack item) {
		Debug.debug("onItemTake: ", item);
		player.getInventory().setItem(index, null);
		return true;
	}
}
