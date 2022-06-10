package com.isna.appinformationcovid.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isna.appinformationcovid.data.Repository
import com.isna.appinformationcovid.data.room.UserEntity
import kotlinx.coroutines.launch

class RegisterFragmentViewModel(private val repository: Repository): ViewModel() {
    private val _userData = MutableLiveData<UserEntity>()
    val userData : LiveData<UserEntity> get() = _userData

//    fun checkUsernameExists(username: String){
//        viewModelScope.launch {
//            _userData.postValue(repository.checkUsernameExists(username))
//        }
//    }

    fun insertUser(userEntity: UserEntity){
        viewModelScope.launch {
            repository.insertUser(userEntity)
        }
    }


}