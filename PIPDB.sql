CREATE DATABASE PIPDB;
USE PIPDB;

CREATE TABLE Suppliers (
supID INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
contact NVARCHAR(10),
email varchar(100)
);
CREATE TABLE Products (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
category VARCHAR(50),
pQuantity INT,
price DECIMAL(10, 2),
reorder_level INT,
supplier_ID INT,
date_added DATE,
date_updated DATE,
FOREIGN KEY (supplier_ID) references Suppliers(supID) On Delete SET NULL On Update CASCADE
);
CREATE TABLE Users (
uID INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50),
password_hash VARCHAR(255),
role VARCHAR(50)
); 
CREATE TABLE Transactions (
taID INT AUTO_INCREMENT PRIMARY KEY,
user_ID INT,
product_id INT,
type VARCHAR(100),
quantity INT,
date DATE,
notes TEXT,
FOREIGN KEY(product_ID) references Products(id) On Delete RESTRICT On UPDATE CASCADE,
FOREIGN KEY (user_ID) references Users(uID) On DELETE SET NULL On Update CASCADE
);

-- Sample data sets to insert:
INSERT INTO Suppliers (name, contact, email) VALUES
('Tech Distributors Inc', 4155551234, 'sales@techdist.com'),
('Office Supply Co', 6505559876, 'orders@officesupply.com'),
('Electronics Warehouse', 8185554567, 'info@elecwarehouse.com');
INSERT INTO Users (username, password_hash, role) VALUES
('admin', 'admin123', 'admin'),
('john_doe', 'password123', 'employee'),
('jane_smith', 'password456', 'employee');
INSERT INTO Products (name, category, pQuantity, price, reorder_level, supplier_ID, date_added, date_updated) VALUES
('Laptop - Dell XPS 13', 'Electronics', 15, 1299.99, 5, 1, CURDATE(), CURDATE()),
('Wireless Mouse', 'Electronics', 50, 24.99, 20, 1, CURDATE(), CURDATE()),
('USB-C Cable', 'Electronics', 100, 12.99, 30, 3, CURDATE(), CURDATE()),
('Office Chair', 'Furniture', 8, 249.99, 3, 2, CURDATE(), CURDATE()),
('Desk Lamp', 'Furniture', 25, 39.99, 10, 2, CURDATE(), CURDATE()),
('Notebook (Pack of 5)', 'Office Supplies', 200, 8.99, 50, 2, CURDATE(), CURDATE());
INSERT INTO Transactions (user_ID, product_ID, type, quantity, notes) VALUES
(1, 1, 'RESTOCK', 10, 'Initial inventory'),
(1, 2, 'RESTOCK', 50, 'Initial inventory'),
(2, 1, 'SALE', -2, 'Sold to corporate client'),
(3, 2, 'SALE', -5, 'Walk-in customer purchase'),
(2, 3, 'RESTOCK', 50, 'Weekly restock'),
(1, 4, 'SALE', -1, 'Employee purchase');

ALTER TABLE Products
MODIFY date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
MODIFY date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;