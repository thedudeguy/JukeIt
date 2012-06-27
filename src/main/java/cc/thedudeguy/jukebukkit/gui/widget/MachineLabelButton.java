package cc.thedudeguy.jukebukkit.gui.widget;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.events.MachineStartEvent;

public class MachineLabelButton extends MachineStartButton {

	GenericTextField labelInput;
	
	public MachineLabelButton(SpoutPlayer player, SpoutBlock block, GenericSlot primary, GenericSlot secondary, GenericTextField labelInput) {
		super("Label Disc!", player, block, primary, secondary);
		this.labelInput = labelInput;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().getMainScreen().getActivePopup().close();
		
		MachineStartEvent startEvent = new MachineStartEvent(block, primarySlot.getItem(), additiveSlot.getItem(), labelInput.getText() );
		Bukkit.getServer().getPluginManager().callEvent(startEvent);
		
		
	}

}
