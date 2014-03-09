package com.fabbandco.android.util;

import com.fabbandco.android.model.Entity;

import android.location.Location;

public class Advisor extends Entity {
	//class about application the advisor
	private long id;
	private String nom;
	private String prenom;
	private String login;
	private double latitude;
	private double longitude;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLocation(Location location){
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
	}
}
