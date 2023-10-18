package com.kamabd.exchangeratesapp.ui.features.currencies.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.ui.features.currencies.data.defaultBaseCurrency
import com.kamabd.exchangeratesapp.ui.features.currencies.data.defaultCurrencySymbols
import com.kamabd.uikit.theme.impl.localColors
import com.kamabd.uikit.theme.impl.localDimens
import com.kamabd.uikit.theme.impl.localShapes
import com.kamabd.uikit.theme.impl.localTypography
import com.kamabd.uikit.utils.clickableWithRipple

@Composable
fun CurrenciesHeaderContent(
    title: String,
    onOpenFiltersClicked: () -> Unit,
    onBaseCurrencySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val menuItems = defaultCurrencySymbols
    val expanded = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf(defaultBaseCurrency) }
    Column(
        modifier = modifier
            .background(localColors.bgColors.bgColorHeader)
            .fillMaxWidth()
            .padding(
                horizontal = localDimens.spacing_16,
                vertical = localDimens.spacing_12
            )
    ) {
        Text(
            text = title,
            style = localTypography.title1,
            color = localColors.textColors.textPrimary
        )
        Spacer(modifier = Modifier.height(localDimens.spacing_16))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .weight(.9f)
                    .clip(localShapes.small)
                    .border(
                        width = 1.dp,
                        color = localColors.bgColors.bgColorBorder,
                        shape = localShapes.small
                    )
                    .clickableWithRipple { expanded.value = true }
                    .padding(
                        horizontal = localDimens.spacing_16,
                        vertical = localDimens.spacing_12
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    Text(
                        text = selectedItem.value,
                        style = localTypography.caption,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(localDimens.spacing_8))
                    Icon(
                        painter = getArrowIconByExpanded(expanded.value),
                        tint = localColors.btnColors.btnPrimary,
                        contentDescription = null,
                    )
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    menuItems.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                selectedItem.value = item
                                expanded.value = false
                                onBaseCurrencySelected(item)
                            }
                        ) {
                            Text(text = item)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(localDimens.spacing_8))
            Box(
                modifier = Modifier
                    .clip(localShapes.small)
                    .border(
                        width = 1.dp,
                        color = localColors.bgColors.bgColorBorder,
                        shape = localShapes.small
                    )
                    .clickableWithRipple(onClick = onOpenFiltersClicked)
                    .padding(localDimens.spacing_12)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_svg_filters),
                    tint = localColors.btnColors.btnPrimary,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun getArrowIconByExpanded(expanded: Boolean): Painter = if (expanded) {
    painterResource(id = R.drawable.ic_svg_arrow_up)
} else {
    painterResource(id = R.drawable.ic_svg_arrow_down)
}