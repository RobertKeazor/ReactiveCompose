package com.robertkeazor.myapplication.repo

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class StoreImpl<T : Action, S : State, I : Interpreter>(
    val stateObject: MutableState<S>,
    val creator: ActionCreator<I, T>,
    val scope: CoroutineScope,
    val reducer: Reducer<T, S>
) : Store<T, S, I> {
    val actionStateFlow: MutableStateFlow<T?> = MutableStateFlow(null)
    val states: MutableStateFlow<S?> = MutableStateFlow(null)
    init {
        scope.launch {
            actionStateFlow
                .filterNotNull()
                .map { reducer.reduce(it) }
                .onEach { stateObject.value = it }
        }
    }

    override fun interpret(interpreter: I) {
        scope.launch {
            actionStateFlow.value = creator.sendAction(interpreter)
        }
    }
}
