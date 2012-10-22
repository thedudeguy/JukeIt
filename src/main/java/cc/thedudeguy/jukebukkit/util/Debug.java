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
package cc.thedudeguy.jukebukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class Debug {
	
	public static final String tag = "JukeBukkit";
	
	public static void debug(String debugText) {
		if (JukeBukkit.getInstance().getConfig().getBoolean("debug")) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD +"["+tag+"] " + debugText);
		}
	}
	
	public static void debug(Object... debugTexts) {
		String allText = "";
		for (Object debugText : debugTexts) {
			allText = allText + debugText.toString();
		}
		debug(allText);
	}
	
	public static void sdebug(Object... debugTexts) {
		String allText = "";
		for (Object debugText : debugTexts) {
			allText = allText + debugText.toString() + " :: ";
		}
		debug(allText);
	}
	
	public static void debug(Player player, String debugText) {
		if (JukeBukkit.getInstance().getConfig().getBoolean("debug")) {
			player.sendMessage("&8["+tag + "] &7"+debugText);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD +"["+tag+"] " + player.getName() + ": " + debugText);
		}
	}
	
	public static void debug(Player player, Object... debugTexts) {
		String allText = "";
		for (Object debugText : debugTexts) {
			allText = allText + debugText.toString();
		}
		debug(player, allText);
	}
	
}
