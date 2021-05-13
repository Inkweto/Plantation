CREATE TABLE `Users` (
  `id` INT(11) AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password_hash` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Plant` (
  `plant_id` INT(11) AUTO_INCREMENT,
  `user_owner_id` INT(11),
  `name` VARCHAR(20) NOT NULL,
  `heigth` INT(3),
  `diameter` INT(3),
  `photo_path` VARCHAR(20),
  PRIMARY KEY (`plant_id`),
  FOREIGN KEY (`user_owner_id`)
    REFERENCES `Users`(`id`)
);

CREATE TABLE `Notice` (
  `notice_id` INT(11) AUTO_INCREMENT,
  `user_id` INT(11),
  `plant_id` INT(11),
  `notice_text` TEXT,
  PRIMARY KEY (`notice_id`),
  FOREIGN KEY (`user_id`)
    REFERENCES `Users`(`id`),
  FOREIGN KEY (`plant_id`)
    REFERENCES `Plant`(`plant_id`),
);

CREATE TABLE `TemplatePlant` (
  `template_plant_id` INT(11) AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `height` INT(3),
  `diameter` INT(3),
  `photo_path` VARCHAR(20),
  PRIMARY KEY (`template_plant_id`)
);

