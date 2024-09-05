package com.panducerdas.id.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDao {


    //USER
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * from userentity WHERE UserEmail = :userEmail AND UserPassword = :passWord")
    fun getUser(userEmail: String, passWord: String): LiveData<List<UserEntity>>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateUser(userProperties: UserEntity)


    //ADMIN
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAdmin(adminProperties: AdminEntity)

    @Query("SELECT * from adminentity WHERE AdminEmail = :adminEmail AND AdminPassword = :passWord")
    fun getAdmin(adminEmail: String, passWord: String): LiveData<List<AdminEntity>>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateAdmin(adminProperties: AdminEntity)

    //EXAM
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClass(artikel: List<ClassEntity>)

}