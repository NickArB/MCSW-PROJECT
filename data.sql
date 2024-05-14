
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    passwordHash VARCHAR(255),
    role VARCHAR(255),
    createdAt DATETIME
);

-- Contraseña por defecto: Ejemplo1! <--
INSERT INTO users (name, lastName, email, passwordHash, role, createdAt) VALUES ('Nicolas', 'Ariza', 'nicolas.ariza@mail.escuelaing.edu.co', '$2a$10$IiO71BWhKzMsW8pUksSmeueONEfpVqjROMXhKGlTcKbLwHrAdoq72', 'AUDITOR', '2024-02-26');
INSERT INTO users (name, lastName, email, passwordHash, role, createdAt) VALUES ('Andrés', 'Oñate', 'andres.onate@mail.escuelaing.edu.co', '$2a$10$.y33p1sebHuIn0j3C2txV.n5MDCoRHz.2vhMOZjs3mWdbiVwbsoQC', 'ADMIN', '2024-02-26');
INSERT INTO users (name, lastName, email, passwordHash, role, createdAt) VALUES ('Juan', 'Sanchez', 'juan.sanchez@mail.escuelaing.edu.co', '$2a$10$.4F9v9WHOEPilcnnWH9R2.6eP5m8at7ERRZz2b5ERl7y5hF8BqaGa', 'USER', '2024-02-26');

DROP TABLE IF EXISTS users;

CREATE TABLE bills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userEmail VARCHAR(255),
    company VARCHAR(255),
    billing_date DATETIME,
    deadline DATETIME,
    debt VARCHAR(255),
    payment_status VARCHAR(255)
);


INSERT INTO bills (userEmail, company, billing_date, deadline, debt, payment_status) 
            VALUES ('nicolas.ariza@mail.escuelaing.edu.co', 'Acueducto', '2024-02-28', 
                        '2024-03-28', '51000', 'PENDIENTE');

INSERT INTO bills (userEmail, company, billing_date, deadline, debt, payment_status) 
            VALUES ('juan.sanchez@mail.escuelaing.edu.co', 'Codensa', '2024-02-28', 
                        '2024-03-28', '40000', 'PENDIENTE');

INSERT INTO bills (userEmail, company, billing_date, deadline, debt, payment_status)
            VALUES ('andres.onate@mail.escuelaing.edu.co', 'Gas Natural', '2024-02-28', 
                        '2024-03-28', '45000', 'PENDIENTE');

DROP TABLE IF EXISTS bills;

CREATE TABLE requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paymentId VARCHAR(255),
    newValue VARCHAR(255),
    requestState VARCHAR(255)
);

INSERT INTO requests (paymentId, newValue, requestState) VALUES ('1', '$55000', 'PENDIENTE');
INSERT INTO requests (paymentId, newValue, requestState) VALUES ('2', '$35000', 'PENDIENTE');
INSERT INTO requests (paymentId, newValue, requestState) VALUES ('3', '$50000', 'PENDIENTE');


DROP TABLE IF EXISTS requests;

CREATE TABLE cards (
    account_number VARCHAR(255) PRIMARY KEY,
    available_balance VARCHAR(255),
    expiration_date DATE,
    type VARCHAR(255),
    cvc VARCHAR(255),
    owner_name VARCHAR(255),
    owner_id VARCHAR(255),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

-- account_number 1234567890, cvc 123 --
INSERT INTO cards (account_number, available_balance, expiration_date, type, cvc, owner_name, owner_id)
VALUES ('$2a$10$ePX0nfIA.B84.AmE1XBgUupn/PAijz1T6vGzmS.wujhN1oursIrJe', '60000', '2024-12-31', 'credito', '$2a$10$GcUv0VJ6p2Bf/.ZbaK/nmeNV2jVvmmZv5Ru/aGpHcK1j/1C6RM7x.', 'Nicolas Ariza', '1');

-- account_number 2333142121 --
INSERT INTO cards (account_number, available_balance, expiration_date, type, cvc, owner_name, owner_id)
VALUES ('$2a$10$5TEZXwzn8HiWq0iRUBau6ugFFfPb6rdqrzpYdh3wVlq3X3KAgra72', '60000', '2024-12-31', 'debito', null, null, '2');

-- account_number 12387127468 --
INSERT INTO cards (account_number, available_balance, expiration_date, type, cvc, owner_name, owner_id)
VALUES ('$2a$10$ueg5ZpJ/Ov/6qHEq3I8m9eHUqLBnuvOqwd6ueG4ERLtyR09PeYTdu', '60000', '2024-12-31', 'credito', '$2a$10$GcUv0VJ6p2Bf/.ZbaK/nmeNV2jVvmmZv5Ru/aGpHcK1j/1C6RM7x.', ' Juan Sanchez', '3');

DROP TABLE IF EXISTS cards;

