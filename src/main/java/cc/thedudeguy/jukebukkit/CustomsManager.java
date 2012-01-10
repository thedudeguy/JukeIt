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
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.inventory.SpoutItemStack;
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
	
	private File textureFile;
	private FileConfiguration  textureConfig;
	
	public static final String SF_JUKEBOX_START = "http://dev.bukkit.org/media/attachments/21/202/jb_startup.wav";
	public static final String SF_JUKEBOX_ERROR = "http://dev.bukkit.org/media/attachments/21/203/jb_error.wav";
	
	public static String TEXTURE_URL_WHITE_DISC;
	public static String TEXTURE_URL_RED_DISC;
	public static String TEXTURE_URL_GREEN_DISC;
	public static String TEXTURE_URL_BROWN_DISC;
	public static String TEXTURE_URL_BLUE_DISC;
	public static String TEXTURE_URL_PURPLE_DISC;
	public static String TEXTURE_URL_CYAN_DISC;
	public static String TEXTURE_URL_LIGHTGRAY_DISC;
	public static String TEXTURE_URL_GRAY_DISC;
	public static String TEXTURE_URL_PINK_DISC;
	public static String TEXTURE_URL_LIME_DISC;
	public static String TEXTURE_URL_YELLOW_DISC;
	public static String TEXTURE_URL_LIGHTBLUE_DISC;
	public static String TEXTURE_URL_MAGENTA_DISC;
	public static String TEXTURE_URL_ORANGE_DISC;
	public static String TEXTURE_URL_BLACK_DISC;
	
	public static String TEXTURE_URL_WHITE_DISC_BURNED;
	public static String TEXTURE_URL_RED_DISC_BURNED;
	public static String TEXTURE_URL_GREEN_DISC_BURNED;
	public static String TEXTURE_URL_BROWN_DISC_BURNED;
	public static String TEXTURE_URL_BLUE_DISC_BURNED;
	public static String TEXTURE_URL_PURPLE_DISC_BURNED;
	public static String TEXTURE_URL_CYAN_DISC_BURNED;
	public static String TEXTURE_URL_LIGHTGRAY_DISC_BURNED;
	public static String TEXTURE_URL_GRAY_DISC_BURNED;
	public static String TEXTURE_URL_PINK_DISC_BURNED;
	public static String TEXTURE_URL_LIME_DISC_BURNED;
	public static String TEXTURE_URL_YELLOW_DISC_BURNED;
	public static String TEXTURE_URL_LIGHTBLUE_DISC_BURNED;
	public static String TEXTURE_URL_MAGENTA_DISC_BURNED;
	public static String TEXTURE_URL_ORANGE_DISC_BURNED;
	public static String TEXTURE_URL_BLACK_DISC_BURNED;
	
	public static String TEXTURE_URL_LABEL;
	
	public static String TEXTURE_URL_BLOCKS;
	
	//public static final String TEXTURE_URL_GUI_PAPER = "http://chrischurchwell.com/minecraft/paper.png";
	
	public static Texture customBlockTexture;
	
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
		
		//load the texture config.
		textureFile = new File(plugin.getDataFolder(), "textures.yml");
		textureConfig = YamlConfiguration.loadConfiguration(textureFile);
		
		//create any missing configurations in the config file
		textureConfig.set("texture.blockfaces", textureConfig.getString("texture.blockfaces", "default"));
		
		textureConfig.set("texture.discs.blank.white", textureConfig.getString("texture.discs.blank.white", "default"));
		textureConfig.set("texture.discs.blank.red", textureConfig.getString("texture.discs.blank.red", "default"));
		textureConfig.set("texture.discs.blank.green", textureConfig.getString("texture.discs.blank.green", "default"));
		textureConfig.set("texture.discs.blank.brown", textureConfig.getString("texture.discs.blank.brown", "default"));
		textureConfig.set("texture.discs.blank.blue", textureConfig.getString("texture.discs.blank.blue", "default"));
		textureConfig.set("texture.discs.blank.purple", textureConfig.getString("texture.discs.blank.purple", "default"));
		textureConfig.set("texture.discs.blank.cyan", textureConfig.getString("texture.discs.blank.cyan", "default"));
		textureConfig.set("texture.discs.blank.lightgray", textureConfig.getString("texture.discs.blank.lightgray", "default"));
		textureConfig.set("texture.discs.blank.gray", textureConfig.getString("texture.discs.blank.gray", "default"));
		textureConfig.set("texture.discs.blank.pink", textureConfig.getString("texture.discs.blank.pink", "default"));
		textureConfig.set("texture.discs.blank.lime", textureConfig.getString("texture.discs.blank.lime", "default"));
		textureConfig.set("texture.discs.blank.yellow", textureConfig.getString("texture.discs.blank.yellow", "default"));
		textureConfig.set("texture.discs.blank.lightblue", textureConfig.getString("texture.discs.blank.lightblue", "default"));
		textureConfig.set("texture.discs.blank.magenta", textureConfig.getString("texture.discs.blank.magenta", "default"));
		textureConfig.set("texture.discs.blank.orange", textureConfig.getString("texture.discs.blank.orange", "default"));
		textureConfig.set("texture.discs.blank.black", textureConfig.getString("texture.discs.blank.black", "default"));
		
		textureConfig.set("texture.discs.burned.white", textureConfig.getString("texture.discs.burned.white", "default"));
		textureConfig.set("texture.discs.burned.red", textureConfig.getString("texture.discs.burned.red", "default"));
		textureConfig.set("texture.discs.burned.green", textureConfig.getString("texture.discs.burned.green", "default"));
		textureConfig.set("texture.discs.burned.brown", textureConfig.getString("texture.discs.burned.brown", "default"));
		textureConfig.set("texture.discs.burned.blue", textureConfig.getString("texture.discs.burned.blue", "default"));
		textureConfig.set("texture.discs.burned.purple", textureConfig.getString("texture.discs.burned.purple", "default"));
		textureConfig.set("texture.discs.burned.cyan", textureConfig.getString("texture.discs.burned.cyan", "default"));
		textureConfig.set("texture.discs.burned.lightgray", textureConfig.getString("texture.discs.burned.lightgray", "default"));
		textureConfig.set("texture.discs.burned.gray", textureConfig.getString("texture.discs.burned.gray", "default"));
		textureConfig.set("texture.discs.burned.pink", textureConfig.getString("texture.discs.burned.pink", "default"));
		textureConfig.set("texture.discs.burned.lime", textureConfig.getString("texture.discs.burned.lime", "default"));
		textureConfig.set("texture.discs.burned.yellow", textureConfig.getString("texture.discs.burned.yellow", "default"));
		textureConfig.set("texture.discs.burned.lightblue", textureConfig.getString("texture.discs.burned.lightblue", "default"));
		textureConfig.set("texture.discs.burned.magenta", textureConfig.getString("texture.discs.burned.magenta", "default"));
		textureConfig.set("texture.discs.burned.orange", textureConfig.getString("texture.discs.burned.orange", "default"));
		textureConfig.set("texture.discs.burned.black", textureConfig.getString("texture.discs.burned.black", "default"));
		
		textureConfig.set("texture.label", textureConfig.getString("texture.label", "default"));
		
		//load the config into static vars
		TEXTURE_URL_BLOCKS = (!textureConfig.getString("texture.blockfaces").equalsIgnoreCase("default")?textureConfig.getString("texture.blockfaces"):"http://dev.bukkit.org/media/attachments/21/413/customblocksv2.png");
		
		TEXTURE_URL_WHITE_DISC = (!textureConfig.getString("texture.discs.blank.white").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.white"):"http://dev.bukkit.org/media/attachments/21/200/rdisc_white.png");
		TEXTURE_URL_RED_DISC = (!textureConfig.getString("texture.discs.blank.red").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.red"):"http://dev.bukkit.org/media/attachments/21/239/rdisc_red.png");
		TEXTURE_URL_GREEN_DISC = (!textureConfig.getString("texture.discs.blank.green").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.green"):"http://dev.bukkit.org/media/attachments/21/322/rdisc_green.png");
		TEXTURE_URL_BROWN_DISC = (!textureConfig.getString("texture.discs.blank.brown").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.brown"):"http://dev.bukkit.org/media/attachments/21/319/rdisc_brown.png");
		TEXTURE_URL_BLUE_DISC = (!textureConfig.getString("texture.discs.blank.blue").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.blue"):"http://dev.bukkit.org/media/attachments/21/318/rdisc_blue.png");
		TEXTURE_URL_PURPLE_DISC = (!textureConfig.getString("texture.discs.blank.purple").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.purple"):"http://dev.bukkit.org/media/attachments/21/329/rdisc_purple.png");
		TEXTURE_URL_CYAN_DISC = (!textureConfig.getString("texture.discs.blank.cyan").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.cyan"):"http://dev.bukkit.org/media/attachments/21/320/rdisc_cyan.png");
		TEXTURE_URL_LIGHTGRAY_DISC = (!textureConfig.getString("texture.discs.blank.lightgray").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.lightgray"):"http://dev.bukkit.org/media/attachments/21/324/rdisc_lightgray.png");
		TEXTURE_URL_GRAY_DISC = (!textureConfig.getString("texture.discs.blank.gray").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.gray"):"http://dev.bukkit.org/media/attachments/21/321/rdisc_gray.png");
		TEXTURE_URL_PINK_DISC = (!textureConfig.getString("texture.discs.blank.pink").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.pink"):"http://dev.bukkit.org/media/attachments/21/328/rdisc_pink.png");
		TEXTURE_URL_LIME_DISC = (!textureConfig.getString("texture.discs.blank.lime").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.lime"):"http://dev.bukkit.org/media/attachments/21/325/rdisc_lime.png");
		TEXTURE_URL_YELLOW_DISC = (!textureConfig.getString("texture.discs.blank.yellow").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.yellow"):"http://dev.bukkit.org/media/attachments/21/330/rdisc_yellow.png");
		TEXTURE_URL_LIGHTBLUE_DISC = (!textureConfig.getString("texture.discs.blank.blue").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.blue"):"http://dev.bukkit.org/media/attachments/21/323/rdisc_lightblue.png");
		TEXTURE_URL_MAGENTA_DISC = (!textureConfig.getString("texture.discs.blank.magenta").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.magenta"):"http://dev.bukkit.org/media/attachments/21/326/rdisc_magenta.png");
		TEXTURE_URL_ORANGE_DISC = (!textureConfig.getString("texture.discs.blank.orange").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.orange"):"http://dev.bukkit.org/media/attachments/21/327/rdisc_orange.png");
		TEXTURE_URL_BLACK_DISC = (!textureConfig.getString("texture.discs.blank.black").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.blank.black"):"http://dev.bukkit.org/media/attachments/21/317/rdisc_black.png");
		
		TEXTURE_URL_WHITE_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.white").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.white"):"http://dev.bukkit.org/media/attachments/21/331/rdisc_white_burned.png");
		TEXTURE_URL_RED_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.red").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.red"):"http://dev.bukkit.org/media/attachments/21/332/rdisc_red_burned.png");
		TEXTURE_URL_GREEN_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.green").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.green"):"http://dev.bukkit.org/media/attachments/21/338/rdisc_green_burned.png");
		TEXTURE_URL_BROWN_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.brown").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.brown"):"http://dev.bukkit.org/media/attachments/21/335/rdisc_brown_burned.png");
		TEXTURE_URL_BLUE_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.blue").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.blue"):"http://dev.bukkit.org/media/attachments/21/334/rdisc_blue_burned.png");
		TEXTURE_URL_PURPLE_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.purple").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.purple"):"http://dev.bukkit.org/media/attachments/21/345/rdisc_purple_burned.png");
		TEXTURE_URL_CYAN_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.cyan").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.cyan"):"http://dev.bukkit.org/media/attachments/21/336/rdisc_cyan_burned.png");
		TEXTURE_URL_LIGHTGRAY_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.lightgray").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.lightgray"):"http://dev.bukkit.org/media/attachments/21/340/rdisc_lightgray_burned.png");
		TEXTURE_URL_GRAY_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.gray").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.gray"):"http://dev.bukkit.org/media/attachments/21/337/rdisc_gray_burned.png");
		TEXTURE_URL_PINK_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.pink").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.pink"):"http://dev.bukkit.org/media/attachments/21/344/rdisc_pink_burned.png");
		TEXTURE_URL_LIME_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.lime").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.lime"):"http://dev.bukkit.org/media/attachments/21/341/rdisc_lime_burned.png");
		TEXTURE_URL_YELLOW_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.yellow").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.yellow"):"http://dev.bukkit.org/media/attachments/21/346/rdisc_yellow_burned.png");
		TEXTURE_URL_LIGHTBLUE_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.lightblue").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.lightblue"):"http://dev.bukkit.org/media/attachments/21/339/rdisc_lightblue_burned.png");
		TEXTURE_URL_MAGENTA_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.magenta").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.magenta"):"http://dev.bukkit.org/media/attachments/21/342/rdisc_magenta_burned.png");
		TEXTURE_URL_ORANGE_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.orange").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.orange"):"http://dev.bukkit.org/media/attachments/21/343/rdisc_orange_burned.png");
		TEXTURE_URL_BLACK_DISC_BURNED = (!textureConfig.getString("texture.discs.burned.black").equalsIgnoreCase("default")?textureConfig.getString("texture.discs.burned.black"):"http://dev.bukkit.org/media/attachments/21/333/rdisc_black_burned.png");
		
		TEXTURE_URL_LABEL = (!textureConfig.getString("texture.label").equalsIgnoreCase("default")?textureConfig.getString("texture.label"):"http://dev.bukkit.org/media/attachments/21/204/label.png");
		
		//save.
		try {
			textureConfig.save(textureFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
				new SpoutShapedRecipe( new SpoutItemStack(jukeboxBasic, 1) )
				.shape("jn")
				.setIngredient('j', MaterialData.jukebox)
				.setIngredient('n', MaterialData.noteblock)
				);
		
		///////////////////////
		// Low Range Jukebox //
		///////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( new SpoutItemStack(jukeboxLowRange, 1) )
			.shape("www", "wjw", "www")
			.setIngredient('j', jukeboxBasic)
			.setIngredient('w', MaterialData.wood)
			);
		
		///////////////////////
		// Mid Range Jukebox //
		///////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( new SpoutItemStack(jukeboxMidRange, 1) )
			.shape("www", "njn", "www")
			.setIngredient('j', jukeboxLowRange)
			.setIngredient('w', MaterialData.wood)
			.setIngredient('n', MaterialData.noteblock)
			);
		
		///////////////////////
		// Long Range Jukebox //
		///////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( new SpoutItemStack(jukeboxLongRange, 1) )
			.shape("wnw", "njn", "wnw")
			.setIngredient('j', jukeboxMidRange)
			.setIngredient('w', MaterialData.wood)
			.setIngredient('n', MaterialData.noteblock)
			);
		
		///////////////////////
		// Max Range Jukebox //
		///////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( new SpoutItemStack(jukeboxMaxRange, 1) )
			.shape("nnn", "njn", "nnn")
			.setIngredient('j', jukeboxLongRange)
			.setIngredient('n', MaterialData.noteblock)
			);
		
		//////////////////////
		// Prototype Burner //
		//////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(prototypeBurner, 1) )
				.shape("jf")
				.setIngredient('j', MaterialData.jukebox)
				.setIngredient('f', MaterialData.furnace)
				);
		
		/////////////////////
		// Blank Obsidyisc //
		/////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankObsidyisc(plugin), 1) )
				.shape(" o ", "oRo", " o ")
				.setIngredient('o', MaterialData.obsidian)
				.setIngredient('R', MaterialData.redstone)
				);
		
		///////////////////////////
		// Black Blank Obsidyisc //
		///////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankBlackObsidyisc(plugin), 1) )
				.shape("d", "o")
				.setIngredient('d', MaterialData.inkSac)
				.setIngredient('o', new ItemBlankObsidyisc(plugin))
				);
		
		/////////////////////////
		// Red Blank Obsidyisc //
		/////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankRedObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.roseRed)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		///////////////////////////
		// Green Blank Obsidyisc //
		///////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankGreenObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.cactusGreen)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		/////////////////////////
		// Brown Blank Obsidyisc //
		/////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankBrownObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.cocoaBeans)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		//////////////////////////
		// Blue Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankBlueObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.lapisLazuli)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		////////////////////////////
		// Purple Blank Obsidyisc //
		////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankPurpleObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.purpleDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		//////////////////////////
		// Cyan Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankCyanObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.cyanDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		////////////////////////////////
		// Light Gray Blank Obsidyisc //
		////////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankLightGrayObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.lightGrayDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		//////////////////////////
		// Gray Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankGrayObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.grayDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		//////////////////////////
		// Pink Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankPinkObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.pinkDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		//////////////////////////
		// Lime Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankLimeObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.limeDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		////////////////////////////
		// Yellow Blank Obsidyisc //
		////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankYellowObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.dandelionYellow)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		////////////////////////////////
		// Light Blue Blank Obsidyisc //
		////////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankLightBlueObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.lightBlueDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		/////////////////////////////
		// Magenta Blank Obsidyisc //
		/////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankMagentaObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.magentaDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
		
		////////////////////////////
		// Orange Blank Obsidyisc //
		////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
				new SpoutShapedRecipe( new SpoutItemStack(new ItemBlankOrangeObsidyisc(plugin), 1) )
				.shape("r", "d")
				.setIngredient('r', MaterialData.orangeDye)
				.setIngredient('d', new ItemBlankObsidyisc(plugin))
				);
	}
}
