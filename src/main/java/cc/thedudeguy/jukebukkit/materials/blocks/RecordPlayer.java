package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.RecordPlayerBlockDesigns;
import cc.thedudeguy.jukebukkit.database.RecordPlayerData;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerDesign;

public class RecordPlayer extends GenericCustomBlock {
	
	public static HashMap<String, RecordPlayer> subBlocks = new HashMap<String, RecordPlayer>();
	
	public static class RecordPlayerSubBlock extends RecordPlayer {
		
		public RecordPlayerSubBlock(RecordPlayerDesign rpDesign) {
			super(rpDesign.getDesignTypeId());
			
			this.setName("Record Player SubBlock (Do Not Use)");
			this.setBlockDesign(rpDesign);
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
	
	public boolean onBlockInteract(org.bukkit.World world, int x, int y, int z, SpoutPlayer player) {
		
		player.sendMessage("Yeah Baby");
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);

		
		//if (data.getNeedleType() == 0) Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] No Needle");
		//block.setCustomBlock(getSubBlock(RecordPlayerDesign.NEEDLE_WOOD_FLINT, RecordPlayerDesign.DISC_BLUE, RecordPlayerDesign.INDICATOR_GREEN));
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
					.eq("x", x)
					.eq("y", y)
					.eq("z", z)
					.ieq("worldName", world.getName())
				.findUnique();
		
		rpd.setNeedleType(0);
		rpd.setX(x);
		rpd.setY(y);
		rpd.setZ(z);
		rpd.setWorldName(world.getName());
		JukeBukkit.instance.getDatabase().save(rpd);
	}
	
	/**
	 * Event Fired when this block is destroyed.
	 * Firstly, if this player has any items in it, like a record, or a needle, then those items need
	 * to be spawned into the world.
	 * lastly, remove the block data we have saved in the database to keep it nice and tidy.
	 */
	public void onBlockDestroyed(org.bukkit.World world, int x, int y, int z) {
		RecordPlayerData rpd = JukeBukkit.instance.getDatabase().find(RecordPlayerData.class)
				.where()
					.eq("x", x)
					.eq("y", y)
					.eq("z", z)
					.ieq("worldName", world.getName())
				.findUnique();
		JukeBukkit.instance.getDatabase().delete(rpd);
	}
	
}
