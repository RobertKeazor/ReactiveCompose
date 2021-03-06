package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.ActionCreator
import javax.inject.Inject

class AppActionCreator @Inject constructor() : ActionCreator<AppInterpreter, AppActions> {
    override suspend fun sendAction(item: AppInterpreter): AppActions = when (item) {
        is AppInterpreter.EditEmailAddress -> AppActions.TypeEmailAddress(item.input)
        is AppInterpreter.EditEmailConfirm -> AppActions.TypeConfirmAddress(item.input)
        AppInterpreter.SUBMIT -> TODO()
    }
}
