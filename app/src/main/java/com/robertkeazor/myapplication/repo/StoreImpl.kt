package com.robertkeazor.myapplication.repo

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoreImpl<T : Action, S : State, I : Interpreter> @Inject constructor(
    val creator: ActionCreator<I, T>,
    val reducer: Reducer<T, S>
) : Store<T, S, I> {
    lateinit var scope: CoroutineScope
    lateinit var stateObject: MutableState<S>
    val actionStateFlow: MutableStateFlow<T?> = MutableStateFlow(null)
    val states: MutableStateFlow<S?> = MutableStateFlow(null)

override fun initialize(state: MutableState<S>, scope: CoroutineScope) {
    this.stateObject = state
        scope.launch {
            actionStateFlow
                .filterNotNull()
                .map { reducer.reduce(it) }
                .onEach { stateObject.value = it }
        }
    this.scope = scope
    }

    override fun interpret(interpreter: I) {
        scope.launch {
            actionStateFlow.value = creator.sendAction(interpreter)
        }
    }
}
