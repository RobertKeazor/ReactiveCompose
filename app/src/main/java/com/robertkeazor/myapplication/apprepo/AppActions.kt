package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.Action

sealed class AppActions : Action {
    data class Auth(val userName: String, val password: String) : AppActions()
    object SUBMIT : AppActions()
}
