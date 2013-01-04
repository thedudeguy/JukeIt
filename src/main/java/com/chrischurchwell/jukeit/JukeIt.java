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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.PersistenceException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.mcstats.Metrics;

import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import com.chrischurchwell.jukeit.database.DiscData;
import com.chrischurchwell.jukeit.database.RPStorageData;
import com.chrischurchwell.jukeit.database.RepeaterChipData;
import com.chrischurchwell.jukeit.listener.GeneralListener;
import com.chrischurchwell.jukeit.listener.MachineListener;
import com.chrischurchwell.jukeit.listener.SpeakerWireListener;
import com.chrischurchwell.jukeit.material.Blocks;
import com.chrischurchwell.jukeit.material.Items;
import com.chrischurchwell.jukeit.server.MusicHandler;
import com.chrischurchwell.jukeit.server.ServerHandler;
import com.chrischurchwell.jukeit.util.Debug;
import com.chrischurchwell.jukeit.util.Recipies;
import com.chrischurchwell.jukeit.util.ResourceManager;

/**
 * The main class for the JukeIt Plugin
 * @author Chris Churchwell
 */
public class JukeIt extends JavaPlugin {
	
	private static JukeIt instance;
	public Server HTTPserver;
	SelectChannelConnector HTTPconnector;
	
	Blocks blocks;
	Items items;
	
	public static JukeIt getInstance() {
		return instance;
	}
	
	public static void info(String message) {
		log(Level.INFO, message);
	}
	
	public static void warn(String message) {
		log(Level.WARNING, message);
	}
	
	public static void severe(String message) {
		log(Level.SEVERE, message);
	}
	
	public static void log(Level level, String message) {
		Bukkit.getLogger().log(level, "["+getInstance().getDescription().getName()+"] " + message);
	}
	
	public JukeIt() {
		instance = this;
		
		//load the web server
		HTTPserver = new Server();
		HTTPconnector = new SelectChannelConnector();
	}
	
	public void onEnable()
	{	
		//load mc-stats
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e1) {
			Debug.debug("Unable to submit mcstats");
		}
		
		//copy the config
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		//double check for spout.
		if (!Bukkit.getPluginManager().isPluginEnabled("Spout")) {
			severe("Could not start: SpoutPlugin not found. SpoutPlugin is required for JukeIt to operate.");
			setEnabled(false);
			return;
		}
		
		ResourceManager.copyResources();
		ResourceManager.preLoginCache();
		
		setupDatabase();
		
		items = new Items();
		blocks = new Blocks();
		
		Recipies.load();
		
		//load listeners
		this.getServer().getPluginManager().registerEvents(new GeneralListener(), this);
		this.getServer().getPluginManager().registerEvents(new SpeakerWireListener(), this);
		this.getServer().getPluginManager().registerEvents(new MachineListener(), this);
		
		//start the web server up
		if (getConfig().getBoolean("enableWebServer") == true) {
			HTTPconnector.setPort(getConfig().getInt("webServerPort"));
			HTTPserver.addConnector(HTTPconnector);
			
			ResourceHandler resourceHandler = new ResourceHandler();
			resourceHandler.setDirectoriesListed(false);
			resourceHandler.setResourceBase(new File(this.getDataFolder(), "web").getAbsolutePath());
	 
			HandlerList handlers = new HandlerList();
			handlers.addHandler(new ServerHandler());
			handlers.addHandler(new MusicHandler());
			handlers.addHandler(resourceHandler);
			
			HTTPserver.setHandler(handlers);
			
			if (
					getConfig().getString("minecraftServerHostname").isEmpty() ||
					getConfig().getString("minecraftServerHostname").equalsIgnoreCase("") ||
					getConfig().getString("minecraftServerHostname").equalsIgnoreCase("change.me.com")
				) {
				severe("Unable to start web server: minecraftServerHostname not set. Please check JukeIt config");
			} else {
				try {
					HTTPserver.start();
					//HTTPserver.join();
					info("Web Server started on port: " + getConfig().getString("webServerPort"));
				} catch (Exception e) {
					warn("Unable to start web server");
					e.printStackTrace();
				}
			}
		}
		
		try {
			this.getCommand("jukeit").setExecutor(new CommandHandler());
		} catch (Exception e) {
			warn("An Error Occurred: Unable to load Commands");
		}
	}
	
	public void onDisable()
	{
		if (HTTPserver != null && HTTPserver.isRunning()) {
			try {
				info("Stopping Web Server...");
				HTTPserver.stop();
				HTTPserver.join();
				info("Web server stopped.");
			} catch (Exception e) {
				warn("Could not stop server.");
				e.printStackTrace();
			}
		}
		info("Disabled");
	}
	
	/**
	 * Sets up the ebean database if needed
	 */
	private void setupDatabase() {
		
		try {
			getDatabase().find(RPStorageData.class).findRowCount();
			getDatabase().find(RepeaterChipData.class).findRowCount();
		} catch (PersistenceException ex) {
			info("Attempting to install db tables");
			
			SpiEbeanServer serv = (SpiEbeanServer) getDatabase();
			DdlGenerator gen = serv.getDdlGenerator();
			String ddl = gen.generateCreateDdl();
			
			gen.runScript(true, ddl);
		}
	}
	
	@Override	
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
	    list.add(RPStorageData.class);
	    list.add(DiscData.class);
	    list.add(RepeaterChipData.class);
	    return list;
	}
	
	public static List<String> getServerFileList() {
	
		File musicFolder = new File(JukeIt.instance.getDataFolder(), "music");
		File[] fileList = musicFolder.listFiles(); 
		
		List<String> strList = new ArrayList<String>();
		
		for (File file : fileList) {
			if (file.isFile()) {
				strList.add(file.getName());
			}
		}
		Collections.sort(strList);
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
					JukeIt.instance.getConfig().getString("minecraftServerHostname") + 
					":" + 
					JukeIt.instance.getConfig().getString("webServerPort") +
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
