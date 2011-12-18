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

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.items.ItemBlankObsidyisc;
import cc.thedudeguy.jukebukkit.items.ItemBurnedObsidyisc;


/**
 * The Prototype Disc Burner. Basic Custom Block in which I can expand with.
 * @author Chris Churchwell
 */
public class BlockPrototypeBurner extends GenericCubeCustomBlock {

	public JukeBukkit plugin;
	
	public GenericCubeBlockDesign blockDesign;
	
	/**
	 * Construct
	 * @param JukeBukkit plugin - JukeBukkit instance
	 */
	public BlockPrototypeBurner(JukeBukkit plugin)
	{
		super(
			plugin, 
			"Prototype Burner rev. A", 
			new GenericCubeBlockDesign(
				plugin, 
				plugin.getCustomsManager().getCustomBlockTexture(), 
				new int[] { 2, 3, 3, 4, 3, 2 }
			)
		);
		
		//int faces => { bottom, north, ?, south (should default for inventory faceing), ?, top}
		this.plugin = plugin;
	}
	public BlockPrototypeBurner(JukeBukkit plugin, Texture texture)
	{
		super(
			plugin, 
			"Prototype Burner rev. A", 
			new GenericCubeBlockDesign(
				plugin, 
				texture, 
				new int[] { 2, 3, 3, 4, 3, 2 }
			)
		);
		//int faces => { bottom, north, ?, south (should default for inventory faceing), ?, top}
		this.plugin = plugin;
	}
	
	/**
	 * BurnButton extends a generic button to provide the onbuttonclick event, handling the text input entry.
	 * @author Chris Churchwell
	 */
	public class BurnButton extends GenericButton
	{
		private GenericTextField input;
		private GenericPopup popup;
		private JukeBukkit plugin;
		private Location location;
		
		public BurnButton(JukeBukkit plugin, GenericPopup assocPopup, GenericTextField assocInputField, Location location)
		{
			super();
			setText("Burn!");
			input = assocInputField;
			popup = assocPopup;
			this.location = location;
			this.plugin = plugin;
			
			//plugin.log.info("Burn Disc Button Initialized");
		}
		public void onButtonClick(ButtonClickEvent event) 
		{
			//event.getPlayer().sendMessage(plugin.getDescription().getFullName());
			
	       
	        SpoutPlayer player = event.getPlayer();
			
			String url = input.getText().trim();
			
			if(url == "") {
					player.sendMessage("The URL cannot be blank. You wouldnt want to waste a disc!");
					return;
			} else if (url.contains(".mp3") || url.contains(".m4a") || url.contains(".aac")) {
					player.sendMessage("Currently only .ogg and .wav formats are supported. Please convert the file to one of those.");
					return;
			}
			
	        
	        if (player.getItemInHand() == null) {
	        	return;
	        }
	        
	        SpoutItemStack inHand = new SpoutItemStack(player.getItemInHand());
	        
	        if (!(inHand.getMaterial() instanceof CustomItem)) {
	        	return;
	        }
	        
	        CustomItem disk = (CustomItem)inHand.getMaterial();
	        
	        //whats the color of the disc in hand?
	        int color = plugin.getDiscsManager().findDiscColor(disk);
	        
	        //remove 1 from hand
			if (inHand.getAmount()<2)
			{
				player.setItemInHand(null);
			} else {
				inHand.setAmount(inHand.getAmount()-1);
			}

			//create the key
			String key = UUID.randomUUID().toString();
			//create the physical disc for the pplayer
			ItemBurnedObsidyisc disc = new ItemBurnedObsidyisc(plugin, key, color);
			
			ItemStack iss = new SpoutItemStack(disc, 1);
			location.setY(location.getY()+1);
			location.getWorld().dropItem(location, iss);
			
			//set the disc data and save it
			plugin.getDiscsManager().add(disc.getCustomId());
			plugin.getDiscsManager().setUrl(disc.getCustomId(), url);
			plugin.getDiscsManager().setTitle(disc.getCustomId(), "Burned Obsidyisc");
			plugin.getDiscsManager().setKey(disc.getCustomId(), key);
			plugin.getDiscsManager().setColor(disc.getCustomId(), color);
			plugin.getDiscsManager().save();
			popup.close();
			
			SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_START, false, location, 4);
	        
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
			if (customInHand instanceof ItemBlankObsidyisc)
			{
				SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_START, false, location, 3);
				
				GenericPopup burnPopup = new GenericPopup();
				
				GenericLabel popupTitle = new GenericLabel("Disc Burner");
				burnPopup.attachWidget(plugin, popupTitle);
				
				GenericLabel urlLabel = new GenericLabel("URL:");
				urlLabel.setY(15);
				burnPopup.attachWidget(plugin, urlLabel);
				
				GenericTextField urlInput = new GenericTextField();
				urlInput.setMaximumCharacters(500);
				urlInput.setHeight(100).setWidth(200);
				urlInput.setY(15);
				urlInput.setX(40);
				urlInput.setMaximumLines(0);
				urlInput.setFocus(true);
				burnPopup.attachWidget(plugin, urlInput);
				
				BurnButton button = new BurnButton(plugin, burnPopup, urlInput, location);
				button.setY(140).setX(40);
				button.setWidth(200).setHeight(20);
				burnPopup.attachWidget(plugin, button);
				
				player.getMainScreen().attachPopupScreen(burnPopup);
				
				return true;
			}
		}
		
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
		return true;
	}
	
	@Override
	public void onBlockPlace(World arg0, int arg1, int arg2, int arg3) {
		
		plugin.log.info("onBlockPlace No Entity");
		
		// TODO Auto-generated method stub
		//plugin.log.info("onBlockPlace 1");
	}

	@Override
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity entity) {
		
		plugin.log.info("onBlockInteract Living Entity");
		
		//find which way the player is facing...
		//plugin.log.info("onBlockPlace 2");
		/*
		if(entity instanceof Player) {
			Player player = (Player) entity;
			player.sendMessage("WOW");
		}
		
		double rot = (entity.getLocation().getYaw() -90) % 360;
		if (rot < 0) {
            rot += 360.0;
        }
		
		int[] orientation;
		
		if (0 <= rot && rot < 45) {
            //North
			orientation = new int[] { 2, 4, 3, 3, 3, 2 };
        } else if (45 <= rot && rot < 135) {
        	//East
        	orientation = new int[] { 2, 3, 4, 3, 3, 2 };
        } else if (135 <= rot && rot < 215) {
            //South
        	orientation = new int[] { 2, 3, 3, 4, 3, 2 };
        } else if (215 <= rot && rot < 305) {
            //West
        	orientation = new int[] { 2, 3, 3, 3, 4, 2 };
        } else if (305 <= rot && rot <= 360) {
        	//North
        	orientation = new int[] { 2, 4, 3, 3, 3, 2 };
        } else {
        	//unknown
        	orientation = new int[] { 2, 4, 3, 3, 3, 2 };
        }
		
		GenericCubeBlockDesign newDesign = new GenericCubeBlockDesign(
				plugin, 
				plugin.getCustomsManager().customBlockTexture, 
				orientation
			);
		this.setBlockDesign(newDesign);
		*/
	}
	
	public boolean canPlaceBlockAt(World arg0, int arg1, int arg2, int arg3) {
		//true to place anywhere
		//plugin.log.info("canPlaceBlockAt 1");
		return true;
	}

	public boolean canPlaceBlockAt(World arg0, int arg1, int arg2, int arg3, BlockFace arg4) {
		//true to place anywhere
		//plugin.log.info("canPlaceBlockAt 2");
		return true;
	}
	
	public boolean isIndirectlyProdivingPowerTo(World arg0, int arg1, int arg2, int arg3, BlockFace arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProvidingPowerTo(World arg0, int arg1, int arg2, int arg3,
			BlockFace arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onBlockClicked(World arg0, int arg1, int arg2, int arg3, SpoutPlayer arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockDestroyed(World world, int x, int y, int z) {
		//CustomBlock newblock = new BlockPrototypeBurner(this.plugin);
		
	}

	@Override
	public void onEntityMoveAt(World arg0, int arg1, int arg2, int arg3,
			Entity arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNeighborBlockChange(World arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isIndirectlyProvidingPowerTo(World arg0, int arg1, int arg2,
			int arg3, BlockFace arg4) {
		// TODO Auto-generated method stub
		return false;
	}

}
