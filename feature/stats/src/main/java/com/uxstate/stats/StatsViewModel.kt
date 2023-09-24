package com.uxstate.stats

import androidx.lifecycle.ViewModel
import com.uxstate.source.use_case.FetchCountryDataUseCase
import com.uxstate.util.CountryOrderFormat
import com.uxstate.util.OrderType
import com.uxstate.util.Resource
import com.uxstate.util.use_cases.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val fetchDataUseCase: FetchCountryDataUseCase,
    private val filterDataUseCase: FilterUseCase
) :
    ViewModel() {

    private val _state = MutableStateFlow(StatsScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: StatsScreenEvent) {

        when (event) {

            is StatsScreenEvent.AreaButtonToggle -> {

                _state.update {
                    it.copy(

                            isAreaButtonEnabled = true,
                            isPopulationButtonEnabled = false,
                            countryOrderFormat = CountryOrderFormat.ByArea(it.sortOrder)

                    )

                }

                getCountries(_state.value.countryOrderFormat)
            }

            is StatsScreenEvent.PopulationButtonToggle -> {

                _state.update {
                    it.copy(

                            isAreaButtonEnabled = false,
                            isPopulationButtonEnabled = true,
                            countryOrderFormat = CountryOrderFormat.ByPopulation(it.sortOrder)

                    )
                }

                getCountries(_state.value.countryOrderFormat)
            }

            is StatsScreenEvent.OnSort -> {

                _state.update { it.copy(sortOrder = event.sortType) }
            }
            is StatsScreenEvent.OnChangeOrder -> {


                if (_state.value.countryOrderFormat::class == event.countryOrderFormat::class
                    && _state.value.countryOrderFormat.orderType == event.countryOrderFormat.orderType
                ) {

                    //do nothing since the order hasn't changed
                    return
                }

                //else load countries with the new event's order

                _state.update { it.copy(countryOrderFormat = event.countryOrderFormat) }

                getCountries(format = _state.value.countryOrderFormat)

            }

            is StatsScreenEvent.OrderToggle -> {}
        }
    }

    private fun getCountries(format: CountryOrderFormat) {
        fetchDataUseCase(query = "", fetchFromRemote = false).onEach {

            result ->

            when (result) {
                is Resource.Success -> {

                    _state.update {
                        it.copy(
                                countryData = filterDataUseCase(
                                        countries = result.data ?: emptyList()
                                )
                        )
                    }
                }

                is Resource.Loading -> {

                    _state.update { it.copy(isLoading = result.isLoading) }
                }

                is Resource.Error -> {

                    _state.update {
                        it.copy(
                                errorMessage = result.errorMessage ?: "Unknown Error Occurred"
                        )
                    }
                }

            }
        }


    }


}