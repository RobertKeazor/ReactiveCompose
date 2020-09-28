package com.robertkeazor.myapplication

import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertkeazor.myapplication.apprepo.AppActionCreator
import com.robertkeazor.myapplication.apprepo.AppActions
import com.robertkeazor.myapplication.apprepo.AppInterpreter
import com.robertkeazor.myapplication.apprepo.AppState
import com.robertkeazor.myapplication.apprepo.AppReducer
import com.robertkeazor.myapplication.repo.Store
import com.robertkeazor.myapplication.repo.StoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

class MainViewModel @ViewModelInject constructor(
    val store: Store<AppActions, AppState, AppInterpreter>,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val state: MutableState<AppState> = mutableStateOf(AppState(
        "fake@fake.com",
        View.GONE,
        "",
        View.GONE,
        emailConfirmFieldEnabled = true,
        emailSubmitButtonEnabled = false,
        emailError = null
        )
    )
    init {
        store.initialize(state, viewModelScope)
    }
}

@Module
@InstallIn(ActivityComponent::class)
object StoreModule {
    @Provides
    fun provideStore(
        creator: AppActionCreator,
        reducer: AppReducer
    ): Store<AppActions, AppState, AppInterpreter> = StoreImpl(
        creator,
        reducer
    )
}
