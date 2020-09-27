package com.robertkeazor.myapplication.repo

import androidx.compose.runtime.MutableState

interface Reducer<T : Action, S : State> {
    fun reduce(previousState: S, action: T): S
}
