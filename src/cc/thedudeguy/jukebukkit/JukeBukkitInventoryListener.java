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

import org.bukkit.event.Event.Result;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.inventory.InventoryClickEvent;
import org.getspout.spoutapi.event.inventory.InventoryCraftEvent;
import org.getspout.spoutapi.event.inventory.InventoryListener;
import org.getspout.spoutapi.material.CustomItem;

/**
 * Inventory Listener to handle when the user clicks around in their Inventory
 * @author Chris Churchwell 
 *
 */
public class JukeBukkitInventoryListener extends InventoryListener {
	
	private JukeBukkit plugin;
	
	JukeBukkitInventoryListener(JukeBukkit plugin)
	{
		this.plugin = plugin;
	}
	
	public void onInventoryCraft(InventoryCraftEvent event)
	{
		plugin.log.info("Inventory Craft Event");
	}
	
	/**
	 * Basically handles when a user dropes a label onto a written disc.
	 */
	public void onInventoryClick(InventoryClickEvent event)
	{
		//only on right click.
		if (!event.isLeftClick())
		{
			//only if a Label is on the cursor. is it even a custom item?
			if (SpoutManager.getMaterialManager().isCustomItem(event.getCursor())) {
				
				CustomItem itemOnCursor = SpoutManager.getMaterialManager().getCustomItem(event.getCursor());
				if (itemOnCursor instanceof ItemLabel)
				{
					
					//yep, its a label. let see what were clicking on.
					if (SpoutManager.getMaterialManager().isCustomItem(event.getItem()))
					{
						
						//its custom could be a disc...
						CustomItem itemClickedOn = SpoutManager.getMaterialManager().getCustomItem(event.getItem());
						if ( itemClickedOn instanceof ItemBurnedObsidyisc)
						{
							//its a burned disc! we can do stuff to it.
							String label = plugin.getLabelManager().get(itemOnCursor.getCustomId());
							
							plugin.getDiscsManager().setTitle(itemClickedOn.getCustomId(), label);
							plugin.getDiscsManager().save();
							
							itemClickedOn.setName(label);
							
							//remove the item on the cursor.
							event.setResult(Result.ALLOW);
							event.setCursor(null);
							
							event.setCancelled(true);
							//return true;
						}
					}
				}
			}
		}
	}
}
