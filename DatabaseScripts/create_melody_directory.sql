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
	pass		VARCHAR(32)			NOT NULL,
	adminAccess	BIT					NOT NULL
)
GO

/****** Object:  Table Songs  ******/
CREATE TABLE Songs(
	songId				INT				IDENTITY(1,1)	NOT NULL,
	albumId				INT				NOT NULL,
	artistId			INT				NOT NULL,
	songName			VARCHAR(64)		NOT NULL,
	songGenre			VARCHAR(64)		,
	songDesc			VARCHAR(1024)	,
	songLink			VARCHAR(128)	,
	songReleaseDate		VARCHAR(16)		,
	songPrice			FLOAT			,
	songLength			VARCHAR(16)		,
	CONSTRAINT PK_Songs PRIMARY KEY CLUSTERED (
		songId ASC
	)
)
GO

/****** Object:  Table Albums  ******/
CREATE TABLE Albums(
	albumId				INT				IDENTITY(1,1)	NOT NULL,
	artistId			INT				NOT NULL,
	albumName			VARCHAR(64)		NOT NULL,
	albumGenre			VARCHAR(64)		,
	albumDesc			VARCHAR(1200)	,
	albumLink			VARCHAR(128)	,
	albumReleaseDate	VARCHAR(16)		,
	albumPrice			FLOAT			
	CONSTRAINT PK_Albums PRIMARY KEY CLUSTERED (
		albumId ASC
	)
)
GO

/****** Object:  Table Artist  ******/
CREATE TABLE Artists(
	artistId			INT				IDENTITY(1,1)	NOT NULL,
	artistName			VARCHAR(64)		NOT NULL,
	artistGenre			VARCHAR(64)		,
	artistDesc			VARCHAR(1200)	,
	artistLink			VARCHAR(128)	,
	CONSTRAINT PK_Artists PRIMARY KEY CLUSTERED (
		artistId ASC
	)
)
GO

/* Functions */
CREATE FUNCTION dbo.search(@search VARCHAR(1024))
RETURNS TABLE
RETURN
	SELECT	s.songName, s.songGenre, s.songDesc, s.songLink, s.songReleaseDate, s.songPrice, s.songLength, 
			al.albumName, al.albumGenre, al.albumDesc, al.albumLink, al.albumReleaseDate, al.albumPrice, 
			a.artistName, a.artistGenre, a.artistDesc, a.artistLink
	FROM Songs s	JOIN Albums al ON s.albumId = al.albumId
					JOIN Artists a ON al.artistId = a.artistId
	WHERE	s.songName LIKE ('%' + @search + '%') OR
			s.songGenre LIKE ('%' + @search + '%') OR
			al.albumName LIKE ('%' + @search + '%') OR
			al.albumGenre LIKE ('%' + @search + '%') OR
			a.artistName LIKE ('%' + @search + '%') OR
			a.artistGenre LIKE ('%' + @search + '%') OR
			s.songLink = @search OR
			al.albumLink = @search OR
			a.artistLink = @search
GO


CREATE PROC spAddSong
	@albumName			VARCHAR(64),
	@artistName			VARCHAR(64),
	@songName			VARCHAR(64), 
	@songGenre			VARCHAR(64),
	@songDesc			VARCHAR(1200),
	@songLink			VARCHAR(128),
	@songReleaseDate	VARCHAR(16),
	@songPrice			FLOAT,	
	@songLength			VARCHAR(16),

	@albumGenre			VARCHAR(64),
	@albumDesc			VARCHAR(1200),
	@albumLink			VARCHAR(128),
	@albumReleaseDate	VARCHAR(16),
	@albumPrice			FLOAT,

	@artistGenre		VARCHAR(64),
	@artistDesc			VARCHAR(1200),
	@artistLink			VARCHAR(128)
AS BEGIN SET NOCOUNT ON
	DECLARE @albumId INT, @artistId INT, @songId INT

	/* Checks if the song exists in the song table, if id already exists, then return, the add is not necessary */
	SELECT @songId = ISNULL(songId,-1) FROM Songs WHERE songName = @songName
	IF(@songId > 0) RETURN;

	SELECT @albumId = ISNULL(albumId,-1) FROM Albums WHERE albumName = @albumName
	SELECT @artistId = ISNULL(artistId,-1) FROM Artists WHERE artistName = @artistName

	/* Checks if the artist exists in the artist table, if not creates a new entry */
	IF(@artistId IS NULL) BEGIN
		INSERT INTO Artists(artistName, artistGenre, artistDesc, artistLink) VALUES (@artistName, @artistGenre, @artistDesc, @artistLink)
		SET @artistId = @@IDENTITY
	END

	/* Checks if the albums exists in the albums table, if not creates a new entry */
	IF(@albumId IS NULL) BEGIN
		INSERT INTO Albums (artistId, albumName, albumGenre, albumDesc, albumLink, albumReleaseDate, albumPrice) VALUES (@artistId, @albumName, @albumGenre, @albumDesc, @albumLink, @albumReleaseDate, @albumPrice)
		SET @albumId = @@IDENTITY
	END
	
	/* At this point, new entires for artist and albums have been created if necessary , meaning we can now safely insert a new song */
	INSERT INTO Songs (albumId, artistId, songName, songGenre, songDesc, songLink, songReleaseDate, songPrice, songLength) VALUES (@albumId, @artistId, @songName, @songGenre, @songDesc, @songLink, @songReleaseDate, @songPrice, @songLength)

	
END
GO

CREATE PROC spAddProfile
	@username		VARCHAR(32),
	@pass			VARCHAR(32),
	@adminAccess	BIT
AS BEGIN SET NOCOUNT ON
	DECLARE @userId VARCHAR(32)

	/* Checks if the song exists in the song table, if id already exists, then return, the add is not necessary */
	SELECT @userId = ISNULL(userId, NULL) FROM Profiles WHERE userId = @username
	IF(@userId IS NOT NULL) BEGIN
		RETURN;
	END
	
	/* At this point, new entires for artist and albums have been created if necessary , meaning we can now safely insert a new song */
	INSERT INTO Profiles (userId, pass, adminAccess) VALUES (@username, @pass, @adminAccess)
	
END
GO


INSERT Songs(albumId, artistId, songName, songGenre, songDesc, songLink, songReleaseDate, songPrice, songLength) VALUES
(1, 1,	'title track',									'Punk',		'The first song on MGK''s album Tickets To My Downfall',		'https://music.youtube.com/watch?v=HLTLVVicjEo&feature=share',		'9/25/2020',	1.29,	'2:46'),
(1, 1,	'kiss kiss',									'Punk',		'The second song on MGK''s album Tickets To My Downfall',		'https://music.youtube.com/watch?v=IaMdHBoPuJA&feature=share',		'9/25/2020',	1.29,	'2:19'),
(1, 1,	'drunk face',									'Punk',		'The third song on MGK''s album Tickets To My Downfall',		'https://music.youtube.com/watch?v=86RL3kRbksQ&feature=share',		'9/25/2020',	1.29,	'2:24'),
(1, 1,	'bloody valentine',								'Punk',		'The fourth song on MGK''s album Tickets To My Downfall',		'https://music.youtube.com/watch?v=wSdT-SArM2Q&feature=share',		'9/25/2020',	1.29,	'3:26'),
(1, 1,	'forget me too',								'Punk',		'The fifth song on MGK''s album Tickets To My Downfall',		'https://music.youtube.com/watch?v=7gVNNPv8w4Q&feature=share',		'9/25/2020',	1.29,	'2:52'),

(2, 1,	'DAYWALKER! (feat. CORPSE)',					'Punk',		'"DayWalker" (stylized as "DAYWALKER!") is a song by American musician Machine Gun Kelly featuring Corpse Husband that was released on March 12, 2021. It is a trap metal and techno-punk song produced by Kelly, BazeXX, and SlimXX with additional writing credit by Corpse Husband. Music video co-directed by Kelly and Sam Cahill was published on March 18, and it features Kelly fighting with another man and Valkyrae portraying Corpse Husband. The song garnered commendable success despite being censored on several platforms & playlisting, charting at number 53 in the UK, and 88 in the US, becoming Corpse''s first entry on the Billboard Hot 100.',	'https://music.youtube.com/watch?v=nSBYIzaxijM&feature=share', '3/12/2021',	1.29,	'2:17'),

(3, 1,	'Sex Drive',									'Rap',		'The first song on MGK''s album Hotel Diablo',					'https://music.youtube.com/watch?v=GYmgANV5SEM&feature=share',		'7/5/2019',		1.29,	'2:04'),
(3, 1,	'el Diablo',									'Rap',		'The second song on MGK''s album Hotel Diablo',					'https://music.youtube.com/watch?v=ecw1sMKq5ME&feature=share',		'7/5/2019',		1.29,	'2:27'),
(3, 1,	'Hollywood Whore',								'Rap',		'The third song on MGK''s album Hotel Diablo',					'https://music.youtube.com/watch?v=8Bm6eaIPNts&feature=share',		'7/5/2019',		1.29,	'3:24'),
(3, 1,	'Glass House (feat. Naomi Wild)',				'Rap',		'The fourth song on MGK''s album Hotel Diablo',					'https://music.youtube.com/watch?v=RRcXtqamWWM&feature=share',		'7/5/2019',		1.29,	'3:22'),
(3, 1,	'Burning Memories (feat. Lil Skies)',			'Rap',		'The fifth song on MGK''s album Hotel Diablo',					'https://music.youtube.com/watch?v=gXUOYVRtQlc&feature=share',		'7/5/2019',		1.29,	'3:37'),

(4, 2,	'In My Dreams (Cudder Anthem)',					'Hip-Hop',	'The first song on Kid Cudi''s album Man on the Moon I',		'https://music.youtube.com/watch?v=UNFa23QzdhY&feature=share' ,		'9/15/2009',	1.29,	'3:19'),
(4, 2,	'Soundtrack 2 My Life',							'Hip-Hop',	'The second song on Kid Cudi''s album Man on the Moon I',		'https://music.youtube.com/watch?v=TEmp41IbU-E&feature=share',		'9/15/2009',	1.29,	'3:55'),
(4, 2,	'Simple As...',									'Hip-Hop',	'The third song on Kid Cudi''s album Man on the Moon I',		'https://music.youtube.com/watch?v=rxc63Sc1sMg&feature=share',		'9/15/2009',	1.29,	'2:31'),
(4, 2,	'Solo Dolo (Nightmare)',						'Hip-Hop',	'The fourth song on Kid Cudi''s album Man on the Moon I',		'https://music.youtube.com/watch?v=9f0iRfOBb6A&feature=share',		'9/15/2009',	1.29,	'4:26'),
(4, 2,	'Heart of a Lion (Kid Cudi Theme Music)',		'Hip-Hop',	'The fifth song on Kid Cudi''s album Man on the Moon I',		'https://music.youtube.com/watch?v=KvlUkAtFIxs&feature=share',		'9/15/2009',	1.29,	'4:21'),

(5, 2,	'Scott Mescudi Vs. The World',					'Hip-Hop',	'The first song on Kid Cudi''s album Man on the Moon II',		'https://music.youtube.com/watch?v=Epb1To80PSQ&feature=share',		'11/9/2019',	1.29,	'3:55'),
(5, 2,	'REVOFEV',										'Hip-Hop',	'The second song on Kid Cudi''s album Man on the Moon II',		'https://music.youtube.com/watch?v=XCjTwALse0Q&feature=share',		'11/9/2019',	1.29,	'3:03'),
(5, 2,	'Don’t Play This Song (feat. Mary J. Blige)',	'Hip-Hop',	'The third song on Kid Cudi''s album Man on the Moon II',		'https://music.youtube.com/watch?v=PABQJinVVpo&feature=share',		'11/9/2019',	1.29,	'3:42'),
(5, 2,	'We Aite (Wake Your Mind Up)',					'Hip-Hop',	'The fourth song on Kid Cudi''s album Man on the Moon II',		'https://music.youtube.com/watch?v=RbB329Rg5cE&feature=share',		'11/9/2019',	1.29,	'1:26'),
(5, 2,	'Marijuana',									'Hip-Hop',	'The fifth song on Kid Cudi''s album Man on the Moon II',		'https://music.youtube.com/watch?v=yN_EMqk7HcI&feature=share',		'11/9/2019',	1.29,	'4:20'),

(6, 2,	'Beautiful Trip',								'Hip-Hop',	'The first song on Kid Cudi''s album Man on the Moon III',		'https://music.youtube.com/watch?v=Ym7jTAEtQdE&feature=share',		'12/11/2020',	1.29,	'0:38'),
(6, 2,	'Tequila Shots',								'Hip-Hop',	'The second song on Kid Cudi''s album Man on the Moon III',		'https://music.youtube.com/watch?v=lZcRSy0sk5w&feature=share',		'12/11/2020',	1.29,	'3:14'),
(6, 2,	'Another Day',									'Hip-Hop',	'The third song on Kid Cudi''s album Man on the Moon III',		'https://music.youtube.com/watch?v=OzxHEdcrD1E&feature=share',		'12/11/2020',	1.29,	'3:20'),
(6, 2,	'She Knows This',								'Hip-Hop',	'The fourth song on Kid Cudi''s album Man on the Moon III',		'https://music.youtube.com/watch?v=jgkRLRj4y7k&feature=share',		'12/11/2020',	1.29,	'3:37'),
(6, 2,	'Dive',											'Hip-Hop',	'The fifth song on Kid Cudi''s album Man on the Moon III',		'https://music.youtube.com/watch?v=nRTEhH1QYPk&feature=share',		'12/11/2020',	1.29,	'2:29'),

(7, 3,	'Cannibal',										'Pop',		'An absolute banger',											'https://music.youtube.com/watch?v=48wHhW_dTMo&feature=share',		'11/19/2010',	1.29,	'3:15'),
(7, 3,	'We R Who We R',								'Pop',		'"Hot and dangerous"',											'https://music.youtube.com/watch?v=mXvmSaE0JXA&feature=share',		'11/19/2010',	1.29,	'3:25'),
(7, 3,	'Sleazy',										'Pop',		'I love this song',												'https://music.youtube.com/watch?v=n2kdCJRAiNk&feature=share',		'11/19/2010',	1.29,	'3:26'),
(7, 3,	'Blow',											'Pop',		'This song is a party anthem ',									'https://music.youtube.com/watch?v=CFWX0hWCbng&feature=share',		'11/19/2010',	1.29,	'3:40'),
(7, 3,	'Crazy Beautiful Life',							'Pop',		'This is a fine song, not her best',							'https://music.youtube.com/watch?v=DtxgCw6qsIk&feature=share',		'11/19/2010',	1.29,	'2:51'),

(8, 3,	'Your Love Is My Drug',							'Pop',		'Sometimes I sing this song in my car with my sister. ',		'https://music.youtube.com/watch?v=QR_qa3Ohwls&feature=share',		'1/1/2010',	1.29,	'3:08'),
(8, 3,	'TiK ToK',										'Pop',		'THE TUNE OF MY CHILDHOOD',										'https://music.youtube.com/watch?v=iP6XpLQM2Cs&feature=share',		'1/1/2010',	1.29,	'3:20'),
(8, 3,	'Take It Off',									'Pop',		'ABOUT STRIPPERS',												'https://music.youtube.com/watch?v=edP0L6LQzZE&feature=share',		'1/1/2010',	1.29,	'3:36'),
(8, 3,	'Kiss N Tell',									'Pop',		'The use of the letter N instead of and inspired katy perry''s Hot N Cold (this is a lie)',		'https://music.youtube.com/watch?v=ERJGipWWPfs&feature=share',		'1/1/2010',	1.29,	'3:28'),
(8, 3,	'Blah Blah Blah (feat. 3OH!3)',					'Pop',		'Another party bob',											'https://music.youtube.com/watch?v=3taEuL4EHAg&feature=share', 		'1/1/2010',	1.29,	'2:53'),

(9, 3,	'Warrior',										'Pop',		'This is the first song in a far less superior album',			'https://music.youtube.com/watch?v=IkxuUvxgCoM&feature=share',		'11/19/2010',	1.29,	'4:01'),
(9, 3,	'Die Young',									'Pop',		'Sometimes I think about that tik tok trend and cry',			'https://music.youtube.com/watch?v=NOubzHCUt48&feature=share',		'11/19/2010',	1.29,	'3:32'),
(9, 3,	'C''Mon',										'Pop',		'I don''t know anything about this song',						'https://music.youtube.com/watch?v=c8A4QVop3-8&feature=share',		'11/19/2010',	1.29,	'3:35'),
(9, 3,	'Thinking of You',								'Pop',		'I don''t think of this song',									'https://music.youtube.com/watch?v=heMpvpZr7PQ&feature=share',		'11/19/2010',	1.29,	'3:05'),
(9, 3,	'Crazy Kids',									'Pop',		'Again, not a superior song but it''s by Kesha so it''s fine',	'https://music.youtube.com/watch?v=xdeFB7I0YH4&feature=share',		'11/19/2010',	1.29,	'3:51')
GO

INSERT Albums(artistId, albumName, albumGenre, albumDesc, albumLink, albumReleaseDate, albumPrice) VALUES
(1, 'Tickets To My Downfall',						'Alternative/punk',		'Tickets to My Downfall is the fifth studio album by American musician Machine Gun Kelly. A departure from his established rap sound, the album is a more guitar-driven pop punk album. It was released through Bad Boy Records and Interscope Records on September 25, 2020. The album was a commercial success, debuting atop the US Billboard 200, and was met with positive reviews from critics.', 'https://music.youtube.com/playlist?list=OLAK5uy_lXa9nraZv-czIZ6aQsAUpeLYFCQ2JtKYo', '9/25/2020', 10.99),
(1, 'DAYWALKER! (feat. CORPSE)',					'Alternative',			'"DayWalker" (stylized as "DAYWALKER!") is a song by American musician Machine Gun Kelly featuring Corpse Husband that was released on March 12, 2021. It is a trap metal and techno-punk song produced by Kelly, BazeXX, and SlimXX with additional writing credit by Corpse Husband. Music video co-directed by Kelly and Sam Cahill was published on March 18, and it features Kelly fighting with another man and Valkyrae portraying Corpse Husband. The song garnered commendable success despite being censored on several platforms & playlisting, charting at number 53 in the UK, and 88 in the US, becoming Corpse''s first entry on the Billboard Hot 100.', 'https://music.youtube.com/playlist?list=OLAK5uy_lib6UnBdiaMxdOpycmzgVao6aUC-pKTNw', '3/12/2021', 1.29),
(1, 'Hotel Diablo',									'Rap',					'Hotel Diablo is the fourth studio album by American rapper Machine Gun Kelly. It was released on July 5, 2019 via Bad Boy Records and Interscope Records. The album was supported by four singles: "Hollywood Whore", "El Diablo", "I Think I''m Okay" with Yungblud and Travis Barker, and "Glass House" featuring Naomi Wild, with promotional single "Floor 13". The record is a rap rock album and followed less than 10 months after September 2018''s Binge EP. The album''s production also included Foster The People frontman Mark Foster. It debuted at number five on the US Billboard 200. The album has received generally positive reviews. Lyrically, the songs deal with problems with drugs, childhood family struggles, among other themes.', 'https://music.youtube.com/playlist?list=OLAK5uy_mhxPh_P5otpvlSx722VEqzyIlenwdyy8Y', '7/5/2019', 9.99),

(2, 'Man on the Moon: The End of Day',				'Hip-Hop',				'Man on the Moon: The End of Day is the debut studio album by American rapper Kid Cudi. It was released on September 15, 2009, through Dream On, GOOD Music, and Universal Motown Records. A concept album, narrated by fellow American rapper Common, it follows the release of his first full-length project A Kid Named Cudi, and is the first installment of the Man on the Moon trilogy. Production was handled by several high-profile record producers, including Kanye West, Emile Haynie, Plain Pat, No I.D., Dot da Genius, and Jeff Bhasker, among others. Man on the Moon: The End of Day spawned three singles—"Day ''n'' Nite", "Make Her Say" and "Pursuit of Happiness"—that attained chart success, the first of which became a US platinum-certified hit single. To further promote the album, he toured with Asher Roth and Lady Gaga, respectively. The album received generally positive reviews from critics, who praised it for its music composition and different approach to being a hip-hop record. Aside from being included on critics list of the best albums of the year, Man on the Moon: The End of Day received three Grammy Awards nominations.', 'https://music.youtube.com/playlist?list=OLAK5uy_m7hP3M7qj_uCp44DIGHI2Kt5WHottxJ9k', '9/15/2009', 6.99),
(2, 'Man on the Moon II: The Legend of Mr. Rager',	'Hip-Hop',				'Man on the Moon II: The Legend of Mr. Rager is the second studio album by American rapper Kid Cudi. It was released on November 9, 2010, through Dream On and GOOD Music, and distributed by Universal Motown Records. It serves as a sequel to his debut studio album Man on the Moon: The End of Day, and is the second installment of the Man on the Moon trilogy. Production for the album took place during 2009 to 2010 at various recording studios and was handled by long-time collaborators Emile Haynie and Plain Pat. It also featured contributions from Anthony Kilhoffer, Blended Babies, Chuck Inglish, Dot da Genius, Jim Jonsin, and Rami Beatz, among others. The album was supported by two singles: "Erase Me" and "Mr. Rager". The album incorporates alternative and psychedelic elements to the sound that Cudi explored in his previous album. It features a blend of dark and emotional lyrics, exploring themes of depression, loneliness, detachment, and isolation. The album also highlights other topics, such as Cudi''s former cocaine addiction, fame, and alcoholism, as well as family issues and women. The album features guest appearances from CeeLo Green, Mary J. Blige, Kanye West, Cage, St.', 'https://music.youtube.com/playlist?list=OLAK5uy_logUKI-PsWfbeMSy4rfHaX0MB7xbzZP1g', '11/9/2019', 6.99),
(2, 'Man On The Moon III: The Chosen',				'Hip-Hop',				'Man on the Moon III: The Chosen is the seventh studio album by American rapper Kid Cudi. It was released on December 11, 2020 by Republic Records. It is the final installment of Cudi''s Man on the Moon trilogy of albums. The 18-track album was produced mainly by Cudi himself and Dot da Genius, along with Plain Pat, Emile Haynie and Mike Dean, all of whom contributed to the previous two Man on the Moon albums. Guest vocals on the album are contributed by Phoebe Bridgers, Pop Smoke, Skepta and Trippie Redd. Thematically, Man on the Moon III is a concept album that finds Cudi in an internal struggle against his evil alter ego, Mr. Rager, in hopes to win back his peace and happiness. As with the previous two installments in the trilogy, the album is divided into separate acts. Man on the Moon III: The Chosen received mostly positive reviews from music critics, who praised Cudi''s songwriting and generally favored the latter two acts, though some found it derivative of Cudi''s earlier albums. It debuted at number two on the Billboard 200, earning 144,000 album-equivalent units of which 15,000 were pure sales, marking Cudi''s fifth top 10 album in the US.', 'https://music.youtube.com/playlist?list=OLAK5uy_kGU18jNHGpux2MwN_jTzX6OyUJJf9gUN8', '12/11/2020', 11.99),

(3, 'Cannibal',										'Pop',					'Cannibal is the first extended play (EP) by American recording artist Kesha, released on November 19, 2010. The EP is a follow-up companion to her debut album, Animal. Originally, the record was thought to be released as a deluxe edition of Animal, but was instead sold and released as both an EP and a deluxe edition of Animal. Kesha worked with a variety of producers and writers such as executive producer Dr. Luke, Benny Blanco, Ammo, Max Martin, Bangladesh and others. Musically, the songs on Cannibal are of the dance-pop genre, with some songs incorporating elements of electro and electropop in their production and beats. Throughout the album, the use of Auto-Tune and vocoders is prominent. Lyrically, the songs on Cannibal speak of ignoring judgement or hate and experiences based on love and heartbreak. Cannibal received generally positive reviews from music critics. However, a common complaint amongst critics was the overuse of Auto-Tune, while the album''s production was generally highlighted. The album''s lyrics generally polarized music critics; some praised her boldness, while others criticized them as being too raunchy.', 'https://music.youtube.com/playlist?list=OLAK5uy_ncTxsDgH9MdCHsS9vno3iM2wYWHAEC_js', '11/19/2010', 9.99),
(3, 'Animal (Extended Edition)',					'Pop',					'Animal is the debut studio album by American singer and songwriter Kesha. The album was released on January 1, 2010, through RCA Records and distributed through Sony Music Entertainment. Kesha worked on the album with a variety of record producers and songwriters such as Lukasz "Dr. Luke" Gottwald, Benny Blanco, David Gamson, Greg Kurstin, Max Martin and others. Kesha had been recording demos for several years when one eventually ended up in the hands of Samantha Cox, senior director of writer/publisher relations at BMI. Cox passed along the demo and it ended up in the hands of Gottwald, who decided to have Kesha perform on the song "Right Round" with American rapper Flo Rida. Within two months, the song became a hit in multiple countries around the world. The event led to Kesha being sought after by many major labels, and she eventually signed a multi-album deal with RCA Records. Musically, Animal draws from the electropop genre, while incorporating elements of dance-pop in its production and beats. Lyrically, the majority of the album''s songs are based on Kesha''s past life experiences of love, heartbreak, boys, and having a good time.', 'https://music.youtube.com/playlist?list=OLAK5uy_kHUjTE56OFpxtw0F6SekRQqNGJH-eXW3k', '1/1/2010', 13.99),
(3, 'Warrior (Deluxe Version)',						'Pop',					'Warrior is the second studio album by American singer and songwriter Kesha, released on November 30, 2012, by Kemosabe and RCA Records. Its music incorporates a wide range of genres, including pop, EDM, rock, punk, rap, country, and balladry. Kesha described the album as more personal than her previous material in addition to mentioning it was her attempt at reviving the rock genre, calling it a "cock pop" record. Its theme is said to be magic. Writing for Warrior began in late 2011 and ended in early 2012, with recording taking place from January through August 2012. Kesha wrote the majority of the album while touring internationally and during her spiritual journey. Contributions to the album''s production came from longtime collaborators Dr. Luke, Max Martin, Shellback, Ammo and Benny Blanco.', 'https://music.youtube.com/playlist?list=OLAK5uy_lkIlVMFXY1CStOOgStagBgo2a1EaG1g40', '11/19/2010', 13.99)
GO

INSERT Artists(artistName, artistGenre, artistDesc, artistLink) VALUES
('Machine Gun Kelly',	'Punk/rap/hip-hop', 'Colson Baker, known professionally as Machine Gun Kelly, is an American rapper, singer, and actor. He is noted for his compositional blending of contemporary and alternative hip hop with rock. Machine Gun Kelly released four mixtapes between 2007 and 2010 before signing with Bad Boy Records. He released his debut studio album, Lace Up, in 2012, which peaked at number four on the US Billboard 200 and contained his breakout single "Wild Boy". His second and third albums, General Admission and Bloom, achieved similar commercial success; the latter included the single "Bad Things", which peaked at number 4 on the Billboard Hot 100. His fourth album, Hotel Diablo, included rap rock. Machine Gun Kelly released his fifth album, Tickets to My Downfall, in 2020; it marked a complete departure from hip hop and entry into pop punk. It debuted at number one on the Billboard 200, the only rock album to do so that year, and contained the single "My Ex''s Best Friend", which reached number 20 on the Hot 100.', 'https://music.youtube.com/channel/UCDbLC4CaZzi4jt5KyAF0ZQw'),
('kid Cudi',			'Rock', 'Kid Cudi is an American rapper, singer, songwriter, multi-instrumentalist, record producer and actor from Cleveland, Ohio.', 'https://music.youtube.com/channel/UCo2-dPLJcpbZK84FIt0vD-w'),
('Kesha',				'Pop', 'Kesha Rose Sebert, known mononymously as Kesha, is an American singer and songwriter. In 2005, at age 18, Kesha was signed to Kemosabe Records. Her first major success came in early 2009 after she was featured on American rapper Flo Rida''s number-one single "Right Round". Kesha''s music and image propelled her to immediate success. She has earned two number-one albums on the US Billboard 200 with Animal and Rainbow, and the top-ten records Warrior and High Road. Kesha has attained ten top-ten singles on the US Billboard Hot 100, including "Tik Tok", "Blah Blah Blah", "Your Love Is My Drug", "Take It Off", "Blow", "Die Young", "My First Kiss" with 3OH!3, "We R Who We R", "Right Round" with Flo Rida, and "Timber" with Pitbull. Her 2009 single "Tik Tok" was the best-selling digital single in history, selling over 14 million units internationally, until surpassed in 2011. Kesha''s career was halted between Warrior and Rainbow due to a legal dispute with her former producer Dr. Luke, which has been ongoing since 2014. A series of lawsuits, known collectively as Kesha v. Dr.', 'https://music.youtube.com/channel/UC4dTCL_is1o-dQLksanXmRg')
GO

INSERT Profiles(userId, pass, adminAccess) VALUES
('Bricen',	'group17', 1),
('Rylee',	'group17', 1),
('Manas',	'group17', 1),
('Shane',	'group17', 1),
('Isaiah',	'group17', 1)
GO

/*
SELECT * FROM Profiles
SELECT * FROM Songs
SELECT * FROM Albums
SELECT * FROM Artists
GO
*/

SELECT * FROM Profiles
SELECT * FROM Songs
SELECT * FROM Albums
SELECT * FROM Artists
GO

EXEC spAddSong
	@albumName			= 'Hotel Diablo',
	@artistName			= 'Machine Gun Kelly',
	@songName			= 'FLOOR 13', 
	@songGenre			= 'Rap',
	@songDesc			= 'Another song from Hotel Diablo by MGK',
	@songLink			= 'https://music.youtube.com/watch?v=oIm-GQml3ew&feature=share',
	@songReleaseDate	= '7/5/2019',
	@songPrice			= 1.29,
	@songLength			= '3:15',
	
	@albumGenre			= 'Rap',
	@albumDesc			= 'Hotel Diablo is the fourth studio album by American rapper Machine Gun Kelly. It was released on July 5, 2019 via Bad Boy Records and Interscope Records. The album was supported by four singles: "Hollywood Whore", "El Diablo", "I Think I''m Okay" with Yungblud and Travis Barker, and "Glass House" featuring Naomi Wild, with promotional single "Floor 13". The record is a rap rock album and followed less than 10 months after September 2018''s Binge EP. The album''s production also included Foster The People frontman Mark Foster. It debuted at number five on the US Billboard 200. The album has received generally positive reviews. Lyrically, the songs deal with problems with drugs, childhood family struggles, among other themes.',
	@albumLink			= 'https://music.youtube.com/playlist?list=OLAK5uy_mhxPh_P5otpvlSx722VEqzyIlenwdyy8Y',
	@albumReleaseDate	= '7/5/2019',
	@albumPrice			= 9.99,

	@artistGenre		= 'Punk/rap/hip-hop',
	@artistDesc			= 'Colson Baker, known professionally as Machine Gun Kelly, is an American rapper, singer, and actor. He is noted for his compositional blending of contemporary and alternative hip hop with rock. Machine Gun Kelly released four mixtapes between 2007 and 2010 before signing with Bad Boy Records. He released his debut studio album, Lace Up, in 2012, which peaked at number four on the US Billboard 200 and contained his breakout single "Wild Boy". His second and third albums, General Admission and Bloom, achieved similar commercial success; the latter included the single "Bad Things", which peaked at number 4 on the Billboard Hot 100. His fourth album, Hotel Diablo, included rap rock. Machine Gun Kelly released his fifth album, Tickets to My Downfall, in 2020; it marked a complete departure from hip hop and entry into pop punk. It debuted at number one on the Billboard 200, the only rock album to do so that year, and contained the single "My Ex''s Best Friend", which reached number 20 on the Hot 100.',
	@artistLink			= 'https://music.youtube.com/channel/UCDbLC4CaZzi4jt5KyAF0ZQw'
GO

EXEC spAddSong
	@albumName			= 'test album',
	@artistName			= 'test artist',
	@songName			= 'test song', 
	@songGenre			= 'test genre',
	@songDesc			= 'test description',
	@songLink			= 'test link',
	@songReleaseDate	= '11/15/2021',
	@songPrice			= 999.99,
	@songLength			= '9:99',

	@albumGenre			= 'Rap',
	@albumDesc			= 'test album description',
	@albumLink			= 'test album link',
	@albumReleaseDate	= '7/5/2019',
	@albumPrice			= 9.99,
	
	@artistGenre		= 'test artist genre',
	@artistDesc			= 'test artist description',
	@artistLink			= 'test artist link'
GO

EXEC spAddProfile
	@username		=	'test user',
	@pass			=	'test password',
	@adminAccess	=	1
GO

EXEC spAddProfile
	@username		=	'Shane',
	@pass			=	'test password',
	@adminAccess	=	0
GO


SELECT * FROM Profiles
SELECT * FROM Songs
SELECT * FROM Albums
SELECT * FROM Artists
GO

SELECT * FROM dbo.search('test')
GO

SELECT * FROM dbo.search('FLOOR 13')
GO