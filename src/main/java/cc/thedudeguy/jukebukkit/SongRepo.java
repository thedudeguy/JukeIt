package cc.thedudeguy.jukebukkit;

import java.io.IOException;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.web.Resty;


public class SongRepo {
	
	public static final String repoAddress = "jukebukkit.chrischurchwell.com";
	
	public static JSONArray musicList;
	
	public void refreshList() {
		
		Resty r = new Resty();
		try {
			
			musicList = r.json("http://"+ repoAddress +"/music/listmusic").object().getJSONArray("music");
			
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
