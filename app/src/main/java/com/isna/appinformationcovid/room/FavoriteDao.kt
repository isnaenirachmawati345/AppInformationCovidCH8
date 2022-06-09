package com.isna.appinformationcovid.data.room

import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoriteEntity: FavoriteEntity): Long

    @Query("SELECT * FROM favoriteentity WHERE id_user= :id_user AND country_name= :country_name")
    suspend fun getFavorite(id_user: Int, country_name: String): FavoriteEntity

    @Query("SELECT * FROM favoriteentity WHERE id_user= :id_user")
    suspend fun getAllFavoriteById(id_user: Int): List<FavoriteEntity>

    @Query("DELETE FROM favoriteentity WHERE id_user= :id_user AND country_name= :country_name")
    suspend fun deleteFavorite(id_user: Int, country_name: String): Int
}