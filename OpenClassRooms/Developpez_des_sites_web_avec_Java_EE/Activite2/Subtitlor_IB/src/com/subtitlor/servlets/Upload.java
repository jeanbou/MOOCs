package com.subtitlor.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Upload extends HttpServlet {

    public static final int   TAILLE_TAMPON    = 10240;
    public static final int   MAX_SIZE         = 10 * 1024 * 1024; // 10Mb, IB: based on example given on OCR
    private ServletFileUpload uploader         = null;
    private static final long serialVersionUID = 1L;
    private File              file;
    private File              filesDir;
    private FileItem          fileItem;
    private String            fileDescription;

    @Override
    public void init() throws ServletException {
        final DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        this.filesDir = (File) this.getServletContext().getAttribute( "FILES_DIR_FILE" );
        fileFactory.setRepository( this.filesDir );
        this.uploader = new ServletFileUpload( fileFactory );
    }

    @Override
    protected void doGet( final HttpServletRequest request, final HttpServletResponse response )
            throws ServletException, IOException {

        final DateTime dt = new DateTime();

        final org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern( "dd/MM/yyyy HH:mm:ss" );
        final String date = dt.toString( formatter );
        request.setAttribute( "disparaitreBouttonEditer", "disparaitreBouttonEditer" );
        this.getServletContext().getRequestDispatcher( "/WEB-INF/upload.jsp" ).forward( request, response );
    }

    @Override
    public void doPost( final HttpServletRequest request, final HttpServletResponse response )
            throws ServletException, IOException {
        final String description = null;
        if ( !ServletFileUpload.isMultipartContent( request ) )
            throw new ServletException( "Content type is not multipart/form-data" );
        try {

            final FileItemFactory factory = new DiskFileItemFactory();

            final ServletFileUpload upload = new ServletFileUpload( factory );
            final List<FileItem> uploadItems = upload.parseRequest( request );

            for ( final FileItem uploadItem : uploadItems ) {
                if ( uploadItem.isFormField() ) {
                    final String fieldName = uploadItem.getFieldName();
                    this.fileDescription = uploadItem.getString();
                }
            }
            final Iterator<FileItem> fileUploadItemsIterator = uploadItems.iterator();
            while ( fileUploadItemsIterator.hasNext() ) {
                this.fileItem = fileUploadItemsIterator.next();
                if ( this.fileItem.getName() == null ) {
                    continue;
                }
                this.file = new File(
                        request.getServletContext().getAttribute( "FILES_DIR" ) + File.separator
                                + this.fileItem.getName() );
                String filename;
                filename = this.file.getName();
                if ( ( filename != null ) && !filename.isEmpty() && !filename.equals( "tmpfiles" ) ) {
                    
                    filename = filename.substring( filename.lastIndexOf( '/' ) + 1 )
                            .substring( filename.lastIndexOf( '\\' ) + 1 );
                    final String extension = filename.substring( filename.indexOf( "." ) );
                    if ( !extension.equals( ".srt" ) ) {
                        request.setAttribute( "erreurPasFichierSrt", new String( "erreurPasFichierSrt" ) );
                        request.setAttribute( "disparaitreBouttonEditer", "disparaitreBouttonEditer" );
                    } else {
                        System.out.println( "Absolute Path at server=" + this.file.getAbsolutePath() );
                        this.fileItem.write( this.file );
                        System.out.println( "File " + this.fileItem.getName() + " uploaded successfully." );
                        request.setAttribute( "succesUpload", "succesUpload" );
                        request.getSession().setAttribute( "fileItem", this.fileItem );
                        request.getSession().setAttribute( "nomFichier", this.fileItem.getName() );
                        request.getSession().setAttribute( "Fichierdescription", this.fileDescription );
                        request.getSession().setAttribute( "pathFile", this.file.getAbsolutePath() );
                        String pathFile = this.file.getAbsolutePath();
                        final int nbDerniereSlash = pathFile.lastIndexOf( "\\" );
                        pathFile = pathFile.substring( 0, nbDerniereSlash + 1 );
                        request.getSession().setAttribute( "pathFileTomcat", pathFile );
                    }
                } else {
                    request.setAttribute( "disparaitreBouttonEditer", "disparaitreBouttonEditer" );
                    request.setAttribute( "erreurPasDeFichierSelectionne",
                            new String( "erreurPasDeFichierSelectionne" ) );
                }
            }

        } catch ( final FileUploadException e ) {
            request.setAttribute( "errorFileUpload", e.getMessage() );
        } catch ( final Exception e ) {
            request.setAttribute( "errorFileUpload", e.getMessage() );
        }
        this.getServletContext().getRequestDispatcher( "/WEB-INF/upload.jsp" ).forward( request, response );
    }

}
