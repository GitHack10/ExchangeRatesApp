package com.kamabd.exchangeratesapp.ui.features.currencies.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kamabd.exchangeratesapp.R

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun CurrenciesHeaderContentPreview() {
    CurrenciesHeaderContent(
        title = stringResource(id = R.string.currencies_title),
        onBaseCurrencySelected = { },
        onOpenFiltersClicked = { },
    )
}