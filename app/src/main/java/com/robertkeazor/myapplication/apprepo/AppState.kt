package com.robertkeazor.myapplication.apprepo

import android.view.View
import com.robertkeazor.myapplication.repo.State

data class AppState(
    val emailAddress: String,
    val emailAddressErrorVisibility: Int = View.GONE,
    val emailConfirmAddress: String,
    val emailConfirmAddressErrorVisibility: Int = View.GONE,
    val emailConfirmFieldEnabled: Boolean,
    val emailSubmitButtonEnabled: Boolean = false,
    val emailError: EmailError?
) : State
