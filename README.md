# 🧠 Migraine Tracker
## (Android Application for Migraine Monitoring & Reminders)

---

## 📌 Project Overview

**Migraine Tracker** is a specialized Android-based health utility designed to help users track migraine episodes, manage medication schedules, and improve personal health awareness.

The application allows users to log migraine occurrences, receive automated reminders, and maintain consistent tracking even after device restarts. It is developed using **Java, Android Studio, SQLite, and Android System Services**, following **Material Design** principles.

> This project is developed as an academic and practical Android project with an emphasis on background services, broadcast receivers, and persistent reminder logic.

---

## 🎯 Project Objectives

- Track migraine-related events efficiently
- Provide reminder notifications for medication or logs
- Ensure reminders persist after device reboot
- Maintain a clean and minimal UI for ease of use during episodes
- Demonstrate Android system-level components
- Apply real-world Android lifecycle handling

---

## 🧰 Technologies & Tools Used

| Technology | Purpose |
|:---|:---|
| **Java** | Core Android Logic |
| **Android Studio** | Development IDE |
| **SQLite** | Local Data Storage |
| **BroadcastReceiver** | System Event Handling (Boot Detection) |
| **AlarmManager** | Precise Reminder Scheduling |
| **Material Design** | UI/UX Styling |
| **NotificationManager** | User Alerts & Reminders |

---

## 📅 Development Timeline (Module-wise)

### 📍 Project Initialization

**Work Done:**
- Project planning and requirements gathering
- App naming & theme selection
- Package structure design (`com.example.migrainetracker`)
- Android permissions analysis

**Learning Outcome:**
- Mastery of Android project structure
- Resource & theme management

---

### 📍 Main Activity & UI Setup

**Work Done:**
- `MainActivity` implementation
- App launch configuration
- Material Design UI initialization

**Logic Explanation:**
The `MainActivity` acts as the primary interface for users to view their status and set new reminders. It serves as the entry point defined in the Manifest.

---

### 📍 SQLite Database Integration

**Work Done:**
- Database Helper class creation
- Migraine logs table implementation
- CRUD operations for tracking episodes

**Logic Explanation:**
SQLite ensures that all migraine history is stored locally on the device, allowing for offline access and data persistence.

```java
// Example Table Creation
db.execSQL("CREATE TABLE migraine_logs (id INTEGER PRIMARY KEY, date TEXT, severity TEXT, triggers TEXT)");
