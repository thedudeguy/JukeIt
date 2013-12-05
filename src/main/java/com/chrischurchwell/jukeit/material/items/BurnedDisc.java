/**
 * This file is part of JukeIt-Free
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
/**
 * This file is part of JukeIt
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
package com.chrischurchwell.jukeit.material.items;

import net.minecraft.server.v1_6_R3.NBTTagCompound;
import net.minecraft.server.v1_6_R3.NBTTagString;

import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.database.RPStorageData;
import com.chrischurchwell.jukeit.material.DiscColor;
import com.chrischurchwell.jukeit.material.Items;


public class BurnedDisc extends GenericCustomItem implements DiscColorable {
	
	
	public static ItemStack createDisc(DiscColor color, String url) {
		return createDisc(color, url, null);
	}
	
	public static ItemStack createDisc(RPStorageData data) {
		return createDisc(DiscColor.getByIdentifier(data.getColor()), data.getUrl(), data.getTitle());
	}
	
	public static ItemStack createDisc(DiscColor color, String url, String label) {
		if (color == null || color.equals(DiscColor.NONE)) {
			color = DiscColor.WHITE;
		}
		ItemStack newDisc = new SpoutItemStack(Items.Disc.getDiscByColor(color).burned());
		if (url == null) url = "";
		newDisc = BurnedDisc.encodeDisc(newDisc, url);
		if (label == null || label.isEmpty()) {
			label = "Unlabeled Audio";
		}
		newDisc = BurnedDisc.labelDisc(newDisc, label);
		
		return newDisc;
	}
	
	public static ItemStack encodeDisc(ItemStack item, String url) {
		net.minecraft.server.v1_6_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		if (nmsStack.tag == null) {
			nmsStack.tag = new NBTTagCompound();
		}
		nmsStack.tag.setString("jukeit", url);
		return (ItemStack)CraftItemStack.asCraftMirror(nmsStack);
	}
	
	public static String decodeDisc(ItemStack item) {
		net.minecraft.server.v1_6_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		if (nmsStack.tag == null) return "";
		if (nmsStack.tag.hasKey("jukeit")) {
			return nmsStack.tag.getString("jukeit");
		} else {
			return "";
		}
	}
	
	public static String getDiscLabel(ItemStack item) {
		net.minecraft.server.v1_6_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		if (
				nmsStack.tag != null &&
				nmsStack.tag.hasKey("display") &&
				nmsStack.tag.getCompound("display").hasKey("Name") &&
				!nmsStack.tag.getCompound("display").getString("Name").isEmpty()
				) {
			return nmsStack.tag.getCompound("display").getString("Name");
		}
		return "Unlabeled Audio";
	}
	
	public static ItemStack labelDisc(ItemStack item, String label) {
		net.minecraft.server.v1_6_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		if (nmsStack.tag == null) {
			nmsStack.tag = new NBTTagCompound();
		}
		if (!nmsStack.tag.hasKey("display"))
		{
			nmsStack.tag.set("display", new NBTTagCompound());
		}
		nmsStack.tag.getCompound("display").set("Name", new NBTTagString("1", label));
		
		return (ItemStack)CraftItemStack.asCraftMirror(nmsStack);
	}
	
	private DiscColor color = DiscColor.WHITE; //white is the default color
	
	public BurnedDisc(DiscColor color) {
		super(JukeIt.getInstance(), color.toString());
		this.color = color;
		setName("Ruined Burned Disc");
		setTexture(color.burnedTexture().getFile());
	}
	
	public DiscColor getColor() {
		return color;
	}
	
	/*
	public static final String generateNameKey() {
		return  UUID.randomUUID().toString();
	}
	
	public static final Map<Integer, String> discColorTextureMap;
	static {
		Map<Integer, String> dctMap = new HashMap<Integer, String>();
		dctMap.put(DiscColorable.BLACK, 	"burned_disc_black.png");
		dctMap.put(DiscColorable.RED, 		"burned_disc_red.png");
		dctMap.put(DiscColorable.GREEN, 	"burned_disc_green.png");
		dctMap.put(DiscColorable.BROWN, 	"burned_disc_brown.png");
		dctMap.put(DiscColorable.BLUE, 		"burned_disc_blue.png");
		dctMap.put(DiscColorable.PURPLE, 	"burned_disc_purple.png");
		dctMap.put(DiscColorable.CYAN, 		"burned_disc_cyan.png");
		dctMap.put(DiscColorable.LIGHTGRAY,	"burned_disc_lightgray.png");
		dctMap.put(DiscColorable.GRAY, 		"burned_disc_gray.png");
		dctMap.put(DiscColorable.PINK, 		"burned_disc_pink.png");
		dctMap.put(DiscColorable.LIME, 		"burned_disc_lime.png");
		dctMap.put(DiscColorable.YELLOW, 	"burned_disc_yellow.png");
		dctMap.put(DiscColorable.LIGHTBLUE, "burned_disc_lightblue.png");
		dctMap.put(DiscColorable.MAGENTA, 	"burned_disc_magenta.png");
		dctMap.put(DiscColorable.ORANGE, 	"burned_disc_orange.png");
		dctMap.put(DiscColorable.WHITE, 	"burned_disc_white.png");
		discColorTextureMap = Collections.unmodifiableMap(dctMap);
	}
	
	private int color = DiscColorable.WHITE; //whit disc is the default color
	private String key;
	private String url;
	
	public BurnedDisc() {
		super(JukeIt.getInstance(), "Reference Burn Disc");
		setName("Reference Burn Disc (Do Not Use)");
		setColor(DiscColorable.WHITE);
	}
	
	public BurnedDisc(DiscData discData) {
		super(JukeIt.getInstance(), discData.getNameKey());
		
		this.setColor(discData.getColor());
		this.setLabel(discData.getLabel());
		
		key = discData.getNameKey();
		url = discData.getUrl();
	}
	
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, org.bukkit.block.BlockFace face) {
		
		//player.sendMessage("interacting with item");
		//player.sendMessage("Key is: " + getKey());
		//player.sendMessage("Color is: " + String.valueOf(getColor()));
		
		return true;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getUrl() {
		return url;
	}
	
	@Override
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
		
		if (discColorTextureMap.containsKey(color)) {
			setTexture(discColorTextureMap.get(color));
		} else {
			setTexture(discColorTextureMap.get(DiscColorable.WHITE));
		}
		
	}
	
	public void setLabel(String label) {
		
		if (label == null || label.isEmpty() || label.equalsIgnoreCase("") ) {
			setName("*Burned* Obsidyisc");
		} else {
			setName(label);
		}
	}
	*/
}
