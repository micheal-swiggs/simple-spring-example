CREATE TABLE books (
    id                  SERIAL          PRIMARY KEY,
    title               VARCHAR(200)    NOT NULL,
    author              VARCHAR(200)    NOT NULL,
    year_of_purchase    SMALLINT        NOT NULL,
    condition           VARCHAR(50),
    borrowed_at         DATE,
    due_back_by         DATE
);