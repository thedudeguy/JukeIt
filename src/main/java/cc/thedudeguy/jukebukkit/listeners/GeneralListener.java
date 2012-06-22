package cc.thedudeguy.jukebukkit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import cc.thedudeguy.jukebukkit.permission.CraftPermission;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.util.Debug;

public class GeneralListener implements Listener {
	
	@EventHandler
	public void onCraftItem(CraftItemEvent event) {
		Debug.debug("CraftItemEvent");
		SpoutItemStack craftedStack = new SpoutItemStack(event.getRecipe().getResult());
		
		if (craftedStack.getMaterial() != null && craftedStack.getMaterial() instanceof CraftPermissible) {
			Debug.debug("crafted item has permission attached");
			
			CraftPermission permission = ((CraftPermissible)craftedStack.getMaterial()).getPermission();
			
			if (permission.hasCraftPermission() && !event.getWhoClicked().hasPermission(permission.getCraftPermission())) {
				event.setResult(Result.DENY);
				((Player)event.getWhoClicked()).sendMessage("You do not have permission to craft this item.");
				((Player)event.getWhoClicked()).sendMessage("("+permission.getCraftPermission()+")");
				event.setCancelled(true);
				return;
			}
			
		}
	}
}
