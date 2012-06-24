package cc.thedudeguy.jukebukkit.gui.widget;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

import cc.thedudeguy.jukebukkit.events.LabelMachineStartEvent;

public class LabelMachineStartButton extends GenericButton{
	
	private SpoutBlock block;
	
	public LabelMachineStartButton(SpoutBlock block) {
		super("Start!");
		this.block = block;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().sendMessage("Zomg you started it?");
		event.getPlayer().getMainScreen().getActivePopup().close();
		
		LabelMachineStartEvent startEvent = new LabelMachineStartEvent(block);
		Bukkit.getServer().getPluginManager().callEvent(startEvent);
	}
}
