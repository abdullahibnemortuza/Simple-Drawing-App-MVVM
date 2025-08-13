Simple Drawing App
A lightweight Android drawing application built with Kotlin, XML, ViewBinding, and MVVM. Users can draw on a canvas, clear their drawings, save them as images, and automatically preserve their work across app restarts.

Features
🎨 Freehand Drawing: Draw on the canvas with your finger.

🗑️ Clear Canvas: Erase your drawing with a single tap.

💾 Save Drawings: Save drawings as PNG files with timestamp.

⏱️ Auto-Save: Automatically saves drawing after each stroke.

🔄 Persistent Drawings: Auto-loads the last drawing when the app reopens.

🖤 Dark Modern Theme: Comfortable dark canvas with colorful buttons.

Tech Stack
Language: Kotlin

UI: XML layouts + ViewBinding

Architecture: MVVM (Model-View-ViewModel)

Storage: Internal app-specific storage (/Android/data/<package>/files/Pictures/Drawings)

Coroutines: For background auto-saving

Project Structure
bash
Copy
Edit
app/
 ├── data/
 │    └── repository/
 │         └── DrawingRepository.kt   # Handles saving images
 ├── ui/
 │    ├── customviews/
 │    │    └── DrawingView.kt        # Custom view for drawing
 │    └── drawing/
 │         ├── DrawingViewModel.kt
 │         └── DrawingViewModelFactory.kt
 ├── MainActivity.kt
 └── res/
      ├── layout/activity_main.xml
      └── values/themes.xml
Installation & Running
Clone the repository:

bash
Copy
Edit
git clone <repo-url>
Open the project in Android Studio.

Make sure ViewBinding is enabled in build.gradle.

Build and run the app on an emulator or Android device (API 21+).

Usage
Draw on the canvas with your finger.

Press Clear to erase the canvas.

Press Save to save your drawing with a timestamp.

The app auto-saves after each stroke and loads your last drawing when reopened.

Folder Location for Save
