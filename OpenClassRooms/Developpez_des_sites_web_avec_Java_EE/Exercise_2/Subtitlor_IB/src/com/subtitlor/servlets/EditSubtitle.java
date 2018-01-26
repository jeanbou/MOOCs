package com.subtitlor.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subtitlor.utilities.SubtitlesHandler;

public class EditSubtitle extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private SubtitlesHandler  subtitlesHandler;

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String nomFichier = (String) request.getSession().getAttribute( "nomFichier" );
        if ( nomFichier == null ) {
            String erreur = "Vous devez choisir un fichier à uploader";
            request.setAttribute( "erreur", erreur );
            this.getServletContext().getRequestDispatcher( "/WEB-INF/upload.jsp" ).forward( request, response );
        } else {
            request.removeAttribute( "disparaitreBouttonEditer" );
            String description = (String) request.getSession().getAttribute( "Fichierdescription" );
            String pathFile = (String) request.getSession().getAttribute( "pathFile" );
            System.out.println( "pathFile : " + pathFile );
            if ( pathFile == null ) {
                String erreur = "aucun fichier ne sont disponible à l'export ";
                request.setAttribute( "erreur", erreur );
                this.getServletContext().getRequestDispatcher( "/WEB-INF/upload.jsp" ).forward( request, response );
            }
            this.subtitlesHandler = new SubtitlesHandler( pathFile );
            this.subtitlesHandler.setDescription( description );
            this.subtitlesHandler.setFileName( nomFichier );
            this.subtitlesHandler.setPathFileName( pathFile );
            request.getSession().setAttribute( "subtitles", this.subtitlesHandler.getOriginalSubtitles() );
            request.getSession().setAttribute( "subtitlesHandler", subtitlesHandler );
            File fileToDelete = new File( pathFile );
            boolean isDFilDeleted = false;
            isDFilDeleted = fileToDelete.delete();
            if ( isDFilDeleted ) {
                System.out.println( "le fichier " + this.subtitlesHandler.getFileName() + " a bien été éffacé" );
            } else {
                System.out.println( "le fichier " + this.subtitlesHandler.getFileName() + " n\'a pas été éffacé" );
            }
            this.getServletContext().getRequestDispatcher( "/WEB-INF/editSubtitle.jsp" ).forward( request, response );
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        request.setAttribute( "subtitles", subtitlesHandler.getOriginalSubtitles() );
        this.getServletContext().getRequestDispatcher( "/WEB-INF/editSubtitle.jsp" ).forward( request, response );

    }

    public SubtitlesHandler getSubtitlesHandler() {
        return subtitlesHandler;
    }

    public void setSubtitlesHandler( SubtitlesHandler subtitlesHandler ) {
        this.subtitlesHandler = subtitlesHandler;
    }
}
