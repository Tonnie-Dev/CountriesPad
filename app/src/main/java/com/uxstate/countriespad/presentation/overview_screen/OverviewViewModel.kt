package com.uxstate.countriespad.presentation.overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uxstate.countriespad.domain.use_cases.GetCountryDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val useCase:GetCountryDataUseCase) : ViewModel() {


    var state by mutableStateOf(OverviewState())
    private set

    init {
        getCountries()
    }

}