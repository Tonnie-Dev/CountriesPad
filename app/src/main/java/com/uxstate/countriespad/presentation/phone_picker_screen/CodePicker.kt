package com.uxstate.countriespad.presentation.phone_picker_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joelkanyi.jcomposecountrycodepicker.component.CountryCodePicker
import com.joelkanyi.jcomposecountrycodepicker.component.KomposeCountryCodePicker

@Composable
fun CountryCodePicker() {

    val phoneNumber = rememberSaveable { mutableStateOf("") }

    TextField(
            modifier = Modifier
                    .fillMaxWidth(),
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            placeholder = { Text(text = "Phone Number") },
            leadingIcon = {
                KomposeCountryCodePicker(
                        modifier = Modifier,
                        showOnlyCountryCodePicker = true,
                        showCountryFlag = false,
                )
            },
            colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
            ),
    )
}