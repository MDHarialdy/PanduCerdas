package com.panducerdas.id.ui.user.soal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.SoalEntity

class SoalUserViewModel(val appRepository: AppRepository): ViewModel() {
    private val _soal = MutableLiveData<List<SoalEntity>>()
    val soal: LiveData<List<SoalEntity>> = _soal

    fun getAllSoal(): MutableLiveData<List<SoalEntity>> {
        val value = appRepository.getAllSoal()
        _soal.value = value.value
        return _soal
    }

}