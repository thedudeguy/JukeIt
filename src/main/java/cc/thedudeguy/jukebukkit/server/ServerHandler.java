package cc.thedudeguy.jukebukkit.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
			cfg.setDirectoryForTemplateLoading( new File(JukeBukkit.instance.getDataFolder(), "web") );
			cfg.setObjectWrapper(new DefaultObjectWrapper());  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List getFileList() {
		
		File musicFolder = new File(JukeBukkit.instance.getDataFolder(), "music");
		if (!musicFolder.exists()) {
			return Arrays.asList(new String[0]);
		}
		File[] fileList = musicFolder.listFiles(); 
		if (fileList.length < 1) {
			return Arrays.asList(new String[0]);
		}
		
		return Arrays.asList(new String[0]);
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
			
			//TODO: Fix warnings
			Map dataRoot = new HashMap();
			dataRoot.put("serverName", "JukeBukkit");
			
			List files = getFileList();
			dataRoot.put("files", files);
			
			try {
				template.process(dataRoot, response.getWriter());
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*
			response.getWriter().println("<html><head><title>JukeBukkit Web</title></head><body>");
			
			response.getWriter().println("<h1>Upload</h1>");
			
			response.getWriter().println("<form action=\"/upload\" method=\"post\" enctype=\"multipart/form-data\">");
			response.getWriter().println("<input type=\"file\" name=\"music\" />");
			response.getWriter().println("<input type=\"submit\" value=\"Submit\" />");
			response.getWriter().println("</form>");
			
			response.getWriter().println("<h1>Files</h1>");
			
			
			File musicFolder = new File(JukeBukkit.instance.getDataFolder(), "music");
			if (!musicFolder.exists()) {
				
				response.getWriter().println("<p>No music files on server</p>");
				
				
			} else {
				File[] fileList = musicFolder.listFiles(); 
				if (fileList.length < 1) {
					response.getWriter().println("<p>No music files on server</p>");
				} else {
					for (File file : fileList) {
						if (file.isFile()) {
							response.getWriter().println("<p>"+file.getName()+"</p>");
						}
					}
				}
			}
			
			response.getWriter().println("</body></html>");
			*/
			
			return;
		}
		
		
		if ( target.equalsIgnoreCase("/upload") ) {
			
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			baseRequest.setHandled(true);
			
			StringBuffer buff = new StringBuffer();
			
			if (!ServletFileUpload.isMultipartContent(request))
			{
				buff.append("No File Upload Detected");
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
				        
				    	File uploadedFile = new File(JukeBukkit.instance.getDataFolder(), "music/"+item.getName());
				        item.write(uploadedFile);
				        
				        buff.append( "File successfully uploaded." );
				    }
				}
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	 
	        response.getWriter().write( "<html>" );
	        response.getWriter().write( "<head><title>JukeBukkit Upload</title></head>" );
	        response.getWriter().write( "<body>" );
	        response.getWriter().write( "<h2>" + buff.toString() + "</h2>" );
	        response.getWriter().write( "</body>" );
	        response.getWriter().write( "</html>" );
	        
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
