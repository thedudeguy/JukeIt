package cc.thedudeguy.jukebukkit.material.blocks;

import org.bukkit.World;
import org.getspout.spoutapi.block.design.Axis;
import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.repeater.RepeaterChipGUI;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

import com.chrischurchwell.meshit.Model;

public class RepeaterChipBlock extends GenericCustomBlock {

	public RepeaterChipBlock() {
		super(JukeBukkit.getInstance(), "Repeater Chip", 36);
		
		Model model = new Model(JukeBukkit.getInstance().getResource("models/repeater.obj"));
		GenericBlockDesign design = model.getDesign();
		design.setTexture(JukeBukkit.getInstance(), TextureFile.BLOCK_REPEATER_CHIP.getTexture());
		
		setBlockDesign(design, 0);
		setBlockDesign(design.rotate(90, Axis.Y), 1);
		setBlockDesign(design.rotate(180, Axis.Y), 2);
		setBlockDesign(design.rotate(270, Axis.Y), 3);
	}
	
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		player.getMainScreen().attachPopupScreen(new RepeaterChipGUI());
		
		return true;
	}
}
