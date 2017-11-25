package com.subtitlor.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class DownloadFileServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = (String) request.getSession().getAttribute("pathFile");
	 	String fileNameTrad = (String)request.getSession().getAttribute("fileNameTrad");
        File downloadFile = new File(filePath+fileNameTrad);
        FileInputStream inStream = new FileInputStream(downloadFile);
         
        ServletContext context = getServletContext();
         
        
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {        
            
            mimeType = "application/octet-stream";
        }
         
        
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096]; // IB: Yes not cool at all, but...
        int bytesRead = -1; 
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inStream.close();
        outStream.close();
        downloadFile.delete();
    }

}
