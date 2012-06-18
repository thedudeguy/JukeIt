package cc.thedudeguy.jukebukkit.gui.widget;

import org.bukkit.block.Block;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

import cc.thedudeguy.jukebukkit.gui.RepoSelector;

public class RepoListButton extends GenericButton {

	Block block;
	
	public RepoListButton(Block block) {
		super("Repo");
		this.block = block;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().getMainScreen().getActivePopup().close();
		event.getPlayer().getMainScreen().attachPopupScreen(new RepoSelector(event.getPlayer(), block));
	}
	
}
