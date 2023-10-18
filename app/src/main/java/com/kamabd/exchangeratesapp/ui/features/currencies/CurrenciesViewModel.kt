package com.kamabd.exchangeratesapp.ui.features.currencies

import com.kamabd.base.BaseVm
import com.kamabd.domain.CurrencyInfo
import com.kamabd.exchangeratesapp.ui.features.currencies.data.defaultBaseCurrency
import com.kamabd.exchangeratesapp.ui.features.currencies.data.defaultCurrencySymbols
import com.kamabd.i_currencies.use_case.AddCurrencyToFavoritesUseCase
import com.kamabd.i_currencies.use_case.GetLatestCurrenciesUseCase
import com.kamabd.i_currencies.use_case.RemoveCurrencyFromFavoritesUseCase
import com.kamabd.i_currencies.use_case.SubscribeToFavoriteCurrenciesUseCase
import com.kamabd.logger.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val getLatestCurrenciesUseCase: GetLatestCurrenciesUseCase,
    private val addCurrencyToFavoritesUseCase: AddCurrencyToFavoritesUseCase,
    private val removeCurrencyFromFavoritesUseCase: RemoveCurrencyFromFavoritesUseCase,
    private val subscribeToFavoriteCurrenciesUseCase: SubscribeToFavoriteCurrenciesUseCase,
) : BaseVm() {

    private val _uiState = MutableStateFlow<CurrenciesState>(CurrenciesState.Idle)
    val uiState: StateFlow<CurrenciesState>
        get() = _uiState

    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()

    init {
        _uiState.value = CurrenciesState.Loading
        loadLatestCurrencies()
        subscribeToFavoriteCurrencies()
    }

    fun onBaseCurrencySelected(baseCurrency: String) {
        loadLatestCurrencies(baseCurrency)
    }

    fun onFavoriteIconClicked(currencyInfo: CurrencyInfo) {
        // todo handle favorite click
        val currentState = uiState.value as? CurrenciesState.Success ?: return
        val newItems = currentState.data.currencies
            .map {
                if (currencyInfo.currencyCode == it.currencyCode) {
                    it.copy(
                        isFavorite = it.isFavorite.not()
                    )
                } else {
                    it
                }
            }
            .sortedByDescending { it.isFavorite }
        val newState = currentState.data.copy(
            currencies = newItems
        )
        _uiState.value = currentState.copy(data = newState)
        launch {
            if (currencyInfo.isFavorite) {
                removeCurrencyFromFavoritesUseCase(
                    RemoveCurrencyFromFavoritesUseCase.Params(
                        currencyInfo
                    )
                ).handleResult {  }
            } else {
                addCurrencyToFavoritesUseCase(
                    AddCurrencyToFavoritesUseCase.Params(
                        currencyInfo
                    )
                ).handleResult { }
            }
        }
    }

    private fun loadLatestCurrencies(
        baseCurrency: String = defaultBaseCurrency
    ) {
        launch {
            getLatestCurrenciesUseCase(
                GetLatestCurrenciesUseCase.Params(
                    base = baseCurrency,
                    symbols = defaultCurrencySymbols.filterNot { it == baseCurrency },
                )
            )
                .collect {
                    it.handleResultWithError(
                        resultBlock = { result ->
                            val sortedCurrencies = result.data
                                .currencies
                                .sortedByDescending { item -> item.isFavorite }
                            _uiState.value = CurrenciesState.Success(
                                data = result.data.copy(
                                    currencies = sortedCurrencies
                                ),
                                baseCurrency = baseCurrency,
                            )
                        },
                        errorBlock = {
                            AppLogger.logD("GetLatest: ${it.message}")
                        }
                    )
                }
        }
    }

    private fun subscribeToFavoriteCurrencies() {
        launch {
            subscribeToFavoriteCurrenciesUseCase()
                .collect { result ->
                    result.handleResult { favoriteItems ->
                        val currentState = uiState.value
                        if (currentState is CurrenciesState.Success) {
                            val favoriteCurrenciesIds = favoriteItems.map { it.currencyCode }
                            val newItems = currentState.data
                                .currencies
                                .map {
                                    it.copy(
                                        isFavorite = favoriteCurrenciesIds.contains(it.currencyCode)
                                    )
                                }
                                .sortedByDescending { it.isFavorite }
                            _uiState.value = currentState.copy(
                                data = currentState.data.copy(
                                    currencies = newItems
                                )
                            )
                        }
                    }
                }
        }
    }
}
