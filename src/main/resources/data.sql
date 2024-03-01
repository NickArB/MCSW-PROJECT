
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

INSERT INTO requests (paymentId, newValue, requestState) VALUES ('30', '$55000', 'PENDIENTE');
INSERT INTO requests (paymentId, newValue, requestState) VALUES ('31', '$35000', 'PENDIENTE');
INSERT INTO requests (paymentId, newValue, requestState) VALUES ('32', '$50000', 'PENDIENTE');

DROP TABLE IF EXISTS bills;