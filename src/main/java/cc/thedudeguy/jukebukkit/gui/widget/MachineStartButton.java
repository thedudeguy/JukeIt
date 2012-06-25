package cc.thedudeguy.jukebukkit.gui.widget;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.events.MachineStartEvent;

public class MachineStartButton extends GenericButton{
	
	protected SpoutBlock block;
	
	protected GenericSlot primarySlot;
	protected GenericSlot additiveSlot;
	
	public MachineStartButton(SpoutPlayer player, SpoutBlock block, GenericSlot primary, GenericSlot secondary) {
		this("Start!", player, block, primary, secondary);
	}
	
	public MachineStartButton(String label, SpoutPlayer player, SpoutBlock block, GenericSlot primary, GenericSlot secondary) {
		super(label);
		this.block = block;
		this.primarySlot = primary;
		this.additiveSlot = secondary;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().sendMessage("Zomg you started it?");
		event.getPlayer().getMainScreen().getActivePopup().close();
		
		MachineStartEvent startEvent = new MachineStartEvent(block, primarySlot.getItem(), additiveSlot.getItem(), null);
		Bukkit.getServer().getPluginManager().callEvent(startEvent);
		
		
	}
}
