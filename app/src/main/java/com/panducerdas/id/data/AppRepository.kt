package com.panducerdas.id.data

import com.panducerdas.id.data.database.AdminEntity
import com.panducerdas.id.data.database.AppDao

class AppRepository private constructor(
    private val appDao: AppDao
){
     suspend fun insertAdmin(adminEntity: AdminEntity) {
         appDao.insertAdmin(adminEntity)
    }

    suspend fun getAdmin(adminEmail: String) {
        appDao.getAdmin(adminEmail)
    }

    companion object {
        fun clearInstance() {
            instance = null
        }

        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            appDao: AppDao,
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(appDao)
            }.also { instance = it }
    }
}