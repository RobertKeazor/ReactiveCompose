package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.Reducer
import javax.inject.Inject

class AppReducer @Inject constructor() : Reducer<AppActions, AppState> {
    override fun reduce(previousState: AppState, action: AppActions) = when (action) {
        is AppActions.TypeUserName -> previousState.copy(userName = action.userName)
        is AppActions.TypePassword -> previousState.copy(password = action.password)
        AppActions.SUBMIT -> TODO()
    }
}
