package com.kamabd.exchangeratesapp.ui.features.currencies.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kamabd.exchangeratesapp.ui.features.currencies.data.currenciesMockData

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun CurrenciesContentPreview() {
    CurrenciesContent(
        data = currenciesMockData,
        onFavoriteIconClicked = { },
    )
}