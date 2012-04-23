package cc.thedudeguy.jukebukkit.gui.widget;

import org.bukkit.block.Block;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

import cc.thedudeguy.jukebukkit.gui.BurnSelector;

public class ServerListButton extends GenericButton {

	Block block;
	
	public ServerListButton(Block block) {
		super("List");
		this.block = block;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().getMainScreen().getActivePopup().close();
		event.getPlayer().getMainScreen().attachPopupScreen(new BurnSelector(event.getPlayer(), block));
	}
	
}
