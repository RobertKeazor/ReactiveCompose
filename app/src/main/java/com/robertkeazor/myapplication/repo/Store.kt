package com.robertkeazor.myapplication.repo

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope

interface Store<T : Action, S : State, I : Interpreter> {
    fun initialize(state: MutableState<S>, scope: CoroutineScope)
    fun interpret(interpreter: I)
}
