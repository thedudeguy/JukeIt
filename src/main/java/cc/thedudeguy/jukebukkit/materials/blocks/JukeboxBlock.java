package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.database.RecordPlayerData;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerDesign;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;

public abstract class JukeboxBlock extends GenericCustomBlock  {

	public JukeboxBlock(String name) {
		super(JukeBukkit.instance, name);
		setBlockDesign(getCustomBlockDesign());
	}
	
	public abstract int getRange();
	
	public abstract String getPermission();
	
	public abstract boolean canRedstoneActivate();
	
	public abstract GenericCubeBlockDesign getCustomBlockDesign();
	
	
	
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

	public boolean isProvidingPowerTo(World arg0, int arg1, int arg2, int arg3,
			BlockFace arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player) {
		/*
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
		*/
	}

	public void onBlockDestroyed(World world, int x, int y, int z) {
		//TODO Eject disc if contains a disc
		
		//delete ALL data associated to this location, just incase somehow multiples got into the database this will take care of that.
		List<RecordPlayerData> rpdall = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
				.where()
					.eq("x", (double)x)
					.eq("y", (double)y)
					.eq("z", (double)z)
					.ieq("worldName", world.getName())
				.findList();
		if (!rpdall.isEmpty()) {
			JukeBukkit.instance.getDatabase().delete(rpdall);
		}
	}

	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		Location location = new Location(world, (double)x, (double)y, (double)z);
		
		//get data from the db
		RecordPlayerData rpdata = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
				.where()
					.eq("x", (double)x)
					.eq("y", (double)y)
					.eq("z", (double)z)
					.ieq("worldName", world.getName())
				.findUnique();
		if (rpdata == null) {
			Bukkit.getLogger().log(Level.WARNING, "[JukeBukkit] Missing Record Player Data, this data should have been created when the block was placed.");
			return false;
		}
		
		if (rpdata.hasDisc()) {
			
			//get disc.
			DiscData discData = JukeBukkit.instance.getDatabase().find(DiscData.class)
					.where()
						.ieq("nameKey", rpdata.getDiscKey())
					.findUnique();
			if (discData == null) {
				Bukkit.getLogger().log(Level.WARNING, "Disc Key is missing from discs table");
			} else {
				//create disc to spawn
				BurnedDisc disc = new BurnedDisc(discData);
				ItemStack iss = new SpoutItemStack(disc, 1);
				Location spawnLoc = location;
				spawnLoc.setY(spawnLoc.getY()+1);
				spawnLoc.getWorld().dropItem(spawnLoc, iss);
			}
			
			rpdata.setDiscKey(null);
			JukeBukkit.instance.getDatabase().save(rpdata);
			
			stopMusic(location);
			
			return true;
		}
		
		SpoutItemStack inHand = new SpoutItemStack(player.getItemInHand());
		
		if (inHand.getMaterial() instanceof BurnedDisc) {
			
			//we know its a custom item, go ahaed and remove 1 from the hand.
			if (inHand.getAmount()<2) {
				player.setItemInHand(new ItemStack(Material.AIR));
			} else {
				player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount()-1);
			}
			
			BurnedDisc discInHand = (BurnedDisc)inHand.getMaterial();
			
			rpdata.setDiscKey(discInHand.getKey());
			JukeBukkit.instance.getDatabase().save(rpdata);
			
			SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, "jb_startup.wav", false, location, 8);
			
			//start the music
			playMusic(discInHand.getUrl(), location);
			
			return true;
		}
		
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, "jb_error.wav", false, location, 8);
		return false;
	}

	@Override
	public void onBlockPlace(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		//when the block is placed we need to make sure to get data set up for it.
		RecordPlayerData rpd = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
				.where()
					.eq("x", (double)x)
					.eq("y", (double)y)
					.eq("z", (double)z)
					.ieq("worldName", world.getName())
				.findUnique();
		if (rpd == null) {
			rpd = new RecordPlayerData();
			rpd.setNeedleType(RecordPlayerDesign.NEEDLE_NONE);
			rpd.setDiscKey(null);
			rpd.setX((double)x);
			rpd.setY((double)y);
			rpd.setZ((double)z);
			rpd.setWorldName(world.getName());
			JukeBukkit.instance.getDatabase().save(rpd);
		}
		/* If its still set, well go ahead and leave it, because it could be an blockplace even from setting the custom block to a different subblock for this location */
	}

	@Override
	public void onBlockPlace(World arg0, int arg1, int arg2, int arg3, LivingEntity arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEntityMoveAt(World arg0, int arg1, int arg2, int arg3, Entity arg4) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean isIndirectlyProvidingPowerTo(World arg0, int arg1, int arg2, int arg3, BlockFace arg4) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int changedId) {
		/*
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
		*/
	}

	public void playMusic(String url, Location location, SpoutPlayer player) {
		/*
		//get players in radius of the jukebox and start it for only those players
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)getRange()) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					try {
						SpoutManager.getSoundManager().playCustomMusic(plugin, sp, url, true, location, getRange());
					} catch (Exception e) {
						//the disc has an error.
						if(player != null) player.sendMessage(e.getMessage());
						SpoutManager.getSoundManager().playGlobalCustomSoundEffect(plugin, CustomsManager.SF_JUKEBOX_ERROR, false, location, 3);
					}
				}
			}
		}
		*/
	}

	public void playMusic(String url, Location location) {
		/*
		playMusic(url, location, null);
		*/
	}

	public void stopMusic(Location location) {
		/*
		//get players in radius of the jukebox and start it for only those players
		for(Player p:location.getWorld().getPlayers()) {
			double distance = location.toVector().distance(p.getLocation().toVector());
			if (distance<=(double)getRange()) {
				SpoutPlayer sp = SpoutManager.getPlayer(p);
				if (sp.isSpoutCraftEnabled()) {
					SpoutManager.getSoundManager().stopMusic(sp);
				}
			}
		}
		*/
	}
}
