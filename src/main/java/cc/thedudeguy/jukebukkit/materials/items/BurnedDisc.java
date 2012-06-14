package cc.thedudeguy.jukebukkit.materials.items;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.DiscData;
import cc.thedudeguy.jukebukkit.util.Debug;
import cc.thedudeguy.jukebukkit.util.ResourceManager;

public class BurnedDisc extends GenericCustomItem implements DiscColor {
	
	public static final String generateNameKey() {
		return  UUID.randomUUID().toString();
	}
	
	public static final Map<Integer, String> discColorTextureMap;
	static {
		Map<Integer, String> dctMap = new HashMap<Integer, String>();
		dctMap.put(DiscColor.BLACK, 	"burned_disc_black.png");
		dctMap.put(DiscColor.RED, 		"burned_disc_red.png");
		dctMap.put(DiscColor.GREEN, 	"burned_disc_green.png");
		dctMap.put(DiscColor.BROWN, 	"burned_disc_brown.png");
		dctMap.put(DiscColor.BLUE, 		"burned_disc_blue.png");
		dctMap.put(DiscColor.PURPLE, 	"burned_disc_purple.png");
		dctMap.put(DiscColor.CYAN, 		"burned_disc_cyan.png");
		dctMap.put(DiscColor.LIGHTGRAY,	"burned_disc_lightgray.png");
		dctMap.put(DiscColor.GRAY, 		"burned_disc_gray.png");
		dctMap.put(DiscColor.PINK, 		"burned_disc_pink.png");
		dctMap.put(DiscColor.LIME, 		"burned_disc_lime.png");
		dctMap.put(DiscColor.YELLOW, 	"burned_disc_yellow.png");
		dctMap.put(DiscColor.LIGHTBLUE, "burned_disc_lightblue.png");
		dctMap.put(DiscColor.MAGENTA, 	"burned_disc_magenta.png");
		dctMap.put(DiscColor.ORANGE, 	"burned_disc_orange.png");
		dctMap.put(DiscColor.WHITE, 	"burned_disc_white.png");
		discColorTextureMap = Collections.unmodifiableMap(dctMap);
	}
	
	private int color = DiscColor.WHITE; //whit disc is the default color
	private String key;
	private String url;
	
	public BurnedDisc() {
		super(JukeBukkit.instance, "Reference Burn Disc");
		setName("Reference Burn Disc (Do Not Use)");
		setColor(DiscColor.WHITE);
	}
	
	public BurnedDisc(DiscData discData) {
		super(JukeBukkit.instance, discData.getNameKey());
		
		this.setColor(discData.getColor());
		this.setLabel(discData.getLabel());
		
		Debug.debug("BurnedDisc:Construct - ", "id=", discData.getNameKey());
		
		key = discData.getNameKey();
		url = discData.getUrl();
	}
	
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, org.bukkit.block.BlockFace face) {
		/*
		player.sendMessage("interacting with item");
		player.sendMessage("Key is: " + getKey());
		player.sendMessage("Color is: " + String.valueOf(getColor()));
		*/
		return true;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getUrl() {
		return url;
	}
	
	@Override
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
		
		Debug.debug("BurnedDisc:setColor - ", "id=",color," image=",discColorTextureMap.get(color) );
		
		if (discColorTextureMap.containsKey(color)) {
			setTexture(discColorTextureMap.get(color));
		} else {
			setTexture(discColorTextureMap.get(DiscColor.WHITE));
		}
		
		//temporary fix for texture issue with spout
		ResourceManager.cacheSingle("textures", discColorTextureMap.get(color));
	}
	
	public void setLabel(String label) {
		
		if (label == null || label.isEmpty() || label.equalsIgnoreCase("") ) {
			setName("*Burned* Obsidyisc");
		} else {
			setName(label);
		}
	}

}
