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
