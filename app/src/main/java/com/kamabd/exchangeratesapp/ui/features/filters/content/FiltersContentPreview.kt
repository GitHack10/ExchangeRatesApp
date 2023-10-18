package com.kamabd.exchangeratesapp.ui.features.filters.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.ui.features.filters.FiltersScreen
import com.kamabd.exchangeratesapp.ui.features.filters.data.filterMockItems

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun FiltersContentPreview() {
    FiltersContent(
        title = stringResource(id = R.string.filters_title),
        items = filterMockItems,
        pressOnBack = { },
    )
}