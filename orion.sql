-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 31, 2019 at 08:46 PM
-- Server version: 5.7.23
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `orion`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `name` varchar(40) NOT NULL,
  `customer_Number` int(200) NOT NULL,
  `payment` decimal(10,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`name`, `customer_Number`, `payment`) VALUES
('Electronics Unlimited', 3176, '0.00'),
('Sam’s Small Appliance', 3175, '0.00'),
('Sam’s Small Appliance', 3175, '0.00');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_Number` int(200) UNSIGNED NOT NULL,
  `name` varchar(40) NOT NULL,
  `address` varchar(40) NOT NULL,
  `city` varchar(30) NOT NULL,
  `province` varchar(5) NOT NULL,
  `zip` varchar(4) NOT NULL,
  `deposit` decimal(10,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_Number`, `name`, `address`, `city`, `province`, `zip`, `deposit`) VALUES
(3175, 'Sam\'s Small Appliance', '100 Main Street', 'Pretoria', 'ZA-GT', '0127', '2460.02'),
(3176, 'Electronics Unlimited ', '1175 Nelson Mandela Road', 'Pretoria', 'ZA-GT', '0002', '3900.00'),
(3182, 'test', 'add', 'city', 'pr', '7898', '7895.00');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `invoice_Number` int(200) UNSIGNED NOT NULL,
  `customer_Number` int(11) NOT NULL,
  `payment` decimal(10,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoice_Number`, `customer_Number`, `payment`) VALUES
(11731, 3175, '0.00'),
(11732, 3176, '249.50'),
(11733, 3175, '0.00');

-- --------------------------------------------------------

--
-- Table structure for table `lineitem`
--

CREATE TABLE `lineitem` (
  `invoice_Number` int(200) NOT NULL,
  `product_Code` varchar(7) NOT NULL,
  `quantity` int(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lineitem`
--

INSERT INTO `lineitem` (`invoice_Number`, `product_Code`, `quantity`) VALUES
(11731, '116-064', 3),
(11731, '257-535', 1),
(11731, '643-119', 2),
(11732, '116-064', 10),
(11733, '116-064', 2),
(11733, '643-119', 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_Code` varchar(7) NOT NULL,
  `description` varchar(40) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_Code`, `description`, `price`) VALUES
('116-064', 'Toaster', '24.95'),
('257-535', 'Hair dryer', '29.95'),
('643-119', 'Car vaccum', '19.99');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_Number`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoice_Number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_Number` int(200) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3183;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoice_Number` int(200) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11738;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
