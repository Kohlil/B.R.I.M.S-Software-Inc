/* 
 * Author: Rylee Domonkos
 * Date: 11/11/21
 * Purpose: Fills testing requirements
 */
package com.levi.melodydirectory;

import com.levi.melodydirectory.Generic.DataTypes;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class MelodyDirectoryTest {

	// white
	@Test
	void test_toArray() {

	}
	
	// white
	@Test
	void test_toString() {

	}
	
	// white
	//@Test
	//test_toString() {

	//}
	
	// black
	@Test
	void test_isUserCommentMod() {
		user comMod = new user("DanTDM", "robloxsux");
		assertFalse(comMod.isCommentMod());
	}
	
	// black
	@Test
	void test_isUserAdministrator() {
		user comMod = new user("DanTDM", "robloxsux");
		assertFalse(comMod.isSysAdmin());
		}
	
	// black
	@Test
	void test_songToString() {
		Song song = new Song("Teenage Dream", "This song reminds me of one of my relationships", 
				"Pop", "10/24/2009", "https://music.youtube.com/watch?v=fQoRohKYvy4&feature=share",
				"Teenage Dream", "1.29",  "3:48",  "Katy Perry");
		assertTrue(song.toString().equals(DataTypes.SONG+",Teenage Dream,This song reminds me of one of my relationships,Pop,10/24/2009,https://music.youtube.com/watch?v=fQoRohKYvy4&feature=share,Teenage Dream,1.29,3:48,Katy Perry"));
		}
	
	// grey
	@Test
	void test_Song() {
		Song song = new Song("Teenage Dream", "This song reminds me of one of my relationships", 
				"Pop", "10/24/2009", "https://music.youtube.com/watch?v=fQoRohKYvy4&feature=share",
				"Teenage Dream", "1.29",  "3:48",  "Katy Perry");
		assertTrue(song.getAlbum().equals("Teenage Dream"));
		assertFalse(song.getPrice().equals("10/24/2009"));
		assertFalse(song.getPrice().equals("Teenage Dream"));
	}
	
	
	// grey
	@Test
	void test_Album() {
		ArrayList<String> songs = new ArrayList<String>();
		songs.add("Teenage Dream");
		songs.add("The one that got Away");
		Album album = new Album("Teenage Dream", "BOPPPS", "Pop", "10/24/2009", 
				"https://music.youtube.com/watch?v=fQoRohKYvy4&feature=share",
				"Katy Perry", songs, "Blah blah", "7.99");
		assertTrue(album.getSongs().get(0).equals("Teenage Dream"));
		assertFalse(album.getPrice().equals("10/24/2009"));
		assertFalse(album.getArtist().equals("Kady Perry"));
	}
	
	// grey
	void test_Artist() {
		ArrayList<String> albums = new ArrayList<String>();
		albums.add("Teenage Dream");
		albums.add("Prism");
		Artist artist = new Artist("Katy Perry",  "Living my dream",  "Pop",  "https://music"
                        + ".youtube.com/channel/UC_7s69e1mDS3lgcTMJEPjCg", albums);
		assertTrue(artist.getAlbums().get(0).equals("Teenage Dream"));
		assertFalse(artist.getGenre().equals("Rock"));
		assertFalse(artist.getName().equals("Teenage Dream"));
	}
	
	
	@Test
	void test_ArtistToString() {
		ArrayList<String> albums = new ArrayList<String>();
		albums.add("Teenage Dream");
		albums.add("Prism");
		Artist artist = new Artist("Katy Perry",  "Living my dream",  "Pop",
				"https://music.youtube.com/channel/UC_7s69e1mDS3lgcTMJEPjCg", albums);
		assertTrue(artist.toString().equals(DataTypes.ARTIST+",Katy Perry,Living my dream,Pop,N/A,https://music.youtube.com/channel/UC_7s69e1mDS3lgcTMJEPjCg,"+ albums.toString()));
		}

}
