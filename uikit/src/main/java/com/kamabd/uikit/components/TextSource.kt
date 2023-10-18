package com.kamabd.uikit.components

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalContext

@Stable
sealed class TextSource {

    @Stable
    data class StringSource(
        val text: String
    ) : TextSource()

    @Stable
    data class ResourceIdSource(
        @StringRes
        val text: Int
    ) : TextSource()

    @Composable
    fun stringText(resources: Resources = LocalContext.current.resources): String =
        when (this) {
            is StringSource -> text
            is ResourceIdSource -> resources.getString(text)
        }
}

fun textSourceFromStringOrNull(source: String?): TextSource? =
    if (source == null) {
        null
    } else {
        TextSource.StringSource(source)
    }

fun textSourceFromResourceOrNull(@StringRes source: Int?): TextSource? =
    if (source == null) {
        null
    } else {
        TextSource.ResourceIdSource(source)
    }