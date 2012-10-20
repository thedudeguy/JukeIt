package cc.thedudeguy.jukebukkit.material.items;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.material.Blocks;
import cc.thedudeguy.jukebukkit.material.blocks.RecordPlayer;
import cc.thedudeguy.jukebukkit.texture.TextureFile;
import cc.thedudeguy.jukebukkit.util.Debug;

public class RepeaterChipItem extends GenericCustomItem {
	
	public RepeaterChipItem() {
		super(JukeBukkit.instance, "Repeater Chip Item", TextureFile.ITEM_REPEATER_CHIP.getFile());
		this.setName("Repeater Chip");
	}
	
	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, org.bukkit.block.BlockFace face) {
		
		if (
				block == null || 
				block.getType().equals(Material.AIR) ||
				face.equals(BlockFace.UP) ||
				face.equals(BlockFace.DOWN) ||
				!(block.getCustomBlock() instanceof RecordPlayer)
				) {
			return true;
		}
		
		SpoutBlock target = block.getRelative(face);
		
		if (target == null || target.getType().equals(Material.AIR)) {
			int rotationDesign = 0;
			
			if (face.equals(BlockFace.NORTH)) {
				rotationDesign = 3;
			} else if (face.equals(BlockFace.EAST)) {
				rotationDesign = 2;
			} else if (face.equals(BlockFace.SOUTH)) {
				rotationDesign = 1;
			} else if (face.equals(BlockFace.WEST)) {
				rotationDesign = 0;
			} else {
				rotationDesign = 0;
			}
			
			Debug.debug(player, face, rotationDesign);
			
			SpoutManager.getMaterialManager().overrideBlock(target, Blocks.repeaterChipBlock, (byte)rotationDesign);
			
			return false;
		}
		
		
		
		return true;
	}
}
