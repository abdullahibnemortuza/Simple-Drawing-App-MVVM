package com.example.simpledrawing.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.simpledrawing.data.repository.DrawingRepository

class DrawingViewModel(private val repository: DrawingRepository) : ViewModel() {

    fun saveDrawing(bitmap: Bitmap, fileName: String) {
        repository.saveImage(bitmap, fileName)
    }
}