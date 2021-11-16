import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class SongTest {

	@Test
	void testToString() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		String wrong = "2,Sunflower,From SpiderMan, Into The Spiderverse,Pop,10/18/18,N/A,SpiderMan Soundtrack,$2.00,2:00";
		String wrong2 = "";
		String correct = "1,Sunflower,From SpiderMan, Into The Spiderverse,Pop,10/18/18,N/A,SpiderMan Soundtrack,$2.00,2:00";
		assertEquals("1,Sunflower,From SpiderMan, Into The Spiderverse,Pop,10/18/18,N/A,SpiderMan Soundtrack,$2.00,2:00",SF.toString());
		assertEquals(wrong.equals(SF.toString()), false);
		assertEquals(wrong2.equals(SF.toString()), false);
		assertEquals(correct.equals(SF.toString()), true);
	}

	@Test
	void testSong() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		assertEquals(SF.getName(), "Sunflower");
		assertEquals(SF.getDescription(), "From SpiderMan, Into The Spiderverse");
		assertEquals(SF.getGenre(), "Pop");
		assertEquals(SF.getReleaseDate(), "10/18/18");
		assertEquals(SF.geteLink(), "N/A");
		assertEquals(SF.getAlbum(), "SpiderMan Soundtrack");
		assertEquals(SF.getPrice(), "$2.00");
		assertEquals(SF.getSongLength(), "2:00");
	}

	@Test
	void testGetAlbum() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		assertEquals("SpiderMan Soundtrack", SF.getAlbum());
		SF.setAlbum("SpiderMan ITSV");
		assertEquals("SpiderMan ITSV", SF.getAlbum());
		assertEquals("SpiderMan".equals(SF.getAlbum()), false);
	}

	@Test
	void testSetAlbum() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		assertEquals("SpiderMan Soundtrack", SF.getAlbum());
		SF.setAlbum("SpiderMan ITSV");
		assertEquals("SpiderMan ITSV", SF.getAlbum());
		assertEquals("SpiderMan".equals(SF.getAlbum()), false);
	}

	@Test
	void testGetPrice() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		assertEquals(SF.getPrice(), "$2.00");
		assertEquals(SF.getPrice().equals("$2.00"), true);
		assertEquals(SF.getPrice().equals("$3.00"), false);
		SF.setPrice("$3.00");
		assertEquals(SF.getPrice(), "$3.00");
	}

	@Test
	void testSetPrice() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		assertEquals(SF.getPrice(), "$2.00");
		assertEquals(SF.getPrice().equals("$2.00"), true);
		assertEquals(SF.getPrice().equals("$3.00"), false);
		SF.setPrice("$3.00");
		assertEquals(SF.getPrice(), "$3.00");
	}

	@Test
	void testGetSongLength() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		assertEquals(SF.getSongLength(), "2:00");
		assertEquals(SF.getSongLength().equals("2:00"), true);
		assertEquals(SF.getSongLength().equals("3:00"), false);
		SF.setSongLength("3:00");
		assertEquals(SF.getSongLength(), "3:00");
	}

	@Test
	void testSetSongLength() {
		Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", "Marvel Studios", "SpiderMan Soundtrack", "$2.00", "2:00");
		assertEquals(SF.getSongLength(), "2:00");
		assertEquals(SF.getSongLength().equals("2:00"), true);
		assertEquals(SF.getSongLength().equals("3:00"), false);
		SF.setSongLength("3:00");
		assertEquals(SF.getSongLength(), "3:00");
	}

}
