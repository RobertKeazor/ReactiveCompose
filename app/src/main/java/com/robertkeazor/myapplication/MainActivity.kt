package com.robertkeazor.myapplication

import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.robertkeazor.myapplication.apprepo.AppActions
import com.robertkeazor.myapplication.apprepo.AppInterpreter
import com.robertkeazor.myapplication.apprepo.AppState
import com.robertkeazor.myapplication.repo.Store
import com.robertkeazor.myapplication.ui.MyApplicationTheme
import com.robertkeazor.myapplication.ui.topActionBar
import com.robertkeazor.myapplication.ui.submitButton
import dagger.hilt.android.AndroidEntryPoint

typealias AppStore = Store<AppActions, AppState, AppInterpreter>

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()
    lateinit var legalText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        legalText = resources.getString(R.string.legal_text)
        setContent {
            MyApplicationTheme {
                // A./ surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    EmailPluginContent(vm, legalText)
                }
            }
        }
    }
}

@Composable
fun loginField(store: AppStore, state: MutableState<AppState>) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text("Email Address")
        TextField(
            value = state.value.userName,
            onValueChange = {store.interpret(AppInterpreter.EditLogin(it))},
            backgroundColor = Color.White)

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Confirm Email Address"
        )
        TextField(
            value = state.value.password,
            onValueChange = {store.interpret(AppInterpreter.EditPassword(it))},
            backgroundColor = Color.White
        )
    }
}

@Composable
fun legalText(legalText: String) {
    Text(modifier = Modifier.padding(12.dp),
        text = legalText
    )
}

@Composable
fun EmailTypeActionBar(typeOfCustomerEmail: String) {
    TopAppBar(backgroundColor = topActionBar) {
        Text(
            modifier = Modifier.gravity(Alignment.CenterVertically),
            text = typeOfCustomerEmail,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center)
    }
}

@Composable
fun SubmitButton() {
    Button(
        onClick = { },
        backgroundColor = submitButton
    ) {
        Text(
            color = Color.White,
            text = "Update Email"
        )
    }
}

@Composable
fun EmailPluginContent(vm: MainViewModel, legalText: String) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Row {
           EmailTypeActionBar("Primary Personal Email")
        }
        Row {
            loginField(vm.store, vm.state)
        }
        Row {
            legalText(legalText)
        }
        Row(modifier = Modifier.align(Alignment.End).padding(end = 10.dp)) {
            SubmitButton()
        }
    }
}

