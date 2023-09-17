package com.uxstate.overview.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.overview.domain.use_cases.GetCountryDataUseCase
import com.uxstate.util.use_cases.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val getCountryDataUseCase: GetCountryDataUseCase, private val filterUseCase:FilterUseCase) : ViewModel() {


    var state by mutableStateOf(OverviewState())
        private set

    private var countryJob: Job? = null

    init {
        getCountries(countryOrderFormat = state.countryOrderFormat)
    }

    fun onEvent(event: OverviewEvent) {

        when (event) {

            is OverviewEvent.OnClearSearchBox -> {

                if (state.query.isNotEmpty()) {
                    state = state.copy(query = "")

                } else {

                    state = state.copy(isActive = !state.isActive)
                }

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


            is OverviewEvent.OnSearchBarActiveStateChange -> {

                state = state.copy(isActive = event.isActive)
            }

            is OverviewEvent.OnSearchBackClick -> {
                state = state.copy(isActive = false)

            }

            is OverviewEvent.OnSelectCountry -> {
                state = state.copy(isActive = false, query = "")
                getCountries(countryOrderFormat = state.countryOrderFormat)
            }

            is OverviewEvent.OnChangeOrder -> {


                if (state.countryOrderFormat::class == event.countryOrderFormat::class
                    && state.countryOrderFormat.orderType == event.countryOrderFormat.orderType
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


        }
    }

    private fun getCountries(
        query: String = state.query,
        fetchFromRemote: Boolean = false,
        countryOrderFormat: com.uxstate.util.CountryOrderFormat
    )
     {


         getCountryDataUseCase(query, fetchFromRemote)
                .onEach { result ->
                    state = when (result) {

                        is com.uxstate.util.Resource.Loading -> {

                            state.copy(isLoading = result.isLoading)
                        }
                        is com.uxstate.util.Resource.Error -> {

                            state.copy(
                                    errorMessage = result.errorMessage ?: "Unknown Error Occurred"
                            )
                        }
                        is com.uxstate.util.Resource.Success -> {
                            // state.copy(countriesData = container.filterUseCase(it,countryOrderFormat))

                            //state.copy(countriesData = result.data?: emptyList())


                            state.copy(
                                    countriesData = filterUseCase(
                                            result.data ?: emptyList(), countryOrderFormat
                                    )
                            )


                        }
                    }


                }
                .launchIn(viewModelScope)
    }

}