package com.isna.appinformationcovid.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM userentity")
    suspend fun getAllUser(): UserEntity

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM userentity where username= :username AND password= :password ")
    suspend fun loginUser(username: String, password: String): UserEntity

    @Update
    suspend fun updateUser(userEntity: UserEntity)
}