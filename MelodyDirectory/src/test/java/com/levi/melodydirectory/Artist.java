import java.util.ArrayList;

public class Artist extends Generic<Artist> {
	/*
	 * Artist Class
	 * (Everything in the Generic) with;
	 * ArrayList of albums
	 * toString()
	 * tags() (Gets the genre)
	 */
	
	private ArrayList<Album> albums = new ArrayList<Album>();
	
	public Artist(String name, String description, String genre, String releaseDate, String eLink, ArrayList<Album> albums) {
		super(name, description, genre, releaseDate, eLink);
		setDataType(DataTypes.ARTIST);
		this.albums = albums;
	}
	
	// dataType, name, description, genre, releaseDate, eLink, albums ArrayList
	public String toString() {
		return super.toString() + "," + albums;
	}

	public ArrayList<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}
	
}
