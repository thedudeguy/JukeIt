package cc.thedudeguy.jukebukkit.gui.recordplayer;

import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

import cc.thedudeguy.jukebukkit.material.blocks.RecordPlayer;

public class PlayButton extends GenericButton {
	
	SpoutBlock block;
	
	public PlayButton(SpoutBlock block) {
		super("Play");
		
		this.block = block;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		event.getPlayer().getMainScreen().getActivePopup().close();
		
		if (block.getCustomBlock() instanceof RecordPlayer) {
			((RecordPlayer)block.getCustomBlock()).onBlockClicked(block.getWorld(), block.getX(), block.getY(), block.getZ(), event.getPlayer());
		}
	}
}
