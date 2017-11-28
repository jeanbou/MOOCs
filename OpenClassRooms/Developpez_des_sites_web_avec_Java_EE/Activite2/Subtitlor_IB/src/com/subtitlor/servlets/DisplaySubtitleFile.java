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

public class DisplaySubtitleFile extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private FileDao fileDao;

	public void init() {
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
		String finished = (String) request.getParameter("finished");
		ArrayList<TradFileRecord> listDbTradFileRecords = null;
		try {
			listDbTradFileRecords = fileDao.lister(fileName);
		} catch (DaoException e) {
			request.setAttribute("errorDB", e.getMessage());
		}
		ArrayList<CustomizedString> listOfTranslatedCustomizedString = new ArrayList<CustomizedString>();
		ArrayList<CustomizedString> listOfNotTranslatedCustomizedString = new ArrayList<CustomizedString>();
		ObjectLineSrt objectLineSrt;
		ArrayList<ObjectLineSrt> listOfObjectLineSrt = null;
		listOfObjectLineSrt = convertRecordToLine (listDbTradFileRecords);
		for (int j= 0; j < listOfObjectLineSrt.size() ; j++) {
			objectLineSrt = listOfObjectLineSrt.get(j);
			listOfTranslatedCustomizedString.add(new CustomizedString(objectLineSrt.getIdLigne()));
			listOfNotTranslatedCustomizedString.add(new CustomizedString(objectLineSrt.getIdLigne()));
			listOfTranslatedCustomizedString.add(new CustomizedString(objectLineSrt.getTimeValues()));
			listOfNotTranslatedCustomizedString.add(new CustomizedString(objectLineSrt.getTimeValues()));
			listOfTranslatedCustomizedString.add(new CustomizedString(objectLineSrt.getTranslatedText1()));
			listOfNotTranslatedCustomizedString.add(new CustomizedString(objectLineSrt.getText1()));
			if ((!objectLineSrt.getText2().equals("_"))) {
				listOfTranslatedCustomizedString.add(new CustomizedString(objectLineSrt.getTranslatedText2()));
				listOfNotTranslatedCustomizedString.add(new CustomizedString(objectLineSrt.getText2()));			}
			listOfNotTranslatedCustomizedString.add(new CustomizedString(""));
			listOfTranslatedCustomizedString.add(new CustomizedString(""));
		}
		
		SubtitlesHandler subtitlesHandler= new SubtitlesHandler();
		subtitlesHandler.setFileName(fileName);
		subtitlesHandler.setTranslatedSubtitles(listOfTranslatedCustomizedString);
		subtitlesHandler.setDescription("");
		subtitlesHandler.setOriginalSubtitles(listOfNotTranslatedCustomizedString);
		subtitlesHandler.setListTradFileRecord(listDbTradFileRecords);
		if (finished != null) {
			if (finished.equals("true")) {
				subtitlesHandler.setTraductionFinished(true);	
			} else if (finished.equals("true")) {
				subtitlesHandler.setTraductionFinished(false);
			}		
		}
		ArrayList<CoupleOfCustomizedString> listOfCoupleCustomizedString = new ArrayList<CoupleOfCustomizedString>();
		CoupleOfCustomizedString coupleOfCustomizedString = null;
		CustomizedString customizedString1 = null;
		CustomizedString customizedString2 = null;
		for (int i =0; i < listOfNotTranslatedCustomizedString.size() ; i++ ) {
				customizedString1 = listOfNotTranslatedCustomizedString.get(i);
				customizedString2 = listOfTranslatedCustomizedString.get(i);
				coupleOfCustomizedString = new CoupleOfCustomizedString(customizedString1,customizedString2);
				listOfCoupleCustomizedString.add(coupleOfCustomizedString);
		}
		subtitlesHandler.setListCoupleOfCustomizedString(listOfCoupleCustomizedString);
		request.getSession().setAttribute("nomFichier", subtitlesHandler.getFileName());
		request.getSession().setAttribute("subtitlesHandler", subtitlesHandler);
		request.getSession().removeAttribute("disparaitreBouttonModifier");
		request.setAttribute("doubleSubtitles", listOfCoupleCustomizedString);
		this.getServletContext().getRequestDispatcher("/WEB-INF/editSubtitleDouble.jsp").forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/editSubtitle.jsp").forward(request, response);
	}

	public ArrayList<ObjectLineSrt> convertRecordToLine(ArrayList<TradFileRecord> list) {
		ArrayList<ObjectLineSrt> listObjectLineSrtToUpdate = new ArrayList<ObjectLineSrt>();
		TradFileRecord tradFileRecord;
		ObjectLineSrt objectLineSrt = null;
		for (int i = 0  ; i < list.size(); i++) {
			objectLineSrt = new ObjectLineSrt();
			tradFileRecord = list.get(i);
			objectLineSrt.setIdLigne(tradFileRecord.getIdLigne());
			objectLineSrt.setTimeValues(tradFileRecord.getTimeValues());
			objectLineSrt.setTranslatedText1(tradFileRecord.getTranslatedLine1());
			objectLineSrt.setTranslatedText2(tradFileRecord.getTranslatedLine2());
			objectLineSrt.setText1(tradFileRecord.getOriginalLine1());
			if ((tradFileRecord.getOriginalLine2().equals(""))) {
				objectLineSrt.setText2("_");
			} else  {
				objectLineSrt.setText2(tradFileRecord.getOriginalLine2());
			}
			listObjectLineSrtToUpdate.add(objectLineSrt);
		}
		return listObjectLineSrtToUpdate;
	}

}
