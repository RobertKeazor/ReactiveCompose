package com.robertkeazor.myapplication.repo

interface Store<T : Action, S : State, I : Interpreter> {
    fun interpret(interpreter: I)
}
