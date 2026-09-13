ALTER TABLE `applicants` 
DROP COLUMN `password`,
DROP COLUMN `username`,
ADD COLUMN `account_id` BIGINT NOT NULL AFTER `qualifications_and_experience`,
ADD INDEX `account_id_idx` (`account_id` ASC),
DROP INDEX `username_UNIQUE`;

ALTER TABLE `applicants` 
ADD CONSTRAINT `fk_applicants_account_id`
  FOREIGN KEY (`account_id`)
  REFERENCES `accounts` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `employers` 
DROP COLUMN `password`,
DROP COLUMN `username`,
ADD COLUMN `account_id` BIGINT NOT NULL AFTER `email`,
ADD INDEX `account_id_idx` (`account_id` ASC),
DROP INDEX `username_UNIQUE`;

ALTER TABLE `employers` 
ADD CONSTRAINT `fk_employers_account_id`
  FOREIGN KEY (`account_id`)
  REFERENCES `accounts` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;