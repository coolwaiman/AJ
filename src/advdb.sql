-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 22, 2016 at 05:55 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.5.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `advdb`
--
CREATE DATABASE IF NOT EXISTS `advdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `advdb`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `AccountId` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Passwd` varchar(255) NOT NULL,
  `Balance` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CategoryId` int(11) NOT NULL,
  `CategoryName` varchar(255) NOT NULL,
  `CategoryDescription` varchar(255) DEFAULT NULL,
  `ParentCategory` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cusorder`
--

CREATE TABLE `cusorder` (
  `OrderId` int(11) NOT NULL,
  `StaffId` int(11) NOT NULL,
  `CusId` int(11) NOT NULL,
  `OrderDiscount` double DEFAULT '1',
  `IsOrderDelivery` tinyint(1) DEFAULT '0',
  `DeliveryAddress` varchar(255) DEFAULT NULL,
  `DeliveryDate` date DEFAULT NULL,
  `OrderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `OrderPrePaid` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CusId` int(11) NOT NULL,
  `CusName` varchar(255) NOT NULL,
  `CusAddress` varchar(255) DEFAULT NULL,
  `CusPhone` varchar(30) DEFAULT NULL,
  `CusEmail` varchar(255) NOT NULL,
  `CusAccountId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `orderline`
--

CREATE TABLE `orderline` (
  `OrderId` int(11) NOT NULL,
  `ProductSn` int(11) NOT NULL,
  `ProductPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ProductId` int(11) NOT NULL,
  `ProductName` varchar(255) NOT NULL,
  `ProductDescription` varchar(255) DEFAULT NULL,
  `ProductPrice` double NOT NULL DEFAULT '0',
  `CategoryId` int(11) NOT NULL,
  `ProviderId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `productrepairwork`
--

CREATE TABLE `productrepairwork` (
  `RepairWorkId` int(11) NOT NULL,
  `OrderId` int(11) NOT NULL,
  `ProductSn` int(11) NOT NULL,
  `CreatedStaff` int(11) NOT NULL,
  `AssignedTechnician` int(11) DEFAULT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SolvedDate` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `provider`
--

CREATE TABLE `provider` (
  `ProviderId` int(11) NOT NULL,
  `ProdviderName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `StaffId` int(11) NOT NULL,
  `StaffName` varchar(80) NOT NULL,
  `StaffPhone` varchar(30) DEFAULT NULL,
  `StaffAccountId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

CREATE TABLE `store` (
  `StoreId` int(11) NOT NULL,
  `StoreName` varchar(255) NOT NULL,
  `StoreLocation` varchar(255) NOT NULL,
  `StoreContact` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `storeproduct`
--

CREATE TABLE `storeproduct` (
  `ProductSn` int(11) NOT NULL,
  `StoreId` int(11) NOT NULL,
  `ProductId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `storestockwarning`
--

CREATE TABLE `storestockwarning` (
  `StoreId` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `WarningLevel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `technician`
--

CREATE TABLE `technician` (
  `TechnicianId` int(11) NOT NULL,
  `TechnicianName` varchar(255) NOT NULL,
  `TechnicianContact` varchar(30) NOT NULL,
  `AccountId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`AccountId`),
  ADD UNIQUE KEY `account_AccountId_uindex` (`AccountId`),
  ADD UNIQUE KEY `account_Username_uindex` (`Username`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryId`),
  ADD UNIQUE KEY `Category_CategoryId_uindex` (`CategoryId`),
  ADD KEY `category_category_CategoryId_fk` (`ParentCategory`);

--
-- Indexes for table `cusorder`
--
ALTER TABLE `cusorder`
  ADD PRIMARY KEY (`OrderId`),
  ADD UNIQUE KEY `CusOrder_OrderId_uindex` (`OrderId`),
  ADD KEY `CusOrder_staff_StaffId_fk` (`StaffId`),
  ADD KEY `CusOrder_customer_CusId_fk` (`CusId`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CusId`),
  ADD UNIQUE KEY `Customer_CusId_uindex` (`CusId`),
  ADD KEY `customer_account_AccountId_fk` (`CusAccountId`);

--
-- Indexes for table `orderline`
--
ALTER TABLE `orderline`
  ADD PRIMARY KEY (`OrderId`,`ProductSn`),
  ADD KEY `orderline_storeproduct_ProductSn_fk` (`ProductSn`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductId`),
  ADD UNIQUE KEY `Product_ProductId_uindex` (`ProductId`),
  ADD KEY `product_category_CategoryId_fk` (`CategoryId`),
  ADD KEY `product_provider_ProviderId_fk` (`ProviderId`);

--
-- Indexes for table `productrepairwork`
--
ALTER TABLE `productrepairwork`
  ADD PRIMARY KEY (`RepairWorkId`),
  ADD UNIQUE KEY `productrepairwork_RepairWorkId_uindex` (`RepairWorkId`),
  ADD KEY `productrepairwork_storeproduct_ProductSn_fk` (`ProductSn`),
  ADD KEY `productrepairwork_staff_StaffId_fk` (`CreatedStaff`),
  ADD KEY `productrepairwork_technician_TechnicianId_fk` (`AssignedTechnician`);

--
-- Indexes for table `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`ProviderId`),
  ADD UNIQUE KEY `provider_ProviderId_uindex` (`ProviderId`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`StaffId`),
  ADD UNIQUE KEY `staff_StaffId_uindex` (`StaffId`),
  ADD KEY `staff_account_AccountId_fk` (`StaffAccountId`);

--
-- Indexes for table `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`StoreId`),
  ADD UNIQUE KEY `store_StoreId_uindex` (`StoreId`);

--
-- Indexes for table `storeproduct`
--
ALTER TABLE `storeproduct`
  ADD PRIMARY KEY (`ProductSn`),
  ADD UNIQUE KEY `StoreProduct_ProductSn_uindex` (`ProductSn`),
  ADD KEY `StoreProduct_store_StoreId_fk` (`StoreId`);

--
-- Indexes for table `storestockwarning`
--
ALTER TABLE `storestockwarning`
  ADD PRIMARY KEY (`StoreId`,`ProductId`),
  ADD KEY `StoreStockWarning_product_ProductId_fk` (`ProductId`);

--
-- Indexes for table `technician`
--
ALTER TABLE `technician`
  ADD PRIMARY KEY (`TechnicianId`),
  ADD UNIQUE KEY `Technician_TechnicianId_uindex` (`TechnicianId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `AccountId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `CategoryId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `cusorder`
--
ALTER TABLE `cusorder`
  MODIFY `OrderId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `CusId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ProductId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `productrepairwork`
--
ALTER TABLE `productrepairwork`
  MODIFY `RepairWorkId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `provider`
--
ALTER TABLE `provider`
  MODIFY `ProviderId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `StaffId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `store`
--
ALTER TABLE `store`
  MODIFY `StoreId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `storeproduct`
--
ALTER TABLE `storeproduct`
  MODIFY `ProductSn` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `technician`
--
ALTER TABLE `technician`
  MODIFY `TechnicianId` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `category_category_CategoryId_fk` FOREIGN KEY (`ParentCategory`) REFERENCES `category` (`CategoryId`);

--
-- Constraints for table `cusorder`
--
ALTER TABLE `cusorder`
  ADD CONSTRAINT `CusOrder_customer_CusId_fk` FOREIGN KEY (`CusId`) REFERENCES `customer` (`CusId`),
  ADD CONSTRAINT `CusOrder_staff_StaffId_fk` FOREIGN KEY (`StaffId`) REFERENCES `staff` (`StaffId`);

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_account_AccountId_fk` FOREIGN KEY (`CusAccountId`) REFERENCES `account` (`AccountId`);

--
-- Constraints for table `orderline`
--
ALTER TABLE `orderline`
  ADD CONSTRAINT `orderline_cusorder_OrderId_fk` FOREIGN KEY (`OrderId`) REFERENCES `cusorder` (`OrderId`),
  ADD CONSTRAINT `orderline_storeproduct_ProductSn_fk` FOREIGN KEY (`ProductSn`) REFERENCES `storeproduct` (`ProductSn`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_category_CategoryId_fk` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`),
  ADD CONSTRAINT `product_provider_ProviderId_fk` FOREIGN KEY (`ProviderId`) REFERENCES `provider` (`ProviderId`);

--
-- Constraints for table `productrepairwork`
--
ALTER TABLE `productrepairwork`
  ADD CONSTRAINT `productrepairwork_staff_StaffId_fk` FOREIGN KEY (`CreatedStaff`) REFERENCES `staff` (`StaffId`),
  ADD CONSTRAINT `productrepairwork_storeproduct_ProductSn_fk` FOREIGN KEY (`ProductSn`) REFERENCES `storeproduct` (`ProductSn`),
  ADD CONSTRAINT `productrepairwork_technician_TechnicianId_fk` FOREIGN KEY (`AssignedTechnician`) REFERENCES `technician` (`TechnicianId`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_account_AccountId_fk` FOREIGN KEY (`StaffAccountId`) REFERENCES `account` (`AccountId`);

--
-- Constraints for table `storeproduct`
--
ALTER TABLE `storeproduct`
  ADD CONSTRAINT `StoreProduct_product_ProductId_fk` FOREIGN KEY (`ProductSn`) REFERENCES `product` (`ProductId`),
  ADD CONSTRAINT `StoreProduct_store_StoreId_fk` FOREIGN KEY (`StoreId`) REFERENCES `store` (`StoreId`);

--
-- Constraints for table `storestockwarning`
--
ALTER TABLE `storestockwarning`
  ADD CONSTRAINT `StoreStockWarning_product_ProductId_fk` FOREIGN KEY (`ProductId`) REFERENCES `product` (`ProductId`),
  ADD CONSTRAINT `StoreStockWarning_store_StoreId_fk` FOREIGN KEY (`StoreId`) REFERENCES `store` (`StoreId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
