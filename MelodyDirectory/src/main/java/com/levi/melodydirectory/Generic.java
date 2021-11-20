package com.levi.melodydirectory;

/**
 * @author Bricen Raynold
 * Generic object class
 * @param <T>
 */
public abstract class Generic<T> {

    /*
	 * Generic Class Structure. Includes;
	 * Name
	 * Description
	 * eLink
	 * Genre
	 * DataType
	 * toString()
	 * tags()
     */
    private String name;
    private String description;
    private String releaseDate;
    private String eLink;
    private String genre;
    private DataTypes dataType;

    /**
     * @author Bricen Raynold
     * Allows us to detect the Data type of the objects inheriting generic
     */
    public static enum DataTypes {
        SONG,
        ARTIST,
        ALBUM
    };

    // Constructor
    /**
     * @param name
     * @param description
     * @param genre
     * @param releaseDate
     * @param eLink
     * Generic object constructor
     */
    public Generic(String name, String description, String genre, String releaseDate, String eLink) {
        // dataType will be tied to object constructor
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.eLink = eLink;
        this.genre = genre;
    }

    // dataType, name, description, genre, releaseDate, eLink
    /**
     * Returns the generic object's information as a string
     */
    public String toString() {
        return dataType + "," + name + "," + description + "," + genre + "," + releaseDate + "," + eLink;
    }

    /**
     * @return
     * gets the generic object's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     * sets the generic object's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * returns the generic object's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     * sets the generic object's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return
     * gets the generic object's release date
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate
     * sets the generic object's release date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return
     * gets the generic object's link
     */
    public String geteLink() {
        return eLink;
    }

    /**
     * @param eLink
     * sets the generic object's link
     */
    public void seteLink(String eLink) {
        this.eLink = eLink;
    }

    /**
     * @return
     * gets the generic object's genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre
     * sets the generic object's genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return
     * gets the generic object's data type
     */
    public DataTypes getDataType() {
        return dataType;
    }

    /**
     * @param dataType
     * sets the generic object's data type
     */
    public void setDataType(DataTypes dataType) {
        this.dataType = dataType;
    }

}
