package com.levi.melodydirectory;

import java.util.ArrayList;

/**
 * @author Bricen Raynold
 * Album object class
 */
public class Album extends Generic<Album> {

    /*
	 * Album Class
	 * (Everything in the Generic) with;
	 * Artist
	 * Label
	 * Price
	 * ArrayList of songs
	 * toString()
     */

    private String artist;
    private String price;
    private ArrayList<String> songs = new ArrayList<String>();

    /**
     * @param name
     * @param description
     * @param genre
     * @param releaseDate
     * @param eLink
     * @param artist
     * @param songs
     * @param price
     * Album object constructor
     */
    public Album(String name, String description, String genre, String releaseDate, String eLink, String artist, ArrayList<String> songs, String price) {
        super(name, description, genre, releaseDate, eLink);
        setDataType(DataTypes.ALBUM);
        this.artist = artist;
        this.songs = songs;
        this.price = price;
    }

    /**
     * @return
     * gets the album's artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist
     * sets the album's artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return
     * gets the album's price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price
     * sets the album's price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return
     * gets the album's songs
     */
    public ArrayList<String> getSongs() {
        return songs;
    }

    /**
     * @param songs
     * sets the album's songs
     */
    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }

    // dataType, name, description, genre, releaseDate, eLink, artist, songs ArrayList, label, price
    /**
     * Returns the album's information as a string
     */
    @Override
    public String toString() {
        return super.toString() + "," + artist + "," + songs + "," + price;
    }
}
