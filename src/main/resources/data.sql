
CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    passwordHash VARCHAR(255),
    role VARCHAR(255),
    createdAt DATETIME
);

INSERT INTO users (id, name, lastName, email, passwordHash, role, createdAt) VALUES (1, 'Nicolas', 'Ariza', 'nicolas.ariza@mail.escuelaing.edu.co', '12345', 'USER', '2024-02-26');
INSERT INTO users (id, name, lastName, email, passwordHash, role, createdAt) VALUES (2, 'Andrés', 'Oñate', 'andres.onate@mail.escuelaing.edu.co', '12345', 'USER', '2024-02-26');
INSERT INTO users (id, name, lastName, email, passwordHash, role, createdAt) VALUES (3, 'Juan', 'Sanchez', 'juan.sanchez@mail.escuelaing.edu.co', '12345', 'USER', '2024-02-26');

DROP TABLE IF EXISTS users;

CREATE TABLE bills (
    id VARCHAR(255) PRIMARY KEY,
    userEmail VARCHAR(255),
    company VARCHAR(255),
    billing_date DATETIME,
    deadline DATETIME,
    debt VARCHAR(255),
    payment_status VARCHAR(255)
);

INSERT INTO bills (id, userEmail, company, billing_date, deadline, debt, payment_status) 
            VALUES (30, 'nicolas.ariza@mail.escuelaing.edu.co', 'Acueducto', '2024-02-28T02:47:55.045+00:00', 
                        '2024-02-28T02:47:55.045+00:00', '$51000', 'OK');

 INSERT INTO bills (id, userEmail, company, billing_date, deadline, debt, payment_status)
            VALUES (31, 'andres.onate@mail.escuelaing.edu.co', 'Luz', '2024-02-28T02:47:55.045+00:00',
                        '2024-03-23T02:47:55.045+00:00', '$100000', 'PENDIENTE');

INSERT INTO bills (id, userEmail, company, billing_date, deadline, debt, payment_status) 
            VALUES (31, 'juan.sanchez@mail.escuelaing.edu.co', 'Codensa', '2024-02-28', 
                        '2024-02-28', '$40000', 'PENDING');

INSERT INTO bills (id, userEmail, company, billing_date, deadline, debt, payment_status) 
            VALUES (32, 'andres.onate@mail.escuelaing.edu.co', 'Gas Natural', '2024-02-28', 
                        '2024-02-28', '$45000', 'PENDING');

DROP TABLE IF EXISTS bills;

CREATE TABLE cards (
    account_number VARCHAR(255) PRIMARY KEY,
    expiration_date DATETIME,
    type VARCHAR(255),
    cvc VARCHAR(255),
    owner_name VARCHAR(255),
    owner_id VARCHAR(255),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

INSERT INTO cards (account_number, expiration_date, type, cvc, owner_name, owner_id)
VALUES ('1234567890', '2024-12-31 23:59:59', 'credito', '123', 'Nicolas Ariza', '1');

INSERT INTO cards (account_number, expiration_date, type, cvc, owner_name, owner_id)
VALUES ('2333142121', '2024-12-31 23:59:59', 'debito', null, null, '2');

DROP TABLE IF EXISTS cards;