CREATE TABLE books
(
    ID    INT PRIMARY KEY,
    TITLE VARCHAR(255)
);
CREATE TABLE authors
(
    ID        INT PRIMARY KEY,
    FIRSTNAME VARCHAR(255),
    LASTNAME  VARCHAR(255)
);
CREATE TABLE books_authors
(
    AUTHOR_ID INT NOT NULL,
    BOOK_ID   INT NOT NULL
);
ALTER TABLE books_authors
    ADD FOREIGN KEY (AUTHOR_ID)
        REFERENCES AUTHORS(ID);
ALTER TABLE books_authors
    ADD FOREIGN KEY (BOOK_ID)
        REFERENCES BOOKS(ID);
INSERT INTO authors
VALUES (1, 'Heinrich', 'Böll');
INSERT INTO books
VALUES (1, 'The Clown');
INSERT INTO books_authors(author_id, book_id)
values (1, 1);