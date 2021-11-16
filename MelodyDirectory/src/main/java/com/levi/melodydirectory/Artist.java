package com.levi.melodydirectory;

import java.util.ArrayList;

public class Artist extends Generic<Artist> {
	/*
	 * Artist Class
	 * (Everything in the Generic) with;
	 * ArrayList of albums
	 * toString()
	 */
	
	private ArrayList<String> albums = new ArrayList<String>();
	
	public Artist(String name, String description, String genre, String eLink, ArrayList<String> albums) {
		super(name, description, genre, "N/A", eLink);
		setDataType(DataTypes.ARTIST);
		this.albums = albums;
	}
	
	// dataType, name, description, genre, releaseDate, eLink, albums ArrayList
	public String toString() {
		return super.toString() + "," + albums;
	}

	public ArrayList<String> getAlbums() {
		return albums;
	}

	public void setAlbums(ArrayList<String> albums) {
		this.albums = albums;
	}
	
}
