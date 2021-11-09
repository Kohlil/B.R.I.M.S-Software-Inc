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

INSERT Songs(albumId, artistId, songName, songGenre) VALUES
(1, 1, 'bloody valentine', 'Punk'),
(1, 1, 'body bag', 'Rock'),
(1, 1, 'hangover cure', 'Alternative'),
(2, 1, 'papercuts', 'Alternative')
GO

INSERT Albums(artistId, albumName, albumGenre) VALUES
(1, 'Tickets To My Downfall(SOLD OUT Deluxe)', 'Alternative'),
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

/* to find a specific user to verify login */
SELECT * FROM Profiles
WHERE userId = 'Shane' AND pass = 'group17'
GO

/* to find all songs by artist */
SELECT s.songName
FROM Songs s JOIN Artist a ON s.artistId = a.artistId
GO

/* to find a specific song by artist */
SELECT s.songName
FROM Songs s JOIN Artist a on s.artistId = a.artistId
WHERE s.songName LIKE '%papercuts%'
GO

/* to find all songs in an album */
SELECT s.songName
FROM Songs s JOIN Albums al on s.albumId = al.albumId
WHERE al.albumName LIKE '%Tickets To My Downfall(SOLD OUT Deluxe)%'
GO

/* to find a specific song using partial song name by a artist */
SELECT s.songName
FROM Songs s JOIN Artist a on s.artistId = a.artistId
WHERE s.songName LIKE '%paper%'
GO

/* to find a song just by song name */
SELECT songName
FROM Songs
WHERE songName LIKE '%bloody%'
GO

/* to find all songs by genre */
SELECT songName
FROM Songs
WHERE songGenre LIKE '%alternative%'
GO

/* to find all albums by genre */
SELECT albumName
FROM Albums
WHERE albumGenre LIKE '%alternative%'
GO

/* to find all artist by genre */
SELECT artistName
FROM Artist
WHERE artistGenre LIKE '%punk%'
GO