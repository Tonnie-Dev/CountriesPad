package com.uxstate.validator

import androidx.compose.foundation.layout.Column
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

    Column {
        val phoneNumber = rememberSaveable { mutableStateOf("") }

        KomposeCountryCodePicker(
                modifier = Modifier
                        .fillMaxWidth(),
                text = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                placeholder = { Text(text = "Phone Number") },
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                ),
        )

    /*TextField(
            modifier = Modifier
                    .fillMaxWidth(),
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            placeholder = { Text(text = "Phone Number") },
            leadingIcon = {
                KomposeCountryCodePicker(
                        modifier = Modifier,
                        showOnlyCountryCodePicker = true,
                        showCountryFlag =true,
                )
            },
            colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
            ),
    )*/
    
    Text(text = CountryCodePicker.getCountryPhoneCodeWithoutPrefix())
    Text(text = CountryCodePicker.getCountryPhoneCode())
    Text(text = CountryCodePicker.getCountryName())
    Text(text = CountryCodePicker.getCountryCodeWithoutPrefix())
    Text(text = CountryCodePicker.getPhoneNumber())
    Text(text = CountryCodePicker.getPhoneNumberWithoutPrefix())
    Text(text = CountryCodePicker.getFullPhoneNumber())
    Text(text = CountryCodePicker.getFullPhoneNumberWithoutPrefix())
    Text(text = CountryCodePicker.isPhoneNumberValid().toString())


    }
}