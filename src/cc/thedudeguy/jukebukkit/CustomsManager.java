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
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

import cc.thedudeguy.jukebukkit.items.ItemBlankObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankBlackObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankBlueObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankBrownObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankCyanObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankGrayObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankGreenObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankLightBlueObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankLightGrayObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankLimeObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankMagentaObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankOrangeObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankPinkObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankPurpleObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankRedObsidyisc;
import cc.thedudeguy.jukebukkit.items.colored.ItemBlankYellowObsidyisc;
import cc.thedudeguy.jukebukkit.jukebox.custom.JukeboxBasic;
import cc.thedudeguy.jukebukkit.jukebox.custom.JukeboxLongRange;
import cc.thedudeguy.jukebukkit.jukebox.custom.JukeboxLowRange;
import cc.thedudeguy.jukebukkit.jukebox.custom.JukeboxMaxRange;
import cc.thedudeguy.jukebukkit.jukebox.custom.JukeboxMidRange;

/**
 * Handles all of the custom items/blocks such as URL's to textures sounds, and block designs and textures.
 * @author Chris Churchwell
 *
 */
public class CustomsManager {
	
	public JukeBukkit plugin;
	
	public static final String SF_JUKEBOX_START = "http://dev.bukkit.org/media/attachments/21/202/jb_startup.wav";
	public static final String SF_JUKEBOX_ERROR = "http://dev.bukkit.org/media/attachments/21/203/jb_error.wav";
	
	public static final String TEXTURE_URL_WHITE_DISC = "http://dev.bukkit.org/media/attachments/21/200/rdisc_white.png";
	public static final String TEXTURE_URL_RED_DISC = "http://dev.bukkit.org/media/attachments/21/239/rdisc_red.png";
	public static final String TEXTURE_URL_GREEN_DISC = "http://dev.bukkit.org/media/attachments/21/322/rdisc_green.png";
	public static final String TEXTURE_URL_BROWN_DISC = "http://dev.bukkit.org/media/attachments/21/319/rdisc_brown.png";
	public static final String TEXTURE_URL_BLUE_DISC = "http://dev.bukkit.org/media/attachments/21/318/rdisc_blue.png";
	public static final String TEXTURE_URL_PURPLE_DISC = "http://dev.bukkit.org/media/attachments/21/329/rdisc_purple.png";
	public static final String TEXTURE_URL_CYAN_DISC = "http://dev.bukkit.org/media/attachments/21/320/rdisc_cyan.png";
	public static final String TEXTURE_URL_LIGHTGRAY_DISC = "http://dev.bukkit.org/media/attachments/21/324/rdisc_lightgray.png";
	public static final String TEXTURE_URL_GRAY_DISC = "http://dev.bukkit.org/media/attachments/21/321/rdisc_gray.png";
	public static final String TEXTURE_URL_PINK_DISC = "http://dev.bukkit.org/media/attachments/21/328/rdisc_pink.png";
	public static final String TEXTURE_URL_LIME_DISC = "http://dev.bukkit.org/media/attachments/21/325/rdisc_lime.png";
	public static final String TEXTURE_URL_YELLOW_DISC = "http://dev.bukkit.org/media/attachments/21/330/rdisc_yellow.png";
	public static final String TEXTURE_URL_LIGHTBLUE_DISC = "http://dev.bukkit.org/media/attachments/21/323/rdisc_lightblue.png";
	public static final String TEXTURE_URL_MAGENTA_DISC = "http://dev.bukkit.org/media/attachments/21/326/rdisc_magenta.png";
	public static final String TEXTURE_URL_ORANGE_DISC = "http://dev.bukkit.org/media/attachments/21/327/rdisc_orange.png";
	public static final String TEXTURE_URL_BLACK_DISC = "http://dev.bukkit.org/media/attachments/21/317/rdisc_black.png";
	
	public static final String TEXTURE_URL_WHITE_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/331/rdisc_white_burned.png";
	public static final String TEXTURE_URL_RED_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/332/rdisc_red_burned.png";
	public static final String TEXTURE_URL_GREEN_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/338/rdisc_green_burned.png";
	public static final String TEXTURE_URL_BROWN_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/335/rdisc_brown_burned.png";
	public static final String TEXTURE_URL_BLUE_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/334/rdisc_blue_burned.png";
	public static final String TEXTURE_URL_PURPLE_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/345/rdisc_purple_burned.png";
	public static final String TEXTURE_URL_CYAN_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/336/rdisc_cyan_burned.png";
	public static final String TEXTURE_URL_LIGHTGRAY_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/340/rdisc_lightgray_burned.png";
	public static final String TEXTURE_URL_GRAY_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/337/rdisc_gray_burned.png";
	public static final String TEXTURE_URL_PINK_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/344/rdisc_pink_burned.png";
	public static final String TEXTURE_URL_LIME_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/341/rdisc_lime_burned.png";
	public static final String TEXTURE_URL_YELLOW_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/346/rdisc_yellow_burned.png";
	public static final String TEXTURE_URL_LIGHTBLUE_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/339/rdisc_lightblue_burned.png";
	public static final String TEXTURE_URL_MAGENTA_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/342/rdisc_magenta_burned.png";
	public static final String TEXTURE_URL_ORANGE_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/343/rdisc_orange_burned.png";
	public static final String TEXTURE_URL_BLACK_DISC_BURNED = "http://dev.bukkit.org/media/attachments/21/333/rdisc_black_burned.png";
	
	public static final String TEXTURE_URL_LABEL = "http://dev.bukkit.org/media/attachments/21/204/label.png";
	
	public static final String TEXTURE_URL_BLOCKS = "http://dev.bukkit.org/media/attachments/21/413/customblocksv2.png";
	
	//public static final String TEXTURE_URL_GUI_PAPER = "http://chrischurchwell.com/minecraft/paper.png";
	
	/** Item Block id's **/
	//public static int WHITE_DISC;
	//public static int PROTOTYPE_JUKEBOX;
	
	public static Texture customBlockTexture;
	
	//public Texture prototypeJukeboxTexture;
	//public Texture prototypeBurnerTexture;
	
	//custom blocks.
	public static BlockPrototypeBurner prototypeBurner;
	public static JukeboxBasic jukeboxBasic;
	public static JukeboxLowRange jukeboxLowRange;
	public static JukeboxMidRange jukeboxMidRange;
	public static JukeboxLongRange jukeboxLongRange;
	public static JukeboxMaxRange jukeboxMaxRange;
	
	//custom items.
	
	public CustomsManager(JukeBukkit plugin)
	{
		this.plugin = plugin;
		
		//load item id's
		//WHITE_DISC = new ItemBlankObsidyisc(plugin).getCustomId();
		
		//client preloads
		setPreCaches();
	}
	
	public Texture getCustomBlockTexture()
	{
		return customBlockTexture;
	}
	
	
	public void createCustomTextures()
	{
		customBlockTexture = new Texture(this.plugin, TEXTURE_URL_BLOCKS, 256, 256, 16);
	}
	
	public void createCustomItems()
	{
		
	}
	public void createCustomBlocks()
	{
		//initialize the custom blocks...
		jukeboxBasic = new JukeboxBasic(plugin, customBlockTexture);
		jukeboxLowRange = new JukeboxLowRange(plugin, customBlockTexture);
		jukeboxMidRange = new JukeboxMidRange(plugin, customBlockTexture);
		jukeboxLongRange = new JukeboxLongRange(plugin, customBlockTexture);
		jukeboxMaxRange = new JukeboxMaxRange(plugin, customBlockTexture);
		prototypeBurner = new BlockPrototypeBurner(plugin, customBlockTexture);
		
	}
	public void setPreCaches()
	{
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_WHITE_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_RED_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_GREEN_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_BROWN_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_BLUE_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_PURPLE_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_CYAN_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_LIGHTGRAY_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_GRAY_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_PINK_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_YELLOW_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_LIGHTBLUE_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_MAGENTA_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_ORANGE_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_BLACK_DISC);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_LIME_DISC);
		
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_WHITE_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_RED_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_GREEN_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_BROWN_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_BLUE_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_PURPLE_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_CYAN_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_LIGHTGRAY_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_GRAY_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_PINK_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_YELLOW_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_LIGHTBLUE_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_MAGENTA_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_ORANGE_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_BLACK_DISC_BURNED);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_LIME_DISC_BURNED);
		
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_LABEL);
		
		SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_BLOCKS);
		//SpoutManager.getFileManager().addToPreLoginCache(plugin, TEXTURE_URL_GUI_PAPER);
		
		SpoutManager.getFileManager().addToPreLoginCache(plugin, SF_JUKEBOX_START);
		SpoutManager.getFileManager().addToPreLoginCache(plugin, SF_JUKEBOX_ERROR);
	}
	
	public void createRecipes()
	{
		
		///////////////////////
		// Basic Jukebox //
		///////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(jukeboxBasic, 1) )
				.shape("jn")
				.setIngredient('j', MaterialData.jukebox)
				.setIngredient('n', MaterialData.noteblock)
				);
		
		///////////////////////
		// Low Range Jukebox //
		///////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(jukeboxLowRange, 1) )
			.shape("www", "wjw", "www")
			.setIngredient('j', jukeboxBasic)
			.setIngredient('w', MaterialData.wood)
			);
		
		///////////////////////
		// Mid Range Jukebox //
		///////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(jukeboxMidRange, 1) )
			.shape("www", "njn", "www")
			.setIngredient('j', jukeboxLowRange)
			.setIngredient('w', MaterialData.wood)
			.setIngredient('n', MaterialData.noteblock)
			);
		
		///////////////////////
		// Long Range Jukebox //
		///////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(jukeboxLongRange, 1) )
			.shape("wnw", "njn", "wnw")
			.setIngredient('j', jukeboxMidRange)
			.setIngredient('w', MaterialData.wood)
			.setIngredient('n', MaterialData.noteblock)
			);
		
		///////////////////////
		// Max Range Jukebox //
		///////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(jukeboxMaxRange, 1) )
			.shape("nnn", "njn", "nnn")
			.setIngredient('j', jukeboxLongRange)
			.setIngredient('n', MaterialData.noteblock)
			);
		
		//////////////////////
		// Prototype Burner //
		//////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(prototypeBurner, 1) )
				.shape("jf")
				.setIngredient('j', MaterialData.jukebox)
				.setIngredient('f', MaterialData.furnace)
				);
		
		/////////////////////
		// Blank Obsidyisc //
		/////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankObsidyisc(plugin), 1) )
				.shape(" o ", "oRo", " o ")
				.setIngredient('o', MaterialData.obsidian)
				.setIngredient('R', MaterialData.redstone)
				);
		
		///////////////////////////
		// Black Blank Obsidyisc //
		///////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankBlackObsidyisc(plugin), 1) )
				.shape("d", "o")
				.setIngredient('d', MaterialData.inkSac)
				.setIngredient('o', new ItemBlankObsidyisc(plugin))
				);
		
		/////////////////////////
		// Red Blank Obsidyisc //
		/////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankRedObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.roseRed)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		///////////////////////////
		// Green Blank Obsidyisc //
		///////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankGreenObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.cactusGreen)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		/////////////////////////
		// Brown Blank Obsidyisc //
		/////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankBrownObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.cocoaBeans)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		//////////////////////////
		// Blue Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankBlueObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.lapisLazuli)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		////////////////////////////
		// Purple Blank Obsidyisc //
		////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankPurpleObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.purpleDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		//////////////////////////
		// Cyan Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankCyanObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.cyanDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		////////////////////////////////
		// Light Gray Blank Obsidyisc //
		////////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankLightGrayObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.lightGrayDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		//////////////////////////
		// Gray Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankGrayObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.grayDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		//////////////////////////
		// Pink Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankPinkObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.pinkDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		//////////////////////////
		// Lime Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankLimeObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.limeDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		////////////////////////////
		// Yellow Blank Obsidyisc //
		////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankYellowObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.dandelionYellow)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		////////////////////////////////
		// Light Blue Blank Obsidyisc //
		////////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankLightBlueObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.lightBlueDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		/////////////////////////////
		// Magenta Blank Obsidyisc //
		/////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankMagentaObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.magentaDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		////////////////////////////
		// Orange Blank Obsidyisc //
		////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( SpoutManager.getMaterialManager().getCustomItemStack(new ItemBlankOrangeObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.orangeDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
	}
}
