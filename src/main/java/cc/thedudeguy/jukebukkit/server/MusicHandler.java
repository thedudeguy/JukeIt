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
		this.setResourceBase(new File(JukeBukkit.getInstance().getDataFolder(), "").getAbsolutePath());
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
