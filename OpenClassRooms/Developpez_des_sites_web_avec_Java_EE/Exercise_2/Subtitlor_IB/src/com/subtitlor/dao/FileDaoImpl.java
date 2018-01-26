package com.subtitlor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.subtitlor.bean.TradFileRecord;
import com.subtitlor.utilities.CustomizedString;

public class FileDaoImpl implements FileDao {
    private DaoFactory daoFactory;

    public FileDaoImpl( DaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public boolean ajouter( ArrayList<TradFileRecord> lisRecords ) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            for ( int i = 0; i < lisRecords.size(); i++ ) {
                preparedStatement = connexion.prepareStatement(
                        "INSERT INTO translatedfile (idline, timevalues, text1, text2, translatedtext1, translatedtext2, filename , descriptionfile) VALUES  ( ?, ?, ?, ?, ?, ?, ?, ? );" );
                preparedStatement.setInt( 1, new Integer( lisRecords.get( i ).getIdLigne() ) );
                preparedStatement.setString( 2, lisRecords.get( i ).getTimeValues() );
                preparedStatement.setString( 3, lisRecords.get( i ).getOriginalLine1() );
                preparedStatement.setString( 4, lisRecords.get( i ).getOriginalLine2() );
                preparedStatement.setString( 5, lisRecords.get( i ).getTranslatedLine1() );
                preparedStatement.setString( 6, lisRecords.get( i ).getTranslatedLine2() );
                preparedStatement.setString( 7, lisRecords.get( i ).getFileName() );
                preparedStatement.setString( 8, lisRecords.get( i ).getDescriptionFile() );
                preparedStatement.executeUpdate();
            }
        }
	catch ( SQLException e ) {
            throw new DaoException( "Impossible de communiquer avec la base de données" );
        } finally {
            try {
                if ( preparedStatement != null )
                    connexion.close();
            } catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
            try {
                if ( connexion != null )
                    connexion.close();
            } catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
        }
        return true;
    }

    @Override
    public ArrayList<TradFileRecord> lister() throws DaoException {
        ArrayList<TradFileRecord> listTradFileRecords = new ArrayList<TradFileRecord>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(
                    "SELECT idLine, timeValues, text1, text2, translatedText1, translatedText2, fileName , descriptionFile FROM translatedfile;" );
            while ( resultat.next() ) {
                TradFileRecord tradFileRecord = new TradFileRecord();
                tradFileRecord.setIdLigne( resultat.getString( "idLine" ) );
                tradFileRecord.setTimeValues( resultat.getString( "timeValues" ) );
                tradFileRecord.setOriginalLine1( resultat.getString( "text1" ) );
                tradFileRecord.setOriginalLine2( resultat.getString( "text2" ) );
                tradFileRecord.setTranslatedLine1( resultat.getString( "translatedText1" ) );
                tradFileRecord.setTranslatedLine2( resultat.getString( "translatedText2" ) );
                tradFileRecord.setFileName( resultat.getString( "fileName" ) );
                tradFileRecord.setDescriptionFile( resultat.getString( "descriptionFile" ) );
                listTradFileRecords.add( tradFileRecord );
            }
        }
	catch ( SQLException e ) {
            throw new DaoException( "Impossible de communiquer avec la base de données" );
        }
        return listTradFileRecords;
    }

    @Override
    public ArrayList<TradFileRecord> lister( String filename ) throws DaoException {
        ArrayList<TradFileRecord> listTradFileRecords = new ArrayList<TradFileRecord>();
        Connection connexion = null;
        ResultSet resultat = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement(
                    "SELECT idline, timevalues, text1, text2, translatedtext1, translatedtext2, filename , descriptionfile FROM translatedfile WHERE filename = ? " );
            preparedStatement.setString( 1, filename );
            resultat = preparedStatement.executeQuery();
            while ( resultat.next() ) {
                TradFileRecord tradFileRecord = new TradFileRecord();
                tradFileRecord.setIdLigne( resultat.getString( "idline" ) );
                tradFileRecord.setTimeValues( resultat.getString( "timevalues" ) );
                tradFileRecord.setOriginalLine1( resultat.getString( "text1" ) );
                if ( resultat.getString( "text2" ).equals( "_" ) ) {
                    tradFileRecord.setOriginalLine2( "" );
                } else {
                    tradFileRecord.setOriginalLine2( resultat.getString( "text2" ) );
                }
                tradFileRecord.setOriginalLine2( resultat.getString( "text2" ) );
                tradFileRecord.setTranslatedLine1( resultat.getString( "translatedtext1" ) );
                tradFileRecord.setTranslatedLine2( resultat.getString( "translatedtext2" ) );
                tradFileRecord.setFileName( resultat.getString( "filename" ) );
                tradFileRecord.setDescriptionFile( resultat.getString( "descriptionfile" ) );
                listTradFileRecords.add( tradFileRecord );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible de communiquer avec la base de données" );
        }
        return listTradFileRecords;
    }

    @Override
    public ArrayList<CustomizedString> retrieveDbSubtitlesFiles() throws DaoException {
        ArrayList<CustomizedString> listDistinctSubtitlesFiles = new ArrayList<CustomizedString>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultSet = statement.executeQuery( "SELECT DISTINCT filename FROM translatedfile;" );
            String filename;
            CustomizedString customizedString;
            boolean isFinished = false;
            ;
            while ( resultSet.next() ) {
                if ( resultSet != null ) {
                    filename = resultSet.getString( "filename" );
                    isFinished = isFinishedInDb( filename );
                    customizedString = new CustomizedString();
                    customizedString.setMyString( filename );
                    customizedString.setTranslationFinished( isFinished );
                    listDistinctSubtitlesFiles.add( customizedString );
                }
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible de communiquer avec la base de données" );
        } finally {
            try {
                if ( statement != null )
                    connexion.close();
            } catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
            try {
                if ( connexion != null )
                    connexion.close();
            }
		catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
        }

        return listDistinctSubtitlesFiles;
    }

    private boolean isFinishedInDb( String filename ) {
        boolean isFinished = true;
        ArrayList<TradFileRecord> listOfTradFileRecords = null;
        try {
            listOfTradFileRecords = lister( filename );
        } 
	catch ( DaoException e ) {
        }
        TradFileRecord tradFileRecord;
        for ( int i = 0; i < listOfTradFileRecords.size(); i++ ) {
            tradFileRecord = listOfTradFileRecords.get( i );
            if ( tradFileRecord.getTranslatedLine1().equals( "" ) ) {
                isFinished = false;
                break;
            } else {
                isFinished = true;
            }
            if ( ( ( !tradFileRecord.getOriginalLine2().equals( "_" ) )
                    && ( !tradFileRecord.getOriginalLine2().equals( "" ) ) )
                    && ( tradFileRecord.getTranslatedLine2().equals( "" ) ) ) {
                isFinished = false;
                break;
            } else if ( ( !tradFileRecord.getOriginalLine2().equals( "_" ) )
                    && ( !tradFileRecord.getTranslatedLine2().equals( "" ) ) ) {
                isFinished = true;
            }
        }
        return isFinished;
    }

    @Override
    public void deleteTable() throws DaoException {
        Connection connexion = null;
        Statement statement = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String sql = "DELETE FROM translatedfile";
            statement.executeUpdate( sql );
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible de communiquer avec la base de données" );
        } finally {
            try {
                if ( statement != null )
                    connexion.close();
            } catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
            try {
                if ( connexion != null )
                    connexion.close();
            } catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
        }
    }

    @Override
    public void deleteTable( String filename ) throws DaoException {
        Connection connexion = null;
        Statement statement = null;
        try {
            connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion
                    .prepareStatement( "DELETE FROM translatedfile WHERE filename = ? " );
            preparedStatement.setString( 1, filename );
            preparedStatement.executeUpdate();
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible de communiquer avec la base de données" );
        } finally {
            
            try {
                if ( statement != null )
                    connexion.close();
            } catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
            try {
                if ( connexion != null )
                    connexion.close();
            } catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
        }
    }

    @Override
    public void updateTradFileRecord( List<TradFileRecord> list ) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            TradFileRecord tradFileRecord;
            connexion = daoFactory.getConnection();
            for ( int i = 0; i < list.size(); i++ ) {
                tradFileRecord = list.get( i );
                preparedStatement = connexion.prepareStatement(
                        "UPDATE translatedfile SET translatedline1 = ?, translatedline2 = ? WHERE idligne = ? " );
                preparedStatement.setString( 1, tradFileRecord.getTranslatedLine1() );
                preparedStatement.setString( 2, tradFileRecord.getTranslatedLine2() );
                preparedStatement.setString( 3, tradFileRecord.getIdLigne() );
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible de communiquer avec la base de données" );
        }
    }

    @Override
    public int recupererNbRecords() throws DaoException {
        Connection connexion = null;
        Statement statement = null;
        int count = 0;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery( "SELECT COUNT(*) FROM translatedfile AS count" );
            while ( resultSet.next() ) {
                count = resultSet.getInt( "count" );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible de communiquer avec la base de données" );
        } finally {
            try {
                if ( statement != null )
                    connexion.close();
            } catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
            try {
                if ( connexion != null )
                    connexion.close();
            } catch ( SQLException se ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
        }
        return count;
    }

}
