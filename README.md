# Name: Isange Nelly 
# Student ID: 27818
# Course: Web Technology and Internet
# Project Name: Campus Printing and Document Submission Management System

## Overview

This backend helps the campus printing center manage the documents students send in for printing.

Here is how a job moves through the system:

1. A **student** submits a **print request** for a document.
2. A **print operator** picks it up and prints it on one of the numbered **printers**. Operators can offer different **services**, like black and white, color, or binding.
3. The request goes from **submitted** to **processing**, and finishes as either **ready** or **cancelled**. Once it is ready or cancelled, it cannot change anymore.
4. When a request is ready, a **payment** can be recorded for it. If a request already has a payment, the payment must be deleted before the request can be deleted.

## Technologies

Java 17 · Spring Boot · Spring Web · Spring Data JPA · PostgreSQL · Postman

## Prerequisites 

**What you need:** Java 17, Maven and PostgreSQL installed on your computer.

**Database:** create a new, empty PostgreSQL database named `campus_printing_a`.

**Settings:** in `src/main/resources/application.properties`, replace the database username and password with yours:

```
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**Start the API:** from the project folder (where `pom.xml` is), run:

```
mvn spring-boot:run
```

After a few seconds the terminal shows `Tomcat started on port 8081`. The API now answers at `http://localhost:8081`, and all the tables have been created for you. Leave the terminal running while you test; closing it turns the API off.

## Testing with Postman

All the tests are saved in one file in the root of this repository:

`campus-printingand-document-submission-management-system-midterm.postman_collection.json`

Import it in Postman (**Import** → choose the file). Inside, every request carries the ID of the requirement it checks, and has a small note explaining the expected answer and what a 400, 404 or 409 response means.

**How the requests are grouped**

- **A2**: lists all students. The API also saves, views, updates, and deletes students, printers, print services, operators, and print requests.
- **B1 – B3**: searching
- **C1 – C4**: queries across several tables
- **D1 – D4**: business rules
- **E1 – E3**: payments
- **F1, F2**: counting and cancelling a whole day
- **The last two**: the dashboard, and print requests shown page by page

**About ids**

When you save a record, the database gives it a new id. That means the ids written in my requests will not exist in your database. Before sending a request that has an id in its address, swap in one of your own:

> Send the request that lists all print operators → copy the `"id"` of one operator from the answer → open **C4** → replace the old id in the address with yours → **Send**.

You should get **200 OK** with that operator's result. To try the failing case, use an id that does not exist, like `00000000-0000-0000-0000-000000000000`; the answer is **404** with a "does not exist" message. Every other request with an id works the same way.

**Be careful with these:** D2 (moves a request to processing), D3 (adds a print service), D4 (moves an operator to a different printer) and E1 (records a payment) change the saved data. Only send them when you want that change.

