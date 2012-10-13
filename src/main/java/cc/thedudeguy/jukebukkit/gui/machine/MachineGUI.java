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
package cc.thedudeguy.jukebukkit.gui.machine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.ScreenCloseEvent;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.DiscSlot;
import cc.thedudeguy.jukebukkit.gui.PlayerInventorySlot;
import cc.thedudeguy.jukebukkit.material.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.util.Debug;

public class MachineGUI extends GenericPopup {
	
	protected GenericContainer labelContainer;
	
	private GenericTexture paperTexture; 
	private MachineStartButton startButton;
	
	private Player player;
	private Block block;
	
	private DiscSlot discSlot;
	private GenericSlot discAddSlot;
	
	public MachineGUI(Player player, Block block) {
		
		this.player = player;
		this.block = block;
		
		GenericTexture border = new GenericTexture("machinegui.png");
		border.setX(-88).setY(-83);
		border.setPriority(RenderPriority.Highest);
		border.setWidth(176).setHeight(166);
		border.setFixed(true);
		border.setAnchor(WidgetAnchor.CENTER_CENTER);
		
		paperTexture = new GenericTexture("paper.png");
		paperTexture.setPriority(RenderPriority.Low);
		paperTexture.setWidth(256).setHeight(128);
		paperTexture.setFixed(true);
		paperTexture.setAnchor(WidgetAnchor.CENTER_CENTER);
		paperTexture.setX(-126).setY(-64);
		paperTexture.setVisible(false);
		
		discSlot = new DiscSlot();
		discSlot.setX(19).setY(-34);
		discSlot.setWidth(16).setHeight(16);
		discSlot.setPriority(RenderPriority.Normal);
		discSlot.setFixed(true);
		discSlot.setAnchor(WidgetAnchor.CENTER_CENTER);
		
		discAddSlot = new GenericSlot();
		discAddSlot.setX(-35).setY(-63);
		discAddSlot.setWidth(16).setHeight(16);
		discAddSlot.setPriority(RenderPriority.Normal);
		discAddSlot.setFixed(true);
		discAddSlot.setAnchor(WidgetAnchor.CENTER_CENTER);
		
		// Select button
		startButton = new MachineStartButton((SpoutPlayer)player, (SpoutBlock) block, discSlot, discAddSlot);
		startButton.setX(-60).setY(-35);
		startButton.setWidth(60).setHeight(20);
		startButton.setPriority(RenderPriority.Normal);
		startButton.setFixed(true);
		startButton.setAnchor(WidgetAnchor.CENTER_CENTER);
		
		createLabelWriter();
		hideLabelWriter();
		hideStartButton();
		
		this.setTransparent(true);
		this.attachWidgets(JukeBukkit.instance, border, paperTexture, discAddSlot, discSlot, startButton);
		
		Inventory inventory = player.getInventory();
		
		int xposition = 0;
		int yposition = 58;
		
		//i know theres some kind of cool math equation to do this, but i cant remember how to figure it out. doin it the cheap way
		for (int i = 0; i < 36; i++) {
			
			PlayerInventorySlot slot = new PlayerInventorySlot(player, i);
			if (xposition == 9) xposition = 0;
			if (i > 8) yposition = 0;
			if (i > 17) yposition = 18;
			if (i > 26) yposition = 36;
			
			slot.setY(1 + yposition);
			slot.setX(-80 + (xposition*18));
			slot.setWidth(16).setHeight(16);
			slot.setPriority(RenderPriority.Normal);
			slot.setFixed(true);
			slot.setAnchor(WidgetAnchor.CENTER_CENTER);
			slot.setItem(inventory.getItem(i));
			this.attachWidget(JukeBukkit.instance, slot);
			
			xposition++;
		}
	}
	
	protected void createLabelWriter() {
		
		if (this.labelContainer != null) {
			return;
		}
		
		labelContainer = new GenericContainer();
		labelContainer.setAnchor(WidgetAnchor.CENTER_CENTER);
		labelContainer.setPriority(RenderPriority.Lowest);
		labelContainer.setAlign(WidgetAnchor.CENTER_CENTER);
		labelContainer.setWidth(256);
		labelContainer.setHeight(64);
		labelContainer.setX(-128);
		labelContainer.setY(-64);
		
		GenericContainer bContainer = new GenericContainer();
		bContainer.setLayout(ContainerType.HORIZONTAL);
		bContainer.setAlign(WidgetAnchor.CENTER_CENTER);
		bContainer.setWidth(256);
		bContainer.setHeight(30);
		
		GenericTextField labelInput = new GenericTextField();
		labelInput.setMaximumCharacters(500);
		labelInput.setHeight(15).setWidth(200);
		//labelInput.setY(25);
		//labelInput.setX(5);
		labelInput.setMaximumLines(1);
		labelInput.setFocus(true);
		labelInput.setPriority(RenderPriority.Lowest);
		labelInput.setFixed(true);
		labelInput.setMarginTop(20);
		labelContainer.addChild(labelInput);
		
		MachineLabelButton button = new MachineLabelButton((SpoutPlayer)player, (SpoutBlock) block, discSlot, discAddSlot, labelInput);
		//button.setY(45).setX(5);
		button.setWidth(75).setHeight(20);
		//button.setAnchor(WidgetAnchor.BOTTOM_CENTER);
		button.setPriority(RenderPriority.Lowest);
		button.setFixed(true);
		bContainer.addChild(button);
		
		LabelCloseButton cancel = new LabelCloseButton(this);
		//button.setY(45).setX(5);
		cancel.setWidth(75).setHeight(20);
		//button.setAnchor(WidgetAnchor.BOTTOM_CENTER);
		cancel.setPriority(RenderPriority.Lowest);
		cancel.setFixed(true);
		bContainer.addChild(cancel);
		
		labelContainer.addChild(bContainer);
		labelContainer.setVisible(false);
		
		this.attachWidget(JukeBukkit.instance, labelContainer);
		
	}
	
	public void showStartButton() {
		startButton.setVisible(true);
	}
	
	public void hideStartButton() {
		startButton.setVisible(false);
	}
	
	public void showLabelWriter() {
		if (player.getItemOnCursor() != null && !player.getItemOnCursor().getType().equals(Material.AIR) ) {
			tossItem((SpoutPlayer) player, player.getItemOnCursor());
			player.setItemOnCursor(new ItemStack(Material.AIR));
		}
		paperTexture.setVisible(true);
		labelContainer.setVisible(true);
	}
	
	public void cancelLabelWriter() {
		player.setItemOnCursor(this.discAddSlot.getItem());
		this.discAddSlot.setItem(new ItemStack(Material.AIR));
		hideLabelWriter();
	}
	
	public void hideLabelWriter() {
		paperTexture.setVisible(false);
		labelContainer.setVisible(false);
	}
	
	@Override
	public void onScreenClose(ScreenCloseEvent event) {
		Debug.debug("MachineGUI:onScreenClose");
		
		//if any items are in the slots we should give them back to the player.
		if (
				this.discSlot.getItem() != null &&
				!this.discSlot.getItem().getType().equals(Material.AIR)
				) {
			tossItem(event.getPlayer(), discSlot.getItem());
		}
		if (
				this.discAddSlot.getItem() != null &&
				!this.discAddSlot.getItem().getType().equals(Material.AIR)
				) {
			tossItem(event.getPlayer(), discAddSlot.getItem());
		}
	}
	
	@Override
	public void handleItemOnCursor(org.bukkit.inventory.ItemStack itemOnCursor) {
		if (
				itemOnCursor != null &&
				!itemOnCursor.getType().equals(Material.AIR)
				) {
			tossItem((SpoutPlayer) player, itemOnCursor);
		}
	}
	
	@Override
	public void onTick() {
		SpoutItemStack sItem = new SpoutItemStack(this.discSlot.getItem());
		if (
				this.discAddSlot.getItem().getType().equals(Material.PAPER) &&
				(sItem.isCustomItem() && sItem.getMaterial() instanceof BurnedDisc)
				) {
			showLabelWriter();
			hideStartButton();
		} else if (
				!this.discSlot.getItem().getType().equals(Material.AIR) &&
				!this.discAddSlot.getItem().getType().equals(Material.AIR)
				){
			hideLabelWriter();
			showStartButton();
		} else {
			hideLabelWriter();
			hideStartButton();
		}
		super.onTick();
	}
	
	private void tossItem(SpoutPlayer player, ItemStack dropItem) {
		Location loc = player.getLocation();
        loc.setY(loc.getY() + 1);
        
        Item item = loc.getWorld().dropItem(loc, dropItem);
        Vector v = loc.getDirection().multiply(0.2);
        v.setY(0.2);
        item.setVelocity(v);
	}
}
