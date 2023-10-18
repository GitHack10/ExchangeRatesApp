package com.kamabd.exchangeratesapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kamabd.exchangeratesapp.R
import com.kamabd.uikit.theme.impl.localColors
import com.kamabd.uikit.theme.impl.localDimens
import com.kamabd.uikit.theme.impl.localTypography
import com.kamabd.uikit.utils.clickableWithRipple

@Composable
fun AppBarWithArrow(
    title: String?,
    pressOnBack: () -> Unit
) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = localColors.bgColors.bgColorHeader,
        modifier = Modifier.height(58.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(localDimens.spacing_4))
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = localColors.iconsColors.iconPrimary,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickableWithRipple {
                        pressOnBack()
                    }
                    .padding(localDimens.spacing_8)
            )
            Spacer(modifier = Modifier.width(localDimens.spacing_8))
            Text(
                text = title ?: "",
                style = localTypography.title1,
                color = localColors.textColors.textPrimary
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun AppBarWithArrowPreview() {
    AppBarWithArrow(
        title = stringResource(id = R.string.app_name)
    ) {
    }
}