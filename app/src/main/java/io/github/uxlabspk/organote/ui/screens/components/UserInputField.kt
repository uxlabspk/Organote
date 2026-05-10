package io.github.uxlabspk.organote.ui.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import io.github.uxlabspk.organote.data.model.InputTypes

@Composable
fun UserInputField(
    type: InputTypes,
    value: MutableState<String>,
    onValueChange: (String) -> Unit
) {
    if (type == InputTypes.Password) {
        PasswordInputField(value, onValueChange)
        return
    }
    EmailInputField(value, onValueChange)
}

@Composable
fun EmailInputField(value: MutableState<String>, onValueChange: (String) -> Unit) {
    TextField(
        value = value.value,
        onValueChange = onValueChange,
        label = { Text("Email") },
        singleLine = true,
    )
}

@Composable
fun PasswordInputField(value: MutableState<String>, onValueChange: (String) -> Unit) {
    val passwordEye = remember { mutableStateOf(false) }

    TextField(
        value = value.value,
        onValueChange = onValueChange,
        label = { Text("Password") },
        singleLine = true,
        visualTransformation = if (passwordEye.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { passwordEye.value = !passwordEye.value }
            ) {
                Icon(
                    imageVector = if (passwordEye.value) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = "null"
                )
            }
        }
    )
}