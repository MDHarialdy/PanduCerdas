package com.panducerdas.id.data

import androidx.lifecycle.LiveData
import com.panducerdas.id.data.database.AdminEntity
import com.panducerdas.id.data.database.AppDao

class AppRepository private constructor(
    private val appDao: AppDao
){
     suspend fun insertAdmin(adminEntity: AdminEntity) {
         appDao.insertAdmin(adminEntity)
    }

    fun getAdmin(adminEmail: String, passWord: String): LiveData<List<AdminEntity>> {
       return appDao.getAdmin(adminEmail, passWord)
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