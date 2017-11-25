package com.subtitlor.bean;

public class ObjectLineSrt {

	private String idLigne;
	private String timeValues;	
	private String text1;
	private String text2;
	private String TranslatedText1;
	private String TranslatedText2;

	public  ObjectLineSrt () {
		super();
	}
	
	public String getTranslatedText1() {
		return TranslatedText1;
	}

	public void setTranslatedText1(String translatedText1) {
		TranslatedText1 = translatedText1;
	}

	public String getTranslatedText2() {
		return TranslatedText2;
	}

	public void setTranslatedText2(String translatedText2) {
		TranslatedText2 = translatedText2;
	}

	public void setIdLigne(String idLigne) {
		this.idLigne = idLigne;
	}

	public void setTimeValues(String timeValues) {
		this.timeValues = timeValues;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getIdLigne() {
		return this.idLigne;
	}
	
	public String getTimeValues() {
		return this.timeValues;
	}
	
	public String getText1() {
		return this.text1;
	}
	
	public String getText2() {
		return this.text2;
	}
	
}
