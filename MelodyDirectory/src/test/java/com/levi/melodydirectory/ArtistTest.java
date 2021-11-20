package com.levi.melodydirectory;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.levi.melodydirectory.Generic.DataTypes;

/**
 * @author Bricen Raynold
 * Tester class for Artist
 */
class ArtistTest {
	
	/**
	 * Tests toString()
	 */
	@Test
	void testToString() {
		// Artist: name, desc, genre, releaseDate, eLink, albums
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A","SpiderMan Soundtrack", "$2.00", "2:00", "Post Malone");
		ArrayList<String> songs = new ArrayList<>();
		songs.add("Sunflower");
		ArrayList<String> albums = new ArrayList<>();
		albums.add("SpiderMan Into the SpiderVerse");
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "Marvel.com", albums);
		assertEquals("ARTIST,Marvel,Soundtrack for SpiderMan ITSV,Pop,N/A,Marvel.com,[SpiderMan Into the SpiderVerse]", marvel.toString());
		assertEquals("Wrong".equals(marvel.toString()), false);
	}

	/**
	 * Tests artist constructor
	 */
	@Test
	void testArtist() {
		ArrayList<String> albums = new ArrayList<>();
		albums.add("SpiderMan Into the SpiderVerse");
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "Marvel.com", albums);
		assertEquals("Marvel", marvel.getName());
		assertEquals("Soundtrack for SpiderMan ITSV", marvel.getDescription());
		assertEquals("Pop", marvel.getGenre());
		assertEquals("N/A", marvel.getReleaseDate());
		assertEquals("Marvel.com", marvel.geteLink());
		assertEquals(albums, marvel.getAlbums());
		assertEquals(marvel.getDataType(), DataTypes.ARTIST);
	}

	/**
	 * Tests getAlbums()
	 */
	@Test
	void testGetAlbums() {
		ArrayList<String> albums = new ArrayList<>();
		ArrayList<String> albums2 = new ArrayList<>();
		albums.add("SpiderMan Into the SpiderVerse");
		albums2.add("Redemption Arc");
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "Marvel.com", albums);
		assertEquals(albums, marvel.getAlbums());
		marvel.setAlbums(albums2);
		assertEquals(albums2, marvel.getAlbums());
	}

	/**
	 * Tests setAlbums()
	 */
	@Test
	void testSetAlbums() {
		ArrayList<String> albums = new ArrayList<>();
		ArrayList<String> albums2 = new ArrayList<>();
		albums.add("SpiderMan Into the SpiderVerse");
		albums2.add("Man on the Moon");
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "Marvel.com", albums);
		assertEquals(albums, marvel.getAlbums());
		marvel.setAlbums(albums2);
		assertEquals(albums2, marvel.getAlbums());
	}

}
