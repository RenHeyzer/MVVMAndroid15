package com.ren.mvvmandroid15.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ren.mvvmandroid15.data.models.User
import com.ren.mvvmandroid15.data.preferences.UserPreferencesHelper
import com.ren.mvvmandroid15.data.repositories.UserRepository
import com.ren.mvvmandroid15.utils.UiState

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _userInfoLiveData = MutableLiveData<UiState<User>>()
    val userInfoLiveData: LiveData<UiState<User>> = _userInfoLiveData

    fun saveUserInfo(user: User) {
        repository.addUser(user)
    }

    fun getUserInfo() {
        val userInfo = repository.getUserInfo()
        _userInfoLiveData.value = UiState(isLoading = false, success = userInfo)
    }
}