# 📚 Nisum Internship — Java Training Tasks

A comprehensive collection of Java training programs completed during my **Java Developer Internship at Nisum Pakistan (Blue Stone Innovation)**.

This repository covers the full Java learning path — from Core Java fundamentals to Spring MVC, Hibernate ORM, and JDBC-based web applications.

---

## 👨‍💻 Internship Details

| | |
|---|---|
| **Company** | Nisum Pakistan (Blue Stone Innovation) |
| **Role** | Java Developer Intern |
| **Duration** | 3 Months |
| **Domain** | Backend Development |

---

## 🔵 Module 1 — Basic Java

### 📌 Encapsulation & Inheritance
- Encapsulated `Student` class with private fields and getters/setters
- Implemented Inheritance with class extension
- Demonstrated Composition via **Aggregation** and **Association**

### 📌 Abstraction & Polymorphism
- Computed areas of Circle, Triangle, and Rectangle using **Compile-Time Polymorphism** (method overloading)
- Computed areas using **Run-Time Polymorphism** (method overriding)
- Implemented Abstraction using `Person` and `Employee` classes — hiding internal details of `Person`

### 📌 Lang Package (String Handling)
- String literal vs String Object — `equals()` vs `==` operator
- String comparison using `compareTo()`
- `StringBuffer` vs `StringBuilder` — performance comparison in milliseconds
- Word capitalization program
- Prime number counter below a given positive number

### 📌 Exception Handling
- Custom Exception class for NULL String validation
- Throws custom exception when String is NULL

### 📌 Multi-Threading
- Created 3 threads — `Main`, `Thread1`, `Thread2`
- Countdown execution with 1-second sleep on Thread1 and Thread2
- Main thread waits for Thread1 and Thread2 to finish (without using `sleep()` on main)

### 📌 Collections Framework
- Binary search on sorted `ArrayList` using Java Collections API
- Performance comparison — `ArrayList` vs `LinkedList` search time in milliseconds
- Student records sorted by name (ascending) — tie-broken by age using `Comparator`

### 📌 File Handling
- Create a text file and write console input to it
- List all files in a given directory
- Filter files by format (e.g. `.xlsx`, `.pptx`)
- Copy contents of one text file to another
- Count frequency of a specific word (case-insensitive)
- Count frequency of all words in a text file

### 📌 Design Patterns
- **Singleton Pattern** — ensures only one object is created, verified using `==` operator

---

## 🟡 Module 2 — Intermediate Java

### 📌 JDBC
- Connect to MySQL database and fetch records to console
- Student management — insert and retrieve via console using JDBC
- Fetch courses of a student by Roll No using **Student-Course** relational database

### 📌 Servlet & JSP
- Web application — Student registration form (JSP) saving to MySQL via JDBC
- Full **CRUD operations** — Create, Read, Update, Delete student records
- Data displayed in tabular format on a separate JSP page

### 📌 Others
- String validation using **Java Regex** — only alphabets allowed, no spaces/numbers/special characters
- Seconds and milliseconds since 1970 (Unix timestamp)
- Leap year checker
- Date manipulation — add 1 month, subtract 15 days, format in multiple date formats

---

## 🟠 Module 3 — Hibernate

- Extended Servlet/JSP application with `Courses` table
- Established **Student-Course relationship** at database level
- Introduced **Hibernate ORM** — mapped `Student` and `Course` Java classes to DB tables
- Implemented relationships at code level (One-to-Many / Many-to-Many)
- UI form for students to select courses — saved/updated via Hibernate on form submission

---

## 🟢 Module 4 — Spring

- Integrated full web application with **Spring Core** and **Spring Web MVC**
- Followed **Web → Service → DAO** layer architecture
- Used **Annotations** for all Spring configurations
- Integrated with **JPA** for database operations
- Combined Servlet/JSP + Hibernate + Spring into one complete web application

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Java 11+ | Core language |
| MySQL | Relational database |
| JDBC | Database connectivity |
| Hibernate | ORM framework |
| Spring Core | Dependency injection |
| Spring Web MVC | Web layer framework |
| JPA | Java Persistence API |
| JSP / Servlet | Web UI layer |
| Git | Version control |
