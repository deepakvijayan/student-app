DROP TABLE IF EXISTS students;

CREATE TABLE students
(
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR (255) NOT NULL
);

CREATE UNIQUE INDEX email_idx ON students(name);