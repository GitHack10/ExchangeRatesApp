package com.kamabd.exchangeratesapp.ui.features.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kamabd.exchangeratesapp.ui.features.root.RootContent
import com.kamabd.uikit.theme.impl.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { mainActivityViewModel.isLoading.value }
        }
        setContent {
            AppTheme {
                RootContent()
            }
        }
    }
}