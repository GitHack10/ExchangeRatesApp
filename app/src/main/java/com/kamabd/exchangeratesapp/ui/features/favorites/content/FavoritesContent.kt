package com.kamabd.exchangeratesapp.ui.features.favorites.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.kamabd.domain.CurrencyInfo
import com.kamabd.exchangeratesapp.R
import com.kamabd.uikit.theme.impl.localColors
import com.kamabd.uikit.theme.impl.localDimens
import com.kamabd.uikit.theme.impl.localShapes
import com.kamabd.uikit.theme.impl.localTypography
import com.kamabd.uikit.utils.clickableWithRipple

@Composable
fun FavoritesContent(
    items: List<CurrencyInfo>,
    onFavoriteIconClicked: (CurrencyInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(localDimens.spacing_16),
        modifier = modifier.fillMaxSize()
    ) {
        items(items) {
            FavoriteItemContent(
                item = it,
                onFavoriteIconClicked = onFavoriteIconClicked
            )
            Spacer(modifier = Modifier.height(localDimens.spacing_8))
        }
    }
}

@Composable
fun FavoriteItemContent(
    item: CurrencyInfo,
    onFavoriteIconClicked: (CurrencyInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(localShapes.medium)
            .background(localColors.bgColors.bgColorSecondary)
            .padding(
                horizontal = localDimens.spacing_16,
                vertical = localDimens.spacing_12
            )
    ) {
        Text(
            text = item.currencyCode,
            style = localTypography.body1,
            color = localColors.textColors.textPrimary,
            modifier = Modifier
                .fillMaxWidth(1f)
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(localDimens.spacing_8))
        Text(
            text = item.currencyValue.toString(),
            style = localTypography.body3,
            color = localColors.textColors.textPrimary
        )
        Spacer(modifier = Modifier.width(localDimens.spacing_16))
        Icon(
            painter = painterResource(id = R.drawable.ic_svg_favorites_filled),
            tint = localColors.iconsColors.iconYellow,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickableWithRipple {
                    onFavoriteIconClicked(item)
                }
        )
    }
}