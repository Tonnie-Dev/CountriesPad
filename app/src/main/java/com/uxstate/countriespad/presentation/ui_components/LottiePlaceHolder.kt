package com.uxstate.countriespad.presentation.ui_components

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.uxstate.countriespad.R

@Composable
fun LottieAnimationDefination(modifier: Modifier = Modifier, @RawRes lottie:Int) {

    //spec to hold anim
    val spec = LottieCompositionSpec.RawRes(R.raw.loading_lottie_anim)

    //composition to render a Lottie

    val lottieComposition = rememberLottieComposition(spec = spec)

}