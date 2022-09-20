CREATE TABLE reponse_option
(
    reponse_id bigint NOT NULL AUTO_INCREMENT,
    option_id bigint NOT NULL ,
    PRIMARY KEY (reponse_id)
);

CREATE TABLE reponse_text
(
    reponse_id bigint NOT NULL AUTO_INCREMENT,
    question_id bigint NOT NULL,
    value_text VARCHAR(500) NOT NULL ,
    PRIMARY KEY (reponse_id)
);