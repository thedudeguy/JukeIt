package cc.thedudeguy.jukebukkit.listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.particle.Particle;
import org.getspout.spoutapi.particle.Particle.ParticleType;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.events.MachineCompleteEvent;
import cc.thedudeguy.jukebukkit.events.MachineEvent;
import cc.thedudeguy.jukebukkit.events.MachineProcessEvent;
import cc.thedudeguy.jukebukkit.events.MachineStartEvent;
import cc.thedudeguy.jukebukkit.materials.Blocks;
import cc.thedudeguy.jukebukkit.materials.Items;
import cc.thedudeguy.jukebukkit.materials.blocks.MachineBlock;
import cc.thedudeguy.jukebukkit.materials.blocks.MachineRecipe;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RPNeedle;
import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.util.Debug;
import cc.thedudeguy.jukebukkit.util.Sound;

public class MachineListener implements Listener {

	public abstract class MachineRunnable implements Runnable {
		SpoutBlock block;
		ItemStack primaryItem;
		ItemStack additionItem;
		String label;
		int taskToStop;
		public MachineRunnable(SpoutBlock block, ItemStack primaryItem, ItemStack additionItem, String label) {
			this.block = block;
			this.primaryItem = primaryItem;
			this.additionItem = additionItem;
			this.label = label;
		}
		public MachineRunnable(SpoutBlock block) {
			this.block = block;
		}
		
		public MachineRunnable(int taskIdToStop) {
			this.taskToStop = taskIdToStop;
		}
	}
	
	@EventHandler
	public void onPistonPull(BlockPistonRetractEvent event) {
		Debug.debug("onpistonpull event");
		
		if (event.isCancelled() || !event.isSticky()) {
			return;
		}
		SpoutBlock block = (SpoutBlock)event.getBlock().getRelative(event.getDirection(), 2);
		
		if ( block.getCustomBlock() == null) {
			return;
		}
		if (block.getCustomBlock() instanceof MachineBlock) {
			Debug.debug("canceling piston event");
			event.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onPistonPush(BlockPistonExtendEvent event) {
		if (event.isCancelled()) return;
		for(Block block : event.getBlocks()) {
			if ( ((SpoutBlock)block).getCustomBlock() != null) {
				if ( ((SpoutBlock)block).getCustomBlock() instanceof MachineBlock ) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (
				((SpoutBlock)event.getBlock()).getCustomBlock() == null ||
				!(((SpoutBlock)event.getBlock()).getCustomBlock() instanceof MachineBlock)
				){
			return;
			
		}
		
		if (
				!event.getBlock().getRelative(BlockFace.UP).getType().equals(Material.AIR)
			) {
			event.getBlock().breakNaturally(new SpoutItemStack(Blocks.machineBlock, 1));
			return;
		}
		
		SpoutManager.getMaterialManager().overrideBlock(event.getBlock().getRelative(BlockFace.UP), Blocks.machineBlock, (byte)1);
	}
	
	@EventHandler
	public void onStart(MachineStartEvent event) {
		Debug.debug("LabelMachineStartEvent heard - starting up. ");
		SpoutManager.getMaterialManager().overrideBlock(event.getBlock().getRelative(BlockFace.UP), Blocks.machineBlock, (byte)2);
		
		try {
			Sound sound = new Sound(new URL("http://dev.bukkit.org/media/attachments/28/192/labelmachine.ogg"));
			sound.setRange(8);
			sound.play(event.getBlock().getLocation());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Particle particle = new Particle(ParticleType.LARGESMOKE, getParticleLocation(event.getBlock()), new Vector(0,0,0));
		particle.setAmount(50);
		particle.setMaxAge(20);
		particle.spawn();
		
		JukeBukkit.instance.getServer().getScheduler().scheduleSyncDelayedTask(JukeBukkit.instance, new MachineRunnable(event.getBlock(), event.getPrimaryItem(), event.getAdditionItem(), event.getLabel()) {
			public void run() {
				MachineProcessEvent pEvent = new MachineProcessEvent(block, primaryItem, additionItem, label);
				Bukkit.getServer().getPluginManager().callEvent(pEvent);
			}
		}, 10L);
	}
	
	@EventHandler
	public void onProcess(MachineProcessEvent event) {
		Debug.debug("LabelMachineProcessEvent heard - processing graphics. bzzrrt! whirr!! chjkchjk ");
		
		int taskID = JukeBukkit.instance.getServer().getScheduler().scheduleSyncRepeatingTask(JukeBukkit.instance, new MachineRunnable(event.getBlock()) {
			public void run() {
				Particle particle = new Particle(ParticleType.SMOKE, getParticleLocation(block), new Vector(0,0.2,0));
				particle.setMaxAge(10);
				particle.setAmount(5);
				particle.spawn();
			}
		}, 0, 5L);
		
		JukeBukkit.instance.getServer().getScheduler().scheduleSyncDelayedTask(JukeBukkit.instance, new MachineRunnable(taskID) {
			public void run() {
				Bukkit.getScheduler().cancelTask(taskToStop);
			}
		}, 100L);
		
		JukeBukkit.instance.getServer().getScheduler().scheduleSyncDelayedTask(JukeBukkit.instance, new MachineRunnable(event.getBlock(), event.getPrimaryItem(), event.getAdditionItem(), event.getLabel()) {
			public void run() {
				MachineCompleteEvent pEvent = new MachineCompleteEvent(block, primaryItem, additionItem, label);
				Bukkit.getServer().getPluginManager().callEvent(pEvent);
			}
		}, 110L);
	}
	
	@EventHandler
	public void onComplete(MachineCompleteEvent event) {
		Debug.debug("LabelMachineCompleteEvent heard - its magic.");
		SpoutManager.getMaterialManager().overrideBlock(event.getBlock().getRelative(BlockFace.UP), Blocks.machineBlock, (byte)1);
		
		Particle particle = new Particle(ParticleType.CLOUD, getParticleLocation(event.getBlock()), new Vector(0,0,0));
		particle.setGravity(0);
		particle.setScale(0.5F);
		particle.setAmount(100);
		particle.spawn();
		
		//if either item is air, transaction is a flop, just abort.
		Debug.sdebug("values -- ", event.getPrimaryItem(), event.getAdditionItem(), event.hasLabel());
		if (event.getPrimaryItem().getType().equals(Material.AIR) || event.getAdditionItem().getType().equals(Material.AIR)) {
			abortEject(event);
			return;
		}
		
		//checking to see if we had a labeling disc setup.
		SpoutItemStack sPrimItem = new SpoutItemStack(event.getPrimaryItem());
		SpoutItemStack sAddItem = new SpoutItemStack(event.getAdditionItem());
		if (
				sAddItem.getType().equals(Material.PAPER) &&
				event.hasLabel() &&
				sPrimItem.getMaterial() instanceof BurnedDisc &&
				((BurnedDisc)sPrimItem.getMaterial()).getKey() != null
				) {
				BurnedDisc disc = (BurnedDisc)sPrimItem.getMaterial();
			//label the disc yo!
			DiscData discData = JukeBukkit.instance.getDatabase().find(DiscData.class)
					.where()
						.eq("nameKey", disc.getKey())
					.findUnique();
			if (discData == null) {
				abortEject(event);
				return;
			}
			discData.setLabel(event.getLabel());
			JukeBukkit.instance.getDatabase().save(discData);
			disc.setLabel(event.getLabel());
			eject(event.getBlock(), new SpoutItemStack(disc, 1));
			if (!lastOne(event.getAdditionItem())) eject(event.getBlock(), removeOne(event.getAdditionItem()));
			return;
			
		}
		
		//check if were cloning a disc.
		if ( 
				(sPrimItem.getMaterial() instanceof BlankDisc && sAddItem.getMaterial() instanceof BurnedDisc) ||
				(sPrimItem.getMaterial() instanceof BurnedDisc && sAddItem.getMaterial() instanceof BlankDisc)
				) {
			
			BurnedDisc master;
			BlankDisc target;
			//which one are we making a copy of?
			if (sPrimItem.getMaterial() instanceof BurnedDisc) {
				master = (BurnedDisc) sPrimItem.getMaterial();
				target = (BlankDisc) sAddItem.getMaterial();
			} else {
				master = (BurnedDisc) sAddItem.getMaterial();
				target = (BlankDisc) sPrimItem.getMaterial();
			}
			
			//get burned disc data
			DiscData discData = JukeBukkit.instance.getDatabase().find(DiscData.class)
					.where()
						.ieq("nameKey", master.getKey())
					.findUnique();
			if (discData == null) {
				Bukkit.getLogger().log(Level.WARNING, "Disc Data not found");
				abortEject(event);
				return;
			}
			
			//create the disc
			//create the key
	      	String key = BurnedDisc.generateNameKey();
	      	//disc color...
	      	int color = target.getColor();
	      	//add the disc into the database
	      	DiscData newDiscData = new DiscData();
	      	newDiscData.setNameKey(key);
	      	newDiscData.setUrl(discData.getUrl());
	      	newDiscData.setLabel(discData.getLabel());
	      	newDiscData.setColor(color);
	      	JukeBukkit.instance.getDatabase().save(newDiscData);
	      	//create the physical disc for the pplayer
	    	BurnedDisc disc = new BurnedDisc(newDiscData);
	    	Items.burnedDiscs.put(key, disc);
	    	ItemStack iss = new SpoutItemStack(disc, 1);
	      	//eject the disc.
	    	this.eject(event.getBlock(), iss);
	    	//eject leftovers. since were cloning the master disc, should not destroy it.
	    	if (sPrimItem.getMaterial() instanceof BurnedDisc) {
	    		eject(event.getBlock(), event.getPrimaryItem());
		    	if (!lastOne(event.getAdditionItem())) eject(event.getBlock(), removeOne(event.getAdditionItem()));
			} else {
				eject(event.getBlock(), event.getAdditionItem());
		    	if (!lastOne(event.getPrimaryItem())) eject(event.getBlock(), removeOne(event.getPrimaryItem()));
			}
	    	
			return;
		}
		
		//check if were cloning a disc
		
		//set if we have a recipe match
		ItemStack recipeResult = MachineRecipe.getRecipeMatch(sPrimItem, sAddItem);
		if (recipeResult != null) {
			eject(event.getBlock(), recipeResult);
			if (!lastOne(event.getAdditionItem())) eject(event.getBlock(), removeOne(event.getAdditionItem()));
			return;
		}
		
		
		//if were here, than there was nothing but unmatchable junk.
		abortEject(event);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		
		
		if (!(((SpoutBlock)event.getBlock()).getCustomBlock() instanceof MachineBlock)){
			return;
		}
		
		destroyOtherHalf(event.getBlock());
		
		((SpoutBlock)event.getBlock()).setCustomBlock(null);
		((SpoutBlock)event.getBlock()).setType(Material.AIR);
		
		if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			Location loc = event.getBlock().getLocation();
			loc.setX(loc.getX() + 0.5);
			loc.setY(loc.getY() + 0.5);
			event.getBlock().getWorld().dropItem(loc, new SpoutItemStack(Blocks.machineBlock, 1));
		}
		
		event.setCancelled(true);
	}
	
	private void destroyOtherHalf(Block block) {
		//possibly broken top block need to destroy bottom block
		if (
				((SpoutBlock)block).getCustomBlockData() > 0 &&
				(
					((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock() instanceof MachineBlock &&
					((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlockData() == 0
				)
			) {
				((SpoutBlock)block).getRelative(BlockFace.DOWN).setCustomBlock(null);
				((SpoutBlock)block).getRelative(BlockFace.DOWN).setType(Material.AIR);
		}
		
		//possibly broke bottom block need to destroy top block
		else if (
				((SpoutBlock)block).getCustomBlockData() == 0 &&
				(
					((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock() instanceof MachineBlock &&
					((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlockData() > 0
				)
			) {
				((SpoutBlock)block).getRelative(BlockFace.UP).setCustomBlock(null);
				((SpoutBlock)block).getRelative(BlockFace.UP).setType(Material.AIR);
		}
	}
	
	private ItemStack removeOne(ItemStack item) {
		item.setAmount(item.getAmount()-1);
		return item;
	}
	
	private boolean lastOne(ItemStack item) {
		if (item.getAmount() == 1) return true;
		return false;
	}
	
	private boolean notAir(ItemStack item) {
		if (item.getType().equals(Material.AIR)) return false;
		return true;
	}
	
	private void eject(Block block, ItemStack item) {
		Location loc = block.getLocation();
		//loc.setX(loc.getX()+0.5);
		//loc.setZ(loc.getZ()+0.5);
		loc.setY(loc.getY()+1.0);
		block.getWorld().dropItem(loc, item);
	}
	
	private void abortEject(MachineEvent event) {
		if (notAir(event.getPrimaryItem())) {
			eject(event.getBlock(), event.getPrimaryItem());
		}
		if (notAir(event.getAdditionItem())) {
			eject(event.getBlock(), event.getAdditionItem());
		}
	}
	
	private Location getParticleLocation(Block block) {
		Location loc = block.getLocation();
		loc.setX(loc.getX()+0.5);
		loc.setY(loc.getY()+1.0);
		loc.setZ(loc.getZ()+0.5);
		
		return loc;
	}
}
