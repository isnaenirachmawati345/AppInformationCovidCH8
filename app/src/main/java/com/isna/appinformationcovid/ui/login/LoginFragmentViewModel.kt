package com.isna.appinformationcovid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isna.appinformationcovid.data.Repository
import com.isna.appinformationcovid.data.room.UserEntity
import kotlinx.coroutines.launch

class LoginFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _loginData : MutableLiveData<UserEntity> = MutableLiveData()
    val loginData: LiveData<UserEntity> get() = _loginData

    private val _userPref : MutableLiveData<UserEntity> = MutableLiveData()
    val userPref : LiveData<UserEntity> get() = _userPref

    fun setUserPref(userEntity: UserEntity){
        viewModelScope.launch {
            repository.setUserPref(userEntity)
        }
    }

    fun getUserPref(){
        viewModelScope.launch {
            repository.getUserPref().collect{
                _userPref.postValue(it)
            }
        }
    }


    fun loginUser(username: String, password: String){
        viewModelScope.launch {
            _loginData.postValue(repository.loginUser(username, password))
//            _loginData.value = repository.loginUser(username, password)
        }
    }
}