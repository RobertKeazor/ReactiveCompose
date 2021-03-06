package com.robertkeazor.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
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
import com.robertkeazor.myapplication.apprepo.EmailError
import com.robertkeazor.myapplication.helpers.GlobalConstants
import com.robertkeazor.myapplication.repo.Store
import com.robertkeazor.myapplication.ui.MyApplicationTheme
import com.robertkeazor.myapplication.ui.topActionBar
import com.robertkeazor.myapplication.ui.submitButton
import com.robertkeazor.myapplication.ui.submitButtonDisabled
import dagger.hilt.android.AndroidEntryPoint

typealias AppStore = Store<AppActions, AppState, AppInterpreter>

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()
    lateinit var legalText: String
    lateinit var emailErrorIsNotValidError: String
    lateinit var emailErrorOver50Error: String
    lateinit var emailErrorConfirmNoMatch: String
    lateinit var emailUpdateButtonLabel: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        legalText = resources.getString(R.string.legal_text)
        emailErrorIsNotValidError = resources.getString(R.string.email_error_not_valid)
        emailErrorOver50Error = resources.getString(R.string.email_error_over_50)
        emailErrorConfirmNoMatch = resources.getString(R.string.email_confirm_no_match)
        emailUpdateButtonLabel = resources.getString(R.string.submit_button_text)
        setContent {
            MyApplicationTheme {
                // A./ surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    EmailPluginContent(
                            emailErrorIsNotValidError,
                            emailErrorOver50Error,
                            emailErrorConfirmNoMatch,
                            emailUpdateButtonLabel,
                            GlobalConstants.ACTION_BAR_TILE_LABEL,
                            GlobalConstants.EMAIL_ADDRESS_LABEL,
                            GlobalConstants.CONFIRM_EMAIL_ADDRESS_LABEL,
                            vm,
                            legalText
                    )
                }
            }
        }
    }
}

@Composable
fun EmailInputForm(emailErrorNotValidError: String,
                   emailErrorOver50Error: String,
                   emailConfirmErrorNoMatch: String,
                   emailAddressLabel: String,
                   emailConfirmAddressLabel: String,
                   store: AppStore,
                   state: MutableState<AppState>) {
    Column(
            modifier = Modifier.padding(12.dp)
    ) {
        EmailEditTextAddress(
                emailErrorNotValidError,
                emailErrorOver50Error,
                emailAddressLabel,
                store,
                state)

        EmailConfirmTextAddress(
                emailConfirmErrorNoMatch,
                emailConfirmAddressLabel,
                store,
                state)
    }
}

@Composable
fun EmailEditTextAddress(emailErrorNotValidError: String,
                         emailErrorOver50Error: String,
                         emailAddressLabel: String,
                         store: AppStore,
                         state: MutableState<AppState>) {
    TextField(
            label = { Text(emailAddressLabel) },
            value = state.value.emailAddress,
            isErrorValue = state.value.emailError != null,
            onValueChange = { store.interpret(AppInterpreter.EditEmailAddress(it)) },
            backgroundColor = Color.White
    )
    Text(
            textAlign = TextAlign.Center,
            text = when (state.value.emailError) {
                EmailError.MaxCharLength -> emailErrorOver50Error
                EmailError.InvalidEmail -> emailErrorNotValidError
                else -> ""
            },
            style = MaterialTheme.typography.caption.copy(color = Color.Red),
    )
}

@Composable
fun EmailConfirmTextAddress(emailConfirmErrorNoMatch: String,
                            emailConfirmAddressLabel: String,
                            store: AppStore,
                            state: MutableState<AppState>) {

    TextField(
            modifier = Modifier.padding(top = 16.dp),
            label = { Text(emailConfirmAddressLabel) },
            value = state.value.emailConfirmAddress,
            onValueChange = {
                if (state.value.emailConfirmFieldEnabled)
                    store.interpret(AppInterpreter.EditEmailConfirm(it))
            },
            isErrorValue = state.value.emailError != null,
            backgroundColor = Color.White
    )
    Text(
            textAlign = TextAlign.Center,
            text = when (state.value.emailError) {
                EmailError.EmailMismatch -> emailConfirmErrorNoMatch
                else -> ""
            },
            style = MaterialTheme.typography.caption.copy(color = Color.Red),
    )
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
                modifier = Modifier.gravity(Alignment.CenterVertically).padding(start = 10.dp),
                text = typeOfCustomerEmail,
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center)
    }
}

@Composable
fun SubmitButton(emailUpdateButtonLabel: String, state: MutableState<AppState>) {
    Button(
            onClick = { },
            backgroundColor = if (state.value.emailSubmitButtonEnabled) submitButton else submitButtonDisabled,
            enabled = state.value.emailSubmitButtonEnabled


            ) {
        Text(
                color = Color.White,
                text = emailUpdateButtonLabel
        )
    }
}


@Composable
fun EmailPluginContent(
        emailErrorNotValidError: String,
        emailErrorOver50Error: String,
        emailConfirmErrorNoMatch: String,
        emailUpdateButtonLabel: String,
        actionBarTitleLabel: String,
        emailAddressLabel: String,
        emailConfirmAddressLabel: String,
        vm: MainViewModel,
        legalText: String
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Row {
            EmailTypeActionBar(actionBarTitleLabel)
        }
        Row {
            EmailInputForm(
                    emailErrorNotValidError,
                    emailErrorOver50Error,
                    emailConfirmErrorNoMatch,
                    emailAddressLabel,
                    emailConfirmAddressLabel,
                    vm.store,
                    vm.state)
        }
        Row {
            legalText(legalText)
        }
        Row(modifier = Modifier.align(Alignment.End).padding(end = 10.dp)) {
            SubmitButton(emailUpdateButtonLabel, vm.state)
        }
    }
}

