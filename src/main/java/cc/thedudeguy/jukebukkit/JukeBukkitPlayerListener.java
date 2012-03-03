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

import cc.thedudeguy.jukebukkit.items.ItemBurnedObsidyisc;
import cc.thedudeguy.jukebukkit.items.ItemLabel;
import cc.thedudeguy.jukebukkit.jukebox.JukeboxBlock;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.inventory.InventoryClickEvent;
import org.getspout.spoutapi.event.inventory.InventoryCraftEvent;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.Block;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 * Player Listener. Handles writing of labels and opening the label maker gui.
 * @author Chris Churchwell
 *
 */
public class JukeBukkitPlayerListener implements Listener {
	
	public static JukeBukkit plugin; 
	
	public JukeBukkitPlayerListener(JukeBukkit instance) {
        plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public class CreateButton extends GenericButton
	{
		private GenericTextField input;
		private GenericPopup popup;
		private JukeBukkit myplugin;
		
		public CreateButton(JukeBukkit plugin, GenericPopup assocPopup, GenericTextField assocInputField)
		{
			super();
			setText("Create Label");
			input = assocInputField;
			popup = assocPopup;
			this.myplugin = plugin;
			
			//plugin.log.info("Burn Disc Button Initialized");
		}
                @Override
		public void onButtonClick(ButtonClickEvent event) 
		{
                        // return if the label is blank
                        if(input.getText().equals("")) {
                            popup.close();
                            return;
                        }
			//delete a paper from the hand.
			ItemStack inHand = event.getPlayer().getItemInHand();
			if (inHand.getAmount()<2) {
				event.getPlayer().setItemInHand(null);
			} else {
				inHand.setAmount(inHand.getAmount()-1);
			}		
			
			//give the label to the player.
                        ItemStack newLabel = myplugin.getLabelManager().create(input.getText());
                        event.getPlayer().getInventory().addItem(newLabel);
			popup.close();
	        
	    }
	}

	@EventHandler
	public void onBlockPlaced(BlockPlaceEvent event) {
		event.setBuild(true); //MEW!

		final Player ply = event.getPlayer();
		final Block block = ((SpoutBlock)event.getBlock()).getCustomBlock();
		if(!(block instanceof JukeboxBlock)) return;
		final JukeboxBlock jukeboxBlock = (JukeboxBlock)block;
		String permission = jukeboxBlock.getPermission();
		if(permission == null) return;
		if(!ply.hasPermission(permission)) {
			event.setBuild(false);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerCraft(InventoryCraftEvent event) {
		final Player ply = event.getPlayer();
                final ItemStack st = event.getResult();
                if (st==null) return;
		final org.getspout.spoutapi.material.Material block = new SpoutItemStack(st).getMaterial();
		if(!(block instanceof JukeboxBlock)) return;
		final JukeboxBlock jukeboxBlock = (JukeboxBlock)block;
		String permission = jukeboxBlock.getPermission();
		if(permission == null) return;
		if(!ply.hasPermission(permission)) {
			event.setResult(null);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		SpoutPlayer player = SpoutManager.getPlayer(event.getPlayer());
		
		if (player.isSpoutCraftEnabled())
		{
			ItemStack inHand = event.getPlayer().getItemInHand();
			
			if (
					event.useItemInHand() != Result.DENY && 
					event.getAction() == Action.RIGHT_CLICK_AIR &&  
					inHand.getType() == Material.PAPER && 
					!new SpoutItemStack(inHand).isCustomItem()
				) {
				
				//punching air while holding a blank peice of paper.
				GenericPopup labelPopup = new GenericPopup();
				
				GenericLabel label = new GenericLabel();
				label.setText("Write Label");
				label.setX(5).setY(5).setWidth(120).setHeight(20);
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

	/**
	 * Basically handles when a user dropes a label onto a written disc.
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		//only on right click.
		if (!event.isLeftClick())
		{
			//only if a Label is on the cursor. is it even a custom item?
			if (event.getCursor() != null && new SpoutItemStack(event.getCursor()).isCustomItem()) {

				CustomItem itemOnCursor = (CustomItem) new SpoutItemStack(event.getCursor()).getMaterial();
				if (itemOnCursor instanceof ItemLabel)
				{

					//yep, its a label. let see what were clicking on.
					if (new SpoutItemStack(event.getItem()).isCustomItem())
					{

						//its custom could be a disc...
						CustomItem itemClickedOn = (CustomItem) new SpoutItemStack(event.getItem()).getMaterial();
						if ( itemClickedOn instanceof ItemBurnedObsidyisc)
						{
							//its a burned disc! we can do stuff to it.
							String label = plugin.getLabelManager().get(itemOnCursor.getCustomId());

							plugin.getDiscsManager().setTitle(itemClickedOn.getCustomId(), label);
							plugin.getDiscsManager().save();

							itemClickedOn.setName(label);

							//remove the item on the cursor.
							event.setResult(Result.ALLOW);
							event.setCursor(null);

							event.setCancelled(true);
							//return true;
						}
					}
				}
			}
		}
	}
}
