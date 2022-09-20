CREATE TABLE survey
(
    survey_id bigint NOT NULL AUTO_INCREMENT,
    survey_title varchar(100) NOT NULL,
    survey_url varchar(100),
    survey_description varchar(100),
    survey_is_available boolean DEFAULT FALSE ,
    survey_date Date DEFAULT (CURRENT_DATE),
    PRIMARY KEY (survey_id)
);

CREATE TABLE question
(
    question_id bigint NOT NULL AUTO_INCREMENT,
    question_type varchar(100) NOT NULL,
    question_text varchar(100) NOT NULL,
    survey_id bigint NOT NULL,
    PRIMARY KEY (question_id),
    FOREIGN KEY (survey_id) REFERENCES survey(survey_id)
);

CREATE TABLE option_q
(
    option_id bigint NOT NULL AUTO_INCREMENT,
    option_text varchar(100) NOT NULL,
    question_id bigint NOT NULL,
    PRIMARY KEY (option_id),
    FOREIGN KEY (question_id) REFERENCES question(question_id)
);

CREATE TABLE fileS
(
    file_id bigint NOT NULL AUTO_INCREMENT,
    file_path varchar(255) NOT NULL UNIQUE,
    file_shared_path varchar(255) NOT NULL,
    survey_id bigint NOT NULL,
    PRIMARY KEY (file_id),
    FOREIGN KEY (survey_id) REFERENCES survey(survey_id)
);

CREATE TABLE user
(
    user_id varchar(300) NOT NULL,
    user_username varchar(100) NOT NULL UNIQUE,
    user_nom varchar(100) NOT NULL,
    user_prenom varchar(100) NOT NULL,
    user_email varchar(100) NOT NULL,
    PRIMARY KEY (user_id)
);