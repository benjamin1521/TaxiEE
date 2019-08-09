CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `full_name_en` varchar(45) DEFAULT NULL,
  `full_name_ua` varchar(45) DEFAULT NULL,
  `money_spent` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `taxis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `driver_name` varchar(45) NOT NULL,
  `car_number` varchar(45) NOT NULL,
  `busy` tinyint(1) DEFAULT NULL,
  `location_street` varchar(45) DEFAULT NULL,
  `location_house` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `car_number_UNIQUE` (`car_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_taxi` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `start_street` varchar(45) DEFAULT NULL,
  `start_house` int(11) DEFAULT NULL,
  `end_street` varchar(45) DEFAULT NULL,
  `end_house` int(11) DEFAULT NULL,
  `cost` varchar(45) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL,
  `waiting_time` varchar(45) DEFAULT NULL,
  `driving_time` varchar(45) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_orders_users1_idx` (`id_user`),
  KEY `fk_orders_taxis1_idx` (`id_taxi`),
  CONSTRAINT `fk_orders_taxis1` FOREIGN KEY (`id_taxi`) REFERENCES `taxis` (`id`),
  CONSTRAINT `fk_orders_users1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `coupons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_order` int(11) DEFAULT NULL,
  `discount_percent` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_coupon_users1_idx` (`id_user`),
  KEY `fk_coupons_orders1_idx` (`id_order`),
  CONSTRAINT `fk_coupon_users1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_coupons_orders1` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `taxi_final`.`taxis`
(`id`,`type`,`driver_name`,`car_number`,`busy`,`location_street`,`location_house`)
VALUES
('1', 'Eco', 'Petro', '1a', '0', 'Bondarska', '1'),
('2', 'Comfort', 'Stepan', '2b', '0', 'Bondarska', '1');

INSERT INTO `taxi_final`.`users`
(`id`,`role`,`username`,`password`,`full_name_en`,`full_name_ua`,`money_spent`)
VALUES
('3', 'Client', 'c', 'q', 'Petro Petrov', 'Петро Петров', '5118'),
('4', 'Admin', 'a', 'q', 'Admin', 'Адмін', '0');
	
INSERT INTO `taxi_final`.`orders`
(`id`,`id_user`,`id_taxi`,`type`,`start_street`,`start_house`,`end_street`,`end_house`,`cost`,`distance`,`waiting_time`,`driving_time`,`order_date`,`status`)
VALUES
('13', '3', '1', 'Eco', 'Bondarska', '3', 'Bondarska', '1', '10', '2', '2.0', '2.0', '2019-08-09 19:47:49', 'Done'),
('14', '3', '1', 'Eco', 'Bondarska', '2', 'Bondarska', '1', '100', '1', '1.0', '1.0', '2019-08-09 20:16:16', 'Unavailable'),
('15', '3', '1', 'Eco', 'Gorkogo', '1', 'Bondarska', '1', '100', '1', '1.0', '1.0', '2019-08-09 20:16:59', 'Done'),
('16', '3', '1', 'Eco', 'Zamkova', '1', 'Bondarska', '1', '300', '3', '3.0', '3.0', '2019-08-09 20:17:07', 'Unavailable'),
('17', '3', '2', 'Comfort', 'Parkova', '1', 'Bondarska', '9', '4800', '16', '2.6666666666666665', '5.333333333333333', '2019-08-09 20:24:02', 'Done'),
('18', '3', '2', 'Comfort', 'Tverska', '1', 'Bondarska', '1', '1080', '4', '4.0', '1.3333333333333333', '2019-08-09 20:25:55', 'Active');

INSERT INTO `taxi_final`.`coupons`
(`id`,`id_user`,`id_order`,`discount_percent`)
VALUES
('3', '3', NULL, '0.31');
