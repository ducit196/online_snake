USE [master]
GO
/****** Object:  Database [snakeonline]    Script Date: 11/16/2017 8:18:08 PM ******/
CREATE DATABASE [snakeonline]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'snakeonline', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\snakeonline.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'snakeonline_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\snakeonline_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [snakeonline] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [snakeonline].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [snakeonline] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [snakeonline] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [snakeonline] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [snakeonline] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [snakeonline] SET ARITHABORT OFF 
GO
ALTER DATABASE [snakeonline] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [snakeonline] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [snakeonline] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [snakeonline] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [snakeonline] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [snakeonline] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [snakeonline] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [snakeonline] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [snakeonline] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [snakeonline] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [snakeonline] SET  DISABLE_BROKER 
GO
ALTER DATABASE [snakeonline] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [snakeonline] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [snakeonline] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [snakeonline] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [snakeonline] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [snakeonline] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [snakeonline] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [snakeonline] SET RECOVERY FULL 
GO
ALTER DATABASE [snakeonline] SET  MULTI_USER 
GO
ALTER DATABASE [snakeonline] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [snakeonline] SET DB_CHAINING OFF 
GO
ALTER DATABASE [snakeonline] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [snakeonline] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'snakeonline', N'ON'
GO
USE [snakeonline]
GO
/****** Object:  Table [dbo].[tbl_match]    Script Date: 11/16/2017 8:18:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_match](
	[match_id] [int] NOT NULL,
	[username1] [nchar](45) NULL,
	[username2] [nchar](45) NULL,
	[point1] [int] NULL,
	[point2] [int] NULL,
 CONSTRAINT [PK_tbl_match] PRIMARY KEY CLUSTERED 
(
	[match_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tbl_user]    Script Date: 11/16/2017 8:18:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_user](
	[username] [nchar](45) NOT NULL,
	[password] [nchar](45) NULL,
	[address] [nchar](45) NULL,
	[phone] [nchar](45) NULL,
 CONSTRAINT [PK_tbl_user] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[tbl_match]  WITH CHECK ADD  CONSTRAINT [FK_tbl_match_tbl_user] FOREIGN KEY([username1])
REFERENCES [dbo].[tbl_user] ([username])
GO
ALTER TABLE [dbo].[tbl_match] CHECK CONSTRAINT [FK_tbl_match_tbl_user]
GO
ALTER TABLE [dbo].[tbl_match]  WITH CHECK ADD  CONSTRAINT [FK_tbl_match_tbl_user1] FOREIGN KEY([username2])
REFERENCES [dbo].[tbl_user] ([username])
GO
ALTER TABLE [dbo].[tbl_match] CHECK CONSTRAINT [FK_tbl_match_tbl_user1]
GO
USE [master]
GO
ALTER DATABASE [snakeonline] SET  READ_WRITE 
GO
