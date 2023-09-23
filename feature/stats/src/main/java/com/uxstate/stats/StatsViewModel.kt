package com.uxstate.stats

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor() : ViewModel() {




    fun onEvent(event:StatsScreenEvent){

        when(event){

            is StatsScreenEvent.AreaButtonToggle -> {}
            is StatsScreenEvent.PopulationButtonToggle -> {}
        }
    }
}