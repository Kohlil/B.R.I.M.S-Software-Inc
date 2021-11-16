import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ArtistTest {

	@Test
	void testToString() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		ArrayList<Song> songs = new ArrayList<Song>();
		songs.add(SF);
		Album spiderMan = new Album("SpiderMan Into the SpiderVerse", "Movie Soundtrack", "Pop", "10/18/18", "N/A", "Various Artists", songs, "Marvel Studios", "$2.00");
		ArrayList<Album> albums = new ArrayList<Album>();
		albums.add(spiderMan);
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "10/18/18", "Marvel.com", albums);
		assertEquals("3,Marvel,Soundtrack for SpiderMan ITSV,Pop,10/18/18,Marvel.com,[2,SpiderMan Into the SpiderVerse,Movie Soundtrack,10/18/18,N/A,Pop,Various Artists,[1,Sunflower,From SpiderMan, Into The Spiderverse,Pop,10/18/18,N/A,SpiderMan Soundtrack,$2.00,2:00],Marvel Studios,$2.00]" , marvel.toString());
		assertEquals("Wrong".equals(marvel.toString()), false);
	}

	@Test
	void testTags() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		ArrayList<Song> songs = new ArrayList<Song>();
		songs.add(SF);
		Album spiderMan = new Album("SpiderMan Into the SpiderVerse", "Movie Soundtrack", "Pop", "10/18/18", "N/A", "Various Artists", songs, "Marvel Studios", "$2.00");
		ArrayList<Album> albums = new ArrayList<Album>();
		albums.add(spiderMan);
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "10/18/18", "Marvel.com", albums);
		assertEquals("Pop", marvel.tags());
		assertFalse("Pop2".equals(marvel.tags()));
		assertFalse("".equals(marvel.tags()));
	}

	@Test
	void testArtist() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		ArrayList<Song> songs = new ArrayList<Song>();
		songs.add(SF);
		Album spiderMan = new Album("SpiderMan Into the SpiderVerse", "Movie Soundtrack", "Pop", "10/18/18", "N/A", "Various Artists", songs, "Marvel Studios", "$2.00");
		ArrayList<Album> albums = new ArrayList<Album>();
		albums.add(spiderMan);
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "10/18/18", "Marvel.com", albums);
		assertEquals("Marvel", marvel.getName());
		assertEquals("Soundtrack for SpiderMan ITSV", marvel.getDescription());
		assertEquals("Pop", marvel.getGenre());
		assertEquals("10/18/18", marvel.getReleaseDate());
		assertEquals("Marvel.com", marvel.geteLink());
		assertEquals(albums, marvel.getAlbums());
	}

	@Test
	void testGetAlbums() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		Song SF2 = new Song("Sunflower", "From SpiderMan", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		ArrayList<Song> songs = new ArrayList<Song>();
		ArrayList<Song> songs2 = new ArrayList<Song>();
		songs.add(SF);
		songs2.add(SF2);
		Album spiderMan = new Album("SpiderMan ITSV", "Movie Soundtrack", "Pop", "10/18/18", "N/A", "Various Artists", songs, "Marvel Studios", "$2.00");
		ArrayList<Album> albums = new ArrayList<Album>();
		ArrayList<Album> albums2 = new ArrayList<Album>();
		albums.add(spiderMan);
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "10/18/18", "Marvel.com", albums);
		assertEquals(albums, marvel.getAlbums());
		marvel.setAlbums(albums2);
		assertEquals(albums2, marvel.getAlbums());
	}

	@Test
	void testSetAlbums() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		Song SF2 = new Song("Sunflower", "From SpiderMan", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		ArrayList<Song> songs = new ArrayList<Song>();
		ArrayList<Song> songs2 = new ArrayList<Song>();
		songs.add(SF);
		songs2.add(SF2);
		Album spiderMan = new Album("SpiderMan ITSV", "Movie Soundtrack", "Pop", "10/18/18", "N/A", "Various Artists", songs, "Marvel Studios", "$2.00");
		ArrayList<Album> albums = new ArrayList<Album>();
		ArrayList<Album> albums2 = new ArrayList<Album>();
		albums.add(spiderMan);
		Artist marvel = new Artist("Marvel", "Soundtrack for SpiderMan ITSV", "Pop", "10/18/18", "Marvel.com", albums);
		assertEquals(albums, marvel.getAlbums());
		marvel.setAlbums(albums2);
		assertEquals(albums2, marvel.getAlbums());
	}

}
