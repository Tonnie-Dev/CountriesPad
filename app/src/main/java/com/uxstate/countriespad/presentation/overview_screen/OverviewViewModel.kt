package com.uxstate.countriespad.presentation.overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.countriespad.domain.use_cases.GetCountryDataUseCase
import com.uxstate.countriespad.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val useCase:GetCountryDataUseCase) : ViewModel() {


    var state by mutableStateOf(OverviewState())
    private set

    private var countryJob: Job? = null

    init {
       getCountries()
    }

    private fun getCountries(query:String = "", fetchFromRemote:Boolean = false
    ) {

        countryJob?.cancel()

        countryJob = useCase(query, fetchFromRemote).onEach {
           result ->
            state = when(result){

                is Resource.Loading -> {

                    state.copy(isLoading = result.isLoading)
                }
                is Resource.Error -> {

                    state.copy(errorMessage = result.errorMessage ?: "Unknown Error Occurred")
                }
                is Resource.Success-> {

                    state.copy(countriesData = result.data ?: emptyList())
                }
            }


       }.launchIn(viewModelScope)
    }

}