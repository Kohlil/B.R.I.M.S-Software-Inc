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
	
	public Album(String name, String description, String genre, String releaseDate, String eLink, String artist, ArrayList<Song> songs, String label, String price) {
		super(name, description, releaseDate, eLink, genre);
		setDataType(2);
		this.artist = artist;
		this.label = label;
		this.songs = songs;
		this.price = price;
	}
	
	// dataType, name, description, genre, releaseDate, eLink, artist, songs ArrayList, label, price
	public String toString() {
		return super.toString() + "," + artist + "," + songs + "," + label + "," + price;
	}
	
	public String tags() {
		return super.tags();
	}

}
