CREATE TABLE course
(
    course_id       bigint NOT NULL AUTO_INCREMENT,
    course_title    varchar(150),
    course_type     varchar(50),
    course_language varchar(50),
    module_id       bigint,
    PRIMARY KEY (course_id)
);

CREATE TABLE Employee
(
    course_id       bigint NOT NULL AUTO_INCREMENT,
    course_title    varchar(150),
    course_type     varchar(50),
    course_language varchar(50),
    module_id       bigint,
    PRIMARY KEY (course_id)
);