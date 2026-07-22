package com.mizanservicecenter.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mizanservicecenter.app.repository.ConnectivityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WebViewModel(application: Application) : AndroidViewModel(application) {
    private val connectivityRepository = ConnectivityRepository(application)

    val isConnected: StateFlow<Boolean> = connectivityRepository.isConnected

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _loadingProgress = MutableStateFlow(0f)
    val loadingProgress: StateFlow<Float> = _loadingProgress.asStateFlow()

    private val _pageTitle = MutableStateFlow("")
    val pageTitle: StateFlow<String> = _pageTitle.asStateFlow()

    private val _canGoBack = MutableStateFlow(false)
    val canGoBack: StateFlow<Boolean> = _canGoBack.asStateFlow()
    
    private val _canGoForward = MutableStateFlow(false)
    val canGoForward: StateFlow<Boolean> = _canGoForward.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    fun setProgress(progress: Int) {
        _loadingProgress.value = progress / 100f
    }

    fun setTitle(title: String) {
        _pageTitle.value = title
    }

    fun setNavigationState(canGoBack: Boolean, canGoForward: Boolean) {
        _canGoBack.value = canGoBack
        _canGoForward.value = canGoForward
    }
    
    fun setError(error: Boolean) {
        _hasError.value = error
    }
}
