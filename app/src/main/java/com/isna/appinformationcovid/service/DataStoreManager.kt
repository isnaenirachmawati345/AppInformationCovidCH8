package com.isna.appinformationcovid.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.isna.appinformationcovid.data.room.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    companion object {
        private const val DATASTORE_NAME = "user_preferences"
        private val ID_USER_KEY = intPreferencesKey("id_user_key")
        private val NAMA_KEY = stringPreferencesKey("nama_key")
        private val EMAIL_KEY = stringPreferencesKey("email_key")
        private val USERNAME_KEY = stringPreferencesKey("username_key")
        private val PASSWORD_KEY = stringPreferencesKey("password_key")
        private val IMAGE_KEY = stringPreferencesKey("image_key")
        const val DEF_ID = -1
        const val DEF_NAMA = "default_nama"
        const val DEF_EMAIL = "default_email@gmail.com"
        const val DEF_USERNAME = "default_username"
        const val DEF_PASSWORD = "default_password"
        const val DEF_IMAGE = "no_image"
        val Context.userDataStore by preferencesDataStore(DATASTORE_NAME)
    }
    suspend fun setUser(user: UserEntity){
        context.userDataStore.edit { preferences ->
            preferences[ID_USER_KEY] = user.id_user!!.toInt()
            preferences[NAMA_KEY] = user.nama
            preferences[EMAIL_KEY] = user.email
            preferences[USERNAME_KEY] = user.username
            preferences[PASSWORD_KEY] = user.password
            preferences[IMAGE_KEY] = user.image
        }
    }
    fun getUser(): Flow<UserEntity> {
        return context.userDataStore.data.map { preferences ->
            UserEntity(
                preferences[ID_USER_KEY] ?: DEF_ID,
                preferences[NAMA_KEY] ?: DEF_NAMA,
                preferences[EMAIL_KEY] ?: DEF_EMAIL,
                preferences[USERNAME_KEY] ?: DEF_USERNAME,
                preferences[PASSWORD_KEY] ?: DEF_PASSWORD,
                preferences[IMAGE_KEY] ?: DEF_IMAGE
            )
        }
    }
    suspend fun deleteUser(){
        context.userDataStore.edit {
            it.clear()
        }
    }
}