CREATE TABLE `Users` (
  `id` INT(11 AUTO_INCREMENT  PRIMARY KEY,,
  `login` VARCHAR(20) NOT NULL,
  `password_hash` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`)
);

INSERT INTO `Users` (`login`, `password_hash`) VALUES
  ('Aliko', 'Dangote'),
  ('Bill', 'Gates',,
  ('Folrunsho', 'Alakija');