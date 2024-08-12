package com.example.editor.presentation.sign_in


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.editor.data.ProtoDataStore
import com.example.editor.data.Response
import com.example.editor.repository.UserProtoRepository
import com.example.editor.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userProtoRepository: UserProtoRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    var responseState : Response by mutableStateOf(Response.Success(false))

    var state by mutableStateOf(false)
    var check by mutableStateOf(false)
    val listOfInput = mutableListOf(
        mutableStateOf(""),
        mutableStateOf("")
    )
    val listOfLabel = listOf(
        "email",
        "password"
    )
    val listOfError = listOf(
        false,
        false
    )

    fun login() = viewModelScope.launch {
        try {
            responseState = Response.Loading
            val user = userRepository.getUsers(listOfInput[0].value,listOfInput[1].value)
            Log.d("ak", "${user}")
            userProtoRepository.updateUserProto(ProtoDataStore(user.u_username,user.u_id,0,0))
            state = true
            responseState = Response.Success(true)
        } catch (e: Exception) {
            responseState = Response.Error
            println("abdoooo+$e")
        }
    }
}