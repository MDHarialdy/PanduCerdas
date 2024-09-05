package com.panducerdas.id.ui.user.soal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.SoalEntity

class SoalUserViewModel(val appRepository: AppRepository): ViewModel() {
    private val _soal = MutableLiveData<List<SoalEntity>>()
    val soal: LiveData<List<SoalEntity>> = _soal


    private val dummySoalList = listOf(
        SoalEntity(1, "apa nama hewan peliharaanmu?", "Kucing", "kambing", "Angsa", "Hewan"),
        SoalEntity(2, "Soal 2", "A2", "B2", "C2", "D2"),
        SoalEntity(3, "Soal 3", "A3", "B3", "C3", "D3")
    )

    fun getAllSoal(): MutableLiveData<List<SoalEntity>> {
//        val value = appRepository.getAllSoal()
        _soal.value = dummySoalList
        return _soal
    }

}