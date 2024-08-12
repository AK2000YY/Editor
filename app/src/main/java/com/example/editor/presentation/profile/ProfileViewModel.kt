package com.example.editor.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.editor.data.ProtoDataStore
import com.example.editor.data.Response
import com.example.editor.data.User
import com.example.editor.repository.QuestionSolvedRepository
import com.example.editor.repository.UserProtoRepository
import com.example.editor.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProtoRepository: UserProtoRepository,
    private val userRepository: UserRepository,
    private val questionSolvedRepository: QuestionSolvedRepository
) : ViewModel(){

    var response : Response by mutableStateOf(Response.Success(false))
        private set

    var responsePopScreen : Response by mutableStateOf(Response.Success(false))
        private set

    var responseLogout : Response by mutableStateOf(Response.Success(false))
        private set

    var protoDataStore : ProtoDataStore by mutableStateOf(ProtoDataStore())
    var user : User by mutableStateOf(User())
    var questionSolved  = mutableListOf<Int>()
    var isPopup by mutableIntStateOf(0)
    var size by mutableStateOf(IntSize.Zero)
    var usernaem by mutableStateOf("")
    var updateName by mutableStateOf("update name")
    var updatePassword by mutableStateOf("update password")
    var password by mutableStateOf("")
    var newPassword by mutableStateOf("")

    init {
        getIdUser()
    }

    fun updatePopUp(screen : Int){
        responsePopScreen = Response.Success(false)
        isPopup = screen
    }

    fun getIdUser() = viewModelScope.launch {
        try {
            userProtoRepository.getUserProto.collect{
                response = Response.Loading
                protoDataStore.username = it.username
                protoDataStore.userId = it.userId
                if(protoDataStore.userId != null && protoDataStore.userId!! > 0) getUser()
            }
        }catch (e:Exception){
            response = Response.Error
        }
    }

    private fun getUser() = viewModelScope.launch {
        try {
            user = userRepository.getUserInformation(protoDataStore.userId!!)
            questionSolved = questionSolvedRepository.getNumberOfTopicAndQuestion(protoDataStore.userId!!).toMutableList()
            response = Response.Success(true)
        }catch (e : Exception){
            println(e)
        }
    }

    fun reName() = viewModelScope.launch {
        try {
            responsePopScreen = Response.Loading
            userRepository.updateUserName(user.u_id!!,usernaem)
            responsePopScreen = Response.Success(true)
            if(responsePopScreen == Response.Success(true))user.u_username = usernaem
        }catch (e : Exception){
            responsePopScreen = Response.Error
        }
    }

    fun rePassword() = viewModelScope.launch {
        try {
            responsePopScreen = Response.Loading
            var done = userRepository.updateUserPassword(user.u_id!!,password,newPassword)
            if(done!=0) responsePopScreen = Response.Success(true)
            else responsePopScreen = Response.Error
        }catch (e : Exception){
            responsePopScreen = Response.Error
        }
    }

    fun restart() = viewModelScope.launch {
        try {
            responseLogout = Response.Loading
            userProtoRepository.updateUserProto(ProtoDataStore("",0,0,0))
            println("xx   done")
            responseLogout = Response.Success(true)
        }catch (e:Exception){
            println("xx   $e")
            responseLogout = Response.Error
        }
    }
}