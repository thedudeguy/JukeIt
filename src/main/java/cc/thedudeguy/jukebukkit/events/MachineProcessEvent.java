package cc.thedudeguy.jukebukkit.events;

import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;


public class MachineProcessEvent extends MachineEvent {

	public MachineProcessEvent(SpoutBlock block, ItemStack primaryItem, ItemStack addItem, String label) {
		super(block, primaryItem, addItem, label);
		// TODO Auto-generated constructor stub
	}
	
}
