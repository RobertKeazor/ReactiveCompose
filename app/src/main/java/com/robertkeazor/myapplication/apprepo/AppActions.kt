package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.Action

sealed class AppActions : Action {
    data class TypeUserName(val userName: String) : AppActions()
    data class TypePassword(val password: String) : AppActions()
    object SUBMIT : AppActions()
}
