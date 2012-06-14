package cc.thedudeguy.jukebukkit.listeners;

import org.bukkit.Material;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.database.LabelData;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.materials.items.DiscLabel;


public class DiscLabelListener implements Listener {

	public class CreateButton extends GenericButton
	{
		private GenericTextField input;
		private GenericPopup popup;
		
		public CreateButton(GenericPopup assocPopup, GenericTextField assocInputField)
		{
			super();
			setText("Create Label");
			input = assocInputField;
			popup = assocPopup;
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
				event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
			} else {
				inHand.setAmount(inHand.getAmount()-1);
			}		
			
			popup.close();
			
			String labelText = input.getText();
			
			LabelData labelData = new LabelData();
			labelData.setLabel(labelText);
			labelData.generateNameKey();
			JukeBukkit.instance.getDatabase().save(labelData);
			ItemStack newLabel = new SpoutItemStack(new DiscLabel(labelData));
			
			event.getPlayer().openScreen(ScreenType.PLAYER_INVENTORY);
			event.getPlayer().getOpenInventory().setCursor(newLabel);
			
	    }
	}
	
	@EventHandler
	public void handleItemDespawn(ItemDespawnEvent event) {
		//TODO: On label despawn, remove it from the db.
		//Bukkit.getLogger().log(Level.INFO, "ITEM DESPAWNED");
	}
	
	@EventHandler
	public void handleItemIgnite(EntityCombustEvent event) {
		//TODO: on label burn up, remove it from the db.
		//Bukkit.getLogger().log(Level.INFO, "ITEM BURNEDEDED UP");
	}
	
	@EventHandler
	public void handleInteract(PlayerInteractEvent event) {
		SpoutPlayer player = SpoutManager.getPlayer(event.getPlayer());
		
		if (
				player.isSpoutCraftEnabled() && 
				event.hasItem() && 
				event.getItem().getType().equals(Material.PAPER) &&
				event.getAction() == Action.RIGHT_CLICK_AIR
				) {
			
			GenericPopup labelPopup = new GenericPopup();
			
			GenericLabel label = new GenericLabel();
			label.setText("Write Label");
			label.setX(5).setY(5).setWidth(120).setHeight(20);
			labelPopup.attachWidget(JukeBukkit.instance, label);
			
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
			labelPopup.attachWidget(JukeBukkit.instance, labelInput);
			
			CreateButton button = new CreateButton(labelPopup, labelInput);
			button.setY(45).setX(5);
			button.setWidth(75).setHeight(20);
			//button.setAnchor(WidgetAnchor.BOTTOM_CENTER);
			labelPopup.attachWidget(JukeBukkit.instance, button);
			
			player.getMainScreen().attachPopupScreen(labelPopup);
			
		}
	}
	
	@EventHandler
	public void handleUseLabel(InventoryClickEvent event)
	{
		if (event.isRightClick() && event.getCursor() != null && event.getCurrentItem() != null)
		{ 
			SpoutItemStack cursorItem = new SpoutItemStack(event.getCursor());
			SpoutItemStack currentItem = new SpoutItemStack(event.getCurrentItem());
			
			if (cursorItem.getMaterial() instanceof DiscLabel && currentItem.getMaterial() instanceof BurnedDisc) {
				
				DiscLabel label = (DiscLabel)cursorItem.getMaterial();
				BurnedDisc disc = (BurnedDisc)currentItem.getMaterial();
				
				if (label.getKey() != null && disc.getKey() != null) {
					
					LabelData labelData = JukeBukkit.instance.getDatabase().find(LabelData.class)
						.where()
							.eq("nameKey", label.getKey())
						.findUnique();
					DiscData discData = JukeBukkit.instance.getDatabase().find(DiscData.class)
						.where()
							.eq("nameKey", disc.getKey())
						.findUnique();
					
					if (labelData != null && discData != null) {
						
						discData.setLabel(labelData.getLabel());
						JukeBukkit.instance.getDatabase().save(discData);
						
						disc.setLabel(labelData.getLabel());
						
						JukeBukkit.instance.getDatabase().delete(labelData);
						SpoutManager.getMaterialManager().resetName(label);
						
						event.setCursor(new ItemStack(Material.AIR, 0));
						
						event.setResult(Result.ALLOW);
					}
				}
				
			}
		}
	}
	
	
}
