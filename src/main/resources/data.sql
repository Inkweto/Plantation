CREATE TABLE IF NOT EXISTS `users` (
  `id` INT(11) AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `passwordHash` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `plant` (
  `plantId` INT(11) AUTO_INCREMENT,
  `ownerId` INT(11),
  `name` VARCHAR(20) NOT NULL,
  `heigth` INT(3),
  `diameter` INT(3),
  `photoPath` VARCHAR(20),
  PRIMARY KEY (`plantId`),
  FOREIGN KEY (`ownerId`)
    REFERENCES `Users`(`id`)
);

CREATE TABLE IF NOT EXISTS `notice` (
  `noticeId` INT(11) AUTO_INCREMENT,
  `userId` INT(11),
  `plantId` INT(11),
  `noticeText` TEXT,
  PRIMARY KEY (`noticeId`),
  FOREIGN KEY (`userId`)
    REFERENCES `Users`(`id`),
  FOREIGN KEY (`plantId`)
    REFERENCES `Plant`(`plantId`),
);

CREATE TABLE IF NOT EXISTS `templatePlant` (
  `templatePlantId` INT(11) AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `height` INT(3),
  `diameter` INT(3),
  `photoPath` VARCHAR(20),
  PRIMARY KEY (`templatePlantId`)
);