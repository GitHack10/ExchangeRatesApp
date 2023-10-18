package com.kamabd.exchangeratesapp.ui.features.favorites.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kamabd.exchangeratesapp.R
import com.kamabd.uikit.theme.impl.localColors
import com.kamabd.uikit.theme.impl.localDimens
import com.kamabd.uikit.theme.impl.localTypography

@Composable
fun FavoritesHeaderContent(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = localTypography.title1,
        color = localColors.textColors.textPrimary,
        modifier = modifier
            .fillMaxWidth()
            .background(localColors.bgColors.bgColorHeader)
            .padding(
                horizontal = localDimens.spacing_16,
                vertical = localDimens.spacing_12
            )
    )
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun FavoritesHeaderContentPreview() {
    FavoritesHeaderContent(
        title = stringResource(id = R.string.favorites_title),
    )
}