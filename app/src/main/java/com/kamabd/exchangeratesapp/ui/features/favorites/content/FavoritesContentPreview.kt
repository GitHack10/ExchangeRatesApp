package com.kamabd.exchangeratesapp.ui.features.favorites.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.ui.features.favorites.data.favoritesCurrenciesMockItems

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun FavoritesContentPreview() {
    FavoritesContent(
        items = favoritesCurrenciesMockItems,
        onFavoriteIconClicked = { }
    )
}