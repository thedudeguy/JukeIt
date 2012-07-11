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
package cc.thedudeguy.jukebukkit;

import java.io.IOException;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;


public class SongRepo {
	
	//no trailing slash
	public static final String repoAddress = "jukebukkit.chrischurchwell.com";
	
	public static JSONArray musicList;
	public static boolean validSubscriber = false;
	
	public void refreshList() {
		
		if (
				JukeBukkit.instance.getConfig().getString("subscriberId", "").equalsIgnoreCase("") ||
				JukeBukkit.instance.getConfig().getString("subscriberId", "").equalsIgnoreCase("subscribe!")
				) {
			validSubscriber = false;
		}
		else {
			Resty r = new Resty();
			String subscriberId = JukeBukkit.instance.getConfig().getString("subscriberId");
			try {
				JSONObject j = r.json("http://"+ repoAddress +"/music/listmusic/"+subscriberId).object();
				if (j.getBoolean("active") == true) {
					validSubscriber = true;
					musicList = j.getJSONArray("music");
				} else {
					validSubscriber = false;
				}
				//Debug.debug("Retrieved: ", musicList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
