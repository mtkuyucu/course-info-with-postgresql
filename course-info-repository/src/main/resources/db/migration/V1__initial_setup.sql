CREATE TABLE Courses
(
    id     VARCHAR(100) PRIMARY KEY, -- Using SERIAL for auto-increment in PostgreSQL
    name   VARCHAR(255)  NOT NULL,
    length VARCHAR(100)  NOT NULL,   -- Assuming length is a string like "3 hours". Adjust the datatype if it's something else
    url    VARCHAR(1000) NOT NULL,   -- Assuming URL will be long but within 1000 characters
    notes  TEXT                      -- TEXT datatype allows for longer descriptions, can be NULL
);