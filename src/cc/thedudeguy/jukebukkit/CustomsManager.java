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

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.Texture;

/**
 * Handles all of the custom items/blocks such as URL's to textures sounds, and block designs and textures.
 * @author Chris Churchwell
 *
 */
public class CustomsManager {
	
	private JukeBukkit plugin;
	
	public static final String SF_JUKEBOX_START = "http://dev.bukkit.org/media/attachments/21/202/jb_startup.wav";
	public static final String SF_JUKEBOX_ERROR = "http://dev.bukkit.org/media/attachments/21/203/jb_error.wav";
	
	public static final String TEXTURE_URL_WHITE_DISC = "http://dev.bukkit.org/media/attachments/21/200/rdisc_white.png";
	public static final String TEXTURE_URL_WHITE_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/201/rdisc_white_burned.png";
	public static final String TEXTURE_URL_RED_DISC = "http://dev.bukkit.org/media/attachments/21/239/rdisc_red.png";
	public static final String TEXTURE_URL_RED_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/240/rdisc_red_burned.png";
	
	public static final String TEXTURE_URL_LABEL = "http://dev.bukkit.org/media/attachments/21/204/label.png";
	
	public static final String TEXTURE_URL_BLOCKS = "http://dev.bukkit.org/media/attachments/21/205/customblocks.png";
	
	//public static final String TEXTURE_URL_GUI_PAPER = "http://chrischurchwell.com/minecraft/paper.png";
	
	/** Item Block id's **/
	//public static int WHITE_DISC;
	//public static int PROTOTYPE_JUKEBOX;
	
	public Texture customBlockTexture;
	
	public Texture prototypeJukeboxTexture;
	public Texture prototypeBurnerTexture;
	
	public CustomsManager(JukeBukkit plugin)
	{
		this.plugin = plugin;
		
		//load item id's
		//WHITE_DISC = new ItemBlankObsidyisc(plugin).getCustomId();
		
		customBlockTexture = new Texture(this.plugin, TEXTURE_URL_BLOCKS, 256, 256, 16);
		
		//client preloads
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_WHITE_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_WHITE_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_LABEL);
		
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_BLOCKS);
		//SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_GUI_PAPER);
		
		SpoutManager.getFileManager().addToPreLoginCache(plugin, SF_JUKEBOX_START);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, SF_JUKEBOX_ERROR);
	}
	
	/*
	public Texture getCustomBlocksTexture()
	{
		return customBlockTexture;
	}
	*/
}
