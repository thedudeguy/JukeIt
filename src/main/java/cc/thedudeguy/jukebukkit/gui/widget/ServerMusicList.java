package cc.thedudeguy.jukebukkit.gui.widget;

import org.getspout.spoutapi.gui.GenericListWidget;
import org.getspout.spoutapi.gui.ListWidgetItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;

public class ServerMusicList extends GenericListWidget {
	
	public ServerMusicList() {
		for ( String name : JukeBukkit.getServerFileList()) {
			this.addItem(new ListWidgetItem(name, ""));
		}
		
	}
	
	
}
