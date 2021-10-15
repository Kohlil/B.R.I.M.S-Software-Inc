USE master
GO

/****** Object:  Database AP     ******/
IF DB_ID('MelodyDirectory') IS NOT NULL
	DROP DATABASE MelodyDirectory
GO

CREATE DATABASE MelodyDirectory
GO 

USE MelodyDirectory
GO

/****** Object:  Table Profiles  ******/   
CREATE TABLE Profiles(
	userId		VARCHAR(20)			NOT NULL,
	pass		VARCHAR(20)			NOT NULL
)
GO

/****** Object:  Table ObjectInfo  ******/   
CREATE TABLE ObjectInfo(
	info		VARCHAR(1024)		NOT NULL
)
GO
