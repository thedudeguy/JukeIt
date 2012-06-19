package cc.thedudeguy.jukebukkit.materials.items;

import java.io.File;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.LabelData;
import cc.thedudeguy.jukebukkit.util.ResourceManager;

public class DiscLabel extends GenericCustomItem {

	private String key;
	private String label;
	
	public DiscLabel() {
		super(JukeBukkit.instance, "Blank Label");
		setTexture("label.png");
		
		//temporary fix for texture issue with spout
		ResourceManager.cacheSingle("textures", "label.png");
	}
	
	public DiscLabel(LabelData labelData) {
		super(JukeBukkit.instance, labelData.getNameKey());
		setTexture(new File(JukeBukkit.instance.getDataFolder(), new File("textures", "label.png").getPath()));
		setLabel(labelData.getLabel());
		key = labelData.getNameKey();
	}
	
	public String getKey() {
		return key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		setName("Disc Label ("+label+")");
	}
	
}
