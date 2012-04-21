package cc.thedudeguy.jukebukkit.materials.blocks;

import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.SpeakerWireDesign;
import cc.thedudeguy.jukebukkit.materials.items.Items;

public class SpeakerWireBlock extends GenericCustomBlock {
	
	public SpeakerWireBlock(int degrees) {
		super(JukeBukkit.instance, "speakerwireblock_"+String.valueOf(degrees), 3);
		
		this.setBlockDesign(new SpeakerWireDesign(0));
		this.setName("Speaker Wire Block "+ String.valueOf(degrees) + " (DO NOT USE)");
		this.setItemDrop(new SpoutItemStack(Items.speakerWire));
	}
	
	public boolean onBlockInteract(org.bukkit.World world, int x, int y, int z, SpoutPlayer player) { 
		
		
		
		return true;
	}
	
	public void onNeighborBlockChange(org.bukkit.World world, int x, int y, int z, int changedId) {
		
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		if (
				(
						block.getData("wire.powered") == null ||
						(Integer)block.getData("wire.powered") == 0
				) &&
				block.isBlockPowered() == true
				) {
			block.setData("wire.powered", 1);
			//Debug.debug("RecordPlayer: Redstone Activated");
			
			if (block.getData("wire.rotation") == null) {
				block.setData("wire.rotation", 0);
			}
			
			int rotate = (Integer)block.getData("wire.rotation");
			rotate += 10;
			if (rotate >= 360) rotate = 0;
			JukeBukkit.instance.getServer().broadcastMessage("rotating to "+String.valueOf(rotate));
			block.setData("wire.rotation", rotate);
			
			this.setBlockDesign(new SpeakerWireDesign(rotate));
			
		} else if (
				block.getData("wire.powered") != null &&
				(Integer)block.getData("wire.powered") == 1 &&
				block.isBlockPowered() == true
				) {
			//Debug.debug("RecordPlayer: New Redstone Power source, but block is already powered.");
		
		} else if (
				block.getData("wire.powered") != null &&
				(Integer)block.getData("wire.powered") == 1 &&
				block.isBlockPowered() == false
				) {
			block.setData("wire.powered", 0);
			//Debug.debug("RecordPlayer: Lost Redstone Power.");
			
		} else {
			block.setData("wire.powered", 0);
			//Debug.debug("RecordPlayer: Not Powered, and not powering");
		}
		
	}
	
}
