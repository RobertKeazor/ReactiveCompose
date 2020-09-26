package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.State

data class AppState(
    val userName: String,
    val password: String
) : State
