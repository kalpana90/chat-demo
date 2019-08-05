create database chat_demo;

use chat_demo;

CREATE TABLE `chat_demo`.`user` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `mobile_number` VARCHAR(45) NOT NULL,
  `session_id` VARCHAR(100) NOT NULL,
  `created_date` DATETIME NULL,
  `modified_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `mobile_number_UNIQUE` (`mobile_number` ASC),
  UNIQUE INDEX `session_id_UNIQUE` (`session_id` ASC));

CREATE TABLE `chat_demo`.`user_message` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NOT NULL,
  `message` VARCHAR(200) NULL,
  `sender_id` BIGINT(10) NOT NULL,
  `created_date` DATETIME NULL,
  `modified_date` DATETIME NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `chat_demo`.`user_message`
ADD INDEX `user_message_fk_user_id_idx` (`user_id` ASC);

ALTER TABLE `chat_demo`.`user_message`
ADD CONSTRAINT `user_message_fk_user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `chat_demo`.`user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `chat_demo`.`user_password` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NOT NULL,
  `password` VARCHAR(100) NULL,
  `created_date` DATETIME NULL,
  `modified_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `user_password_fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `chat_demo`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `chat_demo`.`user` (`id`, `name`, `mobile_number`,`session_id`, `created_date`, `modified_date`) VALUES ('1', 'Priit', '032156756','SDFG456GH67GHJ', NOW(), NOW());
INSERT INTO `chat_demo`.`user` (`id`, `name`, `mobile_number`, `session_id`,`created_date`, `modified_date`) VALUES ('2', 'Maksim', '0321543422', 'BNKHJG56FCW32WSZDX', NOW(), NOW());
INSERT INTO `chat_demo`.`user` (`id`, `name`, `mobile_number`,`session_id`, `created_date`, `modified_date`) VALUES ('3', 'Ann', '0717533452', 'BXC98765RTYGHJTR654', NOW(), NOW());
INSERT INTO `chat_demo`.`user` (`id`, `name`, `mobile_number`, `session_id`,`created_date`, `modified_date`) VALUES ('4', 'James', '0786353673', 'HU8765EDGHU7Y654', NOW(), NOW());




INSERT INTO `chat_demo`.`user_password` (`id`, `user_id`, `password`, `created_date`, `modified_date`) VALUES ('1', '1', '$2a$10$hAP6obf0wADk5Zssd2RWvuGRYkQPjKGYf9IAIdX6eHoIh8FN8Db9.', NOW(), NOW());
INSERT INTO `chat_demo`.`user_password` (`id`, `user_id`, `password`, `created_date`, `modified_date`) VALUES ('2', '2', '$2a$10$8v4VN2MYUQpUCFJJ1amYGuRk.eWByW0mdeciFybe2t0VZ4JSuPwIe', NOW(), NOW());
INSERT INTO `chat_demo`.`user_password` (`id`, `user_id`, `password`, `created_date`, `modified_date`) VALUES ('3', '3', '$2a$10$XfNzl6I91okzZtB9PW52.e3eux6x7QZOPd/NesUf.Mkm0/NJqpwmK', NOW(), NOW());
INSERT INTO `chat_demo`.`user_password` (`id`, `user_id`, `password`, `created_date`, `modified_date`) VALUES ('4', '4', '$2a$10$C3.u710IckML4hUpn0ksj.PkkQD16e66Zj/TjzybQSHLXcuBRtQ7W', NOW(), NOW());


INSERT INTO `chat_demo`.`user_message` (`id`, `user_id`, `message`,`sender_id`, `created_date`, `modified_date`) VALUES ('1', '1', 'Hi, Priit. How are you','2', NOW(), NOW());
INSERT INTO `chat_demo`.`user_message` (`id`, `user_id`, `message`, `sender_id`,`created_date`, `modified_date`) VALUES ('2', '2', 'Hey, I am on my way.', '1', NOW(), NOW());
INSERT INTO `chat_demo`.`user_message` (`id`, `user_id`, `message`, `sender_id`,`created_date`, `modified_date`) VALUES ('3', '1', 'I forgot my mobile.','3', NOW(), NOW());
INSERT INTO `chat_demo`.`user_message` (`id`, `user_id`, `message`, `sender_id`,`created_date`, `modified_date`) VALUES ('4', '2', 'You are late !!','4', NOW(), NOW());


ALTER TABLE `chat_demo`.`user_message`
ADD INDEX `user_message_fk_sender_id_idx` (`sender_id` ASC);

ALTER TABLE `chat_demo`.`user_message`
ADD CONSTRAINT `user_message_fk_sender_id`
  FOREIGN KEY (`sender_id`)
  REFERENCES `chat_demo`.`user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;







