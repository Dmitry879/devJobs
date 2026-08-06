CREATE TABLE `dev_jobs`.`job_postings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `position` VARCHAR(100) NOT NULL,
  `description` TEXT NOT NULL,
  `requirements` TEXT NOT NULL,
  `employer_id` INT NOT NULL,

  PRIMARY KEY (`id`),

  CONSTRAINT `fk_job_posting_employer`
    FOREIGN KEY (`employer_id`)
    REFERENCES `dev_jobs`.`employers` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dev_jobs`.`job_applications` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `application_date` DATETIME NOT NULL,
    `cover_letter` TEXT NOT NULL,

    `applicant_id` BIGINT NOT NULL,
    `job_posting_id` INT NOT NULL,

    PRIMARY KEY (`id`),

    CONSTRAINT `fk_job_application_applicant`
        FOREIGN KEY (`applicant_id`)
        REFERENCES `dev_jobs`.`applicants` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT `fk_job_application_job_posting`
        FOREIGN KEY (`job_posting_id`)
        REFERENCES `dev_jobs`.`job_postings` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);