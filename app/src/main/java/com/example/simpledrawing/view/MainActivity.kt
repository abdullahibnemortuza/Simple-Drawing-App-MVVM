package com.example.simpledrawing.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.simpledrawing.data.repository.DrawingRepository
import com.example.simpledrawing.databinding.ActivityMainBinding
import com.example.simpledrawing.viewmodel.DrawingViewModel
import com.example.simpledrawing.viewmodel.DrawingViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: DrawingViewModel by viewModels {
        DrawingViewModelFactory(DrawingRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load previous drawing
        // --- Load auto-save first ---
        val drawingsDir = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Drawings")
        var bitmapToLoad: Bitmap? = null

        if (drawingsDir.exists() && drawingsDir.isDirectory) {
            // Check for autosave.png
            val autoSaveFile = File(drawingsDir, "autosave.png")
            if (autoSaveFile.exists()) {
                bitmapToLoad = BitmapFactory.decodeFile(autoSaveFile.absolutePath)
            } else {
                // Load latest manual save if no auto-save
                val files = drawingsDir.listFiles()?.filter { it.extension == "png" }
                val latestFile = files?.maxByOrNull { it.lastModified() }
                latestFile?.let {
                    bitmapToLoad = BitmapFactory.decodeFile(it.absolutePath)
                }
            }
        }

// Set bitmap if found
        bitmapToLoad?.let { binding.drawingView.setBitmap(it) }

        // Clear button
        binding.btnClear.setOnClickListener {
            binding.drawingView.clearCanvas()
        }

        // Save button (optional, manual save)
        binding.btnSave.setOnClickListener {
            val bitmap = binding.drawingView.getBitmap()
            val fileName = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            viewModel.saveDrawing(bitmap, fileName)
            Toast.makeText(this, "Drawing saved!", Toast.LENGTH_SHORT).show()
        }

        // --- Auto-save on stroke end ---
        binding.drawingView.onBitmapChanged = { bitmap ->
            val fileName = "autosave.png" // keep one auto-save file
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.saveDrawing(bitmap, fileName)
            }
        }
    }
}