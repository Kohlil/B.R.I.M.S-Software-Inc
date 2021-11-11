USE master
GO

/****** Object:  Database MelodyDirectory     ******/
IF DB_ID('MelodyDirectory') IS NOT NULL
	DROP DATABASE MelodyDirectory
GO

CREATE DATABASE MelodyDirectory
GO 

USE MelodyDirectory
GO

/****** Object:  Table Profiles  ******/   
CREATE TABLE Profiles(
	userId		VARCHAR(32)			NOT NULL,
	pass		VARCHAR(32)			NOT NULL
)
GO

/****** Object:  Table Songs  ******/
CREATE TABLE Songs(
	songId				INT				IDENTITY(1,1)	NOT NULL,
	albumId				INT				NOT NULL,
	artistId			INT				NOT NULL,
	songName			VARCHAR(64)		NOT NULL,
	songGenre			VARCHAR(32)		NOT NULL
)
GO

/****** Object:  Table Albums  ******/
CREATE TABLE Albums(
	albumId				INT				IDENTITY(1,1)	NOT NULL,
	artistId			INT				NOT NULL,
	albumName			VARCHAR(64)		NOT NULL,
	albumGenre			VARCHAR(32)		NOT NULL
)
GO

/****** Object:  Table Artist  ******/
CREATE TABLE Artist(
	artistId			INT				IDENTITY(1,1)	NOT NULL,
	artistName			VARCHAR(32)		NOT NULL,
	artistGenre			VARCHAR(32)		NOT NULL
)
GO

/*
CREATE TRIGGER songInsert_TRIGGER ON InvoiceLineItems
			FOR INSERT
		AS BEGIN

		END
GO
*/

INSERT Songs(albumId, artistId, songName, songGenre) VALUES
(1, 1, 'bloody valentine', 'Punk'),
(1, 1, 'body bag', 'Rock'),
(1, 1, 'hangover cure', 'Alternative'),
(2, 1, 'papercuts', 'Alternative')
GO

INSERT Albums(artistId, albumName, albumGenre) VALUES
(1, 'Tickets To My Downfall(SOLD OUT Deluxe)', 'Alternative/punk'),
(1, 'papercuts', 'Alternative')
GO

INSERT Artist(artistName, artistGenre) VALUES
('Machine Gun Kelly', 'Punk/rap')
GO

INSERT Profiles(userId, pass) VALUES
('Bricen', 'group17'),
('Rylee', 'group17'),
('Manas', 'group17'),
('Shane', 'group17'),
('Isaiah', 'group17')
GO

SELECT * FROM Profiles
SELECT * FROM Songs
SELECT * FROM Albums
SELECT * FROM Artist
GO

/* Functions */
CREATE FUNCTION dbo.search(@search VARCHAR(64))
RETURNS TABLE
RETURN
	SELECT s.songName, s.songGenre, al.albumName, al.albumGenre, a.artistName, a.artistGenre
	FROM Songs s	JOIN Albums al ON s.albumId = al.albumId
					JOIN Artist a ON al.artistId = a.artistId
	WHERE	s.songName LIKE ('%' + @search + '%') OR
			s.songGenre LIKE ('%' + @search + '%') OR
			al.albumName LIKE ('%' + @search + '%') OR
			al.albumGenre LIKE ('%' + @search + '%') OR
			a.artistName LIKE ('%' + @search + '%') OR
			a.artistGenre LIKE ('%' + @search + '%') 
GO

SELECT * FROM dbo.search('machine gun kelly')
GO
