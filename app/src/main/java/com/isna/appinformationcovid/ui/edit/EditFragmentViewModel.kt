package com.than.covidapp_challengeschapter7.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.than.covidapp_challengeschapter7.data.Repository
import com.than.covidapp_challengeschapter7.data.room.UserEntity
import kotlinx.coroutines.launch

class EditFragmentViewModel( private val repository: Repository): ViewModel() {
    private val _dataUserPref = MutableLiveData<UserEntity>()
    val dataUserPref : LiveData<UserEntity> get() = _dataUserPref

    fun getUserPref(){
        viewModelScope.launch {
            repository.getUserPref().collect{
                _dataUserPref.postValue(it)
            }
        }
    }

    fun updateUser(userEntity: UserEntity){
        viewModelScope.launch {
            repository.updateUser(userEntity)
        }
    }

    fun setUserPref(userEntity: UserEntity){
        viewModelScope.launch {
            repository.setUserPref(userEntity)
        }
    }

}