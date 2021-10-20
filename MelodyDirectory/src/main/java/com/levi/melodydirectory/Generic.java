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
	private int dataType;
	
	// Constructor
	public Generic(String name, String description, String genre, String releaseDate, String eLink) {
		// dataType will be tied to object constructor
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
		this.eLink = eLink;
		this.genre = genre;
	}
	
	// dataType, name, description, genre, releaseDate, eLink
	public String toString() {
		return dataType + "," + name + "," + description + "," + genre + "," + releaseDate + "," + eLink;
	}
	
	public String tags() {
		return genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String geteLink() {
		return eLink;
	}

	public void seteLink(String eLink) {
		this.eLink = eLink;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	
}
