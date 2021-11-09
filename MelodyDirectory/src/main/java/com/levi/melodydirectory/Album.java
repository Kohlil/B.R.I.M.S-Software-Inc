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
    private ArrayList<String> songs = new ArrayList<String>();

    public Album(String name, String description, String genre, String releaseDate, String eLink, String artist, ArrayList<String> songs, String label, String price) {
        super(name, description, genre, releaseDate, eLink);
        setDataType(DataTypes.ALBUM);
        this.artist = artist;
        this.label = label;
        this.songs = songs;
        this.price = price;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
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
