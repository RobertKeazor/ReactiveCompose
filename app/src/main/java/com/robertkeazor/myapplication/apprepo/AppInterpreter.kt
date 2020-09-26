package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.Interpreter

sealed class AppInterpreter : Interpreter {
    data class EditLogin(val input: String) : AppInterpreter()
    data class EditPassword(val input: String) : AppInterpreter()
    object SUBMIT : AppInterpreter()
}
