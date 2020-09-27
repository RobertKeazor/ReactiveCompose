package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.ActionCreator
import javax.inject.Inject

class AppActionCreator @Inject constructor() : ActionCreator<AppInterpreter, AppActions> {
    override suspend fun sendAction(item: AppInterpreter): AppActions = when (item) {
        is AppInterpreter.EditLogin -> AppActions.TypeUserName(item.input)
        is AppInterpreter.EditPassword -> AppActions.TypePassword(item.input)
        AppInterpreter.SUBMIT -> TODO()
    }
}
