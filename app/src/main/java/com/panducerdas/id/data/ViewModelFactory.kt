package com.panducerdas.id.data


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.panducerdas.id.data.di.Injection
import com.panducerdas.id.ui.admin.auth.login.AdminLoginViewModel
import com.panducerdas.id.ui.admin.auth.signup.AdminSignupViewModel
import com.panducerdas.id.ui.admin.home.AdminHomeViewModel
import com.panducerdas.id.ui.user.auth.login.UserLoginViewModel
import com.panducerdas.id.ui.user.auth.signup.UserSignupViewModel
import com.panducerdas.id.ui.user.home.UserHomeViewModel
import com.panducerdas.id.ui.user.soal.SoalUserViewModel

class  ViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserLoginViewModel::class.java) -> {
                UserLoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(UserSignupViewModel::class.java) -> {
                UserSignupViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AdminLoginViewModel::class.java) -> {
                AdminLoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AdminSignupViewModel::class.java) -> {
                AdminSignupViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AdminHomeViewModel::class.java) -> {
                AdminHomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(UserHomeViewModel::class.java) -> {
                UserHomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SoalUserViewModel::class.java) -> {
                SoalUserViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun clearInstance() {
            AppRepository.clearInstance()
            INSTANCE = null
        }

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}