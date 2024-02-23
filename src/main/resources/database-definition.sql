CREATE TABLE Bills (
    id VARCHAR(100) PRIMARY KEY,
    user VARCHAR(255) NOT NULL,
    company VARCHAR(255) NOT NULL,
    billind_date DATE NOT NULL,
    deadline DATE NOT NULL,
    debt DECIMAL(10, 2) NOT NULL,
    current-state VARCHAR(255) NOT NULL
);

-- Users
-- Payments
-- 

