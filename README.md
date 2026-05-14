# Today-Only Todo App

A small Android todo/reminder app built with **Kotlin** and **Jetpack Compose** for the Eulerity Android Internship Take-Home Exercise.

The main idea of this app is simple:

> The app only cares about today.

Tasks belong only to the current day. Tasks from previous days are not shown in the main list, and each new day starts with a clean slate.

---

## Overview

This project is a fully offline Android todo app focused on daily task tracking. Users can add tasks for the current day, mark tasks as complete, and close/reopen the app without losing their tasks because the data is persisted locally.

The app intentionally does not support future scheduling, accounts, authentication, or online syncing. This keeps the project focused on the core requirement: managing tasks that only exist for the current day.

---

## Features

### Required Features

- Add a task for the current day
- View tasks for today only
- Mark tasks as complete or incomplete
- Persist tasks locally
- Automatically hide tasks from previous days
- Start each new day with a clean main task list
- Fully offline functionality
- No network requests
- Built with Kotlin and Jetpack Compose
- Minimum SDK: API 28

### Optional Enhancements Included

- Thoughtful empty state when there are no tasks
- Clean Compose UI
- Light and dark mode support
- Clear separation between UI, state, data, and date logic
- Date abstraction to make today-only logic easier to test

---

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM-style structure
- **Local Persistence:** Room Database
- **State Management:** ViewModel + StateFlow
- **Minimum SDK:** API 28
- **Network:** None

---

## Project Structure

```text
app/
└── src/main/java/com/example/todaytodo/
    │
    ├── MainActivity.kt
    ├── TodayTodoApp.kt
    │
    ├── data/
    │   ├── local/
    │   │   ├── TodoDao.kt
    │   │   ├── TodoDatabase.kt
    │   │   └── TodoEntity.kt
    │   │
    │   ├── mapper/
    │   │   └── TodoMapper.kt
    │   │
    │   └── repository/
    │       └── TodoRepositoryImpl.kt
    │
    ├── domain/
    │   ├── model/
    │   │   └── Todo.kt
    │   │
    │   ├── repository/
    │   │   └── TodoRepository.kt
    │   │
    │   └── util/
    │       ├── DateProvider.kt
    │       └── SystemDateProvider.kt
    │
    ├── presentation/
    │   ├── TodoViewModel.kt
    │   ├── TodoUiState.kt
    │   │
    │   ├── components/
    │   │   ├── AddTodoBar.kt
    │   │   ├── EmptyState.kt
    │   │   └── TodoItemRow.kt
    │   │
    │   └── screen/
    │       └── TodayTodoScreen.kt
    │
    └── ui/theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
