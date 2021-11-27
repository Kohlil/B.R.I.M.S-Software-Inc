package com.levi.melodydirectory;
import java.sql.*;

/**
 * @author Bricen Raynold
 * class for DB interaction methods
 */
public class DBInteractions {
	
	/**
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 * Creates a user if their userID isn't already taken, and their password is a valid entry
	 */
	public static boolean createUser (String username, String password) throws SQLException {
		if (password.equals("")) {
			return false;
		}
		final String DB_URL = "jdbc:sqlserver://DESKTOP-B8GV62L\\PROJECTSV13;databaseName=MelodyDirectory;user=sa;password=brimsSoftware";
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		boolean valid = true;
		conn = DriverManager.getConnection(DB_URL);
		String query = "SELECT * FROM Profiles('" + username + "')";
		p = conn.prepareStatement(query);
		rs = p.executeQuery();
		
		// If the username already exists, return false
		while (rs.next()) {
			if (rs.getString("userID").equals(username)) {
				valid = false;
			}
		}
		// Otherwise, its valid, adding the username password combination to the table
		if (valid = true) {
			String query2 = "INSERT INTO Profiles VALUES (" + username + ", " + password + ", 0";
			PreparedStatement p2 = conn.prepareStatement(query2);
			p2.executeQuery();
		}	
		
		// Returns whichever case
		return valid;
	}
	
	/**
	 * @param username
	 * @return
	 * @throws SQLException
	 * checks if the username already exists 
	 */
	public static boolean searchUser (String username) throws SQLException {
		final String DB_URL = "jdbc:sqlserver://DESKTOP-B8GV62L\\PROJECTSV13;databaseName=MelodyDirectory;user=sa;password=brimsSoftware";
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		boolean exists = false;
		conn = DriverManager.getConnection(DB_URL);
		String query = "SELECT * FROM Profiles('" + username + "')";
		p = conn.prepareStatement(query);
		rs = p.executeQuery();
		// Checks all lines of the returned dataset, seeing if the username exists already
		while (rs.next()) {
			if (rs.getString("userID").equals(username)) {
				exists = true;
			}
		}
		// True if it already exists, False otherwise
		return exists;
	}
	
	/**
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 * checks if the user/pass combination exists prior to a login attempt
	 */
	public static boolean retrieveUser (String username, String password) throws SQLException {
		// If no password input, fail immediately
		if (password.equals("")) {
			return false;
		}
		final String DB_URL = "jdbc:sqlserver://DESKTOP-B8GV62L\\PROJECTSV13;databaseName=MelodyDirectory;user=sa;password=brimsSoftware";
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		boolean exists = false;
		conn = DriverManager.getConnection(DB_URL);
		String query = "SELECT * FROM Profiles('" + username + "')";
		p = conn.prepareStatement(query);
		rs = p.executeQuery();
		while (rs.next()) {
			// If the input username has a matching password field, return true
			if (rs.getString("userID").equals(username) && rs.getString("pass").equals(password)) {
				exists = true;
			}
		}
		// The user/pass combination exists in this case, thus returning true
		return exists;
	}
	
	/**
	 * @param albumName
	 * @param artistName
	 * @param songName
	 * @param songGenre
	 * @param songDesc
	 * @param songLink
	 * @param songReleaseDate
	 * @param songPrice
	 * @param songLength
	 * @param albumGenre
	 * @param albumDesc
	 * @param albumLink
	 * @param albumReleaseDate
	 * @param albumPrice
	 * @param artistGenre
	 * @param artistDesc
	 * @param artistLink
	 * @throws SQLException
	 * adds a song w/ the given parameters
	 */
	public static void addSong (String albumName, String artistName, String songName, String songGenre, String songDesc, String songLink, String songReleaseDate, String songPrice, String songLength,
			String albumGenre, String albumDesc, String albumLink, String albumReleaseDate, String albumPrice, String artistGenre, String artistDesc, String artistLink) throws SQLException {
		float songPriceF = Float.parseFloat(songPrice);
		float albumPriceF = Float.parseFloat(albumPrice);
		final String DB_URL = "jdbc:sqlserver://DESKTOP-B8GV62L\\PROJECTSV13;databaseName=MelodyDirectory;user=sa;password=brimsSoftware";
		Connection conn = null;
		PreparedStatement p = null;
		conn = DriverManager.getConnection(DB_URL);
		
		// The parameters would need to be added through the GUI
		String query = "spAddSong('" + albumName + "', '" + artistName + "', '" + songName + "', '" + songGenre 
				+ "', '" + songDesc + "', '" + songLink + "', '" + songReleaseDate + "', " + songPriceF + ", '" + songLength + "', '" 
				+ albumGenre + "', '" + albumDesc + "', '" + albumLink + "', '" + albumReleaseDate + "', " + albumPriceF + ", '"+ artistGenre + "', '" + artistDesc + "', '" + artistLink + "')";
		p = conn.prepareStatement(query);
		p.executeQuery();
	}
}























