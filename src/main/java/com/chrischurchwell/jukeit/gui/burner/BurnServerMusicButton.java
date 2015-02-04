/**
 * This file is part of JukeIt
 *
 * Copyright (C) 2011-2013  Chris Churchwell
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
package com.chrischurchwell.jukeit.gui.burner;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.ListWidget;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.material.DiscColor;
import com.chrischurchwell.jukeit.material.items.BlankDisc;
import com.chrischurchwell.jukeit.util.DiscUtil;

public class BurnServerMusicButton extends GenericButton {
	
	private ListWidget list;
	private Block block;
	
	public BurnServerMusicButton(ListWidget list, Block block) {
		super("Burn");
		this.list = list;
		this.block = block;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		
		if (!event.getPlayer().hasPermission("jukeit.use.blankdisc")) {
			event.getPlayer().sendMessage("You do not have permission to perform this action");
			event.getPlayer().sendMessage("(jukeit.use.blankdisc)");
			event.getPlayer().getMainScreen().getActivePopup().close();
			event.setCancelled(true);
			return;
		}
		
		Location location = block.getLocation();
		
		String url = null;
		
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
		
		if (url == null)
		{
			event.getPlayer().sendMessage("An Error Occurred");
			event.getPlayer().getMainScreen().getActivePopup().close();
			return;
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
		DiscColor discColor = disk.getColor();
		
		//remove 1 from hand
		if (inHand.getAmount()<2) {
			event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
		} else {
			inHand.setAmount(inHand.getAmount()-1);
			player.setItemInHand(inHand);
		}
		
		//create a new disc.
		ItemStack newDisc = DiscUtil.createDisc(discColor, url);
		
		location.setY(location.getY()+1);
		location.getWorld().dropItem(location, newDisc);
		
		event.getPlayer().getMainScreen().getActivePopup().close();
		
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeIt.getInstance(), "jb_startup.wav", false, location, 8);
		
	}
}
