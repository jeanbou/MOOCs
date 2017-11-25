package com.subtitlor.bean;

public class TradFileRecord {
	
	private String idLigne;
	private String timeValues;	
	private String originalLine1;
	private String originalLine2;
	private String TranslatedLine1;
	private String TranslatedLine2;
	private String fileName;
	private String descriptionFile;
	
	public  TradFileRecord () {
		super();
	}

	public String getIdLigne() {
		return idLigne;
	}

	public void setIdLigne(String idLigne) {
		this.idLigne = idLigne;
	}

	public String getTimeValues() {
		return timeValues;
	}

	public void setTimeValues(String timeValues) {
		this.timeValues = timeValues;
	}

	public String getOriginalLine1() {
		return originalLine1;
	}

	public void setOriginalLine1(String originalLine1) {
		this.originalLine1 = originalLine1;
	}

	public String getOriginalLine2() {
		return originalLine2;
	}

	public void setOriginalLine2(String originalLine2) {
		this.originalLine2 = originalLine2;
	}

	public String getTranslatedLine1() {
		return TranslatedLine1;
	}

	public void setTranslatedLine1(String translatedLine1) {
		TranslatedLine1 = translatedLine1;
	}

	public String getTranslatedLine2() {
		return TranslatedLine2;
	}

	public void setTranslatedLine2(String translatedLine2) {
		TranslatedLine2 = translatedLine2;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescriptionFile() {
		return descriptionFile;
	}

	public void setDescriptionFile(String descriptionFile) {
		this.descriptionFile = descriptionFile;
	}	
}
