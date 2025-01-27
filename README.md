# LibraryDB - **Basi di Dati** Project - University of Tor Vergata

LibraryDB is a project developed in 2022 for the **Basi di Dati** course at the University of Tor Vergata. The project aims to implement a library management system, exploiting relational database modelling, design and management concepts.

---

## Project Content

The repository contains the following main files and directories:

- **ER diagram:** An Entity-Relationship (ER) diagram to represent the conceptual model of the database.
- **SQL script:** SQL files for creating and populating the database.
- **User Interface:** Script or code for interacting with the database, providing search, user management and loan management functionality.
- **Documentation:** Reports describing the design and implementation choices.

---

## Functionality

The system offers the following main functionalities

1. **Book Management:**
   - Entering new books.
   - Modification of information on existing books.
   - Removal of books that are no longer available.

2. **User Management:**
   - Registration of new users.
   - Display information on users.

3. **Loan Management:**
   - Allocate loans to readers.
   - Monitoring of active loans.
   - Reporting late returns.

4. **Advanced Search:**
   - Search for books by title, author or category.
   - Customised filters for library needs.

---

## Requirements

To run the project, you must have installed:

- **MySQL** or another compatible RDBMS.
- **Python** (version 3.7 or higher) with required libraries for the user interface (if present).
- Tools for visualising ER diagrams (e.g. Draw.io or Lucidchart).

---

## Database Configuration

1. **Creation of the Database
   Import the file `create_schema.sql` into your RDBMS to create the necessary tables.

   ```bash
   mysql -u [username] -p [database_name] < create_schema.sql
   ```

2. **Database population
   Use the file `populate_db.sql` to enter example data.

   ```bash
   mysql -u [username] -p [database_name] < populate_db.sql
   ```

---

## Project Execution

1. Clone the repository:

   ```bash
   git clone https://github.com/StitchMl/LibraryDB.git
   cd LibraryDB
   ```

2. Follow the specific instructions to run the user interface or interact with the database via scripts.

---

## Repository Structure

- **/sql:** Contains the scripts for creating and populating the database.
- **/docs:** Includes the documentation and the ER diagram.
- **/src:** Source code for the user interface (optional).

---

## Authors

Questo progetto è stato sviluppato da [StitchMl](https://github.com/StitchMl) come parte del corso di Basi di Dati presso l'Università di Tor Vergata (2022).
