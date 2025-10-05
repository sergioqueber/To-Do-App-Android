# To-Do App

A modern, offline-first To-Do application for Android, built entirely with Kotlin and Jetpack Compose. This app demonstrates best practices for modern Android development, including a reactive architecture, dependency injection, and a clean, user-friendly UI.

## ✨ Features

*   **Create, Read, Update, Delete (CRUD) Tasks**: Easily add, view, and manage your tasks.
*   **Prioritize Tasks**: Assign priorities (High, Medium, Low) to keep track of what's important.
*   **Offline First**: Tasks are stored locally in a Room database, making them available even without an internet connection.
*   **Dynamic Search**: A seamless search bar allows you to filter tasks in real-time.
*   **Sort Tasks**: Organize your list by priority.
*   **Bulk Delete**: Option to delete all completed tasks at once.
*   **Edge-to-Edge UI**: A modern, immersive user interface that extends behind the system bars.

## 🛠️ Tech Stack & Architecture

This project follows the official Android guide to app architecture, using a reactive MVVM (Model-View-ViewModel) pattern.

*   **UI Layer**:
    *   **[Jetpack Compose](https://developer.android.com/jetpack/compose)**: The entire UI is built with Jetpack Compose, Android's modern declarative UI toolkit.
    *   **[Material 3](https://m3.material.io/)**: Implements the latest Material Design guidelines for a clean and dynamic look.
    *   **[Compose Navigation](https://developer.android.com/jetpack/compose/navigation)**: Handles all in-app navigation between screens.
    *   **ViewModel**: Manages UI-related data in a lifecycle-conscious way.

*   **Data Layer**:
    *   **[Room](https://developer.android.com/training/data-storage/room)**: Provides an abstraction layer over SQLite for robust local database storage.
    *   **Repository Pattern**: Isolates the data sources from the rest of the app.

*   **Dependency Injection**:
    *   **[Hilt](https://dagger.dev/hilt/)**: Manages dependencies throughout the app, making the codebase more scalable and testable.

*   **Asynchronous Programming**:
    *   **[Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)**: Used for all asynchronous operations, such as database access.
    *   **[Flow](https://developer.android.com/kotlin/flow)**: Used to create reactive data streams from the database to the UI.

## 🚀 How to Build

To build and run the project, you will need:
1.  Android Studio (latest stable version recommended)
2.  Clone the repository
3.  RUN!