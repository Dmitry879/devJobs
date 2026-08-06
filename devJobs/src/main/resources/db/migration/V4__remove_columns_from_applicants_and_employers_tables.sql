ALTER TABLE `dev_jobs`.`applicants` 
DROP COLUMN `password`,
DROP COLUMN `username`,
ADD COLUMN `account_id` BIGINT NOT NULL AFTER `qualifications_and_experience`,
ADD INDEX `account_id_idx` (`account_id` ASC),
DROP INDEX `username_UNIQUE`;

ALTER TABLE `dev_jobs`.`applicants` 
ADD CONSTRAINT `fk_applicants_account_id`
  FOREIGN KEY (`account_id`)
  REFERENCES `dev_jobs`.`accounts` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `dev_jobs`.`employers` 
DROP COLUMN `password`,
DROP COLUMN `username`,
ADD COLUMN `account_id` BIGINT NOT NULL AFTER `email`,
ADD INDEX `account_id_idx` (`account_id` ASC),
DROP INDEX `username_UNIQUE`;

ALTER TABLE `dev_jobs`.`employers` 
ADD CONSTRAINT `fk_employers_account_id`
  FOREIGN KEY (`account_id`)
  REFERENCES `dev_jobs`.`accounts` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;