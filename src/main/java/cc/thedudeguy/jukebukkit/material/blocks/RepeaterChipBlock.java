package cc.thedudeguy.jukebukkit.material.blocks;

import org.getspout.spoutapi.block.design.Axis;
import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

import com.chrischurchwell.meshit.Model;

public class RepeaterChipBlock extends GenericCustomBlock {

	public RepeaterChipBlock() {
		super(JukeBukkit.instance, "Repeater Chip", 36);
		
		Model model = new Model(JukeBukkit.instance.getResource("models/repeater.obj"));
		GenericBlockDesign design = model.getDesign();
		design.setTexture(JukeBukkit.instance, TextureFile.BLOCK_REPEATER_CHIP.getTexture());
		
		setBlockDesign(design, 0);
		setBlockDesign(design.rotate(90, Axis.Y), 1);
		setBlockDesign(design.rotate(180, Axis.Y), 2);
		setBlockDesign(design.rotate(270, Axis.Y), 3);
	}

}
