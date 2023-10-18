package com.kamabd.uikit.theme.impl

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp
import com.kamabd.uikit.theme.api.AppThemeShapes

val localShapes: AppThemeShapes
    @Composable
    get() = LocalAppTheme.current.shapes

@Immutable
object DefaultAppShapes : AppThemeShapes {

    override val mini: CornerBasedShape = RoundedCornerShape(4.dp)

    override val small: CornerBasedShape = RoundedCornerShape(8.dp)

    override val medium: CornerBasedShape = RoundedCornerShape(12.dp)

    override val large: CornerBasedShape = RoundedCornerShape(16.dp)

    override val xlarge: CornerBasedShape = RoundedCornerShape(20.dp)

    override val bottomSheet: CornerBasedShape = RoundedCornerShape(
        topStart = 12.dp,
        topEnd = 12.dp,
    )
}