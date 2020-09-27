package com.robertkeazor.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.robertkeazor.myapplication.apprepo.AppActions
import com.robertkeazor.myapplication.apprepo.AppInterpreter
import com.robertkeazor.myapplication.apprepo.AppState
import com.robertkeazor.myapplication.repo.Interpreter
import com.robertkeazor.myapplication.repo.Store
import com.robertkeazor.myapplication.ui.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val startNumber = 25
                val age = mutableStateOf(startNumber)
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Greeting("Android", vm.state)
                        submitButton(vm.store)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, age: MutableState<AppState>) {
    Column {
        Text(text = "Hello $name! ${age.value.userName}")
        Text(text = "How are you")
        Row {
            Text(text = "Big Guy", modifier = Modifier.padding(16.dp))
            Checkbox(checked = true, onCheckedChange = { Modifier.padding(vertical = 16.dp) })
        }
    }
}

@Composable
fun submitButton(store: Store<AppActions, AppState, AppInterpreter>) {
    Column {
        Button(onClick = {
            val randomNumber = Random(100)
            store.interpret(AppInterpreter.EditLogin("It Works $randomNumber"))
        }
        ) {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android Robert", mutableStateOf(AppState("Rob", "try")))
    }
}
