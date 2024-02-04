package com.ren.mvvmandroid15.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ren.mvvmandroid15.data.models.User
import com.ren.mvvmandroid15.data.preferences.PreferencesHelper
import com.ren.mvvmandroid15.utils.UiState

class SharedPreferencesViewModel : ViewModel() {

    private val _userInfoLiveData = MutableLiveData<UiState<User>>()
    val userInfoLiveData: LiveData<UiState<User>> = _userInfoLiveData

    fun saveUserInfo(user: User) = with(user) {
        PreferencesHelper.name = name
        PreferencesHelper.age = age
    }

    fun getUserInfo() = with(PreferencesHelper) {
        val user = User(
            name = name ?: "",
            age = age
        )
        userInfoLiveData.value?.copy(isLoading = false, success = user)?.let { newValue ->
            _userInfoLiveData.value = newValue
        }
    }
}