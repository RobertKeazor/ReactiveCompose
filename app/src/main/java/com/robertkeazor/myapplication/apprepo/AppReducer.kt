package com.robertkeazor.myapplication.apprepo

import com.robertkeazor.myapplication.repo.Reducer
import javax.inject.Inject

class AppReducer @Inject constructor() : Reducer<AppActions, AppState> {
    override fun reduce(action: AppActions) = when (action) {
        is AppActions.Auth -> AppState(action.userName, action.password)
        AppActions.SUBMIT -> TODO()
    }
}
