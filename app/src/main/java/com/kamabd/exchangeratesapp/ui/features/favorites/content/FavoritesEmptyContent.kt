package com.kamabd.exchangeratesapp.ui.features.favorites.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kamabd.exchangeratesapp.R
import com.kamabd.uikit.components.TextSource
import com.kamabd.uikit.components.button.AppButton
import com.kamabd.uikit.components.button.AppButtonState
import com.kamabd.uikit.components.button.primaryAppButtonTheme
import com.kamabd.uikit.theme.impl.localColors
import com.kamabd.uikit.theme.impl.localDimens
import com.kamabd.uikit.theme.impl.localTypography

@Composable
fun FavoritesEmptyContent(
    onAddCurrencyClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.favorites_empty),
            style = localTypography.body2,
            color = localColors.textColors.textSecondary
        )
        Spacer(modifier = Modifier.height(localDimens.spacing_32))
        AppButton(
            state = AppButtonState(
                text = TextSource.ResourceIdSource(R.string.favorites_add_currency)
            ),
            theme = primaryAppButtonTheme(),
            onClick = onAddCurrencyClicked,
            modifier = Modifier.fillMaxWidth(.8f)
        )
    }
}