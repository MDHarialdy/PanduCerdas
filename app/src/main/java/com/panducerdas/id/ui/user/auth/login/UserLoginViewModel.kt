package com.panducerdas.id.ui.user.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.AdminEntity
import com.panducerdas.id.data.database.UserEntity

class UserLoginViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getUser(userEmail: String, passWord: String): LiveData<List<UserEntity>> {
        return appRepository.getUser(userEmail, passWord)
    }
}