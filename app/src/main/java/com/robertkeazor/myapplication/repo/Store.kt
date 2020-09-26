package com.robertkeazor.myapplication.repo

import kotlinx.coroutines.CoroutineScope

interface Store<T : Action, S : State, I : Interpreter> {
    fun initialize(scope: CoroutineScope)
    fun interpret(interpreter: I)
}
