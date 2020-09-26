package com.robertkeazor.myapplication.repo

interface ActionCreator<T : Interpreter, A : Action> {
    suspend fun sendAction(item: T): A
}
