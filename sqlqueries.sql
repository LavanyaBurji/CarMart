-- 1️⃣ Create Database (Run this first, if needed)
CREATE DATABASE CarMart;

-- 2️⃣ Use the Database (if using psql)
\c CarMart;

-- 3️⃣ Create Table for Cars
CREATE TABLE Cars (
    CarID SERIAL PRIMARY KEY,
    Company VARCHAR(50) NOT NULL,
    Model VARCHAR(50) NOT NULL,
    Seater INT CHECK (Seater > 0),
    FuelType VARCHAR(20) CHECK (FuelType IN ('Petrol', 'Diesel', 'Electric', 'Hybrid')),
    Type VARCHAR(20) CHECK (Type IN ('Hatchback', 'Sedan', 'SUV', 'Convertible', 'Coupe')),
    Price DECIMAL(10,2) CHECK (Price > 0),
    Sold BOOLEAN DEFAULT FALSE
);

-- 4️⃣ Insert Sample Car Data
INSERT INTO Cars (Company, Model, Seater, FuelType, Type, Price, Sold) VALUES
('Toyota', 'Corolla', 5, 'Petrol', 'Sedan', 22000.00, FALSE),
('Honda', 'Civic', 5, 'Diesel', 'Sedan', 25000.00, FALSE),
('Tesla', 'Model S', 5, 'Electric', 'Sedan', 79999.99, FALSE),
('Maruti', 'Swift', 5, 'Petrol', 'Hatchback', 7000.00, FALSE),
('Hyundai', 'Creta', 5, 'Diesel', 'SUV', 18000.00, FALSE),
('BMW', 'X5', 5, 'Petrol', 'SUV', 60000.00, TRUE);

-- 5️⃣ Retrieve All Unsold Cars
SELECT * FROM Cars WHERE Sold = FALSE;

-- 6️⃣ Retrieve Cars by Specific Company
SELECT * FROM Cars WHERE Company = 'Toyota';

-- 7️⃣ Retrieve Cars by Type (Example: SUV)
SELECT * FROM Cars WHERE Type = 'SUV';

-- 8️⃣ Retrieve Cars in a Specific Price Range
SELECT * FROM Cars WHERE Price BETWEEN 10000 AND 50000;

-- 9️⃣ Update Price of a Specific Car
UPDATE Cars SET Price = 26000 WHERE Model = 'Civic';

-- 🔟 Mark a Car as Sold
UPDATE Cars SET Sold = TRUE WHERE Model = 'Swift';

-- 1️⃣1️⃣ Retrieve All Sold Cars
SELECT * FROM Cars WHERE Sold = TRUE;

-- 1️⃣2️⃣ Delete a Car Record (Example: Remove BMW X5)
DELETE FROM Cars WHERE Model = 'X5';
