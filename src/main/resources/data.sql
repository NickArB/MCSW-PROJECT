
CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    passwordHash VARCHAR(255),
    role VARCHAR(255),
    createdAt DATETIME
);

CREATE TABLE bills (
    id VARCHAR(255) PRIMARY KEY,
    userId VARCHAR(255),
    company VARCHAR(255),
    billing_date DATETIME,
    deadline DATETIME,
    debt VARCHAR(255),
    payment_status VARCHAR(255)
);

INSERT INTO users (id, name, lastName, email, passwordHash, role, createdAt) VALUES (1, 'Nicolas', 'Ariza', 'nicolas.ariza@mail.escuelaing.edu.co', '12345', 'USER', '2024-02-26');
INSERT INTO users (id, name, lastName, email, passwordHash, role, createdAt) VALUES (2, 'Andrés', 'Oñate', 'andres.onate@mail.escuelaing.edu.co', '12345', 'USER', '2024-02-26');
INSERT INTO users (id, name, lastName, email, passwordHash, role, createdAt) VALUES (1, 'Juan', 'Sanchez', 'juan.sanchez@mail.escuelaing.edu.co', '12345', 'USER', '2024-02-26');


DROP TABLE IF EXISTS users;
