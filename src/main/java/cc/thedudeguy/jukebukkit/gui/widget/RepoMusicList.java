/**
 * This file is part of JukeBukkit
 *
 * Copyright (C) 2011-2012  Chris Churchwell
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
 */
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
				this.addItem(new RepoSongItem(item.getString("title"), item.getString("artist"), item.getString("song_id"), item.getString("filename")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
