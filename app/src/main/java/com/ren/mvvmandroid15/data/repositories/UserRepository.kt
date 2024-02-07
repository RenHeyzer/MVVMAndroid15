package com.ren.mvvmandroid15.data.repositories

import com.ren.mvvmandroid15.data.models.User
import com.ren.mvvmandroid15.data.preferences.UserPreferencesHelper

class UserRepository {

    fun getUserInfo() = with(UserPreferencesHelper) {
        if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            User(
                name = name!!,
                age = age,
                email = email!!,
                password = password!!
            )
        } else {
            User(
                name = "",
                age = 0,
                email = "",
                password = ""
            )
        }
    }

    fun addUser(user: User) = with(UserPreferencesHelper) {
        name = user.name
        age = user.age
        email = user.email
        password = user.password
    }
}