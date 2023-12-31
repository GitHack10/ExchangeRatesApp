package com.kamabd.uikit.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp

@Stable
data class ComponentShapeParams(
    val shape: CornerBasedShape,
    val borderColor: Brush? = null,
    val borderWidthDp: Dp? = null,
)

@Composable
fun componentShapeParams(): ComponentShapeParams = ComponentShapeParams(
    CircleShape
)