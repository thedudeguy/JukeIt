package cc.thedudeguy.jukebukkit.gui.widget;

import org.getspout.spoutapi.gui.GenericListWidget;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import cc.thedudeguy.jukebukkit.SongRepo;

public class RepoMusicList extends GenericListWidget {
	
	public RepoMusicList() {
		
		int numItems = SongRepo.musicList.length();
		
		for (int i=0; i<numItems; i++) {
			try {
				JSONObject item = SongRepo.musicList.getJSONObject(i);
				this.addItem(new RepoSongItem(item.getString("title"), item.getString("artist"), item.getString("url")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
