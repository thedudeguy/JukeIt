package cc.thedudeguy.jukebukkit.gui.widget;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

import cc.thedudeguy.jukebukkit.gui.MachineGUI;

public class LabelCloseButton  extends GenericButton{
	
	MachineGUI popup;
	
	public LabelCloseButton(MachineGUI p) {
		super("Cancel");
		this.popup = p;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		popup.cancelLabelWriter();
	}
	
}
