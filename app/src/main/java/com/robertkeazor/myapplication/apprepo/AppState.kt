package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.State

data class AppState(
    val emailAddress: String,
    val emailConfirmAddress: String
) : State
