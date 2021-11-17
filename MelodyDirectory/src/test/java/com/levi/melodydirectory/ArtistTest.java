package com.levi.melodydirectory;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ArtistTest {

	@Test
	void testToString() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A","SpiderMan Soundtrack", "$2.00", "2:00", "Post Malone");
		ArrayList<String> songs = new ArrayList<>();
		songs.add("Sunflower");
		Album spiderMan = new Album("SpiderMan Into the SpiderVerse", "Movie Soundtrack", "Pop", "10/18/18", "N/A", "Various Artists", songs, "Marvel Studios", "$2.00");
		ArrayList<String> albums = new ArrayList<>();
		albums.add("SpiderMan Into the SpiderVerse");
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "Marvel.com", albums);
		assertEquals("3,Marvel,Soundtrack for SpiderMan ITSV,Pop,10/18/18,Marvel.com,[2,SpiderMan Into the SpiderVerse,Movie Soundtrack,10/18/18,N/A,Pop,Various Artists,[1,Sunflower,From SpiderMan, Into The Spiderverse,Pop,10/18/18,N/A,SpiderMan Soundtrack,$2.00,2:00],Marvel Studios,$2.00]" , marvel.toString());
		assertEquals("Wrong".equals(marvel.toString()), false);
	}

	@Test
	void testArtist() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "SpiderMan Soundtrack", "$2.00", "2:00", "Post Malone");
		ArrayList<String> songs = new ArrayList<>();
		songs.add("Sunflower");
		Album spiderMan = new Album("SpiderMan Into the SpiderVerse", "Movie Soundtrack", "Pop", "10/18/18", "N/A", "Various Artists", songs, "Marvel Studios", "$2.00");
		ArrayList<String> albums = new ArrayList<>();
		albums.add("SpiderMan Into the SpiderVerse");
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "Marvel.com", albums);
		assertEquals("Marvel", marvel.getName());
		assertEquals("Soundtrack for SpiderMan ITSV", marvel.getDescription());
		assertEquals("Pop", marvel.getGenre());
		assertEquals("10/18/18", marvel.getReleaseDate());
		assertEquals("Marvel.com", marvel.geteLink());
		assertEquals(albums, marvel.getAlbums());
	}

	@Test
	void testGetAlbums() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "SpiderMan Soundtrack", "$2.00", "2:00", "Post Malone");
		Song SF2 = new Song("Sunflower", "From SpiderMan, ITSV", "Pop", "10/18/18", "N/A", "SpiderMan Soundtrack", "$2.00", "2:00", "Post Malone");
		ArrayList<String> songs = new ArrayList<>();
		ArrayList<String> songs2 = new ArrayList<>();
		songs.add("Sunflower");
		songs2.add("Sunflower");
		Album spiderMan = new Album("SpiderMan ITSV", "Movie Soundtrack", "Pop", "10/18/18", "N/A", "Various Artists", songs, "Marvel Studios", "$2.00");
		ArrayList<String> albums = new ArrayList<>();
		ArrayList<String> albums2 = new ArrayList<>();
		albums.add("SpiderMan Into the SpiderVerse");
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "Marvel.com", albums);
		assertEquals(albums, marvel.getAlbums());
		marvel.setAlbums(albums2);
		assertEquals(albums2, marvel.getAlbums());
	}

	@Test
	void testSetAlbums() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "SpiderMan Soundtrack", "$2.00", "2:00", "Post Malone");
		Song SF2 = new Song("Sunflower", "From SpiderMan, ITSV", "Pop", "10/18/18", "N/A", "SpiderMan Soundtrack", "$2.00", "2:00", "Post Malone");
		ArrayList<String> songs = new ArrayList<>();
		ArrayList<String> songs2 = new ArrayList<>();
		songs.add("Sunflower");
		songs2.add("Sunflower");
		Album spiderMan = new Album("SpiderMan ITSV", "Movie Soundtrack", "Pop", "10/18/18", "N/A", "Various Artists", songs, "Marvel Studios", "$2.00");
		ArrayList<String> albums = new ArrayList<>();
		ArrayList<String> albums2 = new ArrayList<>();
		albums.add("SpiderMan Into the SpiderVerse");
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "Marvel.com", albums);
		assertEquals(albums, marvel.getAlbums());
		marvel.setAlbums(albums2);
		assertEquals(albums2, marvel.getAlbums());
	}

}
