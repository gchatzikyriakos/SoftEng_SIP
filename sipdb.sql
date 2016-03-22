-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 22, 2016 at 02:29 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sipdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE `billing` (
  `user` varchar(40) NOT NULL,
  `total_cost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`user`, `total_cost`) VALUES
('Saitama', 0),
('Genos', 0),
('MumenRider', 0),
('j', 0),
('k', 0.56);

-- --------------------------------------------------------

--
-- Table structure for table `blocks`
--

CREATE TABLE `blocks` (
  `blocker` varchar(40) NOT NULL,
  `blocked` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blocks`
--

INSERT INTO `blocks` (`blocker`, `blocked`) VALUES
('kostas', 'manolis');

-- --------------------------------------------------------

--
-- Table structure for table `forwards`
--

CREATE TABLE `forwards` (
  `fromUser` varchar(40) NOT NULL,
  `toUser` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forwards`
--

INSERT INTO `forwards` (`fromUser`, `toUser`) VALUES
('alice', 'bob'),
('bob', 'jim'),
('yannis', 'giorgos'),
('giorgos', 'yannis');

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `friend1` varchar(40) NOT NULL,
  `friend2` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`friend1`, `friend2`) VALUES
('MumenRider', 'Genos');

-- --------------------------------------------------------

--
-- Table structure for table `premium`
--

CREATE TABLE `premium` (
  `user` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `premium`
--

INSERT INTO `premium` (`user`) VALUES
('Saitama');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`username`, `password`) VALUES
('yannis', 'fb86928f0abb21dc59e97f14293e4924a0493c89'),
('kostas', 'e1a8399e36effe75a124102eb2a2bdfc82e0aab4'),
('giorgos', '4f0561b536eb9180575b09802f074ab4f72dba3d'),
('manolis', 'f2620df262d9e0ce7ac6ed6631fe1cc2c20ab35a'),
('bob', '48181acd22b3edaebc8a447868a7df7ce629920a'),
('alice', '522b276a356bdf39013dfabea2cd43e141ecc9e8'),
('jim', '1cd02e31b43620d7c664e038ca42a060d61727b9'),
('Saitama', '2a728ab75fa12ba391c39e6f9812be8f8ab7fe72'),
('Genos', '6839305294b33dcda76df843f0b8ac90804adbd6'),
('Mumen Rider', 'e8e349300e820fe2bd797226ae1b3ae2271f48de'),
('MumenRider', 'e98bcbe59d19db4820f124c6651a96b623010dab'),
('j', '5c2dd944dde9e08881bef0894fe7b22a5c9c4b06'),
('k', '13fbd79c3d390e5d6585a21e11ff5ec1970cff0c');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
