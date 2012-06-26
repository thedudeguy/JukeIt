package cc.thedudeguy.jukebukkit.materials.blocks;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.MachineGUI;
import cc.thedudeguy.jukebukkit.materials.Blocks;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.permission.CraftPermission;

public class MachineBlock extends GenericCustomBlock implements CraftPermissible {

	public MachineBlock() {
		super(JukeBukkit.instance, "Disc Machine", Material.CAULDRON.getId());
		this.setBlockDesign(Blocks.machineBlockModel.getDesign(), 0);
		this.setBlockDesign(Blocks.machineTopBlockModel.getDesign(), 1);
		this.setBlockDesign(Blocks.machineTopPressedBlockModel.getDesign(), 2);
		this.setItemDrop(null);
	}

	@Override
	public CraftPermission getPermission() {
		return new CraftPermission("jukebukkit.craft.machine");
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		SpoutBlock block = (SpoutBlock) world.getBlockAt(x, y, z);
		
		//always start from the bottom oriented block.
		if (
			block.getCustomBlockData() > 0
			) {
			block = block.getRelative(BlockFace.DOWN);
		}
		
		//only access if the whole 2 peice combo is available
		if (
				!(block.getCustomBlock() instanceof MachineBlock) ||
				!(block.getRelative(BlockFace.UP).getCustomBlock() instanceof MachineBlock)
				) {
			return false;
		}
		
		//if the thing is in use, skip
		if (block.getRelative(BlockFace.UP).getCustomBlockData() > 1) {
				return false;
		}
		
		if (!player.hasPermission("jukebukkit.use.machine")) {
			player.sendMessage("You do not have permission to perform this action.");
			player.sendMessage("(jukebukkit.use.machine)");
			return false;
		}
		
		player.getMainScreen().attachPopupScreen(new MachineGUI(player, block));
		
		return true;
	}
}
