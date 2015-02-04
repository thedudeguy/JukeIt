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
package com.chrischurchwell.jukeit;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import com.chrischurchwell.jukeit.database.URLData;
import com.chrischurchwell.jukeit.material.DiscColor;
import com.chrischurchwell.jukeit.material.blocks.JukeboxBlock;
import com.chrischurchwell.jukeit.material.blocks.RecordPlayer;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;
import com.chrischurchwell.jukeit.util.DiscUtil;
import com.chrischurchwell.jukeit.util.ResourceManager;



/**
 * Command Executor for JukeIt.
 * @author Chris Churchwell
 *
 */
public class CommandHandler implements CommandExecutor {

	public JukeIt plugin;
	
	public CommandHandler() {
		this.plugin = JukeIt.getInstance();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args)
	{
		
		if (args.length < 1){
			return help(sender, args);
		}
		
		String subcommand = args[0].toLowerCase();
		String[] subargs = shiftArgs(args);
		
		try {
			Method m = this.getClass().getMethod(subcommand, CommandSender.class, String[].class);
			return (Boolean)m.invoke(this, sender, subargs);
		} catch (Exception e) {
			return help(sender, args);
		}
	}
	
	private String[] shiftArgs(String[] args) {
		
		if (args.length < 2) return new String[0];
		
		String[] shifted = new String[args.length-1];
		System.arraycopy(args, 1, shifted, 0, shifted.length);
		
		return shifted;
	}
	
	public Boolean help(CommandSender sender, String[] args)
	{
		
		Boolean isPlayer = false;
		Player player = null;
		
		if (sender instanceof Player) {
			isPlayer = true;
			player = (Player)sender;
		}
		
		sender.sendMessage(ChatColor.GOLD.toString() + "-- " + ChatColor.AQUA.toString() + "JukeIt: " + ChatColor.DARK_AQUA.toString() + "Help" + ChatColor.GOLD.toString() + " --");
		sender.sendMessage(ChatColor.AQUA.toString() + "Commands:");
		sender.sendMessage(ChatColor.GRAY.toString() + "/jukeit " + ChatColor.DARK_GREEN.toString() + "help");
		sender.sendMessage(ChatColor.GRAY.toString() + "/jukeit " + ChatColor.DARK_GREEN.toString() + "version");
		
		if (!isPlayer || player.hasPermission("jukeit.command.discinfo")) {
			sender.sendMessage(ChatColor.GRAY.toString() + "/jukeit " + ChatColor.DARK_GREEN.toString() + "discinfo" + ChatColor.GRAY.toString() + " - Requires Burned Disc in Hand");
		}
		if (!isPlayer || player.hasPermission("jukeit.command.listurls")) {
			sender.sendMessage(ChatColor.GRAY.toString() + "/jukeit " + ChatColor.DARK_GREEN.toString() + "listurls" + ChatColor.DARK_AQUA + " [page-number]" + ChatColor.GRAY.toString() + "");
		}
		if (!isPlayer || player.hasPermission("jukeit.command.givedisc")) {
			sender.sendMessage(ChatColor.GRAY.toString() + "/jukeit " + ChatColor.DARK_GREEN.toString() + "givedisc" + ChatColor.DARK_AQUA + " {player} {sid} {color}" + ChatColor.GRAY.toString() + "");
		}
		if (!isPlayer || player.hasPermission("jukeit.command.resetcache")) {
			sender.sendMessage(ChatColor.GRAY.toString() + "/jukeit " + ChatColor.DARK_GREEN.toString() + "resetcache" + ChatColor.GRAY.toString() + " - Can sometimes fix problems with textures.");
		}
		
		if (!isPlayer) {
			sender.sendMessage(ChatColor.GRAY.toString() + "/jukeit " + ChatColor.DARK_GREEN.toString() + "playjuke" + ChatColor.DARK_AQUA + " {world} {x} {y} {z}" + ChatColor.GRAY.toString() + "");
		}
		return true;
	}
	
	public Boolean givedisc(CommandSender sender, String[] args) {
		
		if (sender instanceof Player) {
			Player pplayer = (Player)sender;
			if (!pplayer.hasPermission("jukeit.command.givedisc")) {
				pplayer.sendMessage(ChatColor.RED.toString() + "You do not have permission to use this command.");
				pplayer.sendMessage(ChatColor.GOLD.toString() + "(jukeit.command.givedisc)");
				return true;
			}
		}
		
		//player
		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED.toString() + "Error: Player required.");
			return true;
		}
		
		Player player = Bukkit.getPlayer(args[0]);
		
		if (player == null) {
			sender.sendMessage(ChatColor.RED.toString() + "Error: Cannot find player");
			return true;
		}
		
		//sid
		if (args.length < 2) {
			sender.sendMessage(ChatColor.RED.toString() + "Error: SID required.");
			return true;
		}
		int sid = Integer.valueOf(args[1]);
		String url = DiscUtil.getURLFromSID(sid);
		if (url == null) {
			sender.sendMessage(ChatColor.RED.toString() + "Error: Invalid SID - no URL found.");
			return true;
		}
		
		//color
		if (args.length < 3) {
			sender.sendMessage(ChatColor.RED.toString() + "Error: Disc color required.");
			return true;
		}
		DiscColor color = DiscColor.getByName(args[2]);
		if (color.equals(DiscColor.NONE)) {
			sender.sendMessage(ChatColor.RED.toString() + "Error: Invalid color.");
			return true;
		}
		
		ItemStack disc = DiscUtil.createDisc(color, url);
		player.getInventory().addItem(disc);
		player.sendMessage(ChatColor.GOLD.toString() + "You Received a Music Disc.");
		
		sender.sendMessage(ChatColor.DARK_GREEN.toString() + "Disc given to " + ChatColor.GOLD.toString() + player.getName());
		return true;
	}
	
	public Boolean discinfo(CommandSender sender, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command can only be used by a player.");
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("jukeit.command.discinfo")) {
			player.sendMessage(ChatColor.RED.toString() + "You do not have permission to use this command.");
			player.sendMessage(ChatColor.GOLD.toString() + "(jukeit.command.discinfo)");
			return true;
		}
		
		SpoutItemStack inHand = new SpoutItemStack(player.getItemInHand());
		
		if (!(inHand.getMaterial() instanceof BurnedDisc)) {
			sender.sendMessage(ChatColor.RED.toString() + "Error: You must be holding a burned disc in your hands");
			return true;
		}
		
		int sid = DiscUtil.getSID(inHand);
		DiscColor color = DiscUtil.getColor(inHand); 
		String url = DiscUtil.decodeDisc(inHand);
		
		sender.sendMessage(ChatColor.GOLD.toString() + "-- " + ChatColor.AQUA.toString() + "JukeIt: " + ChatColor.DARK_AQUA.toString() + "Disc Info" + ChatColor.GOLD.toString() + " --");
		sender.sendMessage(ChatColor.DARK_GREEN.toString() + "Disc SID: " + ChatColor.GOLD.toString() + String.valueOf(sid));
		sender.sendMessage(ChatColor.DARK_GREEN.toString() + "Disc Color: " + ChatColor.GOLD.toString() + color.name());
		sender.sendMessage(ChatColor.DARK_GREEN.toString() + "Disc URL: " + ChatColor.GOLD.toString() + url);
		return true;
	}
	
	public Boolean playjuke(CommandSender sender, String[] args) {
		
		if (sender instanceof Player) {
			return false;
		}
		
		if (args.length != 4) {
			sender.sendMessage("Invalid Usage");
			return true;
		}
		
		World world = JukeIt.getInstance().getServer().getWorld(args[0]);
		int x = Integer.valueOf(args[1]);
		int y = Integer.valueOf(args[2]);
		int z = Integer.valueOf(args[3]);
		
		if (world == null) {
			sender.sendMessage("World does not exist.");
			return true;
		}
		
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		
		if (block.getCustomBlock() instanceof JukeboxBlock) {
			((JukeboxBlock)block.getCustomBlock()).onBlockClicked(world, x, y, z, null);
		} else if (block.getCustomBlock() instanceof RecordPlayer) {
			((RecordPlayer)block.getCustomBlock()).onBlockClicked(world, x, y, z, null);
		}
		
		return true;
	}
	
	public Boolean version(CommandSender sender, String[] args)
	{
		sender.sendMessage(ChatColor.GOLD.toString() + "-- " + ChatColor.AQUA.toString() + "JukeIt: " + ChatColor.DARK_AQUA.toString() + "Version" + ChatColor.GOLD.toString() + " --");
		sender.sendMessage(ChatColor.DARK_GREEN.toString() + plugin.getDescription().getFullName());
		return true;
	}
	
	public Boolean resetcache(CommandSender sender, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player)sender;
			
			if (!player.hasPermission("jukeit.command.resetcache")) {
				player.sendMessage(ChatColor.RED.toString() + "You do not have permission to use this command.");
				player.sendMessage(ChatColor.GOLD.toString() + "(jukeit.command.resetcache)");
				return true;
			}
		}
		
		ResourceManager.resetCache();
		sender.sendMessage(ChatColor.GOLD.toString() + "-- " + ChatColor.AQUA.toString() + "JukeIt: " + ChatColor.DARK_AQUA.toString() + "Reset Cache" + ChatColor.GOLD.toString() + " --");
		sender.sendMessage(ChatColor.DARK_GREEN.toString() + "Cache has been reset.");
		return true;
	}
	
	public Boolean listurls(CommandSender sender, String[] args) {
		
		if (sender instanceof Player) {
			Player pplayer = (Player)sender;
			if (!pplayer.hasPermission("jukeit.command.listurls")) {
				pplayer.sendMessage(ChatColor.RED.toString() + "You do not have permission to use this command.");
				pplayer.sendMessage(ChatColor.GOLD.toString() + "(jukeit.command.listurls)");
				return true;
			}
		}
		
		int pagenum = 1;
		
		if (args.length > 0) {
			pagenum = Integer.valueOf(args[0]).intValue();
			if (pagenum < 1) {
				pagenum = 1;
			}
		}
		
		int perPage = 17;
		
		PagingList<URLData> pagingList = JukeIt.getInstance().getDatabase().find(URLData.class).findPagingList(perPage);
		pagingList.getFutureRowCount();
		
		Page<URLData> page = pagingList.getPage(pagenum-1);
		
		List<URLData> urlList = page.getList();
		
		int totalRows = page.getTotalRowCount();
		int totalPages = page.getTotalPageCount();
		
		sender.sendMessage(ChatColor.GOLD.toString() + "-- " + ChatColor.AQUA.toString() + "JukeIt: " + ChatColor.DARK_AQUA.toString() + "Burned URLs" + ChatColor.GOLD.toString() + " --");
		sender.sendMessage(ChatColor.GOLD.toString() + "-- " + ChatColor.DARK_AQUA + "Page " + String.valueOf(pagenum) + " of " + String.valueOf(totalPages) + " ("+ String.valueOf(totalRows) +" entries)");
		sender.sendMessage(ChatColor.YELLOW.toString() + ChatColor.ITALIC.toString() + "sid: " + ChatColor.DARK_GREEN.toString() + ChatColor.ITALIC.toString() + "url");
		
		for(URLData data : urlList) {
			String sid = String.valueOf(data.getId()) + ": ";
			String url = data.getUrl();
			if ((sid.length() + url.length()) > 55) {
				url = url.substring(0, 55-sid.length()) + "...";
			}
			sender.sendMessage(ChatColor.YELLOW.toString() + sid + ChatColor.DARK_GREEN.toString() + url);
		}
		
		return true;
	}
	
	public Boolean listmusic(CommandSender sender, String[] args) {
		sender.sendMessage("-- Server Music List --");
		
		if (!JukeIt.getInstance().HTTPserver.isRunning()) {
			sender.sendMessage("Server is not running");
			return true;
		}
		File musicFolder = new File(JukeIt.getInstance().getDataFolder(), "music");
		if (!musicFolder.exists()) {
			sender.sendMessage("No music files on server");
			return true;
		}
		File[] fileList = musicFolder.listFiles(); 
		if (fileList.length < 1) {
			sender.sendMessage("No music files on server");
			return true;
		}
		
		for (File file : fileList) {
			if (file.isFile()) {
				sender.sendMessage(file.getName());
			}
		}
		return true;
	}
}
