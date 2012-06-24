package cc.thedudeguy.jukebukkit.materials.blocks;

import org.bukkit.World;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.LabelCreator;
import cc.thedudeguy.jukebukkit.materials.Blocks;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.permission.CraftPermission;

public class LabelerBlock extends GenericCustomBlock implements CraftPermissible {

	public LabelerBlock() {
		super(JukeBukkit.instance, "Labeler", 118);
		this.setBlockDesign(Blocks.labelerBlockModel.getDesign(), 0);
		this.setBlockDesign(Blocks.labelerTopBlockModel.getDesign(), 1);
		this.setBlockDesign(Blocks.labelerTopPressedBlockModel.getDesign(), 2);
	}

	@Override
	public CraftPermission getPermission() {
		return new CraftPermission("jukebukkit.craft.labeler");
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		if (!player.hasPermission("jukebukkit.use.labeler")) {
			player.sendMessage("You do not have permission to perform this action.");
			player.sendMessage("(jukebukkit.use.labeler)");
			return false;
		}
		
		player.getMainScreen().attachPopupScreen(new LabelCreator(player, world.getBlockAt(x, y, z)));
		
		return true;
	}
}
