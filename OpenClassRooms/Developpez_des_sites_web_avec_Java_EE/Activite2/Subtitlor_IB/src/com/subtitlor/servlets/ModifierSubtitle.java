package com.subtitlor.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subtitlor.dao.DAOConfigurationException;
import com.subtitlor.dao.DaoException;
import com.subtitlor.dao.DaoFactory;
import com.subtitlor.dao.FileDao;
import com.subtitlor.utilities.CustomizedString;
import com.subtitlor.utilities.SubtitlesHandler;

public class ModifierSubtitle extends HttpServlet  {

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
	    	this.getServletContext().getRequestDispatcher("/WEB-INF/upload.jsp").forward(request, response);
	  }

	  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	    	subtitlesHandler = (SubtitlesHandler) request.getSession().getAttribute("subtitlesHandler");
	    	ArrayList<CustomizedString> listTranslatedSubtitles = subtitlesHandler.getTranslatedSubtitles(request);;
	    	subtitlesHandler.makeLineSrtToRecords();
	    	String succes;
	    	if (subtitlesHandler.isTraductionFinished()) {
	    		succes = "Enregistrement total du fichier effectué";
	    		request.getSession().setAttribute("apparaitreBouttonExporter", "apparaitreBouttonExporter");
	    		request.getSession().setAttribute("disparaitreBouttonModifier", "disparaitreBouttonModifier");
	    		request.getSession().setAttribute("disparaitreBouttonEnregistrer", "disparaitreBouttonEnregistrer");
	    	} else {
	    		succes = "Enregistrement partiel du fichier effectué";
	    		request.getSession().setAttribute("partiel", "partiel");
	    		request.getSession().setAttribute("apparaitreBouttonSauvegarder", "apparaitreBouttonSauvegarder");
	    	}
	    	try {
				this.fileDao.deleteTable(subtitlesHandler.getFileName());
			} catch (DaoException e1) {
				request.setAttribute("errorDB", e1.getMessage());
			}
	    	
	    	boolean isOK = false;
			try {
				isOK = fileDao.ajouter(subtitlesHandler.getListTradFileRecord());
			} catch (DaoException e) {
				request.setAttribute("errorDB", e.getMessage());
			}
	    	if (isOK) {
	    		request.getSession().setAttribute("succes", succes);
	    		request.getSession().setAttribute("doubleSubtitles", subtitlesHandler.getListCoupleOfCustomizedString());
	    		request.getSession().setAttribute("subtitlesHandler", subtitlesHandler);
	    		this.getServletContext().getRequestDispatcher("/WEB-INF/editSubtitleDouble.jsp").forward(request, response);
	    	} else {
	    		request.getSession().setAttribute("subtitlesHandler", subtitlesHandler);
	    		this.getServletContext().getRequestDispatcher("/WEB-INF/modifSubtitle.jsp").forward(request, response);
	    	}
	  }
}
