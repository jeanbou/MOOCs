package com.subtitlor.servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subtitlor.dao.DAOConfigurationException;
import com.subtitlor.dao.DaoException;
import com.subtitlor.dao.DaoFactory;
import com.subtitlor.dao.FileDao;
import com.subtitlor.utilities.SubtitlesHandler;

public class ExporterSubtitle extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private SubtitlesHandler subtitlesHandler; 
    private FileDao fileDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getInstance();
		} catch (DAOConfigurationException e) {
			e.printStackTrace();
		}
        this.fileDao = daoFactory.getFileDao();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String fileName = (String) request.getParameter("name");
    	this.subtitlesHandler = (SubtitlesHandler) request.getSession().getAttribute("subtitlesHandler");
    	if (this.subtitlesHandler == null) {
    		request.setAttribute( "erreurPasDeFichierUploade", "erreurPasDeFichierUploade");
    		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    	} else {
    		String filename = (String)request.getParameter("filename");
    		String isFinished = (String)request.getParameter("finished");
    	}
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	this.subtitlesHandler = (SubtitlesHandler) request.getSession().getAttribute("subtitlesHandler");
    	try {
			fileDao.deleteTable(subtitlesHandler.getFileName());
		} catch (DaoException e1) {
			e1.printStackTrace();
			request.setAttribute("errorDB", e1.getMessage());
		}
    	if (this.subtitlesHandler == null) {
    		request.setAttribute( "erreurPasDeFichierUploade", new String("Aucun fichier ne sont disponible à l'export"));
    		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    	} else {
    		String rootPath;
    		String pathFile = (String)request.getSession().getAttribute("pathFileTomcat");
    		if (pathFile == null) {
    			rootPath = System.getProperty("catalina.home");
    			pathFile = rootPath + "\\tmpfiles\\";
    		}
    		File originalFile = new File(pathFile + this.subtitlesHandler.getFileName() );
    		originalFile.delete();
    		String fileNameTrad;
    		int nbCharBeforePoint = this.subtitlesHandler.getFileName().indexOf(".");
    		fileNameTrad = this.subtitlesHandler.getFileName().substring(0, nbCharBeforePoint+1);
    		fileNameTrad += "en.srt";
        	File file = new File(pathFile+fileNameTrad);
        	try {
    	  	     if (file.createNewFile()) {
    	  	        System.out.println("File is created!");
    	  	     } else {
    	  	        System.out.println("File already exists.");
    	  	     }
          	} catch (IOException e) {
          		  request.setAttribute("errorIO", e.getMessage());
          	}
    	 	try
    	 	{
    	 	   PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (file)));
    	 	   for(int i = 0 ; i < subtitlesHandler.getTranslatedSubtitles().size() ; i++) {
    	 		  pw.println(subtitlesHandler.getTranslatedSubtitles().get(i).getMyString());
    		   }
    	 	   pw.close();
    	 	}
    	 	catch (IOException e) {
    	 		request.setAttribute("errorIO", e.getMessage());
    	 	}
    	 	request.getSession().setAttribute("pathFile", pathFile);
    	 	request.getSession().removeAttribute("fileItem");
    	 	request.getSession().setAttribute("fileNameTrad", fileNameTrad);
    	 	this.subtitlesHandler = (SubtitlesHandler) request.getSession().getAttribute("subtitlesHandler");
    		this.getServletContext().getRequestDispatcher("/WEB-INF/exporterFileSrt.jsp").forward(request, response);       
    	}
    	
    }    
}
