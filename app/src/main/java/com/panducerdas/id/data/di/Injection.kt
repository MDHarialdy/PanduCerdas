package com.panducerdas.id.data.di

import android.content.Context
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.AppDatabase
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): AppRepository = runBlocking{
        val appDatabase = AppDatabase.getDatabase(context)
        val pref = UserPreference.getInstance(context.dataStore)
        val user = pref.getSession().first()
        val apiService = ApiConfig.getApiService(user.token)
        AppRepository.getInstance(carakaDatabase.CarakaDao(), apiService, pref)
    }
}