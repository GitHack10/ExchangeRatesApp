package com.kamabd.exchangeratesapp.ui.features.filters.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kamabd.exchangeratesapp.ui.component.AppBarWithArrow
import com.kamabd.exchangeratesapp.ui.features.filters.data.FilterItem

@Composable
fun FiltersContent(
    title: String,
    items: List<FilterItem>,
    pressOnBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        AppBarWithArrow(
            title = title,
            pressOnBack = pressOnBack,
        )
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(items) {
                FilterItemContent(it)
            }
        }
    }
}

@Composable
fun FilterItemContent(
    item: FilterItem
) {
    // todo
}