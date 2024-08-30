package com.panducerdas.id.ui.admin.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.AdminEntity
import kotlinx.coroutines.launch

class AdminLoginViewModel(private val appRepository: AppRepository): ViewModel(){

    fun getAdmin(adminEmail: String, passWord: String): LiveData<List<AdminEntity>> {
        return appRepository.getAdmin(adminEmail, passWord)
    }
}