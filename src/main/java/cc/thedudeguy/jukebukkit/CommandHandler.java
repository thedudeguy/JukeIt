/**
 * Copyright (C) 2011  Chris Churchwell
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
 **/
package cc.thedudeguy.jukebukkit;

import java.io.File;
import java.lang.reflect.Method;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.thedudeguy.jukebukkit.util.ResourceManager;


/**
 * Command Executor for JukeBukkit.
 * @author Chris Churchwell
 *
 */
public class CommandHandler implements CommandExecutor {

	public JukeBukkit plugin;
	
	public CommandHandler() {
		this.plugin = JukeBukkit.instance;
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
		sender.sendMessage(plugin.getDescription().getName());
		sender.sendMessage("------------------------------------");
		sender.sendMessage("Command Usage: /jukebukkit {command}");
		sender.sendMessage("------------------------------------");
		sender.sendMessage("Commands");
		sender.sendMessage("========");
		sender.sendMessage("version - Version Info");
		sender.sendMessage("help    - Show help");
		sender.sendMessage("recache - Can sometimes fix problems with textures.");
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
			
			if (!player.hasPermission("jukebukkit.command.resetcache")) {
				player.sendMessage("You do not have permission to use this command.");
				player.sendMessage("(jukebukkit.command.resetcache)");
				return true;
			}
		}
		
		ResourceManager.resetCache();
		sender.sendMessage("Cache has been reset.");
		return true;
	}
	
	public Boolean listmusic(CommandSender sender, String[] args) {
		sender.sendMessage("-- Server Music List --");
		
		if (!JukeBukkit.instance.HTTPserver.isRunning()) {
			sender.sendMessage("Server is not running");
			return true;
		}
		File musicFolder = new File(JukeBukkit.instance.getDataFolder(), "music");
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
