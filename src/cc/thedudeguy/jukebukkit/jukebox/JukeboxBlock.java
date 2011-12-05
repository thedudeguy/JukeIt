package cc.thedudeguy.jukebukkit.jukebox;

import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.CustomsManager;
import cc.thedudeguy.jukebukkit.JukeBoxManager;
import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.items.ItemBurnedObsidyisc;

public abstract class JukeboxBlock extends GenericCustomBlock implements Jukebox  {
	
	private JukeBukkit plugin;
	public JukeBoxManager jukeboxManager;
	/**
	* Creates a new opaque/solid cube block material
	*
	* @param plugin making the block
	* @param name of the block
	* @param design to use for the block
	*/
	public JukeboxBlock(JukeBukkit plugin, String name, GenericCubeBlockDesign design) {
		super(plugin, name);
		this.setBlockDesign(design);
		this.plugin = plugin;
		jukeboxManager = plugin.getJukeBoxManager();
	}
	
	public boolean canPlaceBlockAt(World arg0, int arg1, int arg2, int arg3) {
		//block is placeable.
		return true;
	}
	
	public boolean canPlaceBlockAt(World arg0, int arg1, int arg2, int arg3, BlockFace arg4) {
		//placeable anywhere
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
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player) {
		//construct our location
		Location location = new Location(world, (double)x, (double)y, (double)z);
		String locationKey = jukeboxManager.createLocationKey(location);
				
		//aka left clicked - play disc
		if (jukeboxManager.hasDisc(locationKey)) {
			int discId = jukeboxManager.getDisc(locationKey);
			if (plugin.getDiscsManager().hasDiscId(discId)) {
			
				String url = plugin.getDiscsManager().getUrl(discId);
				playMusic(url, location, player);
				return;
			}
		}
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
		return;
	}

	@Override
	public void onBlockDestroyed(World world, int x, int y, int z) {
		//when the block is destroyed if theres a disc in it the disc should be freed from its jail.
		Location location = new Location(world, (double)x, (double)y, (double)z);
		String locationKey = jukeboxManager.createLocationKey(location);
		
		if (jukeboxManager.hasDisc(locationKey)) {
			int discId = jukeboxManager.getDisc(locationKey);
			
			int color = plugin.getDiscsManager().getColor(discId);
			
			ItemBurnedObsidyisc disc = new ItemBurnedObsidyisc(plugin, plugin.getDiscsManager().getKey(discId), plugin.getDiscsManager().getTitle(discId), color);
			ItemStack iss = new SpoutItemStack(disc, 1);
			location.getWorld().dropItem(location, iss);
			stopMusic(location);
		}
		//remove disc from ff
		plugin.getJukeBoxManager().removeDisc(locationKey);
	}

	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		//construct our location
		Location location = new Location(world, (double)x, (double)y, (double)z);
		String locationKey = plugin.getJukeBoxManager().createLocationKey(location);
		//aka right clicked - load or eject disc.
		//check if it has a disc.
		if (!jukeboxManager.hasDisc(locationKey)) {
			//jukebox is empty, load
			ItemStack inHand = player.getItemInHand();
					
			if (new SpoutItemStack(inHand).isCustomItem())
			{
				//we know its a custom item, go ahaed and remove 1 from the hand.
				if (inHand.getAmount()<2) {
					player.setItemInHand(null);
				} else {
					player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount()-1);
				}		
				
				CustomItem custom = (CustomItem) new SpoutItemStack(inHand).getMaterial();
				//see if its a burned disc.
				if (plugin.getDiscsManager().hasDiscId(custom.getCustomId())) {
					
					jukeboxManager.insertDisc(locationKey, custom.getCustomId());
					SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_START, false, location, 3);
						
					/** start the music **/
					int discId = jukeboxManager.getDisc(locationKey);
					if (plugin.getDiscsManager().hasDiscId(discId)) {
						String url = plugin.getDiscsManager().getUrl(discId);
						playMusic(url, location, player);
						return true;
					}
				} else {
					//eject it its bad whatever it is! lolza!1!!
					ItemStack e = inHand.clone();
					e.setAmount(1);
					location.setY(location.getY()+1);
					location.getWorld().dropItem(location, e);
				}
			}
			
			SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
			return true;
		} else {
			//has disc, eject
			//create the disc from the id.
			int discId = plugin.getJukeBoxManager().getDisc(locationKey);
			
			int color = plugin.getDiscsManager().getColor(discId);
			//spawn disc
			ItemBurnedObsidyisc disc = new ItemBurnedObsidyisc(plugin, plugin.getDiscsManager().getKey(discId), plugin.getDiscsManager().getTitle(discId), color);
			ItemStack iss = new SpoutItemStack(disc, 1);
			location.setY(location.getY()+1);
			location.getWorld().dropItem(location, iss);
					
			//remove disc from ff
			plugin.getJukeBoxManager().removeDisc(locationKey);
					
			//stop playing music for people around this box.
			stopMusic(location);
					
		}
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
		return true;
	}

	@Override
	public void onBlockPlace(World arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBlockPlace(World arg0, int arg1, int arg2, int arg3, LivingEntity arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEntityMoveAt(World arg0, int arg1, int arg2, int arg3,
			Entity arg4) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean isIndirectlyProvidingPowerTo(World arg0, int arg1, int arg2, int arg3, BlockFace arg4) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int changedId) {
		
		if (!canRedstoneActivate()) return;
		
		Location location = new Location(world, (double)x, (double)y, (double)z);
		SpoutBlock block = (SpoutBlock) location.getBlock();
		if (block.isBlockPowered()) {
			Serializable data = block.getData("JukeboxBlock.redstone");
			if (data == null || ((Integer)data) == 0)
			{
				//block is currently not powered. //set to powered
				block.setData("JukeboxBlock.redstone", 1);
				
				//if the jukebox has a disc in it, go ahead and play it.
				String locationKey = plugin.getJukeBoxManager().createLocationKey(location);
				if (plugin.getJukeBoxManager().hasDisc(locationKey))
				{
					//play disc.
					int discId = plugin.getJukeBoxManager().getDisc(locationKey);
					if (plugin.getDiscsManager().hasDiscId(discId))
					{
						String url = plugin.getDiscsManager().getUrl(discId);
						playMusic(url, location);
						return;
					}
				}
			} else {
				//block is currently powered. do nothing.
				//set power setting back to unpowered
				block.setData("JukeboxBlock.redstone", 0);
			}
		}
	}
	
}
