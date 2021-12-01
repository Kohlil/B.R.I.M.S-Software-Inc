/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.levi.melodydirectory;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

/**
 *
 * @author isaia
 */
public class DBTest {

    @Test//test adding a song to db
    void testDBRequestSong() throws SQLException {
        DBreturn db = DBreturn.getInstance();
        db.setRequests(true);
        db.addSong("Spiderman Into the Spiderverse", "Post Malone", "Sunflower", "Rap", "I like this song", "nope", "11/11/11", "1.29", "3:00", "Rap", "This is a good album", "Nope", "11/11/11", "5.29", "Rap", "Hes good", "nada");
        db.setRequests(false);
    }
    
    @Test//test deleting a song/album/artist from db
    void testDBDelete() throws SQLException {
        DBreturn db = DBreturn.getInstance();
        db.setRequests(true);
        db.delete("Sunflower");
        db.delete("Post Malone");
        db.delete("Spiderman Into the Spiderverse");
        db.setRequests(false);
    }
}
