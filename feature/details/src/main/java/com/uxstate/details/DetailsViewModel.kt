package com.uxstate.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(handle: SavedStateHandle) : ViewModel() {


    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()



    fun onEvent(event: DetailsEvent){

        when(event){

            is DetailsEvent.ShowFlagEvent -> {

                _state.update { it.copy(
                        isShowFlag = true,
                        url = event.url
                ) }
            }
            is DetailsEvent.ShowCoatOfArmsEvent -> {


                _state.update { it.copy(isShowCoatOfArms = true, url = event.url) }
            }
        }
    }


}