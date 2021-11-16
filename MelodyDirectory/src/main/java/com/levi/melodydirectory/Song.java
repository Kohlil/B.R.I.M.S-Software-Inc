package com.levi.melodydirectory;

public class Song extends Generic<Song> {

    /*
	 * Song Class
	 * (Everything in the Generic) with;
	 * Name of the album it belongs to
	 * Price
	 * Song Length
	 * toString()
	 * tags()
     */

    private String artist;
    private String album;
    private String price;
    private String songLength;

    public Song(String name, String description, String genre, String releaseDate, String eLink, String album, String price, String songLength, String artist) {
        super(name, description, genre, releaseDate, eLink);
        setDataType(DataTypes.SONG);
        this.price = price;
        this.songLength = songLength;
        this.album = album;
        this.artist = artist;
    }

    // dataType, name, description, genre, releaseDate, eLink, album, price, songLength
    @Override
    public String toString() {
        return super.toString() + "," + album + "," + price + "," + songLength;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}
