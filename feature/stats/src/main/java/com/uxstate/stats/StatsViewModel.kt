package com.uxstate.stats

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(StatsScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: StatsScreenEvent) {

        when (event) {

            is StatsScreenEvent.AreaButtonToggle -> {

                _state.update {
                    it.copy(

                            isAreaButtonEnabled = true,
                                    isPopulationButtonEnabled =false

                    )
                }
            }

            is StatsScreenEvent.PopulationButtonToggle -> {

                _state.update {
                    it.copy(

                            isAreaButtonEnabled = false,
                            isPopulationButtonEnabled =true
                          
                    )
                }
            }

            is StatsScreenEvent.OrderToggle -> {}
        }
    }
}