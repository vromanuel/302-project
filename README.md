# WisdomBites – Student Motivation & Tracking App

WisdomBites is a JavaFX-based desktop application designed to help students stay motivated, track their study progress, and maintain daily habits. It combines motivational quotes, digital fortunes, personalized study messages, and a to-do list all in one place.

---

## Features

- **User Login & Registration** (with persistence)
- **Daily Motivational Quotes**
- **Digital Fortune Messages**
- **Personalised Study Tips** (based on user progress)
- **To-Do List** with task creation & completion
- **Study Tracker** with daily log-in history

---

## Architecture

The project follows the **MVC (Model-View-Controller)** pattern:

- `model/` – Manages DB interactions (SQLite)
- `controller/` – JavaFX UI logic and navigation
- `Main/` – App entry point, splash screen, and stage setup
- `resources/` – FXML UI files and assets

---

## Testing

Basic **unit tests** are included for database interactions and task logic. Test classes follow JUnit conventions and are located in the `test/` directory.

---

## Tech Stack

- Java 21
- JavaFX 21
- SQLite (via sqlite-jdbc)
- JUnit 5 (for testing)
- Maven (dependency management)

---

## Folder Structure

src/
├── controller/
├── model/
├── Main/
├── resources/
│   ├── *.fxml
│   └── assets/
└── test/

---

## Getting Started

### Prerequisites
- Java 17 or higher (Amazon Corretto 21 recommended)
- Maven

### Run the App
```bash
mvn javafx:run
