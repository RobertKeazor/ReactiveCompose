package com.robertkeazor.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.robertkeazor.myapplication.apprepo.AppActions
import com.robertkeazor.myapplication.apprepo.AppInterpreter
import com.robertkeazor.myapplication.apprepo.AppState
import com.robertkeazor.myapplication.repo.Store
import com.robertkeazor.myapplication.ui.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

typealias AppStore = Store<AppActions, AppState, AppInterpreter>

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A./ surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        loginField(vm.store, vm.state)
                    }
                }
            }
        }
    }
}

@Composable
fun loginField(store: AppStore, state: MutableState<AppState>) {
    TextField(value = state.value.userName,
        onValueChange = {store.interpret(AppInterpreter.EditLogin(it))},
        backgroundColor = Color.White)
    TextField(
        modifier = Modifier.padding(top = 16.dp),
        value = state.value.password,
        onValueChange = {store.interpret(AppInterpreter.EditPassword(it))},
        backgroundColor = Color.White
    )
}
