package com.uxstate.countriespad.presentation.details_screen.components

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.uxstate.countriespad.R
import com.uxstate.countriespad.util.LocalSpacing
import com.uxstate.countriespad.util.conditional

@Composable
fun ZoomableImage(
    url: String,
    isShowFlag:Boolean,
    onCloseClicked: () -> Unit
) {

    val spacing = LocalSpacing.current
    val space24 = spacing.spaceMedium + spacing.spaceSmall

    /*scale and offset variables are used to keep track of the
    current zoom level and position of the image.*/
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var scale by remember { mutableFloatStateOf(1f) }


    Box(
            modifier = Modifier
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->

                            //max zoom level is 5f
                            scale = maxOf(1f, minOf(scale * zoom, 5f))
                            val maxX = (size.width * (scale - 1)) / 2
                            val minX = -maxX
                            offsetX = maxOf(minX, minOf(maxX, offsetX + pan.x))
                            val maxY = (size.height * (scale - 1)) / 2
                            val minY = -maxY
                            offsetY = maxOf(minY, minOf(maxY, offsetY + pan.y))
                        }
                    }
    ) {

        /* - Image to Zoom - can be zoomed in and out using pinch gestures.
         - AsyncImage fills the MaxSize of the box
         - graphicsLayer Modifier provides a way to modify the graphical properties
           of a composable element, such as its alpha value, rotation, and scale
         - it creates a new GraphicsLayer object and attaches it to the element
         - you can then use this object to apply transformations to the element's
           drawing, such as scaling, rotating, or translating it
        * */
        AsyncImage(
                modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                                scaleX = maxOf(.5f, minOf(3f, scale)),
                                scaleY = maxOf(.5f, minOf(3f, scale)),
                                translationX = offsetX,
                                translationY = offsetY
                        ),
                model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                contentScale = ContentScale.Fit,
                contentDescription = stringResource(R.string.coat_of_arms_text)
        )

        //the row is drawn on top of the AsyncImage filling the entire width
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .conditional(isShowFlag){ align(Alignment.BottomCenter)}
                        .conditional(!isShowFlag){align(Alignment.TopCenter)}
                        .padding(horizontal = space24)
                        .padding(top = space24),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onCloseClicked) {
                Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_icon_text)
                )
                Text(text = "Close")
            }

        }
    }
}