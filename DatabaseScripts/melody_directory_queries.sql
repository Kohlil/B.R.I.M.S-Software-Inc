USE MelodyDirectory
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