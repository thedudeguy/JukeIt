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
package com.chrischurchwell.jukeit;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.PersistenceException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.chrischurchwell.jukeit.database.DiscData;
import com.chrischurchwell.jukeit.database.RPStorageData;
import com.chrischurchwell.jukeit.material.DiscColor;
import com.chrischurchwell.jukeit.material.blocks.JukeboxBlock;
import com.chrischurchwell.jukeit.material.blocks.RecordPlayer;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;
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
	
	public Boolean removeolddiscs(CommandSender sender, String[] args){
		Player player = (Player)sender;
		if(!player.isOp()) {
			player.sendMessage("Must be OP");
		}
		//only need to use if discs table exists and has entries.
		try {
			int count = JukeIt.getInstance().getDatabase().find(DiscData.class).findRowCount();
			if (count < 1) {
				player.sendMessage("Nothing to Delete");
				return true;
			}
		} catch (PersistenceException ex) {
			player.sendMessage("Nothing to Delete");
			return true;
		}
		//get entries.
		List<DiscData> all = JukeIt.getInstance().getDatabase().find(DiscData.class).findList();
		if (!all.isEmpty()) {
			player.sendMessage("Nothing to Delete");
			return true;
		}
		JukeIt.getInstance().getDatabase().delete(all);
		return true;
	}
	
	public Boolean upgradediscs(CommandSender sender, String[] args){
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Command can only be used by a Player");
		}
		Player player = (Player)sender;
		
		if(!player.isOp()) {
			player.sendMessage("Must be OP");
		}
		
		//only need to use if discs table exists and has entries.
		try {
			int count = JukeIt.getInstance().getDatabase().find(DiscData.class).findRowCount();
			if (count < 1) {
				player.sendMessage("Nothing to Convert");
				return true;
			}
		} catch (PersistenceException ex) {
			player.sendMessage("Nothing to Convert");
			return true;
		}
		
		//get entries.
		List<DiscData> all = JukeIt.getInstance().getDatabase().find(DiscData.class).findList();
		if (!all.isEmpty()) {
			player.sendMessage("Nothing to Convert");
			return true;
		}
		
		for(DiscData disc : all) {
			ItemStack is = BurnedDisc.createDisc(DiscColor.getByIdentifier(disc.getColor()), disc.getUrl(), disc.getLabel());
			this.tossItem(player, is);
		}
		
		player.sendMessage("done");
		
		return true;
	}
	
	public Boolean help(CommandSender sender, String[] args)
	{
		sender.sendMessage(plugin.getDescription().getName());
		sender.sendMessage("------------------------------------");
		sender.sendMessage("Command Usage: /jukeit {command}");
		sender.sendMessage("------------------------------------");
		sender.sendMessage("Commands");
		sender.sendMessage("========");
		sender.sendMessage("version - Version Info");
		sender.sendMessage("help    - Show help");
		sender.sendMessage("resetcache - Can sometimes fix problems with textures.");
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("playjuke [world] [x] [y] [z] - Play a JukeBox at this location");
		}
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
		sender.sendMessage(plugin.getDescription().getFullName());
		return true;
	}
	
	public Boolean resetcache(CommandSender sender, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player)sender;
			
			if (!player.hasPermission("jukeit.command.resetcache")) {
				player.sendMessage("You do not have permission to use this command.");
				player.sendMessage("(jukeit.command.resetcache)");
				return true;
			}
		}
		
		ResourceManager.resetCache();
		sender.sendMessage("Cache has been reset.");
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
	
	private void tossItem(Player player, ItemStack dropItem) {
		Location loc = player.getLocation();
        loc.setY(loc.getY() + 1);
        
        Item item = loc.getWorld().dropItem(loc, dropItem);
        Vector v = loc.getDirection().multiply(0.2);
        v.setY(0.2);
        item.setVelocity(v);
	}
}
