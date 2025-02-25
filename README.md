# CarMart
## 📌 Project Description
CarMart is a Java-based car inventory system that allows users to:
1. **Add** car details to the database.
2. **Search** for cars based on various criteria (company, type, price range).
3. **Update** car details (price only).
4. **Mark cars as Sold** when they are purchased.
5. **Retrieve all Sold/Unsold cars**.

The system uses **PostgreSQL** as the database and connects via **JDBC**.

---

## 🔧 Setup Instructions

### 1️⃣ Install PostgreSQL (If Not Installed)
- Download and install **PostgreSQL** from [https://www.postgresql.org/download/](https://www.postgresql.org/download/)
- During installation, **set the username and password** (default: `postgres` with your chosen password).

### 2️⃣ Create the Database and Table
- Open **pgAdmin** or **psql**.
- Run the following command to create the database:
  ```sql
  CREATE DATABASE CarMart;

## 🔧 Usage Instructions
CarMart System
---------------
1. Add a Car
2. Search for Cars
3. Update Car Price
4. Mark as Sold
5. Exit


Enter numbers (1-5) to perform operations

