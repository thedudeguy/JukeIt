package cc.thedudeguy.jukebukkit.gui;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.gui.RenderPriority;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class LabelCreator extends GenericPopup {
	 
	public LabelCreator(Player player, Block block) {
		
		GenericSlot slot = new GenericSlot();
		slot.setX(175).setY(25);
		slot.setPriority(RenderPriority.Lowest);
		slot.setWidth(-1).setHeight(-1);
		
		this.setTransparent(true);
		this.attachWidgets(JukeBukkit.instance, slot);
		
	}
}
