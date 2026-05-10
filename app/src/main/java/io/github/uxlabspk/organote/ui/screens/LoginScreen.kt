package io.github.uxlabspk.organote.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.uxlabspk.organote.data.model.InputTypes
import io.github.uxlabspk.organote.ui.screens.components.UserInputField

@Composable
fun LoginScreen() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        UserInputField(
            InputTypes.Email,
            value = email,
            onValueChange = { email.value = it }
        )

        UserInputField(
            InputTypes.Password,
            value = password,
            onValueChange = { password.value = it }
        )
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}