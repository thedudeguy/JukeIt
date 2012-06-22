package cc.thedudeguy.jukebukkit.gui.widget;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.ListWidget;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.materials.Items;
import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;

public class RepoBurnButton extends GenericButton {
	
	private ListWidget list;
	private Block block;
	
	public RepoBurnButton(ListWidget list, Block block) {
		super("Burn");
		this.list = list;
		this.block = block;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		
		if (!event.getPlayer().hasPermission("jukebukkit.use.blankdisc")) {
			event.getPlayer().sendMessage("You do not have permission to perform this action");
			event.getPlayer().sendMessage("(jukebukkit.use.blankdisc)");
			event.getPlayer().getMainScreen().getActivePopup().close();
			event.setCancelled(true);
			return;
		}
		
		Location location = block.getLocation();
		
		String url = null;
		String label = "";
		
		if (list.getSelectedItem() == null || list.getSelectedItem().getTitle().isEmpty()) {
			event.getPlayer().sendMessage("No selection made");
			event.getPlayer().getMainScreen().getActivePopup().close();
			return;
		}
		
		/*
		if (
				!list.getSelectedItem().getTitle().toLowerCase().endsWith(".ogg") && 
				!list.getSelectedItem().getTitle().toLowerCase().endsWith(".wav")
				) {
			event.getPlayer().sendMessage("Invalid Selection");
			event.getPlayer().getMainScreen().getActivePopup().close();
			return;
		}
		*/
		url = ((RepoSongItem)list.getSelectedItem()).getURL();
		label = ((RepoSongItem)list.getSelectedItem()).getTitle() + " " + ((RepoSongItem)list.getSelectedItem()).getText();
		if (url == null || url.equalsIgnoreCase(""))
		{
			event.getPlayer().sendMessage("An Error Occurred");
			event.getPlayer().getMainScreen().getActivePopup().close();
		}
		
		
		if (event.getPlayer().getItemInHand() == null) {
			event.getPlayer().sendMessage("Invalid Disc in Hand");
			event.getPlayer().getMainScreen().getActivePopup().close();
        	return;
        }
		
        SpoutPlayer player = (SpoutPlayer)event.getPlayer();
        
        
        SpoutItemStack inHand = new SpoutItemStack(player.getItemInHand());
        
        if (!(inHand.getMaterial() instanceof BlankDisc)) {
        	event.getPlayer().sendMessage("Invalid Disc in Hand");
        	event.getPlayer().getMainScreen().getActivePopup().close();
        	return;
        }
        
        BlankDisc disk = (BlankDisc)inHand.getMaterial();
        
        //whats the color of the disc in hand?
        int color = disk.getColor();
        
        //remove 1 from hand
      	if (inHand.getAmount()<2) {
      		event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
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
      		discData.setLabel(label);
      		discData.setColor(color);
      		JukeBukkit.instance.getDatabase().save(discData);
      	
      	//create the physical disc for the pplayer
    	BurnedDisc disc = new BurnedDisc(discData);
    	Items.burnedDiscs.put(key, disc);
    	ItemStack iss = new SpoutItemStack(disc, 1);
    	
    	location.setY(location.getY()+1);
    	location.getWorld().dropItem(location, iss);
    	
    	event.getPlayer().getMainScreen().getActivePopup().close();
    	
    	SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, "jb_startup.wav", false, location, 8);
    	
	}
}
