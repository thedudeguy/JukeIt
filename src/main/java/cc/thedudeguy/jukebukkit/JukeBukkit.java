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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cc.thedudeguy.jukebukkit.database.RecordPlayerBlockDesigns;
import cc.thedudeguy.jukebukkit.database.RecordPlayerData;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;
import cc.thedudeguy.jukebukkit.materials.items.Items;

/**
 * The main class for the Jukebukkit Plugin
 * @author Chris Churchwell
 */
public class JukeBukkit extends JavaPlugin {
	
	public static JukeBukkit instance;
	public Logger log = Logger.getLogger("Minecraft");
	
	public PluginManager pm;
	//private JukeBukkitCommandExecutor commandExecutor;
	
	private DiscsManager discsManager;
	private JukeBoxManager jukeBoxManager;
	private LabelManager labelManager;
	private CustomsManager customsManager;
	
	Blocks blocks;
	Items items;
	
	public void onEnable()
	{	
		instance = this;
		
		setupDatabase();
		
		blocks = new Blocks();
		items = new Items();
		
		
		/**
		 * Below is old stuff
		 */
		
		//load the textures and precaches
		customsManager = new CustomsManager(this);
		//load the disc manager so that it can be used throughout
		discsManager = new DiscsManager(this);
		//initialize the jukebox manager
		jukeBoxManager = new JukeBoxManager(this);
		//initialize the label manager
		labelManager = new LabelManager(this);
		
		customsManager.createCustomTextures();
		customsManager.createCustomItems();
		customsManager.createCustomBlocks();
		customsManager.createRecipes();
		discsManager.reInitDiscs();
		
		//TODO: Cleanup no longer used item ids for lables, and discs
		
		//load the command executor
		//commandExecutor = new JukeBukkitCommandExecutor(this);
		//getCommand("jukebukkit").setExecutor(commandExecutor);
		
		//launch the player Listener.
		new JukeBukkitPlayerListener(this);
		
		log.info("[JukeBukkit] Enabled");
		
	}
	
	public void onDisable()
	{
		log.info("[JukeBukkit] Disabled.");
	}
	
	/**
	 * Sets up the ebean database if needed
	 */
	private void setupDatabase() {
		try {
            getDatabase().find(RecordPlayerBlockDesigns.class).findRowCount();
            getDatabase().find(RecordPlayerData.class).findRowCount();
        } catch (PersistenceException ex) {
            Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Installing database for " + getDescription().getName() + " due to first time usage");
            installDDL();
        }
	}
	
	 @Override
	 public List<Class<?>> getDatabaseClasses() {
		 List<Class<?>> list = new ArrayList<Class<?>>();
	     list.add(RecordPlayerBlockDesigns.class);
	     list.add(RecordPlayerData.class);
	     return list;
	 }
	
	/**
	 * 
	 * @depracated
	 */
	public DiscsManager getDiscsManager()
	{
		return discsManager;
	}
	
	/**
	 * 
	 * @depracated
	 */
	public JukeBoxManager getJukeBoxManager()
	{
		return jukeBoxManager;
	}
	
	/**
	 * 
	 * @depracated
	 */
	public CustomsManager getCustomsManager()
	{
		return customsManager;
	}
	
	/**
	 * 
	 * @depracated
	 */
	public LabelManager getLabelManager()
	{
		return labelManager;
	}
}
