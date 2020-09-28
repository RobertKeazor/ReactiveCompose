package com.robertkeazor.myapplication.apprepo

import android.view.View
import com.robertkeazor.myapplication.helpers.EmailValidator
import com.robertkeazor.myapplication.helpers.GlobalConstants
import com.robertkeazor.myapplication.repo.Reducer
import javax.inject.Inject

class AppReducer @Inject constructor() : Reducer<AppActions, AppState> {
    override fun reduce(previousState: AppState, action: AppActions) = when (action) {
        is AppActions.TypeEmailAddress -> reducePrimaryEmailText(previousState, action)
        is AppActions.TypeConfirmAddress -> reduceSecondaryEmailText(previousState, action)
        AppActions.SUBMIT -> TODO()
    }

    private fun reducePrimaryEmailText(
        previousState: AppState,
        action: AppActions.TypeEmailAddress
    ): AppState {
        val isMatchingEmail =
            action.emailAddress.isNotEmpty() &&
                    action.emailAddress == previousState.emailConfirmAddress
        val mismatchViolation = if (isMatchingEmail) {
            null
        } else {
            EmailError.EmailMismatch
        }

        val primaryIsValidError =
            when {
                EmailValidator.isEmailValid(action.emailAddress) -> null
                action.emailAddress.isBlank() -> null
                else -> EmailError.InvalidEmail
            }
        val primaryErrorExceedCharsError =
            if (action.emailAddress.length > GlobalConstants.EMAIL_ADDRESS_CHAR_LIMIT)
                EmailError.MaxCharLength
            else null

        val emailError = when {
                primaryErrorExceedCharsError != null -> EmailError.MaxCharLength
                primaryIsValidError != null -> EmailError.InvalidEmail
                else -> null
            }
        val hasConfirmEmail = action.emailAddress.isNotEmpty() && emailError == null
        val emailAddressErrorVisibility = if (emailError == null) {
            View.GONE
        } else {
            View.VISIBLE
        }
        val hasConfirmButtonEnabled =
            hasConfirmEmail &&
                    action.emailAddress == previousState.emailConfirmAddress
           return previousState.copy(
               emailAddress = action.emailAddress,
               emailAddressErrorVisibility = emailAddressErrorVisibility,
               emailError = emailError,
               emailConfirmFieldEnabled = hasConfirmEmail,
               emailSubmitButtonEnabled = hasConfirmButtonEnabled,
               emailConfirmAddressErrorVisibility = if (mismatchViolation == null) {
                   View.GONE
               } else {
                   View.VISIBLE
               }
           )
    }

    private fun reduceSecondaryEmailText(
        previousState: AppState,
        action: AppActions.TypeConfirmAddress
    ): AppState {
        val isMatchingEmail: Boolean =
            action.emailConfirm.isNotEmpty()
                    && action.emailConfirm == previousState.emailAddress
        val emailError = if (isMatchingEmail) null else EmailError.EmailMismatch
        val confirmErrorVisibility = if (emailError == null) View.GONE else View.VISIBLE

        return previousState.copy(
            emailConfirmAddress = action.emailConfirm,
            emailError = emailError,
            emailConfirmAddressErrorVisibility = confirmErrorVisibility,
            emailSubmitButtonEnabled = isMatchingEmail
        )
    }
}
