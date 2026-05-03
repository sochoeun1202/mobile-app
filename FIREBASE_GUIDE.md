# Finalized Guide: Connecting & Distributing to Firebase

This guide reflects the working configuration established for your project.

## Step 1: Project Configuration (Complete)
Your project is configured with the following stable toolset:
- **Gradle**: 8.12.1
- **Android Gradle Plugin (AGP)**: 8.9.1
- **SDK**: 36
- **Kotlin**: 2.0.21

The `app/build.gradle.kts` is set up with your specific `appId` and the explicit configuration required for Kotlin DSL.

## Step 2: One-time Authentication
To upload builds, you must be logged into the Firebase CLI on your machine.

1.  **Install Firebase CLI** (if not already installed):
    ```bash
    npm install -g firebase-tools
    ```
2.  **Login**:
    ```bash
    firebase login
    ```

## Step 3: Build and Distribute
Run this command from the project root to build the APK and upload it to the **App Distribution** dashboard:

```powershell
./gradlew app:assembleDebug app:appDistributionUploadDebug
```

## Step 4: Accessing the App
1.  Go to the [Firebase Console](https://console.firebase.google.com/).
2.  Select **Release & Monitor > App Distribution**.
3.  You will see your latest build with the release note "New build from Gemini CLI".
4.  Add testers by email to invite them to download the app.

## Step 5: Updating the App Version
When you make changes to your app and want to upload a new version, you should update the versioning in **`app/build.gradle.kts`**:

1.  Open **`app/build.gradle.kts`**.
2.  Find the **`defaultConfig`** block:
    ```kotlin
    defaultConfig {
        ...
        versionCode = 2  // Incremented for every new release (integer)
        versionName = "1.1" // The version string shown to users
        ...
    }
    ```
3.  **`versionCode`**: Must be a whole number (1, 2, 3...). Firebase and the Play Store use this to know which build is newer. Always increase this by at least 1 for a new upload.
4.  **`versionName`**: Can be anything (e.g., "1.0.1", "2.0-beta"). This is for your own tracking and for users to see.

---

### Troubleshooting "Red Lines" in Android Studio
If you see red lines in your `build.gradle.kts` files:
- Click the **Sync Project with Gradle Files** icon (the elephant).
- This allows Android Studio to index the plugins and recognize the `firebaseAppDistribution` block.
- Once the sync is "Successful", the red lines will disappear.

### Troubleshooting Build Failures
- **Checksum Error**: If you see a checksum error, ensure `gradle/wrapper/gradle-wrapper.properties` has the correct `distributionSha256Sum` for the Gradle version being used.
- **Login Error**: If you get a 401 error during upload, run `firebase login --reauth`.
