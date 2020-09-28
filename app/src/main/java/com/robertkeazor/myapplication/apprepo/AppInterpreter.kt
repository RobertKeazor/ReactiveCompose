package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.Interpreter

sealed class AppInterpreter : Interpreter {
    data class EditEmailAddress(val input: String) : AppInterpreter()
    data class EditEmailConfirm(val input: String) : AppInterpreter()
    object SUBMIT : AppInterpreter()
}
