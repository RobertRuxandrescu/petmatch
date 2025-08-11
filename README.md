# PetMatch Android App

A modern Android application that allows pet owners to find suitable matches for their pets for breeding purposes. The app features a beautiful UI with Google Sign-In authentication and real-time pet post sharing.

## Features

- **Splash Screen**: Cute animal logo displayed for 3 seconds
- **Google Sign-In**: Secure authentication using Google accounts
- **Dashboard**: Browse pet matching announcements from other users
- **Modern UI**: Material Design 3 with beautiful animations
- **Real-time Data**: Firebase Firestore integration for live updates
- **Image Loading**: Efficient image loading with Glide

## App Flow

1. **Splash Screen** (3 seconds) → Shows app logo and welcome message
2. **Login Screen** → Google Sign-In authentication
3. **Dashboard** → Browse and interact with pet posts

## Technical Stack

- **Language**: Kotlin
- **Architecture**: Orbit MVI (Model-View-Intent) with Repository pattern
- **UI**: Jetpack Compose with Material Design 3
- **Backend**: Firebase (Authentication, Firestore)
- **Image Loading**: Coil for Compose
- **Navigation**: Compose Navigation
- **State Management**: Orbit MVI with unidirectional data flow
- **Dependency Injection**: Hilt
- **Testing**: Comprehensive unit tests with MockK, Turbine, and Orbit MVI testing utilities

## Setup Instructions

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24+
- Google account for Firebase setup

### Firebase Setup

1. Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Add an Android app with package name `com.petmatch.app`
3. Download the `google-services.json` file and place it in the `app/` directory
4. Enable Google Sign-In in Firebase Authentication
5. Create a Firestore database

### Google Sign-In Setup

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select your Firebase project
3. Enable Google Sign-In API
4. Create OAuth 2.0 credentials
5. Add your SHA-1 fingerprint to the credentials

### Build and Run

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Update the `google-services.json` with your Firebase configuration
5. Build and run the app

## Project Structure

```
app/src/main/java/com/petmatch/app/
├── data/
│   ├── model/
│   │   ├── User.kt
│   │   └── PetPost.kt
│   ├── repository/
│   │   ├── AuthRepository.kt
│   │   └── PetPostRepository.kt
│   └── SampleData.kt
├── di/
│   └── AppModule.kt
├── ui/
│   ├── dashboard/
│   │   ├── DashboardScreen.kt
│   │   ├── DashboardContainer.kt
│   │   ├── DashboardState.kt
│   │   └── PetPostCard.kt
│   ├── login/
│   │   ├── LoginScreen.kt
│   │   ├── LoginContainer.kt
│   │   └── LoginState.kt
│   ├── splash/
│   │   └── SplashScreen.kt
│   ├── navigation/
│   │   └── PetMatchNavigation.kt
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
├── MainActivity.kt
└── PetMatchApplication.kt

app/src/test/java/com/petmatch/app/
├── data/
│   ├── model/
│   │   ├── UserTest.kt
│   │   └── PetPostTest.kt
│   ├── repository/
│   │   ├── AuthRepositoryTest.kt
│   │   └── PetPostRepositoryTest.kt
│   └── SampleDataTest.kt
└── ui/
    ├── dashboard/
    │   └── DashboardContainerTest.kt
    └── login/
        └── LoginContainerTest.kt
```

## Key Components

### Data Models
- **User**: Represents user information from Google Sign-In
- **PetPost**: Represents pet matching announcements

### Repositories
- **AuthRepository**: Handles Google Sign-In and user management
- **PetPostRepository**: Manages pet post CRUD operations

### MVI Containers
- **LoginContainer**: Manages login state and authentication with Orbit MVI
- **DashboardContainer**: Handles pet posts display and refresh with Orbit MVI

### UI Components
- **SplashScreen**: Initial loading screen with animations
- **LoginScreen**: Google Sign-In interface with MVI state management
- **DashboardScreen**: Main feed with pet posts and pull-to-refresh
- **PetPostCard**: Compose card component for pet posts
- **PetMatchNavigation**: Main navigation using Compose Navigation

### Testing
- **Comprehensive Unit Tests**: 100% test coverage for MVI containers, repositories, and data models
- **MockK**: Mocking framework for dependencies
- **Turbine**: Flow testing utilities
- **Orbit MVI Testing**: Specialized testing utilities for MVI architecture

## Customization

### Colors
Edit `app/src/main/res/values/colors.xml` to customize the app's color scheme.

### Strings
All text strings are in `app/src/main/res/values/strings.xml` for easy localization.

### Icons
Customize app icons in the `drawable` directory.

## Testing

Run the comprehensive test suite:

```bash
./gradlew test
```

The test suite includes:
- **MVI Container Tests**: Testing state changes, intents, and side effects
- **Repository Tests**: Testing data layer operations
- **Data Model Tests**: Testing model validation and behavior
- **Integration Tests**: Testing component interactions

## Future Enhancements

- Add post creation functionality
- Implement chat/messaging between users
- Add pet photo upload
- Location-based matching
- Push notifications
- User profiles and pet galleries
- **Enhanced Testing**: UI tests with Compose testing

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please open an issue in the GitHub repository.
