-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`users` ;

CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(30) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`channels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`channels` ;

CREATE TABLE IF NOT EXISTS `mydb`.`channels` (
  `channel_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`channel_id`),
  INDEX `fk_channels_users_idx` (`user_id` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `fk_channels_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`videos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`videos` ;

CREATE TABLE IF NOT EXISTS `mydb`.`videos` (
  `video_id` INT NOT NULL AUTO_INCREMENT,
  `channel_id` INT NOT NULL,
  `url` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `date` DATETIME NOT NULL,
  `description` VARCHAR(200) NULL,
  `views` INT NULL,
  `likes` INT NULL,
  `dislikes` INT NULL,
  PRIMARY KEY (`video_id`),
  UNIQUE INDEX `url_UNIQUE` (`url` ASC),
  INDEX `fk_videos_channels1_idx` (`channel_id` ASC),
  CONSTRAINT `fk_videos_channels1`
    FOREIGN KEY (`channel_id`)
    REFERENCES `mydb`.`channels` (`channel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`playlists`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`playlists` ;

CREATE TABLE IF NOT EXISTS `mydb`.`playlists` (
  `playlist_id` INT NOT NULL AUTO_INCREMENT,
  `channel_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `create_date` DATETIME NOT NULL,
  `last_video_add_date` DATETIME NULL,
  PRIMARY KEY (`playlist_id`),
  INDEX `fk_playlists_channels1_idx` (`channel_id` ASC),
  CONSTRAINT `fk_playlists_channels1`
    FOREIGN KEY (`channel_id`)
    REFERENCES `mydb`.`channels` (`channel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`comments` ;

CREATE TABLE IF NOT EXISTS `mydb`.`comments` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `video_id` INT NOT NULL,
  `channel_id` INT NOT NULL,
  `response_to_comment_id` INT NULL,
  `content` VARCHAR(45) NOT NULL,
  `likes` INT NULL,
  `dislikes` INT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comments_videos1_idx` (`video_id` ASC),
  INDEX `fk_comments_channels1_idx` (`channel_id` ASC),
  INDEX `fk_comments_comments1_idx` (`response_to_comment_id` ASC),
  CONSTRAINT `fk_comments_videos1`
    FOREIGN KEY (`video_id`)
    REFERENCES `mydb`.`videos` (`video_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_channels1`
    FOREIGN KEY (`channel_id`)
    REFERENCES `mydb`.`channels` (`channel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_comments1`
    FOREIGN KEY (`response_to_comment_id`)
    REFERENCES `mydb`.`comments` (`comment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`playlists_has_videos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`playlists_has_videos` ;

CREATE TABLE IF NOT EXISTS `mydb`.`playlists_has_videos` (
  `video_id` INT NOT NULL,
  `playlist_id` INT NOT NULL,
  PRIMARY KEY (`video_id`, `playlist_id`),
  INDEX `fk_videos_has_playlists_playlists1_idx` (`playlist_id` ASC),
  INDEX `fk_videos_has_playlists_videos1_idx` (`video_id` ASC),
  CONSTRAINT `fk_videos_has_playlists_videos1`
    FOREIGN KEY (`video_id`)
    REFERENCES `mydb`.`videos` (`video_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_videos_has_playlists_playlists1`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `mydb`.`playlists` (`playlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`channels_followed_channels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`channels_followed_channels` ;

CREATE TABLE IF NOT EXISTS `mydb`.`channels_followed_channels` (
  `follower_channel_id` INT NOT NULL,
  `followed_channel_id` INT NOT NULL,
  PRIMARY KEY (`follower_channel_id`, `followed_channel_id`),
  INDEX `fk_channels_has_channels_channels2_idx` (`followed_channel_id` ASC),
  INDEX `fk_channels_has_channels_channels1_idx` (`follower_channel_id` ASC),
  CONSTRAINT `fk_channels_has_channels_channels1`
    FOREIGN KEY (`follower_channel_id`)
    REFERENCES `mydb`.`channels` (`channel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_channels_has_channels_channels2`
    FOREIGN KEY (`followed_channel_id`)
    REFERENCES `mydb`.`channels` (`channel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tags` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tags` (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE INDEX `content_UNIQUE` (`content` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`videos_has_tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`videos_has_tags` ;

CREATE TABLE IF NOT EXISTS `mydb`.`videos_has_tags` (
  `video_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`video_id`, `tag_id`),
  INDEX `fk_videos_has_tags_tags1_idx` (`tag_id` ASC),
  INDEX `fk_videos_has_tags_videos1_idx` (`video_id` ASC),
  CONSTRAINT `fk_videos_has_tags_videos1`
    FOREIGN KEY (`video_id`)
    REFERENCES `mydb`.`videos` (`video_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_videos_has_tags_tags1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `mydb`.`tags` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
