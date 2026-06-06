# ☕ Nisum Internship — Java Backend Training

![Java](https://img.shields.io/badge/Java-100%25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Intermediate-green)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen)

A complete collection of Java backend training tasks completed during my **Java Developer Internship at Nisum Pakistan (Blue Stone Innovation)** — a global technology company operating across 10+ countries.

This repository covers the full Java learning path from **Core Java fundamentals** to **Spring Boot, Hibernate ORM, Servlet/JSP**, and **JDBC** — structured exactly as assigned during the internship.

---

## 👨‍💻 Internship Details

| | |
|---|---|
| **Company** | Nisum Pakistan (Blue Stone Innovation) |
| **Role** | Java Developer Intern |
| **Duration** | 3 Months |
| **Domain** | Backend Development |
| **Language** | Java 100% |

---

## 📁 Repository Structure

```
nisum_internship_java_backend/
│
├── Task1-Encapsulation/
├── Task2-Inheritance/
├── Task3-Poly&Abstraction/
├── Task4-LangPackage/
├── Task5-ExceptionHandling/
├── Task6-MultiThreading/
├── Task7-CollectionsFramework/
├── Task8-FileHandling/
├── Task9-DesignPattern/
├── Task1-JDBC (Intermediate)/
├── Task2-Servlet&JSP (Intermediate)/
├── Task3-Others (Intermediate)/
├── Task4-Hibernate (Intermediate)/
├── Task5-ElectronicWarehouse (Intermediate)/
├── Task6-SpringBoot (Intermediate)/
└── README.md
```

---

## 🔵 Basic Java Tasks

### Task 1 — Encapsulation
- Encapsulated `Student` class with private fields, getters, and setters
- Protected internal data from direct external access

### Task 2 — Inheritance
- Inherited a base class and extended its behavior
- Demonstrated **Composition** via:
  - **Aggregation** — loose relationship between classes
  - **Association** — classes used independently but interact

### Task 3 — Abstraction & Polymorphism
- Calculated areas of Circle, Triangle, and Rectangle using **Compile-Time Polymorphism** (method overloading) — without inheritance
- Calculated areas using **Run-Time Polymorphism** (method overriding) — with inheritance
- Implemented **Abstraction** using `Person` and `Employee` classes — hiding internal `Person` details

### Task 4 — Lang Package (String Handling)
- String literal vs String Object — `equals()` vs `==` comparison
- String comparison using `compareTo()`
- `StringBuffer` vs `StringBuilder` — performance benchmarking in milliseconds
- Word capitalization in a string
- Prime number counter below a given positive number

### Task 5 — Exception Handling
- Custom Exception class for **NULL String validation**
- Exception is thrown and handled when a null String is passed

### Task 6 — Multi-Threading
- Created 3 threads — `Main`, `Thread1`, `Thread2`
- Countdown execution with **1-second sleep** on Thread1 and Thread2
- Main thread waits for Thread1 and Thread2 without using `sleep()` on main thread

### Task 7 — Collections Framework
- Binary search on sorted `ArrayList` using Java Collections API
- Performance comparison — `ArrayList` vs `LinkedList` search time in milliseconds
- Student records sorted by name (ascending) — tie-broken by age using `Comparator`

### Task 8 — File Handling
- Create a text file and write console input to it
- List all files in a given directory
- Filter and list files by format (e.g. `.xlsx`, `.pptx`)
- Copy contents from one text file to another
- Count frequency of a specific word (case-insensitive)
- Count frequency of all words in a text file

### Task 9 — Design Patterns
- **Singleton Pattern** — ensures only one object instance is created
- Verified using `==` operator — returns `true` for same object reference

---

## 🟡 Intermediate Java Tasks

### Task 1 — JDBC
- Connected to **MySQL** database and fetched records to console
- Student management system — insert and retrieve via console using **JDBC**
- Fetch courses of a student by Roll No using **Student-Course** relational schema

### Task 2 — Servlet & JSP
- Web application — Student registration form (JSP) saving data to MySQL via JDBC
- Full **CRUD operations** — Create, Read, Update, Delete student records
- Data displayed in tabular format on a dedicated JSP page

### Task 3 — Others (Utilities)
- String validation using **Java Regex** — alphabets only, no numbers/special characters/spaces
- Seconds and milliseconds since 1970 (Unix timestamp)
- Leap year checker
- Date manipulation — add 1 month, subtract 15 days, display in multiple date formats

### Task 4 — Hibernate
- Extended Servlet/JSP app with `Courses` table
- Established **Student-Course relationship** at database level
- Introduced **Hibernate ORM** — mapped `Student` and `Course` classes to DB tables
- UI form for students to select courses — saved and updated via Hibernate

### Task 5 — Electronics Warehouse System
- Console-based Java application for managing Laptops, Phones, and Accessories
- Full CRUD operations with **MySQL/JDBC**
- Multi-threaded **CSV file import** with defective product filtering
- **Role-based authentication** — Admin and User roles
- Input validation and descriptive error messages

### Task 6 — Spring Boot
- Integrated full web application with **Spring Core** and **Spring Web MVC**
- Followed **Web → Service → DAO** layer architecture
- Used **Annotations** for all Spring configurations
- Integrated with **JPA** for database operations
- Combined Servlet/JSP + Hibernate + Spring into one complete application

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Java 11+ | Core language |
| Spring Boot | Web framework |
| Spring Web MVC | MVC architecture |
| Hibernate | ORM framework |
| JPA | Java Persistence API |
| MySQL | Relational database |
| JDBC | Direct database connectivity |
| JSP / Servlet | Web UI layer |
| Multithreading | Concurrent processing |
| Java Regex | String validation |
| Git | Version control |

---

## 🚀 How to Run

### Prerequisites
- Java 11+
- MySQL
- Apache Tomcat (for Servlet/JSP/Spring tasks)
- Maven (for Spring Boot task)

### Basic Java Tasks
```bash
# Clone the repository
git clone https://github.com/hamzanadeem99/nisum_internship_java_backend.git

# Navigate to any basic task
cd Task1-Encapsulation

# Compile and run
javac Main.java
java Main
```

### JDBC Tasks
```bash
# Setup MySQL database first
mysql -u your_username -p

# Update DB credentials in the source file
# Then compile and run
javac Main.java
java Main
```

### Spring Boot Task
```bash
cd "Task6-SpringBoot (Intermediate)"
mvn spring-boot:run
```

---

## 📊 Task Completion Summary

| # | Task | Module | Status |
|---|---|---|---|
| 1 | Encapsulation | Basic Java | ✅ |
| 2 | Inheritance | Basic Java | ✅ |
| 3 | Abstraction & Polymorphism | Basic Java | ✅ |
| 4 | Lang Package | Basic Java | ✅ |
| 5 | Exception Handling | Basic Java | ✅ |
| 6 | Multi-Threading | Basic Java | ✅ |
| 7 | Collections Framework | Basic Java | ✅ |
| 8 | File Handling | Basic Java | ✅ |
| 9 | Design Patterns | Basic Java | ✅ |
| 10 | JDBC | Intermediate | ✅ |
| 11 | Servlet & JSP | Intermediate | ✅ |
| 12 | Others / Utilities | Intermediate | ✅ |
| 13 | Hibernate | Intermediate | ✅ |
| 14 | Electronics Warehouse | Intermediate | ✅ |
| 15 | Spring Boot MVC | Intermediate | ✅ |

---

## 👨‍💻 Author

**Hamza Nadeem**
Java Developer Intern — Nisum Pakistan (Blue Stone Innovation)
