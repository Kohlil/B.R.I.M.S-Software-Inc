
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
	
	private String album;
	private String price;
	private String songLength;
	private String artistName;
	
	public Song(String name, String description, String genre, String releaseDate, String eLink, String artistName, String album, String price, String songLength) {
		super(name, description, genre, releaseDate, eLink);
		setDataType(1);  
		this.artistName = artistName;
		this.price = price;
		this.songLength = songLength;
		this.album = album;
	}
	
	// dataType, name, description, genre, releaseDate, eLink, album, price, songLength
	public String toString() {
		return super.toString() + "," + artistName + "," + album + "," + price + "," + songLength;
	}
	
	public String tags() {
		return super.tags();
	}

	public String getAlbum() {
		return album;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
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
	
}
