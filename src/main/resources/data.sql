CREATE TABLE `Users` (
  `id` INT(11) AUTO_INCREMENT  PRIMARY KEY,
  `login` VARCHAR(20) NOT NULL,
  `password_hash` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `Plant` (
  `plant_id` INT(11) AUTO_INCREMENT  PRIMARY KEY,
  `user_owner_id` INT(11),
  `name` VARCHAR(20) NOT NULL,
  `heigth` INT(3),
  `diameter` INT(3),
  `photo_path` VARCHAR(20),
  PRIMARY KEY (`plant_id`)
);
