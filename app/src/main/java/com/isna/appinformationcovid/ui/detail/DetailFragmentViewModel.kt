package com.isna.appinformationcovid.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isna.appinformationcovid.data.Repository
import com.isna.appinformationcovid.data.room.FavoriteEntity
import com.isna.appinformationcovid.data.room.UserEntity
import kotlinx.coroutines.launch

class DetailFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _dataFavoriteByCountry = MutableLiveData<FavoriteEntity>()
    val dataFavoriteByCountry : LiveData<FavoriteEntity> get() = _dataFavoriteByCountry

    private val _userPref = MutableLiveData<UserEntity>()
    val userPref : LiveData<UserEntity> get() = _userPref

    fun insertFavorite(favoriteEntity: FavoriteEntity){
        viewModelScope.launch {
            repository.insertFavorite(favoriteEntity)
        }
    }

    fun checkFavorite(id_user: Int, country_name: String){
        viewModelScope.launch {
            _dataFavoriteByCountry.postValue(repository.getFavorite(id_user, country_name))
        }
    }

    fun deleteFavorite(id_user: Int, country_name: String){
        viewModelScope.launch {
            repository.deleteFavorite(id_user, country_name)
        }
    }

    fun getUserPref(){
        viewModelScope.launch {
            repository.getUserPref().collect{
                _userPref.postValue(it)
            }
        }
    }
}