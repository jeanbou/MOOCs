package com.subtitlor.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subtitlor.bean.ObjectLineSrt;
import com.subtitlor.bean.TradFileRecord;
import com.subtitlor.dao.DAOConfigurationException;
import com.subtitlor.dao.DaoException;
import com.subtitlor.dao.DaoFactory;
import com.subtitlor.dao.FileDao;
import com.subtitlor.utilities.CoupleOfCustomizedString;
import com.subtitlor.utilities.CustomizedString;
import com.subtitlor.utilities.SubtitlesHandler;

public class ModifSubtitle extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private SubtitlesHandler subtitlesHandler;
	private FileDao fileDao;
	private ArrayList<ObjectLineSrt> listObjectSrtTranslated = null;
	private ArrayList<TradFileRecord> listTradFileRecord = null;
	private ArrayList<TradFileRecord> listTradFileRecords;
	private ArrayList<CustomizedString> translatedBisSubtitles;
	private int nbTranslatedLine;
	
	
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
		 this.subtitlesHandler = (SubtitlesHandler) request.getSession().getAttribute("subtitlesHandler");
		 if (this.subtitlesHandler == null) {
			 request.setAttribute( "erreurPasDeFichierUploade", new String("Vous n'avez pas uploadé de fichier"));
	    		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	    	} else {
	    		try {
					listTradFileRecords = fileDao.lister();
				} catch (DaoException e) {
					request.setAttribute("errorDB", e.getMessage());
				}
				ArrayList<CustomizedString> listCustomizedString;
				listCustomizedString =  this.subtitlesHandler.getDbTranslatedSubtitles(listTradFileRecords); 
				ArrayList<CoupleOfCustomizedString> doubleSubtitlesDb = this.subtitlesHandler.getListCoupleOfCustomizedString (listCustomizedString);
				request.getSession().setAttribute("doubleSubtitles", doubleSubtitlesDb);
				this.getServletContext().getRequestDispatcher("/WEB-INF/editSubtitleDouble.jsp").forward(request, response);
	    	}
		}
	 
	 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.subtitlesHandler = (SubtitlesHandler) request.getSession().getAttribute("subtitlesHandler");
		ArrayList<String> listLigneNoTranslated;
		translatedBisSubtitles  = new ArrayList<CustomizedString>();
		listLigneNoTranslated = new ArrayList<String>();
		int nbTranslatedLine = subtitlesHandler.getNbTranslatedLine();
		for (int i= 0 ; i < subtitlesHandler.getOriginalSubtitles().size(); i++) {
			String translatedLine;
			CustomizedString elementObject = subtitlesHandler.getOriginalSubtitles().get(i);
			CustomizedString customizedString = new CustomizedString();
			String nb = String.valueOf( i );
			String ligne = "line"+nb;
			if (elementObject.isNumeric()) {
				translatedBisSubtitles.add(elementObject);
				continue;
			} else if (elementObject.isTimeValues()) {
				translatedBisSubtitles.add(elementObject);
				continue;
			} else if (elementObject.isVide()) {
				CustomizedString customizedString1 = new CustomizedString("");
				translatedBisSubtitles.add(customizedString1);
				continue;
			} else if (elementObject.isStringTraduction()) {
					translatedLine = request.getParameter(ligne);
					if (translatedLine != null ) {
						if (translatedLine.equals("")) {
							customizedString.setMyString("lignePasRemplie&"+ligne);
							listLigneNoTranslated.add(ligne);
							translatedBisSubtitles.add(customizedString);
						} else {
							customizedString = new CustomizedString();
							customizedString.setMyString(translatedLine);
							translatedBisSubtitles.add(customizedString);
							this.nbTranslatedLine++;
						}
					continue;
				}
					
			}
		}
		ArrayList<String> listObjectLineSrtTranslatedinLine = new ArrayList<String>();
		String completeLigne = "";
		for (int j = 0 ; j <= translatedBisSubtitles.size() ; j++) {
			if (j == translatedBisSubtitles.size()) {
				completeLigne += "_";
				listObjectLineSrtTranslatedinLine.add(completeLigne);
				completeLigne = "";
				break;
			}
			if (!translatedBisSubtitles.get(j).getMyString().equals("")) {
				completeLigne += translatedBisSubtitles.get(j).getMyString() + "##";
			} else {
				completeLigne += "_";
				listObjectLineSrtTranslatedinLine.add(completeLigne);
				completeLigne = "";
			}
		}
		listObjectSrtTranslated = new ArrayList<ObjectLineSrt>(); 
		ObjectLineSrt lineSrt = new ObjectLineSrt();
		for (int j =0; j < listObjectLineSrtTranslatedinLine.size() ; j++) {
			String str[]  = listObjectLineSrtTranslatedinLine.get(j).split("##");
			if (str.length <3) {
				break;
			}
				lineSrt.setIdLigne(str[0]);
				lineSrt.setTimeValues(str[1]);
				lineSrt.setTranslatedText1(str[2]);
				if (((str[3] != null)  && str[3].equals("_"))) {
					lineSrt.setTranslatedText2("");	
				} else {
					lineSrt.setTranslatedText2(str[3]);	
				}
				listObjectSrtTranslated.add(lineSrt);
				lineSrt = new ObjectLineSrt();
				continue;
		}
		subtitlesHandler.setTranslatedSubtitles(translatedBisSubtitles);
		subtitlesHandler.makeLineSrtToRecords(listObjectSrtTranslated);
		subtitlesHandler.setListObjectSrtTranslated(listObjectSrtTranslated);
		settranslatedBisSubtitles(translatedBisSubtitles);  
		try {
			fileDao.deleteTable(subtitlesHandler.getFileName());
		} catch (DaoException e) {
			request.setAttribute("errorDB", e.getMessage());
		}
		try {
			fileDao.ajouter(subtitlesHandler.getListTradFileRecord());
		} catch (DaoException e) {
			request.setAttribute("errorDB", e.getMessage());
		}
		subtitlesHandler.setListLigneNoTranslated(listLigneNoTranslated);
		subtitlesHandler.setTranslatedSubtitles(translatedBisSubtitles); 
		request.setAttribute("doubleSubtitles", subtitlesHandler.getListCoupleOfCustomizedString());
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifSubtitle.jsp").forward(request, response);
	}
	
	public void getTranslatedSubtitles(SubtitlesHandler subtitlesHandler, HttpServletRequest request) {
		translatedBisSubtitles  = new ArrayList<CustomizedString>();
		ArrayList<String> listLigneNoTranslated = new ArrayList<String>();
		int nbTranslatedLine = subtitlesHandler.getNbTranslatedLine();
		for (int i= 0 ; i < subtitlesHandler.getOriginalSubtitles().size(); i++) {
			String translatedLine;
			CustomizedString elementObject = subtitlesHandler.getOriginalSubtitles().get(i);
			CustomizedString customizedString = new CustomizedString();
			String nb = String.valueOf( i );
			String ligne = "line"+nb;
			if (elementObject.isNumeric()) {
				translatedBisSubtitles.add(elementObject);
				continue;
			} else if (elementObject.isTimeValues()) {
				translatedBisSubtitles.add(elementObject);
				continue;
			} else if (elementObject.isVide()) {
				CustomizedString customizedString1 = new CustomizedString("");
				translatedBisSubtitles.add(customizedString1);
				continue;
			} else if (elementObject.isStringTraduction()) {
					translatedLine = request.getParameter(ligne);
					if (translatedLine != null ) {
						if (translatedLine.equals("")) {
							customizedString.setMyString("lignePasRemplie&"+ligne);
							listLigneNoTranslated.add(ligne);
							translatedBisSubtitles.add(customizedString);
						} else {
							customizedString = new CustomizedString();
							customizedString.setMyString(translatedLine);
							translatedBisSubtitles.add(customizedString);
							this.nbTranslatedLine++;
						}
					continue;
				}
			}
		}
		if (listLigneNoTranslated.size() > 0) {
			subtitlesHandler.setTraductionFinished(false);
		} else {
			subtitlesHandler.setTraductionFinished(true);
		}
		ArrayList<String> listObjectLineSrtTranslatedinLine = new ArrayList<String>();
		String completeLigne = "";
		for (int j = 0 ; j <= translatedBisSubtitles.size() ; j++) {
			if (j == translatedBisSubtitles.size()) {
				completeLigne += "_";
				listObjectLineSrtTranslatedinLine.add(completeLigne);
				completeLigne = "";
				break;
			}
			if (!translatedBisSubtitles.get(j).getMyString().equals("")) {
				completeLigne += translatedBisSubtitles.get(j).getMyString() + "##";
			} else {
				completeLigne += "_";
				listObjectLineSrtTranslatedinLine.add(completeLigne);
				completeLigne = "";
			}
		}
		listObjectSrtTranslated = new ArrayList<ObjectLineSrt>(); 
		ObjectLineSrt lineSrt = new ObjectLineSrt();
		for (int j =0; j < listObjectLineSrtTranslatedinLine.size() ; j++) {
			String str[]  = listObjectLineSrtTranslatedinLine.get(j).split("##");
				lineSrt.setIdLigne(str[0]);
				lineSrt.setTimeValues(str[1]);
				lineSrt.setTranslatedText1(str[2]);
				if (((str[3] != null)  && str[3].equals("_"))) {
					lineSrt.setTranslatedText2("");	
				} else {
					lineSrt.setTranslatedText2(str[3]);	
				}
				listObjectSrtTranslated.add(lineSrt);
				lineSrt = new ObjectLineSrt();
				continue;
		}
	}
	
	public void makeLineSrtToRecords(SubtitlesHandler subtitlesHandler) {
		ObjectLineSrt myObject;
		ArrayList<ObjectLineSrt> listObjectTranslated = new ArrayList<ObjectLineSrt>();  
		for (int i = 0 ; i < listObjectSrtTranslated.size() ; i++) {
			myObject = listObjectSrtTranslated.get(i);
			myObject.setText1(listObjectSrtTranslated.get(i).getText1());
			myObject.setText2(listObjectSrtTranslated.get(i).getText2());
			listObjectTranslated.add(myObject);
		}
		this.listTradFileRecord = new ArrayList<TradFileRecord>();
		TradFileRecord tradFileRecord;
		for (int j = 0 ; j < listObjectTranslated.size() ; j++) {
			tradFileRecord = new TradFileRecord();
			tradFileRecord.setDescriptionFile(subtitlesHandler.getDescription());
			tradFileRecord.setFileName(subtitlesHandler.getFileName());
			tradFileRecord.setIdLigne(listObjectTranslated.get(j).getIdLigne());
			tradFileRecord.setTimeValues(listObjectTranslated.get(j).getTimeValues());
			tradFileRecord.setOriginalLine1(listObjectTranslated.get(j).getText1());
			tradFileRecord.setOriginalLine2(listObjectTranslated.get(j).getText2());
			if (listObjectTranslated.get(j).getTranslatedText1().startsWith("lignePasRemplie")) {
				tradFileRecord.setTranslatedLine1("");
			} else {
				tradFileRecord.setTranslatedLine1(listObjectTranslated.get(j).getTranslatedText1());
			}
			if (listObjectTranslated.get(j).getTranslatedText2().startsWith("lignePasRemplie")) {
				tradFileRecord.setTranslatedLine2("");
			} else {
				tradFileRecord.setTranslatedLine2(listObjectTranslated.get(j).getTranslatedText2());
			}
			listTradFileRecord.add(tradFileRecord);
		}

	}
	
	public ArrayList<TradFileRecord> getListTradFileRecords() {
		return listTradFileRecords;
	}

	public void setListTradFileRecords(ArrayList<TradFileRecord> listTradFileRecords) {
		this.listTradFileRecords = listTradFileRecords;
	}

	public ArrayList<CustomizedString> gettranslatedBisSubtitles() {
		return translatedBisSubtitles;
	}

	public void settranslatedBisSubtitles(ArrayList<CustomizedString> translatedBisSubtitles) {
		this.translatedBisSubtitles = translatedBisSubtitles;
	}

	public int getNbTranslatedLine() {
		return nbTranslatedLine;
	}

	public void setNbTranslatedLine(int nbTranslatedLine) {
		this.nbTranslatedLine = nbTranslatedLine;
	}	
}
