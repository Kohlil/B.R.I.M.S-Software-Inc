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
	
	private ArrayList<Album> albums;
	
	public Artist(String name, String description, String genre, String releaseDate, String eLink, int likes, int dislikes, ArrayList<Album> albums) {
		super(name, description, genre, releaseDate, eLink, likes, dislikes);
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

	public ArrayList<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}
	
}
