package com.robertkeazor.myapplication.apprepo

sealed class EmailError {
    object MaxCharLength : EmailError()

    object InvalidEmail : EmailError()

    object EmailMismatch : EmailError()
}