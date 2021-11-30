package com.levi.melodydirectory;

import java.util.ArrayList;

/**
 * @author Bricen Raynold
 * Artist object class
 */
public class Artist extends Generic {
	/*
	 * Artist Class
	 * (Everything in the Generic) with;
	 * ArrayList of albums
	 * toString()
	 */
	
	private ArrayList<String> albums = new ArrayList<String>();
	
	/**
	 * @param name
	 * @param description
	 * @param genre
	 * @param eLink
	 * @param albums
	 * Artist object constructor
	 */
	public Artist(String name, String description, String genre, String eLink, ArrayList<String> albums) {
		super(name, description, genre, "N/A", eLink);
		setDataType(DataTypes.ARTIST);
		this.albums = albums;
	}
	
	// dataType, name, description, genre, releaseDate, eLink, albums ArrayList
	/**
	 * Returns the artist object's information as a string
	 */
	public String toString() {
		return super.toString() + "," + albums;
	}

	/**
	 * @return
	 * gets the artist object's albums
	 */
	public ArrayList<String> getAlbums() {
		return albums;
	}

	/**
	 * @param albums
	 * sets the artist object's albums
	 */
	public void setAlbums(ArrayList<String> albums) {
		this.albums = albums;
	}
	
}
