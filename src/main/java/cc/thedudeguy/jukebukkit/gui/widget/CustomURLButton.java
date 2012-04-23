package cc.thedudeguy.jukebukkit.gui.widget;

import org.bukkit.block.Block;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

import cc.thedudeguy.jukebukkit.gui.CustomURLSelecter;

public class CustomURLButton extends GenericButton {
	
	Block block;
	
	public CustomURLButton(Block block) {
		super("URL");
		this.block = block;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().getMainScreen().getActivePopup().close();
		event.getPlayer().getMainScreen().attachPopupScreen(new CustomURLSelecter(event.getPlayer(), block));
	}
	
}
