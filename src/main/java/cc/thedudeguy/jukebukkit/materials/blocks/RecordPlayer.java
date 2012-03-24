package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.RecordPlayerBlockDesigns;
import cc.thedudeguy.jukebukkit.database.RecordPlayerData;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerDesign;
import cc.thedudeguy.jukebukkit.materials.items.Items;
import cc.thedudeguy.jukebukkit.materials.items.Needle;

public class RecordPlayer extends GenericCustomBlock {
	
	public static HashMap<String, RecordPlayer> subBlocks = new HashMap<String, RecordPlayer>();
	
	public static class RecordPlayerSubBlock extends RecordPlayer {
		
		public RecordPlayerSubBlock(RecordPlayerDesign rpDesign) {
			super(rpDesign.getDesignTypeId());
			
			this.setName("Record Player SubBlock (Do Not Use)");
			this.setBlockDesign(rpDesign);
			
			SpoutItemStack dropItem = new SpoutItemStack(getSubBlock(RecordPlayerDesign.NEEDLE_NONE, RecordPlayerDesign.DISC_NONE, RecordPlayerDesign.INDICATOR_RED), 1);
			
			this.setItemDrop(dropItem);
		}
	}
	
	public static RecordPlayer getSubBlock(int needle, int disc, int indicator) {
		
		RecordPlayerDesign rpDesign = new RecordPlayerDesign(needle, disc, indicator);
		
		if (!subBlocks.containsKey(rpDesign.getDesignTypeId())) {
			subBlocks.put(rpDesign.getDesignTypeId(), new RecordPlayerSubBlock(rpDesign));
			
			//save the combo to the db if it doesnt already exist.
			RecordPlayerBlockDesigns rpbd = JukeBukkit.instance.getDatabase().find(RecordPlayerBlockDesigns.class)
					.where()
						.eq("needle", rpDesign.getNeedle())
						.eq("disc", rpDesign.getDisc())
						.eq("indicator", rpDesign.getIndicator())
					.findUnique();
			if (rpbd == null) {
				rpbd = new RecordPlayerBlockDesigns();
				rpbd.setDisc(rpDesign.getDisc());
				rpbd.setNeedle(rpDesign.getNeedle());
				rpbd.setIndicator(rpDesign.getIndicator());
				JukeBukkit.instance.getDatabase().save(rpbd);
			}
		} 
		
		return subBlocks.get(rpDesign.getDesignTypeId());
	}
	
	public static void updateBlockDesign(SpoutBlock block, RecordPlayerData data) {
		
		block.setCustomBlock(getSubBlock(data.getNeedleType(), RecordPlayerDesign.DISC_NONE, RecordPlayerDesign.INDICATOR_RED));
		
	}
	
	public RecordPlayer() {
		super(JukeBukkit.instance, "Record Player", 3);
		
		RecordPlayerDesign rpDesign = new RecordPlayerDesign();
		this.setBlockDesign(rpDesign);
		
		this.setHardness(MaterialData.wood.getHardness());
		this.setLightLevel(1);
		
		//store this into the hashmap since it will always be the defaul
		subBlocks.put(rpDesign.getDesignTypeId(), this);
		
		//Bukkit.getLogger().log(Level.INFO, rpDesign.getDesignTypeId());
		
		initDesigns();
	}
	
	public RecordPlayer(String nameId) {
		super(JukeBukkit.instance, nameId, 3);
		
		this.setHardness(MaterialData.wood.getHardness());
		this.setLightLevel(1);
	}
	
	private void initDesigns() {
		 List<RecordPlayerBlockDesigns> rpbd = JukeBukkit.instance.getDatabase().find(RecordPlayerBlockDesigns.class).findList();
		 
		 if (rpbd.isEmpty()) {
			 Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] No RecordPlayer Designs to load.");
		 } else {
			 int count = 0;
			 for (RecordPlayerBlockDesigns record : rpbd) {
				 RecordPlayerDesign rpDesign = new RecordPlayerDesign(record.getNeedle(), record.getDisc(), record.getIndicator());
				 subBlocks.put(rpDesign.getDesignTypeId(), new RecordPlayerSubBlock(rpDesign));
				 count++;
	         }
			 Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Initialized "+ String.valueOf(count) +" RecordPlayer Designs.");
		 }
		 
	}
	
	/**
	 * Event fired when a player right clicks on a block.
	 * Lots of things to do here. if theres a disc in it, the disc needs to be ejected, and if the player
	 * is holding the right items in their hand, then those select items may be taken by this block.
	 */
	public boolean onBlockInteract(org.bukkit.World world, int x, int y, int z, SpoutPlayer player) {
		
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
		
		SpoutItemStack inHand = new SpoutItemStack(player.getItemInHand());
		if ( rpdata.getNeedleType() == RecordPlayerDesign.NEEDLE_NONE && inHand.isCustomItem() && inHand.getMaterial() instanceof Needle ) {
			rpdata.setNeedleType(RecordPlayerDesign.NEEDLE_WOOD_FLINT);
			JukeBukkit.instance.getDatabase().save(rpdata);
			
			 //remove 1 from hand
			if (inHand.getAmount()<2) {
				player.setItemInHand(new ItemStack(Material.AIR));
			} else {
				inHand.setAmount(inHand.getAmount()-1);
				player.setItemInHand(inHand);
			}
			
			updateBlockDesign((SpoutBlock)world.getBlockAt(x, y, z), rpdata);
			
			return true;
		}
		
		if ( rpdata.getNeedleType() != RecordPlayerDesign.NEEDLE_NONE ) {
			
			rpdata.setNeedleType(RecordPlayerDesign.NEEDLE_NONE);
			JukeBukkit.instance.getDatabase().save(rpdata);
			
			Location location = new Location(world, x, y, z);
			location.setY(location.getY()+1);
			world.dropItem(location, new SpoutItemStack(Items.needle, 1));
			
			updateBlockDesign((SpoutBlock)world.getBlockAt(x, y, z), rpdata);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Event Fired when this block is placed.
	 * Update/Insert the data into the DB for this block, so we can keep an eye on it.
	 */
	public void onBlockPlace(org.bukkit.World world, int x, int y, int z) {
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
			rpd.setNeedleType(0);
			rpd.setX((double)x);
			rpd.setY((double)y);
			rpd.setZ((double)z);
			rpd.setWorldName(world.getName());
			JukeBukkit.instance.getDatabase().save(rpd);
		}
		/* If its still set, well go ahead and leave it, because it could be an blockplace even from setting the custom block to a different subblock for this location */
	}
	
	/**
	 * Event Fired when this block is destroyed.
	 * Firstly, if this player has any items in it, like a record, or a needle, then those items need
	 * to be spawned into the world.
	 * lastly, remove the block data we have saved in the database to keep it nice and tidy.
	 */
	public void onBlockDestroyed(org.bukkit.World world, int x, int y, int z) {
		
		//if theres junk in this block we need to make sure it drops too
		RecordPlayerData rpd = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
				.where()
					.eq("x", (double)x)
					.eq("y", (double)y)
					.eq("z", (double)z)
					.ieq("worldName", world.getName())
				.findUnique();
		if (rpd != null) {
			if (rpd.getNeedleType() != RecordPlayerDesign.NEEDLE_NONE) {
				world.dropItem(new Location(world, x, y, z), new SpoutItemStack(Items.needle, 1));
			}
		}
		
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
	
}
