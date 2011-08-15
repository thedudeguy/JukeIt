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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.packet.PacketItemName;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Disc {
	
	private short id;
	private String url;
	private String creator;
	private String songName;
	private String songArtist;
	
	protected Disc(short id, String creator, String url) {
		this.id = id;
		this.creator = creator;
		this.url = url;
		this.songArtist = "";
		this.songName = "";
		refreshItemName();
	}
	
	protected Disc(short id, String creator, String url, String name, String artist) {
		this.id = id;
		this.creator = creator;
		this.url = url;
		if (name == null) name = "";
		if (artist == null) artist = "";
		this.songName = name;
		this.songArtist = artist;
		refreshItemName();
	}
	
	public short getId() {
		return id;
	}
	public String getUrl() {
		return url;
	}
	public String getCreator() {
		return creator;
	}
	public String getName()
	{
		return songName;
	}
	public String getArtist()
	{
		return songArtist;
	}
	
	public void setSongName(String name)
	{
		songName = name;
		refreshItemName();
		return;
	}
	
	public void setSongArtist(String artist)
	{
		songArtist = artist;
		refreshItemName();
		return;
	}
	
	public void refreshItemName()
	{
		
		if (!songArtistIsset() && !songNameIsset())
		{
			String name = url.substring( url.lastIndexOf('/')+1, url.length() );
			setItemName(name);
			return;
		}
		String itemName = "";
		if (songNameIsset())
		{
			itemName = itemName + songName;
			itemName = itemName.trim();
		}
		if (songArtistIsset())
		{
			itemName = itemName + " by " + songArtist;
			itemName = itemName.trim();
		}
		setItemName(itemName);
		return;
		
	}
	
	private boolean songArtistIsset()
	{
		if (songArtist != null && !songArtist.equalsIgnoreCase(""))
		{
			return true;
		}
		return false;
	}
	
	private boolean songNameIsset()
	{
		if (songName != null && !songName.equalsIgnoreCase(""))
		{
			return true;
		}
		return false;
	}
	
	private void setItemName(String itemName)
	{
		//since this plugin is marked as depend sprout, no need to check if sprout is loaded...
		SpoutManager.getItemManager().setItemName(Material.GOLD_RECORD, id, itemName);
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            SpoutPlayer sp = SpoutManager.getPlayer(p);
            if (sp.isSpoutCraftEnabled() == true)
            {
            	sp.sendPacket(new PacketItemName(Material.GOLD_RECORD.getId(), id, itemName));
            }
		}
	}
	
}
