
CREATE DATABASE pharmacy_db;

USE pharmacy_db;


--Table for users

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    role ENUM('Admin', 'Cashier'),
    full_name VARCHAR(100)
);


-- Table for suppliers

CREATE TABLE suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    contact_person VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    address TEXT
);


-- Table for medicines

CREATE TABLE medicines (
    medicine_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150),
    company VARCHAR(100),
    medicine_type VARCHAR(50),
    price DECIMAL(10,2),
    quantity_in_stock INT,
    reorder_level INT,
    expiry_date DATE,
    supplier_id INT,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);


-- Table for sales

CREATE TABLE sales (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);


-- Table for sale_items

CREATE TABLE sale_items (
    sale_item_id INT AUTO_INCREMENT PRIMARY KEY,
    sale_id INT,
    medicine_id INT,
    quantity_sold INT,
    price_at_sale DECIMAL(10,2),
    FOREIGN KEY (sale_id) REFERENCES sales(sale_id),
    FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id)
);


-- Initial sample data

INSERT INTO users (username, password, role, full_name)
VALUES ('admin', 'admin123', 'Admin', 'Harrison Keabetswe');

INSERT INTO users (username, password, role, full_name)
VALUES ('cashier', 'cash123', 'Cashier', 'Thabiso Mokoena');

INSERT INTO suppliers (name, contact_person, phone, email, address)
VALUES (
    'ABC Medical Suppliers',
    'Peter Smith',
    '0123456789',
    'info@abcmedical.com',
    'Pretoria, Gauteng'
);

INSERT INTO suppliers (name, contact_person, phone, email, address)
VALUES (
    'HealthCare Distributors',
    'Sarah Jones',
    '0119876543',
    'info@healthcare.co.za',
    'Johannesburg, Gauteng'
);

INSERT INTO medicines (
    name,
    company,
    medicine_type,
    price,
    quantity_in_stock,
    reorder_level,
    expiry_date,
    supplier_id
)
VALUES (
    'Panado',
    'keaMeds',
    'Tablet',
    35.00,
    100,
    20,
    '2027-12-31',
    1
);

INSERT INTO medicines (
    name,
    company,
    medicine_type,
    price,
    quantity_in_stock,
    reorder_level,
    expiry_date,
    supplier_id
)
VALUES (
    'Amoxicillin',
    'Aspen',
    'Capsule',
    85.00,
    50,
    10,
    '2027-08-31',
    2
);