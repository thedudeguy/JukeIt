package cc.thedudeguy.jukebukkit;

import java.io.IOException;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;


public class SongRepo {
	
	public static final String repoAddress = "jukebukkit.chrischurchwell.com";
	
	public static JSONArray musicList;
	public static boolean validSubscriber = false;
	
	public void refreshList() {
		
		if (JukeBukkit.instance.getConfig().getString("subscriberId", "").equalsIgnoreCase("")) {
			validSubscriber = false;
		}
		else {
			Resty r = new Resty();
			try {
				JSONObject j = r.json("http://"+ repoAddress +"/music/listmusic").object();
				if (j.getBoolean("donator") == true) {
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
