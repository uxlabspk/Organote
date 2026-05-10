package io.github.uxlabspk.organote.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    private val _loginSuccess = MutableSharedFlow<Unit>()
    val loginSuccess = _loginSuccess.asSharedFlow()

    fun onEmailChange(newValue: String) {
        email.value = newValue
    }

    fun onPasswordChange(newValue: String) {
        password.value = newValue
    }

    fun login() {
        // Implement your login logic here
        viewModelScope.launch {
            // Simulate API call
            _loginSuccess.emit(Unit)
        }
    }
}
