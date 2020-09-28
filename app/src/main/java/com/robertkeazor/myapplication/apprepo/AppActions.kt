package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.Action

sealed class AppActions : Action {
    data class TypeEmailAddress(val emailAddress: String) : AppActions()
    data class TypeConfirmAddress(val emailConfirm: String) : AppActions()
    object SUBMIT : AppActions()
}
