# QA Automation Project: Account Creation Validator

This project focuses on **Software Quality Assurance (QA)** principles by implementing automated unit tests for a user registration system.

## 🚀 Features

- **Input Validation:** Validates Name, Email, Password, and Birth Date.
- **Security Checks:** Ensures password strength and matching.
- **Logical Validation:** Prevents future birth dates and invalid formats.

## 🛠 Tech Stack

- **Language:** Java 17
- **Testing Framework:** JUnit 5
- **Build Tool:** Maven
- **Automation:** GitHub Actions (CI/CD)

## 🧪 Testing Techniques Applied

- **Boundary Value Analysis (BVA):** Tested password length limits (7, 8, and 9 characters).
- **Equivalence Partitioning (EP):** Grouped valid/invalid inputs for emails and names.
- **Happy Path Testing:** Verified successful registration with perfect data.

## 🤖 CI/CD Integration

This repository is integrated with **GitHub Actions**. Every push or pull request triggers an automated test suite to ensure code stability.
