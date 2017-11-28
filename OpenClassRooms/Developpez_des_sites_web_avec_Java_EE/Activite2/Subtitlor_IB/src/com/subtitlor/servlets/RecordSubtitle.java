package com.subtitlor.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subtitlor.dao.DAOConfigurationException;
import com.subtitlor.dao.DaoException;
import com.subtitlor.dao.DaoFactory;
import com.subtitlor.dao.FileDao;
import com.subtitlor.utilities.SubtitlesHandler;

public class RecordSubtitle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final int   TAILLE_TAMPON    = 10240; // IB: Copy & Paste from example given at Course, well...
    private SubtitlesHandler  subtitlesHandler;
    private FileDao           fileDao;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = null;
        try {
            daoFactory = DaoFactory.getInstance();
        } catch ( DAOConfigurationException e ) {
            e.printStackTrace();
        }
        this.fileDao = daoFactory.getFileDao();
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/upload.jsp" ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        subtitlesHandler = (SubtitlesHandler) request.getSession().getAttribute( "subtitlesHandler" );
        subtitlesHandler.getTranslatedSubtitles( request );
        subtitlesHandler.makeLineSrtToRecords();
        try {
            fileDao.deleteTable( subtitlesHandler.getFileName() );
        }
	catch ( DaoException e1 ) {
            request.setAttribute( "errorDB", e1.getMessage() );           
        }
        try {
            fileDao.ajouter( subtitlesHandler.getListTradFileRecord() );
        } catch ( DaoException e ) {
            request.setAttribute( "errorDB", e.getMessage() );
        }
        request.setAttribute( "disparaitreBouttonEnregistré", "disparaitreBouttonEnregistré" );
        request.setAttribute( "doubleSubtitles", subtitlesHandler.getListCoupleOfCustomizedString() );
        request.getSession().setAttribute( "subtitlesHandler", subtitlesHandler );
        this.getServletContext().getRequestDispatcher( "/WEB-INF/editSubtitleDouble.jsp" ).forward( request, response );
    }
}
