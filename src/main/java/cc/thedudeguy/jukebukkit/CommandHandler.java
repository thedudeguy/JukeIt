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

import java.lang.reflect.Method;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


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
		return true;
	}
	
	public Boolean version(CommandSender sender, String[] args)
	{
		sender.sendMessage(plugin.getDescription().getFullName());
		return true;
	}
	
}
