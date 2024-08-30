package com.panducerdas.id.ui.admin.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.AdminEntity
import kotlinx.coroutines.launch

class AdminSignupViewModel(private val appRepository: AppRepository): ViewModel() {

    fun insertAdmin(adminEntity: AdminEntity){
        viewModelScope.launch {
            appRepository.insertAdmin(adminEntity)
        }
    }

}