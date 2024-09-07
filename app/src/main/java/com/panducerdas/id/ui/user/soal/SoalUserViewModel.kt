package com.panducerdas.id.ui.user.soal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.SoalEntity

class SoalUserViewModel(val appRepository: AppRepository): ViewModel() {
    private val _soal = MutableLiveData<List<SoalEntity>>()
    val soal: LiveData<List<SoalEntity>> = _soal

    // Updated list with 5 questions for elementary school students
    private val dummySoalList = listOf(
        SoalEntity(1, "Apa warna langit di siang hari?", "Biru", "Kuning", "Merah", "Hijau"),
        SoalEntity(2, "Berapa hasil dari 3 + 5?", "6", "7", "8", "9"),
        SoalEntity(3, "Apa nama ibu kota Indonesia?", "Jakarta", "Bandung", "Surabaya", "Medan"),
        SoalEntity(4, "Apa hewan yang bisa terbang?", "Burung", "Kucing", "Sapi", "Ikan"),
        SoalEntity(5, "Berapa jumlah kaki pada laba-laba?", "8", "6", "4", "2")
    )

    fun getAllSoal(): MutableLiveData<List<SoalEntity>> {
        _soal.value = dummySoalList
        return _soal
    }
}
