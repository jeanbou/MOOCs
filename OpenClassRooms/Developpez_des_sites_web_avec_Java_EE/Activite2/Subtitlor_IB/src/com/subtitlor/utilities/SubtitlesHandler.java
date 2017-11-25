package com.subtitlor.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.subtitlor.bean.ObjectLineSrt;
import com.subtitlor.bean.TradFileRecord;

public class SubtitlesHandler implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private ArrayList<CoupleOfCustomizedString> listCoupleOfCustomizedString = null;
	private ArrayList<CustomizedString> originalSubtitles = null;
	private ArrayList<CustomizedString> translatedSubtitles = null;
	private ArrayList<ObjectLineSrt> listObjectLineSrt = null;
	private ArrayList<ObjectLineSrt> listObjectSrtTranslated = null;
	private ArrayList<ObjectLineSrt> listObjectLineSrtToUpdate = null;
	private ArrayList<TradFileRecord> listTradFileRecord = null;
	private ArrayList<String> listLigneNoTranslated = null;
	private int nombreTotalLignes; 
	private int nbTranslatedLine;
	private String description;
	private String fileName;
	private String pathFileName;
	private boolean isTraductionFinished;

	
	public SubtitlesHandler () {
		super();
	}
	
	
	public ArrayList<CoupleOfCustomizedString> getListCoupleOfCustomizedString (List<ObjectLineSrt> listObjectLineSrtToUpdate) {
		this.listCoupleOfCustomizedString = new ArrayList<CoupleOfCustomizedString>();
		List<CustomizedString> list = new ArrayList<CustomizedString> ();
		ObjectLineSrt objectLineSrt;
		for (int j= 0; j < listObjectLineSrtToUpdate.size() ; j++) {
			objectLineSrt = listObjectLineSrtToUpdate.get(j);
			list.add(new CustomizedString(objectLineSrt.getIdLigne()));	
			list.add(new CustomizedString(objectLineSrt.getTimeValues()));
			list.add(new CustomizedString(objectLineSrt.getTranslatedText1()));
			if ((!objectLineSrt.getTranslatedText2().equals(""))  &&  (!objectLineSrt.getTranslatedText2().equals("_"))) {
				list.add(new CustomizedString(objectLineSrt.getTranslatedText2()));
			} 
			list.add(new CustomizedString(""));
		}
		CoupleOfCustomizedString coupleOfCustomizedString;
		for (int i = 0 ; i < originalSubtitles.size(); i++) {
			coupleOfCustomizedString = new CoupleOfCustomizedString();
			if ( i < list.size()) {
				coupleOfCustomizedString.setCustomizedstring1(originalSubtitles.get(i));
				coupleOfCustomizedString.setCustomizedstring2(list.get(i));	
			} else {
				coupleOfCustomizedString.setCustomizedstring1(originalSubtitles.get(i));
				if ( ( originalSubtitles.get(i).isStringTraduction())  && (!originalSubtitles.get(i).isVide()) ) {
					coupleOfCustomizedString.setCustomizedstring2(new CustomizedString(""));
				} else {
					coupleOfCustomizedString.setCustomizedstring2(originalSubtitles.get(i));
				}
			}
			this.listCoupleOfCustomizedString.add(coupleOfCustomizedString);
		}
		return listCoupleOfCustomizedString;
	}
	
	public ArrayList<CustomizedString> getDbTranslatedSubtitles(ArrayList<TradFileRecord> listTradFileRecords) {
		ArrayList<CustomizedString> listDbTranslatedSubtitles = new ArrayList<CustomizedString>();
		for (int i = 0;i < listTradFileRecords.size() ; i++) {
			CustomizedString customizedString;
			TradFileRecord trd = listTradFileRecords.get(i);
			customizedString = new CustomizedString(trd.getIdLigne());
			listDbTranslatedSubtitles.add(customizedString);
			customizedString = new CustomizedString(trd.getTimeValues());
			listDbTranslatedSubtitles.add(customizedString);
			customizedString = new CustomizedString(trd.getTranslatedLine1());
			listDbTranslatedSubtitles.add(customizedString);
			if ((trd.getTranslatedLine2() != null) && (!trd.getTranslatedLine2().equals(""))) {
				customizedString = new CustomizedString(trd.getTranslatedLine2());
				listDbTranslatedSubtitles.add(customizedString);
			}
			customizedString = new CustomizedString("");
			listDbTranslatedSubtitles.add(customizedString);
		}
		return listDbTranslatedSubtitles;
	}
	
	
	
	public ArrayList<CoupleOfCustomizedString> getListCoupleOfCustomizedString () {
		this.listCoupleOfCustomizedString = new ArrayList<CoupleOfCustomizedString>();
		CoupleOfCustomizedString coupleOfCustomizedString;
		for (int i = 0 ; i < originalSubtitles.size(); i++) {
			coupleOfCustomizedString = new CoupleOfCustomizedString();
			if ( i < translatedSubtitles.size()) {
				coupleOfCustomizedString.setCustomizedstring1(originalSubtitles.get(i));
				coupleOfCustomizedString.setCustomizedstring2(translatedSubtitles.get(i));	
			} else {
				coupleOfCustomizedString.setCustomizedstring1(originalSubtitles.get(i));
				if ( ( originalSubtitles.get(i).isStringTraduction())  && (!originalSubtitles.get(i).isVide()) ) {
					coupleOfCustomizedString.setCustomizedstring2(new CustomizedString(""));
				} else {
					coupleOfCustomizedString.setCustomizedstring2(originalSubtitles.get(i));
				}
			}
			this.listCoupleOfCustomizedString.add(coupleOfCustomizedString);
		}
		return listCoupleOfCustomizedString;
	}

	public ArrayList<CoupleOfCustomizedString> getListCoupleOfCustomizedString (ArrayList<CustomizedString> arrayList) {
		this.listCoupleOfCustomizedString = new ArrayList<CoupleOfCustomizedString>();
		CoupleOfCustomizedString coupleOfCustomizedString;
		for (int i = 0 ; i < originalSubtitles.size(); i++) {
			coupleOfCustomizedString = new CoupleOfCustomizedString();
			if ( i < arrayList.size()) {
				coupleOfCustomizedString.setCustomizedstring1(originalSubtitles.get(i));
				coupleOfCustomizedString.setCustomizedstring2(arrayList.get(i));	
			} else {
				coupleOfCustomizedString.setCustomizedstring1(originalSubtitles.get(i));
				if ( ( originalSubtitles.get(i).isStringTraduction())  && (!originalSubtitles.get(i).isVide()) ) {
					coupleOfCustomizedString.setCustomizedstring2(new CustomizedString(""));
				} else {
					coupleOfCustomizedString.setCustomizedstring2(originalSubtitles.get(i));
				}
			}
			this.listCoupleOfCustomizedString.add(coupleOfCustomizedString);
		}
		return listCoupleOfCustomizedString;
	}
	
	
	public SubtitlesHandler (String fileName) throws IOException {
		this.originalSubtitles = new ArrayList<CustomizedString>();
		BufferedReader br;
		int nbLignes = 0;
		br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			if (!line.equals("")) {
			}
				CustomizedString customizedString = new CustomizedString(line);
				this.originalSubtitles.add(customizedString);
				nbLignes++;	
		}
		br.close();
		this.nombreTotalLignes = nbLignes;		
		this.listObjectLineSrt = new ArrayList<ObjectLineSrt>();
		ArrayList<String> listObjectLineSrtenLigne = new ArrayList<String>();
		String completeLigne = "";
		for(int i = 0;  i <= getOriginalSubtitles().size(); i++) {
			if ( i == getOriginalSubtitles().size()) {
				completeLigne += "_";
				listObjectLineSrtenLigne.add(completeLigne);
				break;
			}
			if (!originalSubtitles.get(i).getMyString().equals("")) {
				completeLigne += originalSubtitles.get(i).getMyString() + "##";
			} else {
				completeLigne += "_";
				listObjectLineSrtenLigne.add(completeLigne);
				completeLigne = "";
			}
		}
		ObjectLineSrt lineSrt = new ObjectLineSrt();
		for (int j =0; j < listObjectLineSrtenLigne.size() ; j++) {
			String str[]  = listObjectLineSrtenLigne.get(j).split("##");
				lineSrt.setIdLigne(str[0]);
				lineSrt.setTimeValues(str[1]);
				lineSrt.setText1(str[2]);
				if (!str[3].equals("_")) {
					lineSrt.setText2(str[3]);
				} else {
					lineSrt.setText2("");
				}
				lineSrt.setText2(str[3]);
				this.listObjectLineSrt.add(lineSrt);
				lineSrt = new ObjectLineSrt();
				continue;
		}
	}
	
	public ArrayList<CustomizedString> getTranslatedSubtitles (HttpServletRequest request) {
		translatedSubtitles  = new  ArrayList<CustomizedString>();
		listLigneNoTranslated = new ArrayList<String>();
		this.nbTranslatedLine = 0;
		for (int i= 0 ; i < this.getOriginalSubtitles().size(); i++) {
			String translatedLine;
			CustomizedString elementObject = this.getOriginalSubtitles().get(i);
			CustomizedString customizedString = new CustomizedString();
			String nb = String.valueOf( i );
			String ligne = "line"+nb;
			if (elementObject.isNumeric()) {
				translatedSubtitles.add(elementObject);
				continue;
			} else if (elementObject.isTimeValues()) {
				translatedSubtitles.add(elementObject);
				continue;
			} else if (elementObject.isVide()) {
				CustomizedString customizedString1 = new CustomizedString("");
				translatedSubtitles.add(customizedString1);
				continue;
			} else if (elementObject.isStringTraduction()) {
					translatedLine = request.getParameter(ligne);
					if (translatedLine != null ) {
						if (translatedLine.equals("")) {
							customizedString.setMyString("lignePasRemplie&"+ligne);
							listLigneNoTranslated.add(ligne);
							translatedSubtitles.add(customizedString);
						} else {
							customizedString = new CustomizedString();
							customizedString.setMyString(translatedLine);
							translatedSubtitles.add(customizedString);
							this.nbTranslatedLine++;
						}
					continue;
				}
			}
		}
		if (listLigneNoTranslated.size() > 0) {
			setTraductionFinished(false);
		}
		ArrayList<String> listObjectLineSrtTranslatedinLine = new ArrayList<String>();
		String completeLigne = "";
		for (int j = 0 ; j <= translatedSubtitles.size() ; j++) {
			if (j == translatedSubtitles.size()) {
				completeLigne += "_";
				listObjectLineSrtTranslatedinLine.add(completeLigne);
				completeLigne = "";
				break;
			}
			if (!translatedSubtitles.get(j).getMyString().equals("")) {
				completeLigne += translatedSubtitles.get(j).getMyString() + "##";
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
			if (str.length < 3) {
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
		setTranslatedSubtitles(translatedSubtitles);
		return translatedSubtitles;  
	}
	
	
	public List<ObjectLineSrt> convertRecordsToLine(ArrayList<TradFileRecord> list) {
		List<TradFileRecord> listTradFileRecords = list;
		listObjectLineSrtToUpdate = new ArrayList<ObjectLineSrt>();
		TradFileRecord tradFileRecord;
		ObjectLineSrt objectLineSrt;
		for (int i = 0  ; i < listTradFileRecords.size(); i++) {
			objectLineSrt = new ObjectLineSrt();
			tradFileRecord  = listTradFileRecords.get(i);
			objectLineSrt.setIdLigne(tradFileRecord.getIdLigne());
			objectLineSrt.setTimeValues(tradFileRecord.getTimeValues());
			objectLineSrt.setText1(tradFileRecord.getOriginalLine1());
			if (tradFileRecord.getOriginalLine2().equals("_")){
				objectLineSrt.setText2("");	
			} else {
				objectLineSrt.setText2(tradFileRecord.getOriginalLine2());
			}
			objectLineSrt.setTranslatedText1(tradFileRecord.getTranslatedLine1());
			objectLineSrt.setTranslatedText2(tradFileRecord.getTranslatedLine2());
			listObjectLineSrtToUpdate.add(objectLineSrt);
		}
		setListObjectLineSrtToUpdate(listObjectLineSrtToUpdate);
		return listObjectLineSrtToUpdate;
	}
	
	public ArrayList<ObjectLineSrt> convertRecordToLine(ArrayList<TradFileRecord> list) {
		listObjectLineSrtToUpdate = new ArrayList<ObjectLineSrt>();
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
			objectLineSrt.setText2(tradFileRecord.getOriginalLine2());
			listObjectLineSrtToUpdate.add(objectLineSrt);
		}
		this.setListObjectLineSrtToUpdate(listObjectLineSrtToUpdate);
		return listObjectLineSrtToUpdate;
	}
	
	
	public ArrayList<CustomizedString> getTranslatedSubtitles() {
		return this.translatedSubtitles;
	}
	
	
	public void makeLineSrtToRecords() {
		ObjectLineSrt myObject;
		ArrayList<ObjectLineSrt> listObjectTranslated = new ArrayList<ObjectLineSrt>();  
		for (int i = 0 ; i < listObjectSrtTranslated.size() ; i++) {
			myObject = listObjectSrtTranslated.get(i);
			myObject.setText1(listObjectLineSrt.get(i).getText1());
			myObject.setText2(listObjectLineSrt.get(i).getText2());
			listObjectTranslated.add(myObject);
		}
		this.listTradFileRecord = new ArrayList<TradFileRecord>();
		TradFileRecord tradFileRecord;
		for (int j = 0 ; j < listObjectTranslated.size() ; j++) {
			tradFileRecord = new TradFileRecord();
			tradFileRecord.setDescriptionFile(getDescription());
			tradFileRecord.setFileName(getFileName());
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
		setListTradFileRecord(listTradFileRecord);
	}
	
	
	public void makeLineSrtToRecords(ArrayList<ObjectLineSrt> listObjectSrtTranslated) {
		ObjectLineSrt myObject;
		
		ArrayList<ObjectLineSrt> listObjectTranslated = new ArrayList<ObjectLineSrt>();  
		for (int i = 0 ; i < listObjectSrtTranslated.size() ; i++) {
			myObject = listObjectSrtTranslated.get(i);
			if (listObjectLineSrt == null) {
				int nbOfArrayList = 0;
				for (int j = 0 ; j < this.originalSubtitles.size() ; j++) {
					if (myObject.getTimeValues().equals(this.originalSubtitles.get(j).getMyString())) {
						nbOfArrayList = j;
						break;
					}
				}
				myObject.setText1(this.originalSubtitles.get(nbOfArrayList + 1).getMyString());
				if (!this.originalSubtitles.get(nbOfArrayList + 2).getMyString().equals("")) {
					myObject.setText2(this.originalSubtitles.get(nbOfArrayList + 2).getMyString());	
				} else {
					myObject.setText2("");
				}	
			} else {
				myObject.setText1(listObjectLineSrt.get(i).getText1());
				myObject.setText2(listObjectLineSrt.get(i).getText2());	
			}
			listObjectTranslated.add(myObject);
		}
		this.listTradFileRecord = new ArrayList<TradFileRecord>();
		TradFileRecord tradFileRecord;
		for (int j = 0 ; j < listObjectTranslated.size() ; j++) {
			tradFileRecord = new TradFileRecord();
			tradFileRecord.setDescriptionFile(getDescription());
			tradFileRecord.setFileName(getFileName());
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
		setListTradFileRecord(listTradFileRecord);
	}
	
	public void getNombreTotalLignes(int nb) {
		this.nombreTotalLignes = nb;
	}
	
	public int getNombreTotalLignes() {
		return nombreTotalLignes;
	}

	public void setListCoupleOfCustomizedString(
			ArrayList<CoupleOfCustomizedString> listCoupleOfCustomizedString) {
		this.listCoupleOfCustomizedString = listCoupleOfCustomizedString;
	}


	public void setNombreTotalLignes(int nombreTotalLignes) {
		this.nombreTotalLignes = nombreTotalLignes;
	}


	public void setTranslatedSubtitles(ArrayList<CustomizedString> translatedSubtitles) {
		this.translatedSubtitles = translatedSubtitles;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList<CustomizedString> getOriginalSubtitles() {
		return originalSubtitles;
	}

	public void setOriginalSubtitles(ArrayList<CustomizedString> originalSubtitles) {
		this.originalSubtitles = originalSubtitles;
	}

	public int getNbTranslatedLine() {
		return this.nbTranslatedLine;
	}
	
	public int setNbTranslatedLine(int i) {
		return this.nbTranslatedLine;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<ObjectLineSrt> getListObjectSrtTranslated() {
		return listObjectSrtTranslated;
	}

	public void setListObjectSrtTranslated(
			ArrayList<ObjectLineSrt> listObjectSrtTranslated) {
		this.listObjectSrtTranslated = listObjectSrtTranslated;
	}
	
	public ArrayList<ObjectLineSrt> getListObjectLineSrt() {
		return listObjectLineSrt;
	}

	public void setListObjectLineSrt(ArrayList<ObjectLineSrt> listObjectLineSrt) {
		this.listObjectLineSrt = listObjectLineSrt;
	}


	public ArrayList<TradFileRecord> getListTradFileRecord() {
		return listTradFileRecord;
	}


	public void setListTradFileRecord(ArrayList<TradFileRecord> listTradFileRecord) {
		this.listTradFileRecord = listTradFileRecord;
	}

	public ArrayList<ObjectLineSrt> getListObjectLineSrtToUpdate() {
		return listObjectLineSrtToUpdate;
	}

	public void setListObjectLineSrtToUpdate(
			ArrayList<ObjectLineSrt> listObjectLineSrtToUpdate) {
		this.listObjectLineSrtToUpdate = listObjectLineSrtToUpdate;
	}
	
	public String getPathFileName() {
		return pathFileName;
	}

	public void setPathFileName(String pathFileName) {
		this.pathFileName = pathFileName;
	}

	public boolean isTraductionFinished() {
		CustomizedString customizedString = null;
		boolean finished = false;
		if ((this.nbTranslatedLine == 0) && (this.listObjectLineSrt == null) && (this.listObjectSrtTranslated == null)) {
			
			if ((this.listTradFileRecord != null) && (this.listTradFileRecord.size() > 0)) {
				TradFileRecord tradFileRecord = null;
				for (int i = 0 ; i < this.listTradFileRecord.size() ; i++) {
					tradFileRecord = listTradFileRecord.get(i);
					 if (tradFileRecord .getTranslatedLine1().equals("")) {
						 finished = false;
						 break;
					 } else {
						 finished = true;
					 }
					 if (((!tradFileRecord.getOriginalLine2().equals("_")) && (!tradFileRecord.getOriginalLine2().equals(""))) && (tradFileRecord.getTranslatedLine2().equals(""))) {
						 finished = false;
						 break;
					 } else if ((!tradFileRecord.getOriginalLine2().equals("_")) && (!tradFileRecord.getTranslatedLine2().equals(""))) { 
						 finished = true;
					 }
				}
			}
		} else {
			if ((this.translatedSubtitles != null) && (this.translatedSubtitles.size() > 0)) {
				for( int i = 0 ; i < this.translatedSubtitles.size() ; i++ ) {
					customizedString = this.translatedSubtitles.get(i);
					if ((customizedString != null) && (customizedString.getMyString() != null) && (customizedString.getMyString().startsWith("lignePasRemplie"))) {
						finished = false;
						break;
					} else {
						finished = true;		
					}
				} 
			}
		}
		return finished;
	}

	public void setTraductionFinished(boolean isTraductionFinished) {
		this.isTraductionFinished = isTraductionFinished;
	}

	public ArrayList<String> getListLigneNoTranslated() {
		return listLigneNoTranslated;
	}

	public void setListLigneNoTranslated(ArrayList<String> listLigneNoTranslated) {
		this.listLigneNoTranslated = listLigneNoTranslated;
	}
	
}
