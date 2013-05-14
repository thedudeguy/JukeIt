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
package com.chrischurchwell.jukeit.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.chrischurchwell.jukeit.JukeIt;


public class Debug {
	
	public static final String tag = "JukeIt";
	
	public static void debug(String debugText) {
		if (JukeIt.getInstance().getConfig().getBoolean("debug")) {
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
		if (JukeIt.getInstance().getConfig().getBoolean("debug")) {
			player.sendMessage(ChatColor.DARK_GRAY + "["+ ChatColor.GRAY + tag + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY +debugText);
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
