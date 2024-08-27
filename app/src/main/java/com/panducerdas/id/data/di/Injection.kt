package com.panducerdas.id.data.di

import android.content.Context
import com.panducerdas.id.data.AppRepository
import com.panducerdas.id.data.database.AppDatabase
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): AppRepository = runBlocking{
        val appDatabase = AppDatabase.getDatabase(context)
        AppRepository.getInstance(appDatabase.AppDao())
    }
}