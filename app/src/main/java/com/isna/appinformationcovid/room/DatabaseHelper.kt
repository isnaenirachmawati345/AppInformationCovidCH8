package com.isna.appinformationcovid.data.room

class DatabaseHelper(private val favoriteDao: FavoriteDao, private val userDao: UserDao) {
    // Favorite
    suspend fun addFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.addFavorite(favoriteEntity)
    suspend fun getFavorite(id_user: Int, country_name: String): FavoriteEntity = favoriteDao.getFavorite(id_user, country_name)
    suspend fun getAllFavoriteById(id_user: Int): List<FavoriteEntity> = favoriteDao.getAllFavoriteById(id_user)
    suspend fun deleteFavorite(id_user: Int, country_name: String): Int = favoriteDao.deleteFavorite(id_user, country_name)

    // User
//    suspend fun checkUsernameExists(username: String): UserEntity = userDao.checkUsernameExists(username)
    suspend fun getALlUser() = userDao.getAllUser()
    suspend fun insertUser(userEntity: UserEntity) = userDao.insertUser(userEntity)
    suspend fun loginUser(username: String, password: String) = userDao.loginUser(username, password)
    suspend fun updateUser(userEntity: UserEntity) = userDao.insertUser(userEntity)
}