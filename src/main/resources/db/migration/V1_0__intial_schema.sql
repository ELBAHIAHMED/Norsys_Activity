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
CREATE TABLE event
(
    id bigint NOT NULL AUTO_INCREMENT,
    event_id varchar(100) NOT NULL,
    event_name varchar(100)  ,
    event_description varchar(100) ,
    event_date VARCHAR(100) ,
    event_responsable varchar(100),
    PRIMARY KEY (id)
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
    file_path varchar(255) NOT NULL,
    file_shared_path varchar(255) NOT NULL,
    survey_id bigint NOT NULL,
    PRIMARY KEY (file_id),
    FOREIGN KEY (survey_id) REFERENCES survey(survey_id)
);

CREATE TABLE filesGallery
(
    file_id bigint NOT NULL AUTO_INCREMENT,
    file_path varchar(255) NOT NULL,
    file_shared_path varchar(255) NOT NULL,
    event_id bigint NOT NULL,
    PRIMARY KEY (file_id),
    FOREIGN KEY (event_id) REFERENCES event(id)
);