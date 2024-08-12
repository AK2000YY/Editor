package com.example.editor


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel(){
}


















/*
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo:AuthRepository
) : ViewModel() {
    init {
        getAuthState()
    }
    fun getAuthState() = repo.getAuthState(viewModelScope)

    val isEmailVerified get() = repo.CurrentUser?.isEmailVerified ?: false
}
 */