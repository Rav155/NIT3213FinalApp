# 📱 NIT3213 Final App – Student Dashboard Android Application

This is a Kotlin-based Android application developed for the NIT3213 Final Assessment. It features a login interface for students to authenticate using their first name and student ID, and then dynamically loads personalized data such as courses, animals, travel, or other topics depending on the user's `keypass`.

---

## ✅ Features

- 🔐 **Login Screen**
  - Username: Student's **first name**
  - Password: Student **ID** (e.g., `s12345678`)
  - Makes a `POST` request to authenticate and fetch the `keypass`.

- 🧾 **Dashboard Screen**
  - Loads entity data from the backend via `GET` using the `keypass`.
  - Uses a `RecyclerView` to list entity summaries.
  - Supports multiple types of data (courses, animals, travel, etc.) dynamically.

- 📝 **Detail Screen**
  - Shows full information about a selected item.
  - Back navigation and logout functionality included.

- 💉 **Hilt Dependency Injection**
  - Hilt is set up and ready for injecting dependencies (Retrofit, ViewModel, etc.).

---

## 🛠️ Tech Stack

- **Kotlin** (Android)
- **Retrofit** for API communication
- **Gson** for JSON parsing
- **Hilt** for Dependency Injection
- **Coroutines** for asynchronous API calls
- **RecyclerView** for dynamic data display
- **MVVM (Modular Clean Structure)**

---

## 🚀 Getting Started

### 🔧 Requirements

- Android Studio Hedgehog (or newer)
- Gradle 8+
- Kotlin 2.0.21
- Android SDK 30+

### 📥 Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/NIT3213FinalApp.git
cd NIT3213FinalApp
```

### 🧰 Build Instructions

1. **Open in Android Studio**
   File > Open > Select the `NIT3213FinalApp` folder.

2. **Sync Gradle**
   Wait for Gradle to sync and download dependencies.

3. **Run the App**
   Click ▶️ "Run" to launch the app on your emulator or physical device.

---

## 📂 Project Structure

```
📁 com.example.nit3213finalapp
├── MainActivity.kt            # Login screen
├── DashboardActivity.kt       # List of entities
├── DetailActivity.kt          # Full entity details
├── EntityAdapter.kt           # Adapter for RecyclerView
├── MyApp.kt                   # Hilt setup
│
📁 model/
│   ├── Entity.kt              # Generalized data model
│   ├── LoginRequest.kt        # Login request body
│   ├── LoginResponse.kt       # Login API response
│   └── DashboardResponse.kt   # Dashboard API response
│
📁 api/
│   ├── ApiService.kt          # Retrofit endpoints
│   └── RetrofitClient.kt      # Retrofit config
```

---

### 🧪 Testing

Basic test files are present (`ExampleUnitTest.kt`, `ExampleInstrumentedTest.kt`)  
➡️ Extend these for advanced unit testing.

---

## 📘 Notes

- Only fields provided by the API are shown. App dynamically adjusts to different student data.
- Future improvements could include:
  - ViewModel integration
  - Hilt-injected Retrofit
  - UI enhancements and animations

---

## 📜 License

This project is submitted as coursework for the NIT3213 unit and is intended for academic use only.

---

### 📤 Submission Instructions

   ```
   git clone https://github.com/Rav155/NIT3213FinalApp.git

   ```