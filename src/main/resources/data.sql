
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    passwordHash VARCHAR(255),
    role VARCHAR(255),
    createdAt DATETIME
);

INSERT INTO users (name, lastName, email, passwordHash, role, createdAt) VALUES ('Nicolas', 'Ariza', 'nicolas.ariza@mail.escuelaing.edu.co', '12345', 'USER', '2024-02-26');
INSERT INTO users (name, lastName, email, passwordHash, role, createdAt) VALUES ('Andrés', 'Oñate', 'andres.onate@mail.escuelaing.edu.co', '12345', 'USER', '2024-02-26');
INSERT INTO users (name, lastName, email, passwordHash, role, createdAt) VALUES ('Juan', 'Sanchez', 'juan.sanchez@mail.escuelaing.edu.co', '12345', 'USER', '2024-02-26');

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
                        '2024-03-28', '$51000', 'PENDIENTE');

INSERT INTO bills (userEmail, company, billing_date, deadline, debt, payment_status) 
            VALUES ('juan.sanchez@mail.escuelaing.edu.co', 'Codensa', '2024-02-28', 
                        '2024-03-28', '$40000', 'PENDIENTE');

INSERT INTO bills (userEmail, company, billing_date, deadline, debt, payment_status)
            VALUES ('andres.onate@mail.escuelaing.edu.co', 'Gas Natural', '2024-02-28', 
                        '2024-03-28', '$45000', 'PENDIENTE');

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

