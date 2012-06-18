package cc.thedudeguy.jukebukkit.gui.widget;

import org.getspout.spoutapi.gui.ListWidgetItem;

public class RepoSongItem extends ListWidgetItem {

	private String url;
	
	public RepoSongItem(String title, String artist, String url) {
		super();
		
		this.setTitle(title);
		this.setText("by " + artist);
		this.setURL(url);
	}
	
	public String getURL() {
		return url;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
}
