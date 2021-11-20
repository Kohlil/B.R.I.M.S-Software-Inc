package com.levi.melodydirectory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.levi.melodydirectory.Generic.DataTypes;

/**
 * @author Bricen Raynold
 * Tester class for Album
 */
class AlbumTest {

	/**
	 * Tests the album constructor
	 */
	@Test
	void testAlbum() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("unimportant");
		Album a = new Album("Redemption Arc", "Omar's Debut Album", "Rap", "12/15/20", "eLink", "OmarCameUp", s, "$3.00");
		assertEquals(a.getName(), "Redemption Arc");
		assertEquals(a.getDescription(), "Omar's Debut Album");
		assertEquals(a.getGenre(), "Rap");
		assertEquals(a.getReleaseDate(), "12/15/20");
		assertEquals(a.geteLink(), "eLink");
		assertEquals(a.getArtist(), "OmarCameUp");
		assertEquals(a.getSongs(), s);
		assertEquals(a.getPrice(), "$3.00");
		assertEquals(a.getDataType(), DataTypes.ALBUM);
	}
	
	/**
	 * Tests getArtist()
	 */
	@Test
	void testGetArtist() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("unimportant");
		Album a = new Album("Redemption Arc", "Omar's Debut Album", "Rap", "12/15/20", "eLink", "OmarCameUp", s, "$3.00");
		assertEquals(a.getArtist(), "OmarCameUp");
		assertFalse(a.getArtist().equals(""));
		a.setArtist("Kanye West");
		assertEquals(a.getArtist(), "Kanye West");
	}
	
	/**
	 * Tests setArtist()
	 */
	@Test
	void testSetArtist() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("unimportant");
		Album a = new Album("Redemption Arc", "Omar's Debut Album", "Rap", "12/15/20", "eLink", "OmarCameUp", s, "$3.00");
		assertEquals(a.getArtist(), "OmarCameUp");
		assertFalse(a.getArtist().equals(""));
		a.setArtist("Kanye West");
		assertEquals(a.getArtist(), "Kanye West");
	}
	
	/**
	 * Tests getPrice()
	 */
	@Test
	void testGetPrice() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("unimportant");
		Album a = new Album("Redemption Arc", "Omar's Debut Album", "Rap", "12/15/20", "eLink", "OmarCameUp", s, "$3.00");
		assertEquals(a.getPrice(), "$3.00");
		assertFalse(a.getPrice().equals(""));
		a.setPrice("$2.00");
		assertEquals(a.getPrice(), "$2.00");
	}
	
	/**
	 * Tests setPrice()
	 */
	@Test
	void testSetPrice() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("unimportant");
		Album a = new Album("Redemption Arc", "Omar's Debut Album", "Rap", "12/15/20", "eLink", "OmarCameUp", s, "$3.00");
		assertEquals(a.getPrice(), "$3.00");
		assertFalse(a.getPrice().equals(""));
		a.setPrice("$2.00");
		assertEquals(a.getPrice(), "$2.00");
	}
	
	/**
	 * Tests getSongs()
	 */
	@Test
	void testGetSongs() {
		ArrayList<String> s = new ArrayList<String>();
		ArrayList<String> s2 = new ArrayList<String>();
		s.add("unimportant");
		s2.add("important");
		Album a = new Album("Redemption Arc", "Omar's Debut Album", "Rap", "12/15/20", "eLink", "OmarCameUp", s, "$3.00");
		assertEquals(a.getSongs(),s);
		assertFalse(a.getSongs().equals(s2));
		a.setSongs(s2);
		assertEquals(a.getSongs(),s2);
	}
	
	/**
	 * Tests setSongs()
	 */
	@Test
	void testSetSongs() {
		ArrayList<String> s = new ArrayList<String>();
		ArrayList<String> s2 = new ArrayList<String>();
		s.add("unimportant");
		s2.add("important");
		Album a = new Album("Redemption Arc", "Omar's Debut Album", "Rap", "12/15/20", "eLink", "OmarCameUp", s, "$3.00");
		assertEquals(a.getSongs(),s);
		assertFalse(a.getSongs().equals(s2));
		a.setSongs(s2);
		assertEquals(a.getSongs(),s2);
	}
	
	/**
	 * Tests toString();
	 */
	@Test
	void testToString() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("unimportant");
		Album a = new Album("Redemption Arc", "Omar's Debut Album", "Rap", "12/15/20", "eLink", "OmarCameUp", s, "$3.00");
		assertEquals(a.toString(), "ALBUM,Redemption Arc,Omar's Debut Album,Rap,12/15/20,eLink,OmarCameUp," + s + ",$3.00");
	}
	
}
