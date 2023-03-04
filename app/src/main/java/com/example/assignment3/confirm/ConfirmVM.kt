package com.example.assignment3.confirm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ConfirmVM : ViewModel(), IConfirmViewModel {
    private val _showConfirmDialog: MutableState<Boolean> = mutableStateOf(false)
    private var _onConfirmDeleteCallback: (suspend () -> Unit)? = null
    override val showConfirmDialog: State<Boolean> = _showConfirmDialog
    private val _waiting: MutableState<Boolean> = mutableStateOf(false)
    override val waiting: MutableState<Boolean> = _waiting
    private val _waitingProgress: MutableState<Float> = mutableStateOf(0.0f)
    override val waitingProgress: MutableState<Float> = _waitingProgress

    override fun showConfirmDelete(onConfirm: suspend () -> Unit) {
        _showConfirmDialog.value = true
        _onConfirmDeleteCallback = onConfirm
    }

    override fun onConfirmDelete() {
        if(_onConfirmDeleteCallback != null) {
            viewModelScope.launch {
                _onConfirmDeleteCallback?.invoke()
                dismissDialog()
            }
        }
    }

    override fun dismissDialog() {
        _showConfirmDialog.value = false
    }
}