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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.util.Debug;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class ServerHandler extends AbstractHandler {

	public Configuration cfg;
	
	public ServerHandler() {
		cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading( new File(JukeBukkit.getInstance().getDataFolder(), "web") );
			cfg.setObjectWrapper(new DefaultObjectWrapper());  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(
				String target,
				Request baseRequest, 
				HttpServletRequest request,
				HttpServletResponse response
			) throws IOException, ServletException {
		
		Debug.debug("Handling Web Request: ", target);
		
		if ( target.equalsIgnoreCase("/") ) {
			
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			baseRequest.setHandled(true);
			
			Template template = cfg.getTemplate("index.html");
			
			Map<String, Object> dataRoot = new HashMap<String, Object>();
			dataRoot.put("serverName", JukeBukkit.getInstance().getConfig().getString("serverName"));
			dataRoot.put("allowUpload", JukeBukkit.getInstance().getConfig().getBoolean("allowWebServerUploads"));
			
			dataRoot.put("files", JukeBukkit.getServerFileList());
			
			try {
				template.process(dataRoot, response.getWriter());
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;
		}
		
		if (target.equalsIgnoreCase("/robots.txt")) {
			response.setContentType("text/plain;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			baseRequest.setHandled(true);
			
			response.getWriter().println("User-agent: *");
			response.getWriter().println("Disallow: /");
			
			return;
		}
		
		if ( target.equalsIgnoreCase("/upload") ) {
			
			if (!JukeBukkit.getInstance().getConfig().getBoolean("allowWebServerUploads")) {
				return;
			}
			
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			baseRequest.setHandled(true);
			
			if (!ServletFileUpload.isMultipartContent(request))
			{
				response.getWriter().println("No File Upload Detected");
				return;
			}
			
			
			/* Straight from commons.io docs */
			
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			// Parse the request
			try {
				
				List<FileItem> items = castList(FileItem.class, upload.parseRequest(request));
				
				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = (FileItem) iter.next();

				    if (item.isFormField()) {
				        //processFormField(item);
				    } else {
				        //processUploadedFile(item);
				    	//String fieldName = item.getFieldName();
				        //String fileName = item.getName();
				        //String contentType = item.getContentType();
				        //boolean isInMemory = item.isInMemory();
				        //long sizeInBytes = item.getSize();
				        
				    	if (!item.getName().endsWith(".ogg") && !item.getName().endsWith(".wav") && !item.getName().endsWith(".mp3")) {
				    		response.getWriter().println("File must be a .ogg or .wave");
				    		return;
				    	}
				    	
				    	String name = item.getName().replace(" ", "_");
				    	File uploadedFile = new File(JukeBukkit.getInstance().getDataFolder(), "music/"+name);
				        item.write(uploadedFile);
				        
				        response.getWriter().println("1");
				        return;
				    }
				}
				
			} catch (FileUploadException e) {
				response.getWriter().println(e.getMessage());
				return;
			} catch (Exception e) {
				response.getWriter().println(e.getMessage());
				return;
			}
	        
	        return;
		}
		
	}
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
}
