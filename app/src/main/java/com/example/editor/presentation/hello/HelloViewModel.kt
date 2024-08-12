package com.example.editor.presentation.hello

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.editor.repository.QuestionSolvedRepository
import com.example.editor.repository.UserProtoRepository
import com.example.editor.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HelloViewModel @Inject constructor(
    private val userProtoRepository: UserProtoRepository
) :ViewModel(){

    var state by mutableIntStateOf(0)
        private set

    var translate by mutableIntStateOf(500)

    var rotate by mutableIntStateOf(0)

    init{
        getAuthState()
    }

    private fun getAuthState() = viewModelScope.launch {
        userProtoRepository.getUserProto.collect{
            Log.d("hello123","ff ${it.userId}")
            state = it.userId!!
            if(state==0) state=-1
            Log.d("hello123","ss ${it.userId}")

        }
        Log.d("12312345","finished")
    }

}