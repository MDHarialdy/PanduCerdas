package com.panducerdas.id.ui.user.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.UserEntity
import kotlinx.coroutines.launch

class UserSignupViewModel(private val appRepository: AppRepository): ViewModel() {

    fun insertUser(userEntity: UserEntity){
        viewModelScope.launch {
            appRepository.insertUser(userEntity)
        }
    }

}