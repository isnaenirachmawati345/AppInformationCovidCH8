package com.isna.appinformationcovid.data

import androidx.lifecycle.LiveData
import com.isna.appinformationcovid.data.room.DatabaseHelper
import com.isna.appinformationcovid.data.room.FavoriteEntity
import com.isna.appinformationcovid.data.room.UserEntity
import com.isna.appinformationcovid.data.service.ApiHelper
import kotlinx.coroutines.flow.Flow

class Repository(private val apiHelper: ApiHelper, private val databaseHelper: DatabaseHelper, private val dataStoreManager: DataStoreManager) {
    // Retrofit
    suspend fun getAllCountryCases() = apiHelper.getAllCountryCases()
//    suspend fun getCountryCasesById(country: String) = apiHelper.getCountryCasesById(country)
    suspend fun getAllDataCases() = apiHelper.getAllDataCases()
    
    // Database
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity) = databaseHelper.addFavorite(favoriteEntity)
    suspend fun getFavorite(id_user: Int, country_name: String): FavoriteEntity = databaseHelper.getFavorite(id_user, country_name)
    suspend fun getAllFavoriteById(id_user: Int): List<FavoriteEntity> = databaseHelper.getAllFavoriteById(id_user)
    suspend fun deleteFavorite(id_user: Int, country_name: String): Int = databaseHelper.deleteFavorite(id_user, country_name)

    // DataStore
    suspend fun setUserPref(userEntity: UserEntity) = dataStoreManager.setUser(userEntity)
    suspend fun getUserPref(): Flow<UserEntity> = dataStoreManager.getUser()
    suspend fun deleteUserPref() = dataStoreManager.deleteUser()

    // User
//    suspend fun checkUsernameExists(username: String): UserEntity = databaseHelper.checkUsernameExists(username)
//    suspend fun getAllUser() = databaseHelper.getALlUser()
    suspend fun insertUser(userEntity: UserEntity) = databaseHelper.insertUser(userEntity)
    suspend fun loginUser(username: String, password: String) = databaseHelper.loginUser(username, password)
    suspend fun updateUser(userEntity: UserEntity) = databaseHelper.updateUser(userEntity)
}