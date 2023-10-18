package com.kamabd.base

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import com.kamabd.base.coroutines.RequestResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseVm : ViewModel(), CoroutineScope {

    private val parentJob = SupervisorJob()

    override val coroutineContext: CoroutineContext = Dispatchers.IO + parentJob

    @MainThread
    protected open fun onError(error: Throwable) {
        showErrorMessage(error)
    }

    @MainThread
    protected open fun showErrorMessage(error: Throwable) {
    }

    protected suspend fun runOnUi(block: suspend () -> Unit) =
        withContext(Dispatchers.Main) {
            block()
        }

    protected suspend fun <T> RequestResult<T>.handleResult(
        block: suspend (T) -> Unit
    ) = runOnUi {
        when (val result = this@handleResult) {
            is RequestResult.Success -> block(result.data)
            is RequestResult.Error -> onError(result.error)
        }
    }

    protected suspend fun <T> RequestResult<T>.handleResultWithError(
        resultBlock: suspend (T) -> Unit,
        errorBlock: suspend (Throwable) -> Unit
    ) = runOnUi {
        when (val result = this@handleResultWithError) {
            is RequestResult.Success -> resultBlock(result.data)
            is RequestResult.Error -> errorBlock(result.error)
        }
    }
}