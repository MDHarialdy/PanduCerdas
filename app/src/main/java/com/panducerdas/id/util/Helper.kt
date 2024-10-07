package com.panducerdas.id.util

import ai.picovoice.porcupine.PorcupineManager
import android.content.Context
import android.util.Log

private lateinit var porcupineManager: PorcupineManager

fun initPorcupine(context: Context) {
    porcupineManager = PorcupineManager.Builder()
        .setAccessKey("YOUR_ACCESS_KEY")  // Masukkan akses kunci Picovoice kamu
        .setKeywordPath("res/raw/hai_pandu.ppn")  // Path ke keyword .ppn
        .setSensitivity(0.9f)  // Atur sensitivitas
        .setErrorCallback { error ->
            Log.e("Porcupine", "Error: ${error.message}")
        }.build(context) { keywordIndex ->
            if (keywordIndex >= 0) {
                respondToKeyword()
            }
        }

    // Mulai Porcupine untuk mendengarkan kata kunci
    porcupineManager.start()
}

// Fungsi untuk menghentikan pendeteksian sementara (onPause)
fun pausePorcupine() {
    try {
        if (::porcupineManager.isInitialized) {
            porcupineManager.stop()
            Log.d("Porcupine", "Porcupine paused.")
        }
    } catch (e: Exception) {
        Log.e("Porcupine", "Error stopping Porcupine: ${e.message}")
    }
}

// Fungsi untuk membersihkan resource saat aplikasi dihentikan (onDestroy)
fun destroyPorcupine() {
    try {
        if (::porcupineManager.isInitialized) {
            porcupineManager.delete()
            Log.d("Porcupine", "Porcupine destroyed.")
        }
    } catch (e: Exception) {
        Log.e("Porcupine", "Error deleting Porcupine: ${e.message}")
    }
}

// Fungsi untuk merespon saat kata kunci 'Pandu' terdeteksi
fun respondToKeyword() {
    Log.d("Porcupine", "Kata kunci 'Pandu' terdeteksi!")
}
