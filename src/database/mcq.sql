-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2019 at 12:54 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mcq`
--

-- --------------------------------------------------------

--
-- Table structure for table `questionbank`
--

CREATE TABLE `questionbank` (
  `QID` int(11) NOT NULL,
  `SubID` int(11) NOT NULL,
  `Question` longtext NOT NULL,
  `Option1` longtext NOT NULL,
  `Option2` longtext NOT NULL,
  `Option3` longtext NOT NULL,
  `Option4` longtext NOT NULL,
  `CorrectAns` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `questionbank`
--

INSERT INTO `questionbank` (`QID`, `SubID`, `Question`, `Option1`, `Option2`, `Option3`, `Option4`, `CorrectAns`) VALUES
(1, 2, 'From where does river Ganga originates?', 'Himalaya', 'Gangotri', 'Brahmputra', 'Gomti', 'B'),
(2, 2, 'Which place receives highest rainfall in India?', 'Jhansi', 'Pune', 'Cherrapunji', 'Allahabad', 'C'),
(3, 4, 'Moon rises from', 'East', 'West', 'North', 'South', 'B'),
(4, 2, 'The land of Rising Sun', 'Norway', 'Germany', 'New Delhi', 'Paris', 'A'),
(5, 7, ' Who invented Compact Disc?', 'James Gosling', 'James T Russel', 'H John', 'John Miller', 'B'),
(6, 7, 'Which day is celebrated as world Computer Literacy Day', 'December 2', 'May 1', 'July 20', 'October 2', 'A'),
(7, 7, 'Longhorn was the code name of', 'Windows 7', 'Windows 2000', 'Windows Vista', 'Windows 10', 'C'),
(8, 7, 'Who is known as the Human Computer of India', 'Dr Abha Kumari', 'Dr Meera Kumari', 'Lady Ada', 'Shakunthala Devi', 'D'),
(9, 7, ' \'Do no evil\' is tag line of', 'Google', 'Yahoo', 'LinkedIn', 'Bing', 'A'),
(10, 7, 'Which IT company\'s nickname is \' The Big Blue \'', 'IBM', 'Google', 'Infosys', 'TCS', 'A'),
(11, 7, 'What is Scareware?', 'Antivirus', 'Fake antivirus softwares', 'Lottery', 'Fake Website', 'B'),
(12, 7, 'What is IMEI', 'International Mobile Equipment Identity.', 'ksajdf', 'ksdj', 'kjdskfl', 'A'),
(13, 7, 'What is NIC?', 'fsdf', ' Network Interface Card', 'dddd', 'dddd', 'B'),
(14, 7, 'GPS was developed by', 'US Army', 'Indian Army', 'Russian Army', 'Turkey', 'A'),
(15, 7, 'Expand SUN in sun Micrsystem.', 'a', 'a', 'Stanford University Network', 'z', 'C'),
(16, 7, 'What is Blue Brain project?', 'a', 'a', 'a', 'Cloning of human brain.', 'D'),
(17, 7, 'What is three finger salutes?', 'a', 'Pressing Ctrl + Alt + Del', 'a', 'a', 'B'),
(18, 7, 'MIPS is?', 'million instructions per second', 'a', 'a', 'a', 'A'),
(19, 7, 'Speed of CPU in the case of Minicomputer is?', '20-50 MIPS.', 'a', 'a', 'a', 'A'),
(20, 7, 'Main memory has 3 distinct parts. What are they?', 'a', 'a', 'a', 'Main memory has 3 distinct parts. What are they?', 'D'),
(21, 7, 'Which beam is commonly used for optical data storage?', 'Laser', 'a', 'a', 'a', 'A'),
(22, 7, 'ISDN stands for?', 'a', 'a', 'Integrated Services Digital Networks.', 'a', 'C'),
(23, 7, 'What is the main disadvantage of PAGER?', 'a', 'It is only a one way communication system.', 'a', 'a', 'B'),
(24, 7, 'Who is considered as the father of industrial robot?', 'a', 'a', 'Joseph Engelberger.', 'a', 'C'),
(25, 7, 'What are the three major components of a robot?', 'Manipulator, Brain, Power supply.', 'q', 'q', 'q', 'A'),
(26, 7, 'Give one example for a computer programming language that can be used for robot programming?', 'AML (A Manufacturing Language)', 'r', 'r', 'r', 'A'),
(27, 7, 'Who is the father of Artificial Intelligence?', 'f', 'f', 'f', 'Allen Turing.', 'D'),
(28, 7, 'What is Finger?', 'g', 'Software that allows the user to enter the address of an internet site to find information about that system users or a particular user.', 'g', 'g', 'B'),
(29, 7, 'Computer Hard Disk was first introduced in 1956  by', 'Dell', 'Apple', 'Microsoft', 'IBM', 'D'),
(30, 7, 'Who is also known as the father of Indian Supercomputing ', 'Raghunath Mashelkar', 'Vijay Bhatkar', 'jayant Narlikar', 'Nandan Nilekani', 'B'),
(31, 2, 'Which of the following rivers does not flow into the Arabian Sea?', 'Tungabhadra', 'Sabarmati', 'Mandovi', 'Narmada', 'A'),
(32, 2, 'Which of the following is the highest peak of Satpura Range?', 'Gurushikhar', 'Dhupgarh', 'Pachmarhi', 'Mahendragiri', 'B'),
(33, 2, 'The Thummalapalle mine which is considered to have one of the world’s largest reserves of 1.50 lakh tonnes of uranium is located in which among the fo', 'Karnataka', 'Andhra Pradesh', 'Tamil Nadu', 'Kerala', 'B'),
(34, 2, 'The land frontier of India is about 15200 KM. Which of the following countries shares the largest border length with India:', 'Bangladesh', 'China', 'Nepal', 'Pakistan', 'A'),
(35, 2, 'The lacustrine deposits of Kashmir called ‘Karewas’ are known for', 'Saffron Cultivation', 'Terrace farming', 'Apple Orchards', 'Jhum Cultivation', 'A'),
(36, 2, 'Which of the following mountain ranges form a dividing line between the Ganges Plain and the Deccan Plateau?', 'Aravalli', 'Vindhya', 'Satpura', 'Ajanta', 'B'),
(37, 2, 'The Andaman and Nicobar Islands are submerged parts of mountain range called:', 'Arakan Yoma', 'Pegu Yoma', 'Askai Chin', 'Tien Shan', 'A'),
(38, 2, 'Which of the following Indian States/UT has the maximum percentage of mangrove cover in the country ?', 'Gujarat', 'West Bengal', 'Andaman and Nicobar', 'Orissa', 'B'),
(39, 2, 'In 2011, ONGC discovered India’s first shale gas reserve in which among the following states?', 'Assam', 'Gujarat', 'West Bengal', 'Maharastra', 'C'),
(40, 2, 'From which of the following countries India does NOT import Uranium?', 'Kazakhstan', 'Namibia', 'Brazil', 'Mongolia', 'C'),
(41, 2, 'Which among the following was the first Indian product to have got Protected Geographic Indicator?', 'Indian Rubber', 'Basmati Rice', 'Malabar Coffee', 'Darjeeling tea', 'D'),
(42, 2, 'At which among the following places, Brahamputra takes a U-turn at the time of entering into India?', 'Kula Kangri', 'Lunpo Gangri', 'Namcha Barwa', 'Noijin Kangsang', 'C'),
(43, 2, 'Which among the following states leads in Handloom and Handicrafts clusters in India?', 'Uttar Pradesh', 'Maharastra', 'Odisha', 'West Bengal', 'A'),
(44, 2, 'The pass located at the southern end of the Nilgiri Hills in south India is called', 'the Palghat gap', 'The Bhorghat pass', 'the Thalgat pass', 'the Bolan pass', 'A'),
(45, 2, 'Rama’s Bridge or Rama Setu is located in which among the following straits?', 'Bering Strait', 'Palk Strait', 'Cook strait', 'Strait of Tebrau', 'B');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `QID` int(11) NOT NULL,
  `SubID` int(11) NOT NULL,
  `Question` longtext NOT NULL,
  `Option1` longtext NOT NULL,
  `Option2` longtext NOT NULL,
  `Option3` longtext NOT NULL,
  `Option4` longtext NOT NULL,
  `CorrectAns` varchar(1) NOT NULL,
  `UserAns` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`QID`, `SubID`, `Question`, `Option1`, `Option2`, `Option3`, `Option4`, `CorrectAns`, `UserAns`) VALUES
(2, 2, 'Which place receives highest rainfall in India?', 'Jhansi', 'Pune', 'Cherrapunji', 'Allahabad', 'C', NULL),
(4, 2, 'The land of Rising Sun', 'Norway', 'Germany', 'New Delhi', 'Paris', 'A', NULL),
(31, 2, 'Which of the following rivers does not flow into the Arabian Sea?', 'Tungabhadra', 'Sabarmati', 'Mandovi', 'Narmada', 'A', NULL),
(32, 2, 'Which of the following is the highest peak of Satpura Range?', 'Gurushikhar', 'Dhupgarh', 'Pachmarhi', 'Mahendragiri', 'B', NULL),
(33, 2, 'The Thummalapalle mine which is considered to have one of the world’s largest reserves of 1.50 lakh tonnes of uranium is located in which among the fo', 'Karnataka', 'Andhra Pradesh', 'Tamil Nadu', 'Kerala', 'B', NULL),
(34, 2, 'The land frontier of India is about 15200 KM. Which of the following countries shares the largest border length with India:', 'Bangladesh', 'China', 'Nepal', 'Pakistan', 'A', NULL),
(38, 2, 'Which of the following Indian States/UT has the maximum percentage of mangrove cover in the country ?', 'Gujarat', 'West Bengal', 'Andaman and Nicobar', 'Orissa', 'B', NULL),
(40, 2, 'From which of the following countries India does NOT import Uranium?', 'Kazakhstan', 'Namibia', 'Brazil', 'Mongolia', 'C', NULL),
(42, 2, 'At which among the following places, Brahamputra takes a U-turn at the time of entering into India?', 'Kula Kangri', 'Lunpo Gangri', 'Namcha Barwa', 'Noijin Kangsang', 'C', NULL),
(45, 2, 'Rama’s Bridge or Rama Setu is located in which among the following straits?', 'Bering Strait', 'Palk Strait', 'Cook strait', 'Strait of Tebrau', 'B', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE `results` (
  `StID` varchar(15) NOT NULL,
  `Stream` varchar(15) NOT NULL,
  `Semester` int(11) NOT NULL,
  `SubID` int(11) NOT NULL,
  `QID` int(11) NOT NULL,
  `CorrectAns` varchar(1) NOT NULL,
  `UserAns` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `results`
--

INSERT INTO `results` (`StID`, `Stream`, `Semester`, `SubID`, `QID`, `CorrectAns`, `UserAns`) VALUES
('1122', 'B Tech (CS)', 3, 2, 32, 'B', 'C'),
('1122', 'B Tech (CS)', 3, 2, 33, 'B', NULL),
('1122', 'B Tech (CS)', 3, 2, 34, 'A', 'A'),
('1122', 'B Tech (CS)', 3, 2, 35, 'A', 'C'),
('1122', 'B Tech (CS)', 3, 2, 36, 'B', 'B'),
('1122', 'B Tech (CS)', 3, 2, 38, 'B', 'B'),
('1122', 'B Tech (CS)', 3, 2, 39, 'C', 'D'),
('1122', 'B Tech (CS)', 3, 2, 40, 'C', 'C'),
('1122', 'B Tech (CS)', 3, 2, 41, 'D', 'D'),
('1122', 'B Tech (CS)', 3, 2, 45, 'B', 'A'),
('1122', 'B Tech (CS)', 3, 7, 6, 'A', 'A'),
('1122', 'B Tech (CS)', 3, 7, 7, 'C', 'C'),
('1122', 'B Tech (CS)', 3, 7, 8, 'D', 'D'),
('1122', 'B Tech (CS)', 3, 7, 11, 'B', NULL),
('1122', 'B Tech (CS)', 3, 7, 12, 'A', NULL),
('1122', 'B Tech (CS)', 3, 7, 14, 'A', 'A'),
('1122', 'B Tech (CS)', 3, 7, 20, 'D', 'A'),
('1122', 'B Tech (CS)', 3, 7, 26, 'A', NULL),
('1122', 'B Tech (CS)', 3, 7, 28, 'B', 'B'),
('1122', 'B Tech (CS)', 3, 7, 29, 'D', NULL),
('1133', 'MCA', 2, 7, 6, 'A', 'A'),
('1133', 'MCA', 2, 7, 10, 'A', 'A'),
('1133', 'MCA', 2, 7, 12, 'A', 'A'),
('1133', 'MCA', 2, 7, 14, 'A', 'A'),
('1133', 'MCA', 2, 7, 16, 'D', 'D'),
('1133', 'MCA', 2, 7, 19, 'A', 'A'),
('1133', 'MCA', 2, 7, 21, 'A', 'A'),
('1133', 'MCA', 2, 7, 22, 'C', 'C'),
('1133', 'MCA', 2, 7, 26, 'A', 'A'),
('1133', 'MCA', 2, 7, 29, 'D', 'D'),
('25456', 'B Tech', 2, 7, 1, 'A', NULL),
('25456', 'B Tech', 2, 7, 2, 'A', NULL),
('54212554', 'MCA', 3, 7, 6, 'A', 'A'),
('54212554', 'MCA', 3, 7, 9, 'A', 'A'),
('54212554', 'MCA', 3, 7, 12, 'A', 'B'),
('54212554', 'MCA', 3, 7, 16, 'D', 'D'),
('54212554', 'MCA', 3, 7, 17, 'B', NULL),
('54212554', 'MCA', 3, 7, 18, 'A', NULL),
('54212554', 'MCA', 3, 7, 19, 'A', NULL),
('54212554', 'MCA', 3, 7, 21, 'A', NULL),
('54212554', 'MCA', 3, 7, 29, 'D', NULL),
('54212554', 'MCA', 3, 7, 30, 'B', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `StID` varchar(15) NOT NULL,
  `StName` varchar(45) DEFAULT NULL,
  `Gender` varchar(6) DEFAULT NULL,
  `StMobile` varchar(10) DEFAULT NULL,
  `StEmail` varchar(45) DEFAULT NULL,
  `StStream` varchar(45) DEFAULT NULL,
  `Semester` int(11) DEFAULT NULL,
  `StImage` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`StID`, `StName`, `Gender`, `StMobile`, `StEmail`, `StStream`, `Semester`, `StImage`) VALUES
('1122', 'AYUSH', 'Male', '6545645648', 'ab@gmail.com', 'B Tech (CS)', 3, ''),
('1133', 'Anuj', 'Male', '9696857485', 'anuj@hotmail.com', 'MCA', 2, ''),
('54212554', 'ANUPAM CHAURASIA', 'Male', '9415518767', 'anu@hotmail.com', 'MCA', 3, '');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `subid` int(11) NOT NULL,
  `Subject` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`subid`, `Subject`) VALUES
(1, 'HISTORY'),
(2, 'GEOGRAPHY'),
(3, 'HISTORY & CIVICS'),
(4, 'HISTORY AND GEOGRAPHY'),
(5, 'CIVICS AND GEOGRAPHY'),
(6, 'HISTORY AND CIVICS'),
(7, 'COMPUTERS'),
(8, 'ARTIFICAL INTELLIGENCE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `questionbank`
--
ALTER TABLE `questionbank`
  ADD PRIMARY KEY (`QID`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`QID`);

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`StID`,`Semester`,`SubID`,`Stream`,`QID`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`StID`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`subid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
