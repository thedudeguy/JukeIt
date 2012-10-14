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
package cc.thedudeguy.jukebukkit.gui.recordplayer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.RecordPlayerData;
import cc.thedudeguy.jukebukkit.gui.PlayerInventorySlot;
import cc.thedudeguy.jukebukkit.material.Items;
import cc.thedudeguy.jukebukkit.material.blocks.RecordPlayer;
import cc.thedudeguy.jukebukkit.material.blocks.designs.RPNeedle;
import cc.thedudeguy.jukebukkit.material.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.material.items.needles.Needle;
import cc.thedudeguy.jukebukkit.util.Debug;

public class RecordPlayerGUI extends GenericPopup {
	
	protected SpoutBlock block;
	protected SpoutPlayer player;
	protected RecordSlot recordSlot;
	protected NeedleSlot needleSlot;
	private PlayerInventorySlot[] playerSlots = new PlayerInventorySlot[36];
	private boolean dirtySlots = true;
	
	public RecordPlayerGUI(SpoutPlayer player, SpoutBlock block) {
		
		this.player = player;
		this.block = block;
		
		GenericTexture border = new GenericTexture("machinegui.png");
		border.setX(-88).setY(-83);
		border.setPriority(RenderPriority.Highest);
		border.setWidth(176).setHeight(166);
		border.setFixed(true);
		border.setAnchor(WidgetAnchor.CENTER_CENTER);
		
		recordSlot = new RecordSlot(this);
		recordSlot.setX(19).setY(-34);
		recordSlot.setWidth(16).setHeight(16);
		recordSlot.setPriority(RenderPriority.Normal);
		recordSlot.setFixed(true);
		recordSlot.setAnchor(WidgetAnchor.CENTER_CENTER);
		
		needleSlot = new NeedleSlot(this);
		needleSlot.setX(-35).setY(-63);
		needleSlot.setWidth(16).setHeight(16);
		needleSlot.setPriority(RenderPriority.Normal);
		needleSlot.setFixed(true);
		needleSlot.setAnchor(WidgetAnchor.CENTER_CENTER);
		
		// Select button
		PlayButton playButton = new PlayButton(block);
		playButton.setX(-60).setY(-35);
		playButton.setWidth(60).setHeight(20);
		playButton.setPriority(RenderPriority.Normal);
		playButton.setFixed(true);
		playButton.setAnchor(WidgetAnchor.CENTER_CENTER);
		
		setTransparent(true);
		attachWidgets(JukeBukkit.instance, border, recordSlot, needleSlot, playButton);
		
		
		
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
			
			playerSlots[i] = slot;
			this.attachWidget(JukeBukkit.instance, playerSlots[i]);
			
			xposition++;
		}
		
		updatePlayerSlots();
	}
	
	public void saveRecord(ItemStack record) {
		Debug.debug("Saving Record Data");
		RecordPlayerData data = getData();
		
		if (record == null || record.getType().equals(Material.AIR)) {
			data.setDiscKey(null);
		} else {
			BurnedDisc d = (BurnedDisc)(new SpoutItemStack(record)).getMaterial();
			data.setDiscKey(d.getKey());
		}
		
		JukeBukkit.instance.getDatabase().save(data);
		RecordPlayer.updateBlockDesign(block, data);
	}
	
	public void saveNeedle(ItemStack needle) {
		Debug.debug("Saving Needle Data");
		RecordPlayerData data = getData();
		
		if (needle == null || needle.getType().equals(Material.AIR)) {
			data.setNeedleType(RPNeedle.NONE);
		} else {
			Needle n = (Needle)(new SpoutItemStack(needle)).getMaterial();
			data.setNeedleType(n.getNeedleType());
		}
		
		JukeBukkit.instance.getDatabase().save(data);
		RecordPlayer.updateBlockDesign(block, data);
	}
	
	private RecordPlayerData getData() {
		//get data from the db
		RecordPlayerData rpdata = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
			.where()
				.eq("x", (double)block.getX())
				.eq("y", (double)block.getY())
				.eq("z", (double)block.getZ())
				.ieq("worldName", block.getWorld().getName())
			.findUnique();
		if (rpdata == null) {
			Debug.debug("RecordPlayerGUI:getData null rpdata");
			rpdata = new RecordPlayerData();
			rpdata.setDiscKey(null);
			rpdata.setNeedleType(RPNeedle.NONE);
			rpdata.setX((double)block.getX());
			rpdata.setY((double)block.getY());
			rpdata.setZ((double)block.getZ());
			rpdata.setWorldName(block.getWorld().getName());
		}
		
		return rpdata;
	}
	
	private void syncDataSlots() {
		RecordPlayerData data = getData();
		
		if(data.hasDisc()) {
			BurnedDisc b = Items.burnedDiscs.get(data.getDiscKey());
			ItemStack i = new SpoutItemStack(b, 1);
			if (!recordSlot.getItem().equals(i)) {
				recordSlot.setItem(i);
			}
		} else {
			recordSlot.setItem(new ItemStack(Material.AIR));
		}
		
		if (!RPNeedle.getById(data.getNeedleType()).equals(RPNeedle.NONE)) {
			if (!recordSlot.getItem().equals(RPNeedle.getById(data.getNeedleType()).getItem())) {
				needleSlot.setItem(new SpoutItemStack(RPNeedle.getById(data.getNeedleType()).getItem(), 1));
			}
		} else {
			needleSlot.setItem(new ItemStack(Material.AIR));
		}
	}
	
	public void setDirtySlots() {
		dirtySlots = true;
	}
	
	private void updatePlayerSlots() {
		if (dirtySlots != true) return;
		
		Debug.debug("Updating Player Slots");
		Inventory inventory = player.getInventory();
		for (int i = 0; i < 36; i++) {
			if (!playerSlots[i].getItem().equals(inventory.getItem(i))) {
				playerSlots[i].setItem(inventory.getItem(i));
			}
		}
		
		dirtySlots = false;
	}
	
	@Override
	public void onTick() {
		
		//make sure theres only one of an item in any slot
		SpoutItemStack nItem = new SpoutItemStack(needleSlot.getItem());
		if (nItem.getAmount() > 1) {
			
			ItemStack tossMe = nItem.clone();
			tossMe.setAmount(nItem.getAmount() - 1);
			tossItem(tossMe);
			
			nItem.setAmount(1);
			needleSlot.setItem(nItem);
		}
		
		SpoutItemStack rItem = new SpoutItemStack(recordSlot.getItem());
		if (rItem.getAmount() > 1) {
			
			ItemStack tossMe = rItem.clone();
			tossMe.setAmount(rItem.getAmount() - 1);
			tossItem(rItem);
			
			rItem.setAmount(1);
			recordSlot.setItem(rItem);
		}
		
		updatePlayerSlots();
		syncDataSlots();
		
		super.onTick();
	}
	
	@Override
	public void handleItemOnCursor(org.bukkit.inventory.ItemStack itemOnCursor) {
		if (
				itemOnCursor != null &&
				!itemOnCursor.getType().equals(Material.AIR)
				) {
			tossItem(itemOnCursor);
		}
	}
	
	private void tossItem(ItemStack dropItem) {
		Location loc = player.getLocation();
        loc.setY(loc.getY() + 1);
        
        Item item = loc.getWorld().dropItem(loc, dropItem);
        Vector v = loc.getDirection().multiply(0.2);
        v.setY(0.2);
        item.setVelocity(v);
	}
}
