package com.uxstate.countriespad.presentation.overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.countriespad.domain.use_cases.UseCaseContainer
import com.uxstate.countriespad.util.CountryOrderFormat
import com.uxstate.countriespad.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val container: UseCaseContainer) : ViewModel() {


    var state by mutableStateOf(OverviewState())
        private set

    private var countryJob: Job? = null

    init {
        getCountries(countryOrderFormat = state.countryOrderFormat)
    }

    fun onEvent(event: OverviewEvent) {

        when (event) {

            is OverviewEvent.OnClearSearchBox -> {

                state = state.copy(query = "")
                getCountries(countryOrderFormat = state.countryOrderFormat)
            }
            is OverviewEvent.OnQueryChange -> {
                //update query value
                state = state.copy(query = event.query)

                //cancel existing job
                countryJob?.cancel()

                //start a new job

                countryJob =

                        //get viewModelScope for delay suspend function
                    viewModelScope.launch {

                        delay(500)
                        //called only after a delay of 500 ms
                        getCountries(countryOrderFormat = state.countryOrderFormat)
                    }

            }

            is OverviewEvent.OnChangeOrder -> {


                if (state.countryOrderFormat::class==event.countryOrderFormat::class
                    && state.countryOrderFormat.orderType==event.countryOrderFormat.orderType
                ) {

                    //do nothing since the order hasn't changed
                    return
                }

                //else load countries with the new event's order
                state = state.copy(countryOrderFormat = event.countryOrderFormat)
                getCountries(countryOrderFormat = state.countryOrderFormat)

            }
            is OverviewEvent.OnToggleSelectionPane -> {
                //toggle boolean status
                state = state.copy(isOrderPaneVisible = !state.isOrderPaneVisible)
            }


            is OverviewEvent.OnSearchBarActiveStateChange ->{

                !event.isActive
            }

        }
    }

    private fun getCountries(
        query: String = state.query,
        fetchFromRemote: Boolean = false,
        countryOrderFormat: CountryOrderFormat)
     {


        container.getCountryDataUseCase(query, fetchFromRemote)
                .onEach { result ->
                    state = when (result) {

                        is Resource.Loading -> {

                            state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Error -> {

                            state.copy(
                                    errorMessage = result.errorMessage ?: "Unknown Error Occurred"
                            )
                        }
                        is Resource.Success -> {
                            // state.copy(countriesData = container.filterUseCase(it,countryOrderFormat))

                            //state.copy(countriesData = result.data?: emptyList())


                            state.copy(
                                    countriesData = container.filterUseCase(
                                            result.data ?: emptyList(), countryOrderFormat
                                    )
                            )


                        }
                    }


                }
                .launchIn(viewModelScope)
    }

}