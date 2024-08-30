package com.panducerdas.id.data


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.panducerdas.id.data.di.Injection
import com.panducerdas.id.ui.admin.auth.login.AdminLoginViewModel
import com.panducerdas.id.ui.admin.auth.signup.AdminSignupViewModel
import com.panducerdas.id.ui.user.auth.login.UserLoginViewModel

class  ViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserLoginViewModel::class.java) -> {
                UserLoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AdminLoginViewModel::class.java) -> {
                AdminLoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AdminSignupViewModel::class.java) -> {
                AdminSignupViewModel(repository) as T
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