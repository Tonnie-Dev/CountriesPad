package com.uxstate.overview.presentation.details_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapComposable(
    country: com.uxstate.util.model.Country,
    modifier: Modifier = Modifier,
    initialZoom: Float,
    finalZoom: Float,
    animationDuration: Int
) {


    val location = country.latLng
    val countryLatLng = LatLng(location.first, location.second)


    Column(modifier = modifier) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(countryLatLng, initialZoom)

        }


        LaunchedEffect(key1 = true, block = {
            cameraPositionState.animate(
                    update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(countryLatLng, finalZoom, 0f, 0f)
                    ), durationMs = animationDuration
            )

        })

        GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
        )
    }
}