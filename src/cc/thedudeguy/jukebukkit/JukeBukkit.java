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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.sound.SoundManager;

public class JukeBukkit extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	PluginManager pm;
	public SoundManager sm;
	
	private final JukeBukkitPlayerListener playerListener = new JukeBukkitPlayerListener(this);
	private final JukeBukkitBlockListener blockListener = new JukeBukkitBlockListener(this);
	//private final JukeBukkitSpoutAudioListener spoutAudioListener = new JukeBukkitSpoutAudioListener(this);
	
	protected HashMap<Short,Disc> discs;
	protected HashMap<String, Short> jukeboxes;
	
	public void onEnable() {
		
		// make sure save folder exists
        if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
        }
        
		loadDiscs();
		loadJukeBoxes();
		
		this.pm = this.getServer().getPluginManager();
		this.pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		this.pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
		//this.pm.registerEvent(Event.Type.CUSTOM_EVENT, spoutAudioListener, Event.Priority.Normal, this);
		
		this.sm = SpoutManager.getSoundManager();
		
		this.log.info("[JukeBukkit] Enabled");
		
	}
	
	public void onDisable() {
		
		saveAllDiscs();
		saveJukeBoxes();
		
		this.log.info("[JukeBukkit] Disabled.");
	}
	
	public short getAvailableDiscId()
	{
		short id = 1;
		while ( id < Short.MAX_VALUE )
		{
			if ( discs.containsKey(id) == false)
			{
				break;
			}
			id = (short)(id + 1);
		}
		return id;
	}
	
	
	public void saveAllDiscs()
	{
		Configuration config = new Configuration(new File(getDataFolder(), "discs.yml"));
		
		for (Map.Entry<Short,Disc> discmap : discs.entrySet()) {
			Disc disc = discmap.getValue();
			
			HashMap<String, String> configmap = new HashMap<String, String>();
			configmap.put("url", disc.getUrl());
			configmap.put("name", disc.getName());
			configmap.put("artist", disc.getArtist());
			configmap.put("creator", disc.getCreator());
			
			config.setProperty(Short.toString(disc.getId()), configmap);
		}
		config.save();
	}
	
	/** 
	 * Saves the jukebox hashmap to file
	 * @authors Tomsik68, Chris Churchwell
	 * 
	 * This function is derived from Tomsik68's Example code on http://wiki.bukkit.org/HUGE_Plugin_Tutorial
	 */
	public void saveJukeBoxes()
	{	
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File(getDataFolder(), "jukeboxes.dat")));
			oos.writeObject(jukeboxes);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	 * Saves the jukebox hashmap to file
	 * @authors Tomsik68, Chris Churchwell
	 * 
	 * This function is derived from Tomsik68's Example code on http://wiki.bukkit.org/HUGE_Plugin_Tutorial
	 */
	@SuppressWarnings("unchecked")
	public void loadJukeBoxes()
	{
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(getDataFolder(), "jukeboxes.dat")));
			Object result = ois.readObject();
			ois.close();
			jukeboxes = (HashMap<String, Short>)result;
			return;
			
		} catch (Exception e) {
			jukeboxes = new HashMap<String, Short>();
			//e.printStackTrace();
		}
		
		jukeboxes = new HashMap<String, Short>();
		
	}
	
	@SuppressWarnings("unchecked")
	public void loadDiscs()
	{
		discs = new HashMap<Short,Disc>();
		
		Configuration config = new Configuration(new File(getDataFolder(), "discs.yml"));
		config.load();
		
		List<String> keys = config.getKeys();
		
		for ( String key : keys )
		{
			short discId = Short.valueOf(key);
			HashMap<String, String> discData = (HashMap<String, String>)config.getProperty(key);
			Disc disc = new Disc(
					discId, 
					discData.get("creator"),
					discData.get("url"),
					discData.get("name"),
					discData.get("artist")
			);
			discs.put(discId, disc);
		}
	}
	
	
	public void showHelp(Player player)
	{
		player.sendMessage("Usage:");
		player.sendMessage("/cd burn <url>");
		player.sendMessage("/cd set title <title>");
		player.sendMessage("/cd set artist <artist>");
		player.sendMessage("/jukebukkit about");
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
		if (sender instanceof Player){
			if(command.getName().equalsIgnoreCase("jukebukkit")){
				
				Player player = (Player)sender;
				
				if (args.length == 0){
					showHelp(player);
					return true;
				}
				if (args[0].equalsIgnoreCase("about"))
				{
					if (args.length != 1){
						showHelp(player);
						return true;
					}
					
					player.sendMessage("JukeBukkit :: Copyright (C) 2011 Chris Churchwell");
					player.sendMessage("This program comes with ABSOLUTELY NO WARRANTY");
					player.sendMessage("This is free software, licensed under the GNU GPL v3.");
					player.sendMessage("You are welcome to redistribute it under certain conditions");
					return true;
				}
				else if (args[0].equalsIgnoreCase("burn"))
				{
					if (!player.hasPermission("jukebukkit.burn"))
					{
						player.sendMessage("You do not have permission to burn discs.");
						return true;
					}
					
					ItemStack inHand = player.getItemInHand();
					if (inHand == null || inHand.getType() != Material.GOLD_RECORD ) {
						player.sendMessage("Must hold a Golden Record in your hand.");
						return true;
					}
					if (inHand.getDurability() != 0) {
						player.sendMessage("This disc has already been burned! Pff. Someone should invent re-writeable discs...");
						return true;
					}
					if (args.length != 2){
						showHelp(player);
						return true;
					}
					String url = args[1];
					
					//validate the url
					//must be atleast 5 characters.
					if (url.length() < 5){
						player.sendMessage("A url must be atleast 5 characters.");
						return true;
					}
					
					//at them moment, it seems spout checks for the extension of a url (.ogg) instead
					//of a mimetype, so passing a url without .ogg or .wav on the end
					//will fail.
					//i havent tested wavs, bud midis just mess EVERYTHING up, and dont follow
					//distance or volume rules. YUCK!. im not supporting them at this time.
					//get the extension
					String ext = url.substring( url.lastIndexOf('.')+1, url.length() );
					if ( !ext.equalsIgnoreCase("ogg") && !ext.equalsIgnoreCase("wav")) {
						player.sendMessage("A url must end with .ogg or .wav");
						return true;
					}
					//check the url
					
					try {
					    URL conurl = new URL(url);
					    URLConnection conn = conurl.openConnection();
					    conn.connect();
					    
					    if ( conn instanceof HttpURLConnection)
					    {
					       HttpURLConnection httpConnection = (HttpURLConnection) conn;
					       int code = httpConnection.getResponseCode();
					       if (code != 200)
					       {
					    	   player.sendMessage("Was not able to connect to the given URL. Maybe you entered it wrong?");
					    	   return true;
					       }
					    }
					    else
					    {
					    	player.sendMessage("Was not able to connect to the given URL. Maybe you entered it wrong?");
					    	return true;
					    }
					} catch (MalformedURLException e) {
						player.sendMessage("The URL does not appear to be valid. Maybe you entered it wrong?");
						return true;
					} catch (IOException e) {
						player.sendMessage("Was not able to connect to the given URL. Maybe you entered it wrong?");
						return true;
					}
					
					
					short discId = this.getAvailableDiscId();
					//burn it!
					try {
						Disc disc = new Disc(discId, player.getName(), url);
						discs.put(discId, disc);
					} catch (Exception e) {
						player.sendMessage("Unable to burn disc");
					} finally {
						inHand.setDurability(discId);
						player.sendMessage("Disc has been burned...");
					}
					
					return true;
				}
				else if (args[0].equalsIgnoreCase("set")) {
					if (args.length < 3)
					{
						showHelp(player);
						return true;
					}
					
					ItemStack inHand = player.getItemInHand();
					
					if (inHand == null || inHand.getType() != Material.GOLD_RECORD ) {
						player.sendMessage("Must hold an already burned Record.");
						return true;
					}
					if (inHand.getDurability() < 1) {
						player.sendMessage("This disc has not been burned yet.");
						return true;
					}
					//make sure the hashmap has a value
					short discId = inHand.getDurability();
					if (!discs.containsKey(discId))
					{
						player.sendMessage("This disc has is no longer decipherable.");
						return true;
					}
					Disc disc = discs.get(discId);
					
					if (!player.hasPermission("jukebukkit.edit"))
					{
						if (!disc.getCreator().equalsIgnoreCase(player.getName()))
						{
							player.sendMessage("Only he who burned this disc can decipher the encryption to edit it.");
							return true;
						}
					}
					
					if (args[1].equalsIgnoreCase("title")) {
						// form the string
						String title = "";
						
						for (int i=2; i<args.length; i++)
						{
							title = title + args[i] + " ";
						}
						title = title.trim();
						disc.setSongName(title);
						discs.put(discId, disc);
						
						player.sendMessage("You have changed the disc's Title.");
						return true;
					} else if (args[1].equalsIgnoreCase("artist")) {
						// form the string
						String title = "";
						for (int i=2; i<args.length; i++)
						{
							title = title + args[i] + " ";
						}
						title = title.trim();
						disc.setSongArtist(title);
						discs.put(discId, disc);
						
						player.sendMessage("You have edited the disc's Artist.");
						
						return true;
					}
				}
				showHelp(player);
				return true;
			}
			return true;
		}
		return true;
	}
}
