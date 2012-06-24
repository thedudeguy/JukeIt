package cc.thedudeguy.jukebukkit.gui;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.gui.RenderPriority;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.widget.LabelMachineStartButton;

public class LabelCreator extends GenericPopup {
	 
	public LabelCreator(Player player, Block block) {
		
		//if the thing is in use, skip
		if (((SpoutBlock)block).getCustomBlockData() > 1) {
			return;
		}
		
		//make sure we are targeting the base block of the creator.
		if (((SpoutBlock)block).getCustomBlockData() > 0) {
			block = block.getRelative(BlockFace.DOWN);
		}
		
		GenericSlot slot = new GenericSlot();
		slot.setX(175).setY(25);
		slot.setPriority(RenderPriority.Lowest);
		slot.setWidth(-1).setHeight(-1);
		
		// Select button
		LabelMachineStartButton startButton = new LabelMachineStartButton((SpoutBlock) block);
		startButton.setX(95).setY(195);
		startButton.setWidth(60).setHeight(20);
		startButton.setPriority(RenderPriority.Lowest);
				
		this.setTransparent(true);
		this.attachWidgets(JukeBukkit.instance, slot, startButton);
		
	}
}
