package cc.thedudeguy.jukebukkit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.materials.blocks.DiscBurner;
import cc.thedudeguy.jukebukkit.util.Debug;

public class DiscBurnerListener implements Listener {

	@EventHandler
	public void rotateBlock(BlockPlaceEvent event) {
		
		SpoutBlock block = (SpoutBlock)event.getBlock();
		
		if (block.getCustomBlock() == null) return;
		if (block.getCustomBlock() instanceof DiscBurner) {
			
			Debug.debug(event.getPlayer(), "Setting New Block based on player YAW");
			block.setCustomBlock(DiscBurner.getBlockByYaw(event.getPlayer().getLocation().getYaw()));
			SpoutManager.getMaterialManager().overrideBlock(block, DiscBurner.getBlockByYaw(event.getPlayer().getLocation().getYaw()));
			
		}
		
	}
	
}
