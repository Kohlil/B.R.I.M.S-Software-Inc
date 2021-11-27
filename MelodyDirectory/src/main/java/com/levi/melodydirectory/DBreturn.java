package com.levi.melodydirectory;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DBreturn {

	public static void main(String[] args) throws IOException, SQLException {
	}
	
	public static ArrayList<Generic> DB (String dbsearcher) throws SQLException {
		ArrayList<Generic> list = new ArrayList<Generic>();
		final String DB_URL = "jdbc:sqlserver://DESKTOP-B8GV62L\\PROJECTSV13;databaseName=MelodyDirectory;user=sa;password=brimsSoftware";
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		conn = DriverManager.getConnection(DB_URL);
		String query = "SELECT * FROM dbo.search('" + dbsearcher + "')";
		p = conn.prepareStatement(query);
		rs = p.executeQuery();
		
		while (rs.next()) {
				// Make the songs
				String sName = rs.getString("songName");
				String sGenre = rs.getString("songGenre");
				String sDesc = rs.getString("songDesc");
				String sELink = rs.getString("songLink");
				String sReleaseDate = rs.getString("songReleaseDate");
				String sPrice = String.valueOf(rs.getFloat("songPrice"));
				String sLength = rs.getString("songLength");
				String sAlbum = rs.getString("albumName");
				String sAName = rs.getString("artistName");
				list.add(new Song(sName, sDesc, sGenre, sReleaseDate, sELink, sAlbum, sPrice, sLength, sAName));
				
				// Make the albums
				// name, description, genre, releaseDate, eLink, artist, ArrayList<String> songs, label, price
				String aName = rs.getString("albumName");
				String aGenre = rs.getString("albumGenre");
				String aDesc = rs.getString("albumDesc");
				String aLink = rs.getString("albumLink");
				String aReleaseDate = rs.getString("albumReleaseDate");
				String aPrice = String.valueOf(rs.getString("albumPrice"));
				
				// Connect to get songs list
				Connection conn2 = null;
				PreparedStatement p2 = null;
				ResultSet rs2 = null;
				conn2 = DriverManager.getConnection(DB_URL);
				String query2 = "SELECT * FROM dbo.search('" + aName + "')";
				p2 = conn2.prepareStatement(query2);
				rs2 = p2.executeQuery();
				ArrayList<String> aSongs = new ArrayList<String>();
				while (rs2.next()) {
					aSongs.add(rs2.getString("songName"));
				}
				list.add(new Album(aName, aDesc, aGenre, aReleaseDate, aLink, sAName, aSongs, aPrice));
				
				// Make the artists
				// name, desc, genre, releaseDate, eLink, albums arraylist
				String artDesc = rs.getString("artistDesc");
				String artGenre = rs.getString("artistGenre");
				String artLink = rs.getString("artistLink");
				ArrayList<String> artAlbums = new ArrayList<>();
				
				// Build the string list of  albums
				Connection conn3 = null;
				PreparedStatement p3 = null;
				ResultSet rs3 = null;
				conn3 = DriverManager.getConnection(DB_URL);
				String query3 = "SELECT * FROM dbo.search('" + sAName + "')";
				p3 = conn3.prepareStatement(query3);
				rs3 = p3.executeQuery();
				
				while (rs3.next()) {
					artAlbums.add(rs3.getString("albumName"));
				}
				list.add(new Artist(sAName, artDesc, artGenre, artLink, artAlbums));
		}
		Set<Generic> s = new LinkedHashSet<Generic>(list);
		list.clear();
		list.addAll(s);
		return list;
	}
}
