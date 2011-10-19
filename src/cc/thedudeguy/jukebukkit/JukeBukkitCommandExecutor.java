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
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.CustomItem;

/**
 * Command Executor for JukeBukkit.
 * @author Chris Churchwell
 *
 */
public class JukeBukkitCommandExecutor implements CommandExecutor{

	private JukeBukkit plugin;
	
	public JukeBukkitCommandExecutor(JukeBukkit plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		//command.getName() should alaways = jukebukkit i think
		Player player = (Player)sender;
		
		if (args.length == 0){
			showHelp(player);
			return true;
		}
		
		String subcommand = args[0].toLowerCase();
		
			try {
				Method m = this.getClass().getMethod(subcommand, CommandSender.class, Command.class, String.class, String[].class);
				return (Boolean)m.invoke(this, sender, command, label, args);
			} catch (Exception e) {
				showHelp(player);
				return true;
			}
	}
	
	public void showHelp(Player player)
	{
		player.sendMessage("Usage:");
		player.sendMessage("/cd burn <url>");
		player.sendMessage("/cd label <label>");
		player.sendMessage("/cd about");
	}
	
	public Boolean about(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player)sender;
		
		if (args.length != 1){
			showHelp(player);
			return true;
		}
		player.sendMessage("JukeBukkit "+ JukeBukkit.version +" :: Copyright (C) 2011 Chris Churchwell");
		player.sendMessage("This program comes with ABSOLUTELY NO WARRANTY");
		player.sendMessage("This is free software, licensed under the GNU GPL v3.");
		player.sendMessage("You are welcome to redistribute it under certain conditions");
		return true;
	}
	
	public Boolean label(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player)sender;
		ItemStack inHand = player.getItemInHand();
		if (inHand == null)
		{
			player.sendMessage("Must hold a Burned Disc in your hand.");
			return true;
		}
		if (!SpoutManager.getMaterialManager().isCustomItem(inHand))
		{
			player.sendMessage("Must hold a Burned Disc in your hand.");
			return true;
		}
		
		CustomItem customItem = SpoutManager.getMaterialManager().getCustomItem(inHand);
		int discId = customItem.getCustomId();
		
		if (!plugin.getDiscsManager().hasDiscId(discId))
		{
			player.sendMessage("No data associated with this Disc/Item");
			return true;
		}
		
		//get the title from the args.
		String title = "";
		for (int i=1; i<args.length; i++)
		{
			title = title + args[i] + " ";
		}
		title = title.trim();
		
		plugin.getDiscsManager().setTitle(discId, title);
		plugin.getDiscsManager().save();
		
		customItem.setName(title);
		
		return true;
	}
	
	public Boolean test(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player)sender;
		
		ItemStack inHand = player.getItemInHand();
		if (inHand == null)
		{
			player.sendMessage("Must hold a Blank Recordable Disc in your hand.");
			return true;
		}
		player.sendMessage("testing...");
		if (SpoutManager.getMaterialManager().isCustomItem(inHand))
		{
			player.sendMessage("itz cushtom");
			//RecordableMusicDisc md = (RecordableMusicDisc)SpoutManager.getItemManager().getCustomItem(inHand);
			//player.sendMessage("url: "+ md.getUrl());
			//player.sendMessage("wheres the yoorl");
		} else {
			player.sendMessage("wtf");
		}
		
		player.sendMessage("Testing ci id");
		//CustomItem ci = SpoutManager.getItemManager().getCustomItem(inHand);
		//player.sendMessage("hmm... " + String.valueOf(ci.getCustomId()));
		
		return true;
	}
	
	public Boolean burn(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player)sender;
		
		if (args.length != 2){
			showHelp(player);
			return true;
		}
		String url = args[1];
		
		ItemStack inHand = player.getItemInHand();
		if (inHand == null)
		{
			player.sendMessage("Must hold a Blank Recordable Disc in your hand.");
			return true;
		}
		
		if (!SpoutManager.getMaterialManager().isCustomItem(inHand))
		{
			player.sendMessage("Must hold a Blank Recordable Disc in your hand.");
			return true;
		}
		
		CustomItem discInHand = SpoutManager.getMaterialManager().getCustomItem(inHand);
		
		if (!discInHand.getName().equalsIgnoreCase("White Recordable Disc"))		
		{
			player.sendMessage("Must hold a Blank Recordable Disc in your hand.");
			return true;
		}
		
		//remove 1 from hand
		if (inHand.getAmount()<2)
		{
			player.setItemInHand(null);
		} else {
			player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount()-1);
		}
		
		//create the key
		String key = UUID.randomUUID().toString();
		
		//create the physical disc for the pplayer
		BurnedMusicDisc disc = new BurnedMusicDisc(plugin, key);
		ItemStack iss=SpoutManager.getMaterialManager().getCustomItemStack(disc, 1);
		player.getInventory().addItem(iss);
		
		//create a disc config.
		plugin.getDiscsManager().add(disc.getCustomId());
		plugin.getDiscsManager().setUrl(disc.getCustomId(), url);
		plugin.getDiscsManager().setTitle(disc.getCustomId(), "Burned Disc");
		plugin.getDiscsManager().setKey(disc.getCustomId(), key);
		plugin.getDiscsManager().save();
		
		return true;
	}
	
}
