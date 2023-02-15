CREATE TABLE IF NOT EXISTS employees
(
    id SERIAL PRIMARY KEY,
    fname VARCHAR(100) NOT NULL,
    sname VARCHAR(100) NOT NULL,
    patronymic VARCHAR(100),
    status VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS departments
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    contacts VARCHAR(200) NOT NULL,
    leader_id INTEGER,
    FOREIGN KEY (leader_id) REFERENCES employees(id)
);

CREATE TABLE IF NOT EXISTS organizations
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    physical_address VARCHAR(150) NOT NULL,
    legal address VARCHAR(150) NOT NULL,
    leader_id INTEGER,
    FOREIGN KEY (leader_id) REFERENCES employees(id)
);

CREATE TABLE IF NOT EXISTS documents
(
    id SERIAL PRIMARY KEY,
    subject VARCHAR(150) NOT NULL,
    author_id INTEGER,
    employees INTEGER[],
    date_perform DATE NOT NULL,
    FOREIGN KEY (author_id) REFERENCES employees(id),
    FOREIGN KEY (EACH ELEMENT OF employees) REFERENCES employees
);
