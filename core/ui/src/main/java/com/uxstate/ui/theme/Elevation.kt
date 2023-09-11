package com.uxstate.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevation (val level0:Dp = 0.0.dp,
                      val level1:Dp = 1.0.dp,
                      val level2:Dp = 3.0.dp,
                      val level3:Dp = 6.0.dp,
                      val level4:Dp = 8.0.dp,
                      val level5:Dp = 12.0.dp)



val LocalElevation = compositionLocalOf {  Elevation() }