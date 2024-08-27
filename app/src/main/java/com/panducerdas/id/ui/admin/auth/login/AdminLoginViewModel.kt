package com.panducerdas.id.ui.admin.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panducerdas.id.data.AppRepository
import kotlinx.coroutines.launch

class AdminLoginViewModel(private val appRepository: AppRepository): ViewModel(){

    fun getAdmin(adminEmail: String){
        viewModelScope.launch {
            try {
                appRepository.getAdmin(adminEmail)
            } catch (e: Exception) {
                Log.e("AdminLoginViewModel", "Error inserting admin: ${e.message}")
            }
        }
    }
}