-- Creación de la tabla Users
CREATE TABLE IF NOT Users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    createDate TIMESTAMP NOT NULL,
    updateDate TIMESTAMP NOT NULL
);

-- Creación de la tabla LendBooks
CREATE TABLE IF NOT LendBooks (
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
    FOREIGN KEY (bookId) REFERENCES Books(id),
    FOREIGN KEY (userId) REFERENCES Users(id)
);

-- Índices adicionales para mejorar la performance en búsquedas comune
CREATE INDEX IF NOT idx_user ON LendBooks(userId);
