CREATE TABLE books (
    id                  INT             PRIMARY KEY     AUTO_INCREMENT,
    title               VARCHAR(200)    NOT NULL,
    author              VARCHAR(200)    NOT NULL,
    year_of_purchase    SMALLINT        NOT NULL,
    condition           VARCHAR(50)
);