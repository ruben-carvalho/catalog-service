CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    full_original_name VARCHAR(255),
    born_date DATE,
    born_place VARCHAR(255),
    died_date DATE,
    died_place VARCHAR(255),
    about_text TEXT
);

CREATE TABLE language (
    id SERIAL PRIMARY KEY,
    language VARCHAR(255) NOT NULL
);

CREATE TABLE format (
    id SERIAL PRIMARY KEY,
    format VARCHAR(255) NOT NULL
);

CREATE TABLE genre (
    id SERIAL PRIMARY KEY,
    genre VARCHAR(255) NOT NULL
);

CREATE TABLE publisher (
    id SERIAL PRIMARY KEY,
    publisher VARCHAR(255) NOT NULL
);

CREATE TABLE availability (
    id SERIAL PRIMARY KEY,
    availability VARCHAR(255) NOT NULL
);

CREATE TABLE tag (
    id SERIAL PRIMARY KEY,
    tag VARCHAR(255) NOT NULL
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    original_title VARCHAR(255),
    isbn VARCHAR(255),
    language_id SERIAL REFERENCES language(id),
    format_id SERIAL REFERENCES format(id),
    release_date DATE,
    edition_date DATE,
    genre_id SERIAL REFERENCES genre(id),
    edition VARCHAR(255),
    is_series BOOLEAN,
    publisher_id SERIAL REFERENCES publisher(id),
    synopsis TEXT,
    price DOUBLE PRECISION,
    promotional_price DOUBLE PRECISION,
    availability_id SERIAL REFERENCES availability(id),
    stock_available INT,
    date_created TIMESTAMP,
    date_updated TIMESTAMP
);

CREATE TABLE book_author (
    book_id SERIAL REFERENCES book(id),
    author_id SERIAL REFERENCES author(id),
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE book_tag (
    book_id SERIAL REFERENCES book(id),
    tag_id SERIAL REFERENCES tag(id),
    PRIMARY KEY (book_id, tag_id)
);

CREATE TABLE book_language (
    book_id SERIAL REFERENCES book(id),
    language_id SERIAL REFERENCES tag(id),
    PRIMARY KEY (book_id, language_id)
);


