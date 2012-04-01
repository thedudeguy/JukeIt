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
package cc.thedudeguy.jukebukkit.materials.blocks;

import java.net.URL;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.DiscBurnerDesign;
import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;


/**
 * The Prototype Disc Burner. Basic Custom Block in which I can expand with.
 * @author Chris Churchwell
 */
public class DiscBurner extends GenericCubeCustomBlock {
	
	public GenericCubeBlockDesign blockDesign;
	
	/**
	 * Construct
	 * @param JukeBukkit plugin - JukeBukkit instance
	 */
	public DiscBurner()
	{
		super(
			JukeBukkit.instance, 
			"Obsidyisc Burner", 4,
			new DiscBurnerDesign(DiscBurnerDesign.SOUTH)
		);
		
		this.setName("Obsidyisc Burner");
		//int faces => { bottom, north, ?, south (should default for inventory faceing), ?, top}
	}
	
	public DiscBurner(int[] direction)
	{
		super(
			JukeBukkit.instance, 
			"discburner_"+direction.toString(), 4,
			new DiscBurnerDesign(direction)
		);
		
		this.setName("Disc Burner SubBlock (DO NOT USE)");
		this.setItemDrop(new SpoutItemStack(Blocks.discBurner, 1));
	}
	
	/**
	 * BurnButton extends a generic button to provide the onbuttonclick event, handling the text input entry.
	 * @author Chris Churchwell
	 */
	public class BurnButton extends GenericButton
	{
		private GenericTextField input;
		private GenericPopup popup;
		private Location location;
		
		public BurnButton(GenericPopup assocPopup, GenericTextField assocInputField, Location location)
		{
			super();
			setText("Burn!");
			input = assocInputField;
			popup = assocPopup;
			this.location = location;
			
			//plugin.log.info("Burn Disc Button Initialized");
		}
        @Override
		public void onButtonClick(ButtonClickEvent event) 
		{
			//event.getPlayer().sendMessage(plugin.getDescription().getFullName());
			
	       
	        SpoutPlayer player = event.getPlayer();
			
			String url = input.getText().trim();
			if(url.isEmpty()) {
				player.sendMessage("The URL cannot be blank. You wouldnt want to waste a disc!");
				return;
			}

			try {
				URL parseURL = new URL(url);
				String fname = parseURL.getFile().toLowerCase();
				if(!fname.endsWith(".ogg") && !fname.endsWith(".wav")) {
					player.sendMessage("Currently only .ogg and .wav formats are supported. Please convert the file to one of those.");
					return;
				}
			} catch(Exception e) {
				player.sendMessage("Invalid URL!");
				return;
			}
			
	        
	        if (player.getItemInHand() == null) {
	        	return;
	        }
	        
	        SpoutItemStack inHand = new SpoutItemStack(player.getItemInHand());
	        
	        if (!(inHand.getMaterial() instanceof BlankDisc)) {
	        	return;
	        }
	        
	        BlankDisc disk = (BlankDisc)inHand.getMaterial();
	        
	        //whats the color of the disc in hand?
	        int color = disk.getColor();
	        
	        //remove 1 from hand
			if (inHand.getAmount()<2) {
				player.setItemInHand(new ItemStack(Material.AIR));
			} else {
				inHand.setAmount(inHand.getAmount()-1);
				player.setItemInHand(inHand);
			}

			//create the key
			String key = BurnedDisc.generateNameKey();
			
			//add the disc into the database
			DiscData discData = JukeBukkit.instance.getDatabase().find(DiscData.class)
					.where()
						.ieq("nameKey", key)
					.findUnique();
			if (discData == null) discData = new DiscData();
			discData.setNameKey(key);
			discData.setUrl(url);
			discData.setLabel("");
			discData.setColor(color);
			JukeBukkit.instance.getDatabase().save(discData);
			
			//create the physical disc for the pplayer
			BurnedDisc disc = new BurnedDisc(discData);
			ItemStack iss = new SpoutItemStack(disc, 1);
			location.setY(location.getY()+1);
			location.getWorld().dropItem(location, iss);
			
			popup.close();
			
			SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, "jb_startup.wav", false, location, 8);
	        
	    }
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		//plugin.log.info("onBlockInteract");
		
		Location location = new Location(world, (double)x, (double)y, (double)z);
		ItemStack inHand = player.getItemInHand();
		
		if (inHand != null && (new SpoutItemStack(inHand).isCustomItem()))
		{
			//checks if item in hand is white disc.
			CustomItem customInHand = (CustomItem)new SpoutItemStack(inHand).getMaterial();
			if (customInHand instanceof BlankDisc)
			{
				SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, "jb_startup.wav", false, location, 8);
				
				GenericPopup burnPopup = new GenericPopup();
				
				GenericLabel popupTitle = new GenericLabel("Disc Burner");
                                popupTitle.setX(10).setY(10).setWidth(125).setHeight(15);
				burnPopup.attachWidget(JukeBukkit.instance, popupTitle);
				
				GenericLabel urlLabel = new GenericLabel("URL:");
				urlLabel.setX(10).setY(30).setWidth(40).setHeight(15);
				burnPopup.attachWidget(JukeBukkit.instance, urlLabel);
				
				GenericTextField urlInput = new GenericTextField();
				urlInput.setMaximumCharacters(500);
				urlInput.setHeight(100).setWidth(200);
				urlInput.setY(30);
				urlInput.setX(50);
				urlInput.setMaximumLines(0);
				urlInput.setFocus(true);
				burnPopup.attachWidget(JukeBukkit.instance, urlInput);
				
				BurnButton button = new BurnButton(burnPopup, urlInput, location);
				button.setY(150).setX(50);
				button.setWidth(200).setHeight(20);
				burnPopup.attachWidget(JukeBukkit.instance, button);
				
				player.getMainScreen().attachPopupScreen(burnPopup);
				
				return true;
			}
		}
		
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, "jb_error.wav", false, location, 8);
		return true;
	}
	
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity entity) {
		
		//Location location = new Location(world, (double)x, (double)y, (double)z);
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		
		//find which way the player is facing...
		double rot = (entity.getLocation().getYaw() -90) % 360;
		if (rot < 0) {
            rot += 360.0;
        }
		
		if (0 <= rot && rot < 45) {
            //WEST
			//Bukkit.getLogger().log(Level.INFO, "west");
			SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerWest);
			return;
        } else if (45 <= rot && rot < 135) {
        	//NORTH
        	//Bukkit.getLogger().log(Level.INFO, "north");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerNorth);
        	return;
        } else if (135 <= rot && rot < 215) {
            //EAST
        	//Bukkit.getLogger().log(Level.INFO, "east");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerEast);
        	return;
        } else if (215 <= rot && rot < 305) {
            //SOUTH
        	//Bukkit.getLogger().log(Level.INFO, "south");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerSouth);
        	return;
        } else if (305 <= rot && rot <= 360) {
        	//WEST
        	//Bukkit.getLogger().log(Level.INFO, "west");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerWest);
        	return;
        } else {
        	//unknown
        	//Bukkit.getLogger().log(Level.INFO, "unknown");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerSouth);
        	return;
        }
	}
	
}
