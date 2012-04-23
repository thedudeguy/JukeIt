package cc.thedudeguy.jukebukkit.server;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.util.Debug;

public class MusicHandler extends ResourceHandler {

	public MusicHandler() {
		this.setDirectoriesListed(false);
		this.setResourceBase(new File(JukeBukkit.instance.getDataFolder(), "").getAbsolutePath());
	}
	
	@Override
	public void handle(
				String target,
				Request baseRequest, 
				HttpServletRequest request,
				HttpServletResponse response
			) throws IOException, ServletException {
		
		Debug.debug("Attempting to handle a music uri");
		
		if (target.startsWith("/music/")) {
			
			Debug.debug("URI is a music URI");
			super.handle(target, baseRequest, request, response);
		} else {
			baseRequest.setHandled(false);
			return;
		}
		
	}
	
}
