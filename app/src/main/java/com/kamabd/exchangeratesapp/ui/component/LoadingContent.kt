package com.kamabd.exchangeratesapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kamabd.uikit.theme.impl.localColors

@Composable
fun LoadingContent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(localColors.bgColors.bgInverted.copy(alpha = .5f))
            .clickable(enabled = false) {}
    ) {
        CircularProgressIndicator()
    }
}