package com.isna.appinformationcovid.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteEntity::class, UserEntity::class],
    version = 2,
    exportSchema = false
)
abstract class CovidDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun userDao(): UserDao
}