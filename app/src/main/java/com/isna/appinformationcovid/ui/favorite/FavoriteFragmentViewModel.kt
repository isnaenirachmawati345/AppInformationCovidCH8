package com.isna.appinformationcovid.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isna.appinformationcovid.data.Repository
import com.isna.appinformationcovid.data.room.FavoriteEntity
import com.isna.appinformationcovid.data.room.UserEntity
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel(private val repository: Repository): ViewModel() {
    private val _dataFavorite = MutableLiveData<List<FavoriteEntity>>()
    val dataFavorite : LiveData<List<FavoriteEntity>> get() = _dataFavorite

    private val _userPref = MutableLiveData<UserEntity>()
    val userPref : LiveData<UserEntity> get() = _userPref

    fun getDataFavorite(id_user: Int){
        viewModelScope.launch {
            _dataFavorite.postValue(repository.getAllFavoriteById(id_user))
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