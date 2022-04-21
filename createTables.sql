CREATE TABLE `ride_tracker`.`ride` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `duration` INT NOT NULL,
  `ride_date` DATETIME,
  PRIMARY KEY (`id`));
