package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.Reducer

class AppReducer : Reducer<AppActions, AppState> {
    override fun reduce(action: AppActions) = when (action) {
        is AppActions.Auth -> AppState(action.userName, action.password)
        AppActions.SUBMIT -> TODO()
    }
}
