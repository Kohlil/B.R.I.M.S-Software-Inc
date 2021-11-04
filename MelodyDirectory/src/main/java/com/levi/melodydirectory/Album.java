package com.levi.melodydirectory;

import java.util.ArrayList;

public class Album extends Generic<Album> {
	/*
	 * Album Class
	 * (Everything in the Generic) with;
	 * Artist
	 * Label
	 * Price
	 * ArrayList of songs
	 * toString()
	 * tags()
	 */
	
	private String artist;
	private String label;
	private String price;
	private ArrayList<Song> songs = new ArrayList<Song>();
	
	public Album(String name, String description, String genre, String releaseDate, String eLink, int likes, int dislikes, String artist, ArrayList<Song> songs, String label, String price) {
		super(name, description, releaseDate, eLink, genre, likes, dislikes);
		setDataType(DataTypes.ALBUM);
		this.artist = artist;
		this.label = label;
		this.songs = songs;
		this.price = price;
	}
	
	// dataType, name, description, genre, releaseDate, eLink, artist, songs ArrayList, label, price
        @Override
	public String toString() {
		return super.toString() + "," + artist + "," + songs + "," + label + "," + price;
	}
	 
        @Override
	public String tags() {
		return super.tags();
	}

}
