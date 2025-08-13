package com.example.simpledrawing.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

class DrawingRepository(private val context: Context) {

    fun saveImage(bitmap: Bitmap, fileName: String) {
        // Folder: Android/data/<package>/files/Pictures/Drawings
        val directory = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "Drawings"
        )
        if (!directory.exists()) directory.mkdirs()

        // Create file
        val file = File(directory, "$fileName.png")
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.flush()
        fos.close()
    }
}