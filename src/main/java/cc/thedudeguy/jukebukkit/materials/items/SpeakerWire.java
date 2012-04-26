package cc.thedudeguy.jukebukkit.materials.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.events.SpeakerWirePlaceEvent;
import cc.thedudeguy.jukebukkit.util.Debug;

public class SpeakerWire extends GenericCustomItem {
	
	public SpeakerWire() {
		super(JukeBukkit.instance, "Speaker Wire");
		this.setTexture("speakerwire.png");
	}
	
	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, org.bukkit.block.BlockFace face) {
		
		if (block != null && !block.getType().equals(Material.AIR) && face.equals(BlockFace.UP)) {
			
			SpoutBlock placeBlock = block.getRelative(face);
			if (placeBlock == null || placeBlock.getType().equals(Material.AIR)) {
				
				SpeakerWirePlaceEvent event = new SpeakerWirePlaceEvent(player, placeBlock);
				Bukkit.getServer().getPluginManager().callEvent(event);
				
				
				Debug.debug(player, "Removing item from hand");
				//remove 1 from hand.
				ItemStack inHand = player.getItemInHand();
				if (inHand.getAmount()<2) {
					player.setItemInHand(new ItemStack(Material.AIR));
				} else {
					player.getItemInHand().setAmount(inHand.getAmount()-1);
				}
				
				
			}
			
		}
		return false;
	}
	
}
