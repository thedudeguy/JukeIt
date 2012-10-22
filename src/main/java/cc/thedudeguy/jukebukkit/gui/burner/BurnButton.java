/**
 * This file is part of JukeBukkit
 *
 * Copyright (C) 2011-2012  Chris Churchwell
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
 */
package cc.thedudeguy.jukebukkit.gui.burner;

import java.net.URL;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.ListWidget;
import org.getspout.spoutapi.gui.TextField;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.material.Items;
import cc.thedudeguy.jukebukkit.material.items.BlankDisc;
import cc.thedudeguy.jukebukkit.material.items.BurnedDisc;

public class BurnButton extends GenericButton {
	
	private ListWidget list;
	private TextField textField;
	private Block block;
	
	public BurnButton(TextField textField, Block block) {
		super("Burn");
		this.textField = textField;
		this.block = block;
	}
	
	public BurnButton(ListWidget list, Block block) {
		super("Burn");
		this.list = list;
		this.block = block;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		
		if (!event.getPlayer().hasPermission("jukebukkit.use.blankdisc")) {
			event.getPlayer().sendMessage("You do not have permission to perform this action");
			event.getPlayer().sendMessage("(jukebukkit.use.blankdisc)");
			event.getPlayer().getMainScreen().getActivePopup().close();
			event.setCancelled(true);
			return;
		}
		
		Location location = block.getLocation();
		
		String url = null;
		
		if (list == null && textField != null) {
			
			url = textField.getText().trim();
			
			if(url.isEmpty()) {
				event.getPlayer().sendMessage("The URL cannot be blank. You wouldnt want to waste a disc!");
				event.getPlayer().getMainScreen().getActivePopup().close();
				return;
			}
			
			try {
				URL parseURL = new URL(url);
				String fname = parseURL.getFile().toLowerCase();
				if(!fname.endsWith(".ogg") && !fname.endsWith(".wav") &&!fname.endsWith(".mp3")) {
					event.getPlayer().sendMessage("Currently only .ogg and .wav formats are supported. Please convert the file to one of those.");
					event.getPlayer().getMainScreen().getActivePopup().close();
					return;
				}
			} catch(Exception e) {
				event.getPlayer().sendMessage("Invalid URL!");
				event.getPlayer().getMainScreen().getActivePopup().close();
				return;
			}
			
		}
		
		else if (list != null) {
			if (list.getSelectedItem() == null || list.getSelectedItem().getTitle().isEmpty()) {
				event.getPlayer().sendMessage("No selection made");
				event.getPlayer().getMainScreen().getActivePopup().close();
				return;
			}
			
			if (
					!list.getSelectedItem().getTitle().toLowerCase().endsWith(".ogg") && 
					!list.getSelectedItem().getTitle().toLowerCase().endsWith(".wav") &&
					!list.getSelectedItem().getTitle().toLowerCase().endsWith(".mp3")
					) {
				event.getPlayer().sendMessage("Invalid Selection");
				event.getPlayer().getMainScreen().getActivePopup().close();
				return;
			}
			
			url = list.getSelectedItem().getTitle();
		}
		
		if (url == null)
		{
			event.getPlayer().sendMessage("An Error Occurred");
			event.getPlayer().getMainScreen().getActivePopup().close();
		}
		
		
		if (event.getPlayer().getItemInHand() == null) {
			event.getPlayer().sendMessage("Invalid Disc in Hand");
			event.getPlayer().getMainScreen().getActivePopup().close();
        	return;
        }
		
        SpoutPlayer player = (SpoutPlayer)event.getPlayer();
        
        
        SpoutItemStack inHand = new SpoutItemStack(player.getItemInHand());
        
        if (!(inHand.getMaterial() instanceof BlankDisc)) {
        	event.getPlayer().sendMessage("Invalid Disc in Hand");
        	event.getPlayer().getMainScreen().getActivePopup().close();
        	return;
        }
        
        BlankDisc disk = (BlankDisc)inHand.getMaterial();
        
        //whats the color of the disc in hand?
        int color = disk.getColor();
        
        //remove 1 from hand
      	if (inHand.getAmount()<2) {
      		event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
      	} else {
      		inHand.setAmount(inHand.getAmount()-1);
      		player.setItemInHand(inHand);
      	}
        
      	//create the key
      	String key = BurnedDisc.generateNameKey();
      	
      	//add the disc into the database
      	DiscData discData = JukeBukkit.getInstance().getDatabase().find(DiscData.class)
      			.where()
      				.ieq("nameKey", key)
      				.findUnique();
      		if (discData == null) discData = new DiscData();
      		discData.setNameKey(key);
      		discData.setUrl(url);
      		discData.setLabel("");
      		discData.setColor(color);
      		JukeBukkit.getInstance().getDatabase().save(discData);
      	
      	//create the physical disc for the pplayer
    	BurnedDisc disc = new BurnedDisc(discData);
    	Items.burnedDiscs.put(key, disc);
    	ItemStack iss = new SpoutItemStack(disc, 1);
    	
    	location.setY(location.getY()+1);
    	location.getWorld().dropItem(location, iss);
    	
    	event.getPlayer().getMainScreen().getActivePopup().close();
    	
    	SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.getInstance(), "jb_startup.wav", false, location, 8);
    	
	}
}
