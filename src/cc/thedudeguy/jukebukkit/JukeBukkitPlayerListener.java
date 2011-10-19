/**
 * Copyright (C) 2011  Chris Churchwell
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package cc.thedudeguy.jukebukkit;

import org.bukkit.Material;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 * Player Listerner. Handles writing of labels and opening the label maker gui.
 * @author Chris Churchwell
 *
 */
public class JukeBukkitPlayerListener extends PlayerListener {
	
	public static JukeBukkit plugin; 
	
	public JukeBukkitPlayerListener(JukeBukkit instance) {
        plugin = instance;
	}
	
	public class CreateButton extends GenericButton
	{
		private GenericTextField input;
		private GenericPopup popup;
		private JukeBukkit plugin;
		
		public CreateButton(JukeBukkit plugin, GenericPopup assocPopup, GenericTextField assocInputField)
		{
			super();
			setText("Create Label");
			input = assocInputField;
			popup = assocPopup;
			this.plugin = plugin;
			
			//plugin.log.info("Burn Disc Button Initialized");
		}
		public void onButtonClick(ButtonClickEvent event) 
		{
			//give the label to the player.
			if (!input.getText().equals(""))
			{
				ItemStack newLabel = plugin.getLabelManager().create(input.getText());
				event.getPlayer().getInventory().addItem(newLabel);
			}
			popup.close();
	        
	    }
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		SpoutPlayer player = SpoutManager.getPlayer(event.getPlayer());
		
		if (player.isSpoutCraftEnabled())
		{
			ItemStack inHand = event.getPlayer().getItemInHand();
			
			if (
					event.useItemInHand() != Result.DENY && 
					event.getAction() == Action.RIGHT_CLICK_AIR &&  
					inHand.getType() == Material.PAPER && 
					!SpoutManager.getMaterialManager().isCustomItem(inHand)
				) {
				
				//punching air while holding a blank peice of paper.
				GenericPopup labelPopup = new GenericPopup();
				labelPopup.setAnchor(WidgetAnchor.CENTER_CENTER);
				labelPopup.setWidth(128).setWidth(64);
				labelPopup.setBgVisible(true);
				
				GenericLabel label = new GenericLabel();
				label.setText("Write Label");
				label.setX(5).setY(5);
				labelPopup.attachWidget(plugin, label);
				
				/*
				GenericTexture paperTexture = new GenericTexture();
				paperTexture.setUrl(CustomsManager.TEXTURE_URL_GUI_PAPER); //Have to be a png or jpg
				paperTexture.setWidth(128).setHeight(64); //Use the same size as the png here.
				paperTexture.setY(10).setX(10);
				labelPopup.attachWidget(plugin, paperTexture);
				*/
				
				GenericTextField labelInput = new GenericTextField();
				labelInput.setMaximumCharacters(500);
				labelInput.setHeight(15).setWidth(200);
				labelInput.setY(25);
				labelInput.setX(5);
				labelInput.setMaximumLines(1);
				labelInput.setFocus(true);
				labelPopup.attachWidget(plugin, labelInput);
				
				CreateButton button = new CreateButton(plugin, labelPopup, labelInput);
				button.setY(45).setX(5);
				button.setWidth(75).setHeight(20);
				//button.setAnchor(WidgetAnchor.BOTTOM_CENTER);
				labelPopup.attachWidget(plugin, button);
				
				player.getMainScreen().attachPopupScreen(labelPopup);
				
				/*
				SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_START, false, location, 3);
				*/
			}
		}
	}
}
