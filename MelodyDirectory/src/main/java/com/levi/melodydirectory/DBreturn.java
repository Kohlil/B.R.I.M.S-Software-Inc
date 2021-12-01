/**
 * Author: Isaiah Kohl & Bricen
 * Date: 11/29/21
 * Purpose: Interaction with DB through singleton class
 */

package com.levi.melodydirectory;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Bricen Raynold & Isaiah Kohl
 * DB interactions class, containing all of our methods for calling database functions
 */
public class DBreturn {

    private static final DBreturn instance = new DBreturn();//one instantiation of class
    
    /***********************************************************************************************
     * 
     * 
     *                  EDIT THE TWO STRINGS BELOW TO ALLOW LOCAL DB ACCESS
     * 
     * 
     ***********************************************************************************************/
    private final String DB_URL = "jdbc:sqlserver://MY-TURING-POINT\\SQLEXPRESS;databaseName=MelodyDirectory;user=sa;password=test;prepareSQL=1;sendStringParametersAsUnicode=false";
    private final String DB2_URL = "jdbc:sqlserver://MY-TURING-POINT\\SQLEXPRESS;databaseName=MelodyDirectoryRequests;user=sa;password=test;prepareSQL=1;sendStringParametersAsUnicode=false";
    private Connection conn1 = null;//for songs
    private Connection conn2 = null;//for albums and artists
    private Connection conn3 = null;//for song requests
    private Connection conn4 = null;//for album and artist requests
    private boolean requests = false;//whether functions should load from normal or requests

    //private constructor to avoid client applications to use constructor
    private DBreturn() {
        try {
            //intialize connections to db
            conn1 = DriverManager.getConnection(DB_URL);
            conn2 = DriverManager.getConnection(DB_URL);
            conn3 = DriverManager.getConnection(DB2_URL);
            conn4 = DriverManager.getConnection(DB2_URL);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(68);
        }
    }

    /**
     * 
     * @return 
     * 
     * Allow public access of class
     */
    public static DBreturn getInstance() {
        return instance;
    }

    //for testing main method
    public static void main(String[] args) throws IOException, SQLException {
        DBreturn db = DBreturn.getInstance();
        System.out.println(db.Search("test"));
    }

    /**
     * 
     * @param item
     * @return
     * @throws SQLException 
     * 
     * Searches DB for a specific song
     */
    public Song getSpecificSong(String item) throws SQLException {
        PreparedStatement p = null;
        ResultSet rs = null;
        String query = "SELECT * FROM dbo.getSong('" + item + "')";//create query
        if (requests) {
            p = conn3.prepareStatement(query);
        } else {
            p = conn1.prepareStatement(query);
        }
        rs = p.executeQuery();
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
        return new Song(sName, sDesc, sGenre, sReleaseDate, sELink, sAlbum, sPrice, sLength, sAName);
    }

    /**
     * 
     * @param item
     * @return
     * @throws SQLException 
     * 
     * Search DB for specific Album, currently does not work which prevents
     * the approval of requests, not sure why as the query created here works
     * perfectly fine in actual DB, for proof this query can be found at the
     * very bottom of the DB script
     */
    public Album getSpecificAlbum(String item) throws SQLException {
        PreparedStatement p = null;
        ResultSet rs = null;
        String query = "SELECT * FROM dbo.getAlbum('" + item + "')";//create query
        if (requests) {
            p = conn3.prepareStatement(query);
        } else {
            p = conn1.prepareStatement(query);
        }
        rs = p.executeQuery();
        // Make the songs
        String aName = rs.getString("albumName");
        String aGenre = rs.getString("albumGenre");
        String aDesc = rs.getString("albumDesc");
        String aLink = rs.getString("albumLink");
        String aReleaseDate = rs.getString("albumReleaseDate");
        String aPrice = String.valueOf(rs.getString("albumPrice"));
        String sAName = "";

        // Connect to get songs list
        PreparedStatement p2 = null;
        ResultSet rs2 = null;
        String query2 = "SELECT * FROM dbo.search('" + aName + "')";
        if (requests) {
            p2 = conn4.prepareStatement(query2);
        } else {
            p2 = conn2.prepareStatement(query2);
        }
        rs2 = p2.executeQuery();
        ArrayList<String> aSongs = new ArrayList<String>();
        while (rs2.next()) {
            aSongs.add(rs2.getString("songName"));
            sAName = rs2.getString("artistName");
        }
        return new Album(aName, aDesc, aGenre, aReleaseDate, aLink, sAName, aSongs, aPrice);
    }

    /**
     * 
     * @param item
     * @return
     * @throws SQLException 
     * 
     * Not sure if this works as it does essentially the same thing as the function above
     */
    public Artist getSpecificArtist(String item) throws SQLException {
        PreparedStatement p = null;
        ResultSet rs = null;
        String query = "SELECT * FROM dbo.getArtist('" + item + "')";//create query
        if (requests) {
            p = conn3.prepareStatement(query);
        } else {
            p = conn1.prepareStatement(query);
        }
        rs = p.executeQuery();
        String artDesc = rs.getString("artistDesc");
        String artGenre = rs.getString("artistGenre");
        String artLink = rs.getString("artistLink");
        ArrayList<String> artAlbums = new ArrayList<>();
        DBreturn db = DBreturn.getInstance();
        String temp = db.getSpecificAlbum(item).getArtist();

        // Build the string list of  albums
        PreparedStatement p2 = null;
        ResultSet rs2 = null;
        String query2 = "SELECT * FROM dbo.search('" + temp + "')";
        if (requests) {
            p2 = conn4.prepareStatement(query2);
        } else {
            p2 = conn2.prepareStatement(query2);
        }
        rs2 = p2.executeQuery();

        while (rs2.next()) {
            artAlbums.add(rs2.getString("albumName"));
        }
        return new Artist(temp, artDesc, artGenre, artLink, artAlbums);
    }

    /**
     *
     * @param item
     * @throws SQLException
     *
     * attempts to delete specified item from each table
     */
    public void delete(String item) throws SQLException {
        PreparedStatement p = null;
        HashSet<Generic> list = new HashSet<>();
        String query = "DELETE FROM Songs WHERE songName='" + item + "'\n"
                + "DELETE FROM Albums WHERE albumName='" + item + "'\n"
                + "DELETE FROM Artists WHERE artistName='" + item + "'\n";
        if (requests) {
            p = conn3.prepareStatement(query);
        } else {
            p = conn1.prepareStatement(query);
        }
        p.execute();
    }

    /**
     *
     * @return @throws SQLException
     *
     * returns all elements in db
     */
    public ArrayList<Generic> getAllSongs() throws SQLException {
        PreparedStatement p = null;
        ResultSet rs = null;
        HashSet<Generic> list = new HashSet<>();
        String query = "SELECT * FROM dbo.getAllSongs()";
        if (requests) {
            p = conn3.prepareStatement(query);
        } else {
            p = conn1.prepareStatement(query);
        }
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
        }
        Set<Generic> s = new HashSet<>(list);
        list.clear();
        list.addAll(s);
        return (ArrayList<Generic>) (list.stream().collect(Collectors.toList()));
    }

    /**
     * 
     * @param dbsearcher
     * @return
     * @throws SQLException 
     * 
     * Search for a specific string and receive all results matching or containing string
     */
    public ArrayList<Generic> Search(String dbsearcher) throws SQLException {
        PreparedStatement p = null;
        ResultSet rs = null;
        HashSet<Generic> list = new HashSet<>();
        String query = "SELECT * FROM dbo.search('" + dbsearcher + "')";//create query
        if (requests) {
            p = conn3.prepareStatement(query);
        } else {
            p = conn1.prepareStatement(query);
        }
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
            PreparedStatement p2 = null;
            ResultSet rs2 = null;
            String query2 = "SELECT * FROM dbo.search('" + aName + "')";
            if (requests) {
                p2 = conn4.prepareStatement(query2);
            } else {
                p2 = conn2.prepareStatement(query2);
            }
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
            String query3 = "SELECT * FROM dbo.search('" + sAName + "')";
            if (requests) {
                p2 = conn4.prepareStatement(query3);
            } else {
                p2 = conn2.prepareStatement(query3);
            }
            rs2 = p2.executeQuery();

            while (rs2.next()) {
                artAlbums.add(rs2.getString("albumName"));
            }
            list.add(new Artist(sAName, artDesc, artGenre, artLink, artAlbums));
        }
        Set<Generic> s = new HashSet<>(list);
        list.clear();
        list.addAll(s);
        return (ArrayList<Generic>) (list.stream().collect(Collectors.toList()));
    }

    /**
     * @param username
     * @param password
     * @return
     * @throws SQLException 
     * Creates a user if their userID isn't already taken,
     * and their password is a valid entry
     */
    public boolean createUser(String username, String password) throws SQLException {
        if (password.equals("")) {
            return false;
        }
        PreparedStatement p = null;
        ResultSet rs = null;
        boolean valid = true;
        String query = "SELECT * FROM Profiles WHERE userID = '" + username + "'";
        p = conn1.prepareStatement(query);
        rs = p.executeQuery();

        // If the username already exists, return false
        while (rs.next()) {
            if (rs.getString("userID").equals(username)) {
                valid = false;
            }
        }
        // Otherwise, its valid, adding the username password combination to the table
        if (valid == true) {
            String query2 = "EXEC spAddProfile @username='" + username + "', @pass = '" + password + "', @adminAccess = 0";
            PreparedStatement p2 = conn1.prepareStatement(query2);
            p2.execute();
        }

        // Returns whichever case
        return valid;
    }

    /**
     * @param username
     * @return
     * @throws SQLException 
     * 
     * checks if the username already exists
     */
    public boolean searchUser(String username) throws SQLException {
        PreparedStatement p = null;
        ResultSet rs = null;
        boolean exists = false;
        String query = "SELECT * FROM Profiles WHERE userID = '" + username + "'";
        p = conn1.prepareStatement(query);
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
     * 
     * checks if the user/pass combination exists prior to
     * a login attempt
     */
    public boolean retrieveUser(String username, String password) throws SQLException {
        PreparedStatement p = null;
        ResultSet rs = null;
        boolean exists = false;
        String query = "SELECT * FROM Profiles WHERE userID = '" + username + "'";
        p = conn1.prepareStatement(query);
        rs = p.executeQuery();
        while (rs.next()) {
            // If the input username has a matching password field, return true
            if (rs.getString("userID").equals(username) && rs.getString("pass").equals(password)) {
                exists = true;
                if (rs.getBoolean("adminAccess")) {
                    HeaderBar.isAdmin = true;
                }
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
     * 
     * adds a song w/ the given parameters
     */
    public void addSong(String albumName, String artistName, String songName, String songGenre, String songDesc, String songLink, String songReleaseDate, String songPrice, String songLength,
            String albumGenre, String albumDesc, String albumLink, String albumReleaseDate, String albumPrice, String artistGenre, String artistDesc, String artistLink) throws SQLException {
        float songPriceF = Float.parseFloat(songPrice);
        float albumPriceF = Float.parseFloat(albumPrice);
        PreparedStatement p = null;

        // The parameters would need to be added through the GUI
        String query = "EXEC spAddSong @albumName= '" + albumName + "',@artistName= '"
                + artistName + "',@songName= '" + songName + "',@songGenre= '"
                + songGenre + "',@songDesc= '" + songDesc + "',@songLink= '"
                + songLink + "',@songReleaseDate= '" + songReleaseDate
                + "',@songPrice= " + songPrice + ",@songLength= '" + songLength
                + "',@albumGenre= '" + albumGenre + "',"
                + "@albumDesc= '" + albumDesc + "',"
                + "@albumLink= '" + albumLink + "',"
                + "@albumReleaseDate= '" + albumReleaseDate + "',"
                + "@albumPrice= " + albumPrice + ","
                + "@artistGenre= '" + artistGenre + "',"
                + "@artistDesc= '" + artistDesc + "',"
                + "@artistLink= '" + artistLink + "'";
        if (requests) {
            p = conn3.prepareStatement(query);
        } else {
            p = conn1.prepareStatement(query);
        }
        p.execute();
    }

    /**
     *
     * @param requests
     *
     * sets whether DB should query for requests
     *
     * True for Requests and false for normal
     */
    public void setRequests(boolean requests) {
        this.requests = requests;
    }

}
