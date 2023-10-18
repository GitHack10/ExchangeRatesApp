package com.kamabd.exchangeratesapp.ui.features.filters.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.ui.component.AppBarWithArrow
import com.kamabd.exchangeratesapp.ui.features.filters.FiltersState
import com.kamabd.exchangeratesapp.ui.features.filters.data.SortBy
import com.kamabd.uikit.components.TextSource
import com.kamabd.uikit.components.button.AppButton
import com.kamabd.uikit.components.button.AppButtonState
import com.kamabd.uikit.components.button.primaryAppButtonTheme
import com.kamabd.uikit.theme.impl.localColors
import com.kamabd.uikit.theme.impl.localDimens
import com.kamabd.uikit.theme.impl.localTypography
import com.kamabd.uikit.utils.clickableWithRipple

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FiltersContent(
    title: String,
    state: FiltersState.Success,
    onFilterItemClicked: (SortBy) -> Unit,
    onApplyButtonClicked: () -> Unit,
    pressOnBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        AppBarWithArrow(
            title = title,
            pressOnBack = pressOnBack,
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            stickyHeader {
                Spacer(modifier = Modifier.height(localDimens.spacing_16))
                Text(
                    text = stringResource(id = R.string.filters_sort_by_title).uppercase(),
                    style = localTypography.body4,
                    color = localColors.textColors.textSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = localDimens.spacing_16)
                )
                Spacer(modifier = Modifier.height(localDimens.spacing_12))
            }
            items(state.items) {
                FilterItemContent(
                    item = it,
                    selected = state.selectedSortBy == it,
                    onFilterItemClicked = onFilterItemClicked
                )
            }
        }
        AppButton(
            state = AppButtonState(TextSource.ResourceIdSource(R.string.filters_sort_by_apply_btn)),
            theme = primaryAppButtonTheme(),
            onClick = onApplyButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(localDimens.spacing_16)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun FilterItemContent(
    item: SortBy,
    selected: Boolean,
    onFilterItemClicked: (SortBy) -> Unit,
    modifier: Modifier = Modifier
) {
    // todo
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickableWithRipple {
                onFilterItemClicked(item)
            }
            .padding(horizontal = localDimens.spacing_16)
    ) {
        Text(
            text = stringResource(id = item.title),
            style = localTypography.body2,
            color = localColors.textColors.textPrimary,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        )
        RadioButton(
            selected = selected,
            colors = RadioButtonDefaults.colors(
                selectedColor = localColors.btnColors.btnPrimary,
                unselectedColor = localColors.btnColors.btnSecondary,
            ),
            onClick = {
                onFilterItemClicked(item)
            }
        )
    }
}