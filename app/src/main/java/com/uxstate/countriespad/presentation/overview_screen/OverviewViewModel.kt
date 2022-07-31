package com.uxstate.countriespad.presentation.overview_screen

import androidx.lifecycle.ViewModel
import com.uxstate.countriespad.domain.use_cases.GetCountryDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val usecase:GetCountryDataUseCase) : ViewModel() {
}