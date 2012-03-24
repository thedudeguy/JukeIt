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
import org.bukkit.plugin.java.JavaPlugin;

import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.database.LabelData;
import cc.thedudeguy.jukebukkit.database.RecordPlayerBlockDesigns;
import cc.thedudeguy.jukebukkit.database.RecordPlayerData;
import cc.thedudeguy.jukebukkit.listeners.DiscLabelListener;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;
import cc.thedudeguy.jukebukkit.materials.items.Items;
import cc.thedudeguy.jukebukkit.util.Recipies;
import cc.thedudeguy.jukebukkit.util.ResourceManager;

/**
 * The main class for the Jukebukkit Plugin
 * @author Chris Churchwell
 */
public class JukeBukkit extends JavaPlugin {
	
	public static JukeBukkit instance;
	public Logger log = Logger.getLogger("Minecraft");
	
	Blocks blocks;
	Items items;
	
	public void onEnable()
	{	
		instance = this;
		
		ResourceManager.copyResources();
		ResourceManager.preLoginCache();
		setupDatabase();
		
		blocks = new Blocks();
		items = new Items();
		
		Recipies.load();
		
		this.getServer().getPluginManager().registerEvents(new DiscLabelListener(), this);
		
		this.getCommand("jukebukkit").setExecutor(new CommandHandler());
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
            getDatabase().find(DiscData.class).findRowCount();
            getDatabase().find(LabelData.class).findRowCount();
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
	     list.add(DiscData.class);
	     list.add(LabelData.class);
	     return list;
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
