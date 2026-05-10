package io.github.uxlabspk.organote.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    var name = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    private val _signupSuccess = MutableSharedFlow<Unit>()
    val signupSuccess = _signupSuccess.asSharedFlow()

    fun onNameChange(newValue: String) {
        name.value = newValue
    }

    fun onEmailChange(newValue: String) {
        email.value = newValue
    }

    fun onPasswordChange(newValue: String) {
        password.value = newValue
    }

    fun signup() {
        // Implement your signup logic here
        viewModelScope.launch {
            // Simulate API call
            _signupSuccess.emit(Unit)
        }
    }
}
