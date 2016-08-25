CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`book_categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`book_categories` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `library`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`books` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(40) NOT NULL,
  `author` VARCHAR(20) NOT NULL,
  `description` VARCHAR(300) NOT NULL,
  `year` YEAR NOT NULL,
  `price` INT(11) NOT NULL,
  `isbn` VARCHAR(20) NOT NULL,
  `count` INT(11) UNSIGNED NOT NULL,
  `book_categories_id` INT(11) NOT NULL,
  `image_url` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  UNIQUE INDEX `isbn_UNIQUE` (`isbn` ASC),
  INDEX `fk_books_book_categories1_idx` (`book_categories_id` ASC),
  FULLTEXT INDEX `fulltext_search` (`title` ASC, `author` ASC, `description` ASC),
  CONSTRAINT `fk_books_book_categories1`
    FOREIGN KEY (`book_categories_id`)
    REFERENCES `library`.`book_categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `library`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `library`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(10) NOT NULL,
  `password` VARCHAR(10) NOT NULL,
  `name` VARCHAR(20) NULL DEFAULT NULL,
  `surname` VARCHAR(20) NULL DEFAULT NULL,
  `roles_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  INDEX `fk_users_roles_idx` (`roles_id` ASC),
  CONSTRAINT `fk_users_roles`
    FOREIGN KEY (`roles_id`)
    REFERENCES `library`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `library`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `books_id` INT(11) NOT NULL,
  `users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `books_id_users_id_UNIQUE` (`books_id` ASC, `users_id` ASC),
  INDEX `fk_orders_books1_idx` (`books_id` ASC),
  INDEX `fk_orders_users1_idx` (`users_id` ASC),  
  CONSTRAINT `fk_orders_books1`
    FOREIGN KEY (`books_id`)
    REFERENCES `library`.`books` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `library`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Adding default data in the database
-- -----------------------------------------------------
INSERT INTO `library`.`roles`(name) 
	VALUES('admin'), ('user');
INSERT INTO `library`.`users`(login, password, name, surname, roles_id) 
	VALUES('admin', 'admin', 'admin', 'admin', 1),
			('user', 'password', 'alex', 'alex', 2);
INSERT INTO `library`.`book_categories`(name) 
	VALUES('prog'), ('fiction'), ('child');
INSERT INTO `library`.`books`(title, author, description, year, price, isbn, book_categories_id, count) 
	VALUES('Java', 'Eckel', 'Cool', 2014, 60, '12-123-456-9', 1, 10),
			('C#', 'Richter', 'Not so cool as Java', 2016, 60, '12-123-987-5', 1, 5);