package com.uxstate.countriespad.presentation.details_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.uxstate.countriespad.presentation.DetailsEvent
import com.uxstate.countriespad.presentation.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(handle: SavedStateHandle) : ViewModel() {


    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()
    val country = handle.navArgs<DetailsNavArgs>().country


    fun onEvent(event:DetailsEvent){

        when(event){

            is DetailsEvent.ShowFlagEvent -> {

                _state.update { it.copy(
                        isShowFlag = true,
                        url = country.flagUrl
                ) }
            }
            is DetailsEvent.ShowCoatOfArmsEvent -> {


                _state.update { it.copy(isShowCoatOfArms = true, url = country.coatOfArmsUrl) }
            }
        }
    }


}