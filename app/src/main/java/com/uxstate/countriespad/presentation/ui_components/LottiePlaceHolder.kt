package com.uxstate.countriespad.presentation.ui_components

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.uxstate.countriespad.R
import com.uxstate.countriespad.util.LocalSpacing

@Composable
fun LottieAnimationDefinition(modifier: Modifier, @RawRes lottie: Int) {

    val spacing = LocalSpacing.current
    //spec to hold anim
    val spec = LottieCompositionSpec.RawRes(lottie)

    //composition to render a Lottie

    val lottieComposition by rememberLottieComposition(spec = spec)

    //monitor state
    val state by animateLottieCompositionAsState(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever
    )

    //lottie composable

    LottieAnimation(
            composition = lottieComposition,
            progress = state,
            modifier = modifier
    )

}

@Composable
fun LottiePlaceHolder(modifier: Modifier, @RawRes lottie: Int) {

    LottieAnimationDefinition(modifier = modifier, lottie = lottie)

}