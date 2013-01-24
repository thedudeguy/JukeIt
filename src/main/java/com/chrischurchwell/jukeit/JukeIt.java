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
import org.mcstats.Metrics;

import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import com.chrischurchwell.jukeit.database.DiscData;
import com.chrischurchwell.jukeit.database.RPStorageData;
import com.chrischurchwell.jukeit.listener.GeneralListener;
import com.chrischurchwell.jukeit.listener.MachineListener;
import com.chrischurchwell.jukeit.listener.SpeakerWireListener;
import com.chrischurchwell.jukeit.material.Blocks;
import com.chrischurchwell.jukeit.material.Items;
import com.chrischurchwell.jukeit.util.Debug;
import com.chrischurchwell.jukeit.util.Recipies;
import com.chrischurchwell.jukeit.util.ResourceManager;

/**
 * The main class for the JukeIt Plugin
 * @author Chris Churchwell
 */
public class JukeIt extends JavaPlugin {
	
	private static JukeIt instance;
	
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
		Bukkit.getLogger().log(level, "["+getInstance().getDescription().getName()+"-Free] " + message);
	}
	
	public JukeIt() {
		instance = this;
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
		
		try {
			this.getCommand("jukeit").setExecutor(new CommandHandler());
		} catch (Exception e) {
			warn("An Error Occurred: Unable to load Commands");
		}
	}
	
	public void onDisable()
	{
		info("Disabled");
	}
	
	/**
	 * Sets up the ebean database if needed
	 */
	private void setupDatabase() {
		
		try {
			getDatabase().find(RPStorageData.class).findRowCount();
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
			e.printStackTrace();
			return url;
		}
		
	}
}
