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
package cc.thedudeguy.jukebukkit;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.PersistenceException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.database.RecordPlayerData;
import cc.thedudeguy.jukebukkit.listener.GeneralListener;
import cc.thedudeguy.jukebukkit.listener.MachineListener;
import cc.thedudeguy.jukebukkit.listener.SpeakerWireListener;
import cc.thedudeguy.jukebukkit.material.Blocks;
import cc.thedudeguy.jukebukkit.material.Items;
import cc.thedudeguy.jukebukkit.server.MusicHandler;
import cc.thedudeguy.jukebukkit.server.ServerHandler;
import cc.thedudeguy.jukebukkit.util.Debug;
import cc.thedudeguy.jukebukkit.util.Recipies;
import cc.thedudeguy.jukebukkit.util.ResourceManager;

/**
 * The main class for the Jukebukkit Plugin
 * @author Chris Churchwell
 */
public class JukeBukkit extends JavaPlugin {
	
	public static JukeBukkit instance;
	public Server HTTPserver;
	SelectChannelConnector HTTPconnector;
	
	Blocks blocks;
	Items items;
	
	public void onEnable()
	{	
		//copy the config
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		//double check for spout.
		if (!Bukkit.getPluginManager().isPluginEnabled("Spout")) {
			Bukkit.getLogger().log(Level.WARNING, "[JukeBukkit] Could not start: SpoutPlugin not found. SpoutPlugin is required for JukeBukkit to operate.");
			setEnabled(false);
			return;
		}
		
		instance = this;
		
		ResourceManager.copyResources();
		//ResourceManager.preLoginCache();
		
		setupDatabase();
		
		items = new Items();
		blocks = new Blocks();
		
		Recipies.load();
		
		//load listeners
		this.getServer().getPluginManager().registerEvents(new GeneralListener(), this);
		this.getServer().getPluginManager().registerEvents(new SpeakerWireListener(), this);
		this.getServer().getPluginManager().registerEvents(new MachineListener(), this);
		
		this.getCommand("jukebukkit").setExecutor(new CommandHandler());
		
		ResourceManager.resetCache();
		
		//start the web server up
		
		HTTPserver = new Server();
		HTTPconnector = new SelectChannelConnector();
		HTTPconnector.setPort(getConfig().getInt("webServerPort"));
		HTTPserver.addConnector(HTTPconnector);
		
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(false);
		resourceHandler.setResourceBase(new File(this.getDataFolder(), "web").getAbsolutePath());
        //resource_handler.setWelcomeFiles(new String[]{ "index.html" });
 
		HandlerList handlers = new HandlerList();
		handlers.addHandler(new ServerHandler());
		handlers.addHandler(new MusicHandler());
		handlers.addHandler(resourceHandler);
		
		HTTPserver.setHandler(handlers);
		
		if (getConfig().getBoolean("enableWebServer") == true) {
			if (
					getConfig().getString("minecraftServerHostname").isEmpty() ||
					getConfig().getString("minecraftServerHostname").equalsIgnoreCase("") ||
					getConfig().getString("minecraftServerHostname").equalsIgnoreCase("change.me.com")
				) {
				Bukkit.getLogger().log(Level.SEVERE, "[JukeBukkit] Unable to start web server: minecraftServerHostname not set. Please check JukeBukkit config");
			} else {
				try {
					HTTPserver.start();
					//HTTPserver.join();
					Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Web Server started on port: " + getConfig().getString("webServerPort"));
				} catch (Exception e) {
					Bukkit.getLogger().log(Level.WARNING, "[JukeBukkit] Unable to start web server");
					e.printStackTrace();
				}
			}
		}
	}
	
	public void onDisable()
	{
		if (HTTPserver.isRunning()) {
			try {
				Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Stopping Web Server...");
				HTTPserver.stop();
				HTTPserver.join();
				Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Web server stopped.");
			} catch (Exception e) {
				Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Could not stop server.");
				e.printStackTrace();
			}
		}
		Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Disabled.");
	}
	
	/**
	 * Sets up the ebean database if needed
	 */
	private void setupDatabase() {
		try {
            getDatabase().find(RecordPlayerData.class).findRowCount();
            getDatabase().find(DiscData.class).findRowCount();
        } catch (PersistenceException ex) {
            Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Installing database for " + getDescription().getName() + " due to first time usage");
            installDDL();
        }
	}
	
	@Override	
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
	    list.add(RecordPlayerData.class);
	    list.add(DiscData.class);
	    return list;
	}
	
	public static List<String> getServerFileList() {
	
		File musicFolder = new File(JukeBukkit.instance.getDataFolder(), "music");
		File[] fileList = musicFolder.listFiles(); 
		
		List<String> strList = new ArrayList<String>();
		
		for (File file : fileList) {
			if (file.isFile()) {
				strList.add(file.getName());
			}
		}
		
		return strList;
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
					JukeBukkit.instance.getConfig().getString("minecraftServerHostname") + 
					":" + 
					JukeBukkit.instance.getConfig().getString("webServerPort") +
					"/music/" +
					url;
			
			try {
				Debug.debug("Checking newly patched url: ", newURL);
				URL serverURL = new URL(newURL);
				Debug.debug("URL Passes, returning as: ", serverURL.toString());
				return serverURL.toString();
				
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Debug.debug("New URL Failed, returning Original URL: ", url);
				return url;
			}
		}
		
	}
	
	 /*
		@EventHandler
		public void onBlockPlaced(BlockPlaceEvent event) {
			event.setBuild(true); //MEW!

			final Player ply = event.getPlayer();
			final Block block = ((SpoutBlock)event.getBlock()).getCustomBlock();
			if(!(block instanceof JukeboxBlock)) return;
			final JukeboxBlock jukeboxBlock = (JukeboxBlock)block;
			String permission = jukeboxBlock.getPermission();
			if(permission == null) return;
			if(!ply.hasPermission(permission)) {
				event.setBuild(false);
				event.setCancelled(true);
			}
		}
		
		
		@EventHandler
		public void onPlayerCraft(CraftItemEvent event) {
			final Player ply = event.getPlayer();
	                final ItemStack st = event.getResult();
	                if (st==null) return;
			final org.getspout.spoutapi.material.Material block = new SpoutItemStack(st).getMaterial();
			if(!(block instanceof JukeboxBlock)) return;
			final JukeboxBlock jukeboxBlock = (JukeboxBlock)block;
			String permission = jukeboxBlock.getPermission();
			if(permission == null) return;
			if(!ply.hasPermission(permission)) {
				event.setResult(null);
				event.setCancelled(true);
			}
		}
		*/
}
