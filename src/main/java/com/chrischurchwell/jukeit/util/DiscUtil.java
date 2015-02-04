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
package com.chrischurchwell.jukeit.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.database.RPStorageData;
import com.chrischurchwell.jukeit.database.URLData;
import com.chrischurchwell.jukeit.material.DiscColor;
import com.chrischurchwell.jukeit.material.Items;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;
import com.chrischurchwell.jukeit.sound.SoundEffect;

public class DiscUtil {
	
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
		
		if (url == null) url = "error";
		
		newDisc = DiscUtil.encodeDisc(newDisc, url);
		
		if (label == null || label.isEmpty()) {
			label = "Unlabeled Audio";
		}
		newDisc = DiscUtil.setLabel(newDisc, label);
		
		return newDisc;
	}
	
	public static ItemStack encodeDisc(ItemStack disc, String url) {
		URLData entry = URLData.saveEntry(url);
		
		Debug.sdebug("encodeDisc", entry.getId(), entry.getUrl());
		
		disc = setSID(disc, entry.getId());
		return disc;
	}
	
	public static String decodeDisc(ItemStack disc) {
		int id = getSID(disc);
		
		if (id >= 0) {
			String url = getURLFromSID(id);
			if (url != null) {
				return url;
			}
		}
		
		setLabel(disc, "Ruined Disc");
		return SoundEffect.SKIPPING_RECORD.getSoundFileName();
	}
	
	public static ItemStack setLabel(ItemStack disc, String label) {
		ItemMeta meta = disc.getItemMeta();
		
		meta.setDisplayName(label);
		disc.setItemMeta(meta);
		return disc;
	}
	
	public static String getLabel(ItemStack disc) {
		ItemMeta meta = disc.getItemMeta();
		
		if (meta.hasDisplayName()) {
			return meta.getDisplayName();
		}
		
		return null;
	}
	
	public static ItemStack setSID(ItemStack disc, int sid) {
		ItemMeta meta = disc.getItemMeta();
		
		String sidString = "sid:"+String.valueOf(sid);
		
		if (JukeIt.getInstance().getConfig().getBoolean("encryptSID")) {
			sidString = ChatColor.DARK_GRAY.toString() + 
					ChatColor.MAGIC.toString() + 
					sidString;
		} else {
			sidString = ChatColor.DARK_GRAY.toString() + 
					sidString;
		}
		
		List<String> lore = new ArrayList<String>();
		lore.add(sidString);
		
		meta.setLore(lore);
		disc.setItemMeta(meta);
		
		return disc;
	}
	
	public static int getSID(ItemStack disc) {
		ItemMeta meta = disc.getItemMeta();
		
		if (!meta.hasLore()) {
			return -1;
		}
		
		for (String loreLine : meta.getLore()) {
			loreLine = ChatColor.stripColor(loreLine);
			if (loreLine.startsWith("sid:")) {
				return Integer.valueOf(loreLine.replace("sid:", ""));
			}
		}
		return -1;
	}
	
	public static String getURLFromSID(int sid) {
		URLData entry = URLData.getEntry(sid);
		
		if (entry == null) return null;
		
		return entry.getUrl();
	}
	
	public static DiscColor getColor(BurnedDisc disc) {
		return disc.getColor();
	}
	
	public static DiscColor getColor(SpoutItemStack disc) {
		if (disc.getMaterial() instanceof BurnedDisc) {
			return getColor((BurnedDisc)disc.getMaterial());
		}
		return DiscColor.NONE;
	}
	
	/**
	 * Checks if a url is only the file name, if it is only a file name, its because its a file
	 * on the webserver, and we need to add the rest of the url to it manually.
	 * @param url
	 * @return
	 */
	public static String finishIncompleteURL(String url) {
		Debug.debug("Checking if URL is complete: ", url);
		
		try {
			new URL(url);
			Debug.debug("URL Passes, returning url");
			return url;
		} catch (MalformedURLException e) {
			
			Debug.debug("URL Failed, probably a server file");
			String newURL = 
					"http://" + 
					JukeIt.getInstance().getConfig().getString("minecraftServerHostname") + 
					":" + 
					JukeIt.getInstance().getConfig().getString("webServerPort") +
					"/music/" +
					url;
			
			try {
				Debug.debug("Checking newly patched url: ", newURL);
				URL serverURL = new URL(newURL);
				Debug.debug("URL Passes, returning as: ", serverURL.toString());
				return serverURL.toString();
				
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
				Debug.debug("New URL Failed, returning Original URL: ", url);
				return url;
			}
		}
		
	}
}

