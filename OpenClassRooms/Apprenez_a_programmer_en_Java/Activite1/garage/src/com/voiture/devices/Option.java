package com.voiture.devices;

public interface Option {
	public Double getPrix();
	// IB I have created extra method in order to be more flexible. For example someone will want gps, another GPS, and the rest GPS basic or look how they write BarDeToit
	public String getOptionName();
}
