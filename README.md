# GitHub User Browser

This Android application allows you to browse a list of GitHub users and view detailed information about a specific user.

<img src="https://github.com/Djostanisic/github_users_app/assets/102266290/f5533ca8-633e-402c-8e97-26cc502b9a55" width="200"/>
<img src="https://github.com/Djostanisic/github_users_app/assets/102266290/240c428a-cc11-4d69-9f69-cf3a653e6f24" width="200"/>

## Features

- Displays a list of GitHub users with avatar, login, and ID.
- Implements pagination for efficient loading of large user lists.
- Enables pull-to-refresh functionality for updating the user list.
- Navigates to a detailed user profile screen showcasing name, email, organization, follower/following count, and creation date.
- Handles loading states and displays error messages.

## Technologies Used

- **Architecture:** MVVM (Model-View-ViewModel) with Clean Architecture use cases
- **UI Framework:** Jetpack Compose
- **Language:** Kotlin
- **Network Calls:** Retrofit
- **Dependency Injection:** Dagger Hilt

## Project Structure

The project is organized into the following packages:

- **data:** Contains data classes representing GitHub User and UserDetails. Contains the repository interface and implementation for fetching user data.
- **di:** Handles dependency injection using Dagger Hilt (if implemented).
- **domain:** Contains refined data classes from ```data``` package and use cases.
- **presentation:** Contains composable functions for UI screens (UserList and UserDetails), view models and themes.
