-- Creación de la tabla Users
CREATE TABLE IF NOT EXISTS Users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    createDate TIMESTAMP NOT NULL,
    updateDate TIMESTAMP NOT NULL
);

-- Creación de la tabla LendBooks
CREATE TABLE IF NOT EXISTS LendBooks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bookId BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    checkoutDate DATE NOT NULL,
    dueDate DATE NOT NULL,
    returnDate DATE,
    conditionOnLoan VARCHAR(255),
    conditionOnReturn VARCHAR(255),
    renewals INT DEFAULT 0,
    lateFee DECIMAL(10, 2) DEFAULT 0.00,
    FOREIGN KEY (userId) REFERENCES Users(id)
);

-- Índices adicionales para mejorar la performance en búsquedas comunes
CREATE INDEX IF NOT EXISTS idx_user ON LendBooks(userId);

-- Insertar usuarios de prueba en la tabla Users
INSERT INTO Users (name, lastName, createDate, updateDate) VALUES
('John', 'Doe', NOW(), NOW()),
('Jane', 'Smith', NOW(), NOW()),
('Michael', 'Johnson', NOW(), NOW()),
('Emily', 'Davis', NOW(), NOW()),
('David', 'Wilson', NOW(), NOW());

-- Insertar registros de prueba en la tabla LendBooks
INSERT INTO LendBooks (bookId, userId, checkoutDate, dueDate, returnDate, conditionOnLoan, conditionOnReturn, renewals, lateFee) VALUES
(1, 1, '2023-01-01', '2023-01-15', '2023-01-14', 'Good', 'Good', 0, 0.00),
(2, 2, '2023-02-01', '2023-02-15', '2023-02-16', 'Good', 'Fair', 0, 1.00),
(3, 3, '2023-03-01', '2023-03-15', NULL, 'Excellent', NULL, 1, 0.00),
(4, 4, '2023-04-01', '2023-04-15', '2023-04-10', 'Good', 'Good', 0, 0.00),
(5, 5, '2023-05-01', '2023-05-15', '2023-05-14', 'Fair', 'Fair', 0, 0.00);
