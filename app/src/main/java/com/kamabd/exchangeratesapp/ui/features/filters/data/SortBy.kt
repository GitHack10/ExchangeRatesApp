package com.kamabd.exchangeratesapp.ui.features.filters.data

import android.os.Parcelable
import com.kamabd.exchangeratesapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class SortBy(val title: Int) : Parcelable {
    CodeAsc(R.string.filters_sort_by_code_asc),
    CodeDesc(R.string.filters_sort_by_code_desc),
    QuoteAsc(R.string.filters_sort_by_quote_asc),
    QuoteDesc(R.string.filters_sort_by_quote_desc),

//    @Parcelize
//    object CodeAsc : SortBy(R.string.filters_sort_by_code_asc)
//
//    @Parcelize
//    object CodeDesc : SortBy(R.string.filters_sort_by_code_desc)
//
//    @Parcelize
//    object QuoteAsc : SortBy(R.string.filters_sort_by_quote_asc)
//
//    @Parcelize
//    object QuoteDesc : SortBy(R.string.filters_sort_by_quote_desc)
}