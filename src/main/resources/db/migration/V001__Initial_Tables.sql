CREATE TABLE persons
(
  id SERIAL PRIMARY KEY,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  birthday DATE,
  gender CHAR CHECK(gender in ('F', 'M')) NOT NULL
);

CREATE TABLE addresses
(
  id SERIAL PRIMARY KEY,
  person_id INT REFERENCES persons(id) NOT NULL,
  street VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  zip_code VARCHAR(8) NOT NULL,
  country VARCHAR(30) NOT NULL
);

CREATE TABLE events
(
  id SERIAL PRIMARY KEY,
  title VARCHAR(70) NOT NULL,
  person_id INT NOT NULL REFERENCES persons(id),
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP NOT NULL,
  street VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  zip_code VARCHAR(50) NOT NULL,
  country VARCHAR(50) NOT NULL
);
