package com.robertkeazor.myapplication

import android.os.Bundle
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
import com.robertkeazor.myapplication.ui.MyApplicationTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val startNumber = 25
                val age = mutableStateOf(startNumber)
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Greeting("Android", age)
                        submitButton(age = age)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, age: MutableState<Int>) {
    Column {
        Text(text = "Hello $name! ${age.value}")
        Text(text = "How are you")
        Row {
            Text(text = "Big Guy", modifier = Modifier.padding(16.dp))
            Checkbox(checked = true, onCheckedChange = { Modifier.padding(vertical = 16.dp) })
        }

    }
    
}

@Composable
fun submitButton(age: MutableState<Int>) {
    Column {
        Button(onClick = {
            val oldAge = age.value
            age.value = oldAge + 1
        }
        ) {

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android Robert", mutableStateOf(15))
    }
}
