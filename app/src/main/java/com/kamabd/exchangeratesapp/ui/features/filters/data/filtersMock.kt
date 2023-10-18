package com.kamabd.exchangeratesapp.ui.features.filters.data

val filterMockItems: List<FilterItem>
    get() = listOf(
        FilterItem(
            sortType = "code",
            sortBy = "asc"
        ),
        FilterItem(
            sortType = "code",
            sortBy = "desc"
        ),
        FilterItem(
            sortType = "quote",
            sortBy = "asc"
        ),
        FilterItem(
            sortType = "quote",
            sortBy = "desc"
        )
    )