CREATE TABLE `accounts` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
