package com.levi.melodydirectory;

/**
 * @author Bricen Raynold
 * Song object class
 */
public class Song extends Generic<Song> {
	/*
	 * Song Class
	 * (Everything in the Generic) with;
	 * Name of the album it belongs to
	 * Price
	 * Song Length
	 * toString()
	 */
	
	private String album;
	private String price;
	private String songLength;
	private String artist;
	
	/**
	 * @param name
	 * @param description
	 * @param genre
	 * @param releaseDate
	 * @param eLink
	 * @param album
	 * @param price
	 * @param songLength
	 * @param artist
	 * Constructor for the song object
	 */
	public Song(String name, String description, String genre, String releaseDate, String eLink, String album, String price, String songLength, String artist) {
		super(name, description, genre, releaseDate, eLink);
		setDataType(DataTypes.SONG);  
		this.artist = artist;
		this.price = price;
		this.songLength = songLength;
		this.album = album;
	}
	
	// name, description, genre, releaseDate, eLink, album, price, songLength, artistName
	/**
	 * Returns the song's information as a string
	 */
	public String toString() {
		return super.toString() + "," +  album + "," + price + "," + songLength + "," + artist;
	}

	/**
	 * @return
	 * returns the song's album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @return
	 * returns the song's artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artistName
	 * sets the song's artist
	 */
	public void setArtist(String artistName) {
		this.artist = artistName;
	}

	/**
	 * @param album
	 * sets the song's album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return
	 * gets the song's price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 * sets the song's price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return
	 * gets the song's length
	 */
	public String getSongLength() {
		return songLength;
	}

	/**
	 * @param songLength
	 * sets the song's length
	 */
	public void setSongLength(String songLength) {
		this.songLength = songLength;
	}
	
}
