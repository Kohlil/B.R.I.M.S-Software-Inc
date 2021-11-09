package com.levi.melodydirectory;

import java.util.ArrayList;

public class Artist extends Generic<Artist> {
	/*
	 * Artist Class
	 * (Everything in the Generic) with;
	 * ArrayList of albums
	 * toString()
	 * tags() (Gets the genre)
	 */
	
	private ArrayList<String> albums;
	
	public Artist(String name, String description, String genre, String releaseDate, String eLink, ArrayList<String> albums) {
		super(name, description, genre, releaseDate, eLink);
		setDataType(DataTypes.ARTIST);
		this.albums = albums;
	}
	
	// dataType, name, description, genre, releaseDate, eLink, albums ArrayList
        @Override
	public String toString() {
		return super.toString() + "," + albums;
	}
	
        @Override
	public String tags() {
		return super.tags();
	}

	public ArrayList<String> getAlbums() {
		return albums;
	}

	public void setAlbums(ArrayList<String> albums) {
		this.albums = albums;
	}
	
}
