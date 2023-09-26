package com.uxstate.validator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joelkanyi.jcomposecountrycodepicker.component.CountryCodePicker
import com.joelkanyi.jcomposecountrycodepicker.component.KomposeCountryCodePicker
import com.uxstate.ui.theme.LocalSpacing

@Composable
fun CountryCodePicker(modifier: Modifier = Modifier) {


val spacing = LocalSpacing.current

    Column (modifier = modifier.padding(spacing.spaceSmall)){
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

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(spacing.spaceSmall).fillMaxWidth()) {
            Text(text = "Country Name:", style = MaterialTheme.typography.titleSmall)
            Text(text = CountryCodePicker.getCountryName(), style = MaterialTheme.typography.titleLarge)
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(spacing.spaceSmall).fillMaxWidth())  {
            Text(text = "Country Code:", style = MaterialTheme.typography.titleSmall)
            Text(text = CountryCodePicker.getCountryPhoneCode(), style = MaterialTheme.typography.titleLarge)
        }


        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(spacing.spaceSmall).fillMaxWidth())  {
            Text(text = "Phone Number:", style = MaterialTheme.typography.titleSmall)
            Text(text = CountryCodePicker.getPhoneNumber(), style = MaterialTheme.typography.titleLarge)
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(spacing.spaceSmall).fillMaxWidth())  {
            Text(text = "Phone Number Without Prefix:", style = MaterialTheme.typography.titleSmall)
            Text(text = CountryCodePicker.getPhoneNumberWithoutPrefix(), style = MaterialTheme.typography.titleLarge)
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(spacing.spaceSmall).fillMaxWidth())  {
            Text(text = "Full Phone Number:", style = MaterialTheme.typography.titleSmall)
            Text(text = CountryCodePicker.getFullPhoneNumber(), style = MaterialTheme.typography.titleLarge)
        }
/*
    Text(text = CountryCodePicker.getCountryPhoneCodeWithoutPrefix())
    Text(text = CountryCodePicker.getCountryPhoneCode())
    Text(text = CountryCodePicker.getCountryName())
    Text(text = CountryCodePicker.getCountryCodeWithoutPrefix())
    Text(text = CountryCodePicker.getPhoneNumber())
    Text(text = CountryCodePicker.getPhoneNumberWithoutPrefix())
    Text(text = CountryCodePicker.getFullPhoneNumber())
    Text(text = CountryCodePicker.getFullPhoneNumberWithoutPrefix())
    Text(text = CountryCodePicker.isPhoneNumberValid().toString())*/


    }
}