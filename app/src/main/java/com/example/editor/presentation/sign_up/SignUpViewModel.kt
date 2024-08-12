package com.example.editor.presentation.sign_up

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.editor.data.ProtoDataStore
import com.example.editor.data.Response
import com.example.editor.repository.QuestionSolvedRepository
import com.example.editor.repository.UserProtoRepository
import com.example.editor.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userProtoRepository: UserProtoRepository,
    private val userRepository: UserRepository,
    private val questionSolvedRepository: QuestionSolvedRepository
) : ViewModel(){

    var responseState : Response by mutableStateOf(Response.Success(false))
        //private set
    private var protoDataStore by mutableStateOf(ProtoDataStore())
    var state by mutableStateOf(false)
    var check by mutableStateOf(false)
    val list = listOf(
        "username",
        "email",
        "phone",
        "password",
        "confirm password"
    )
    val listOfInput = mutableListOf(
        mutableStateOf(""),
        mutableStateOf(""),
        mutableStateOf(""),
        mutableStateOf(""),
        mutableStateOf(""),
    )
    val listOfPlaceholder = listOf(
        mutableStateOf("username"),
        mutableStateOf("email"),
        mutableStateOf("phone"),
        mutableStateOf("password"),
        mutableStateOf("confirm password")
    )
    val listOfError = mutableListOf(
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false)
    )

    fun checkNull() : Boolean{
        for(i in 0..4)
            if(listOfInput[i].value==""){
                listOfError[i].value = true
                listOfPlaceholder[i].value = "It is Empty"
            }
            else{
                listOfError[i].value = false
                listOfPlaceholder[i].value = list[i]
            }
        for(i in 0..4)
            if(listOfError[i].value) return false
        return true
    }

    fun checkEmail()  = viewModelScope.launch {
        responseState = try {
            check = userRepository.checkEmail(listOfInput[1].value) != "used"
            Response.Success(true)
        }catch (e : Exception){
            Response.Error
        }
    }

    fun signup() = viewModelScope.launch {
        try{
            val userR = userRepository.insertUsers(
                listOfInput[0].value,
                listOfInput[1].value,
                listOfInput[2].value,
                listOfInput[3].value
            )
            val numberTopicAndQuestion = questionSolvedRepository.getNumberOfTopicAndQuestion(userR.u_id!!)
            protoDataStore.userId = userR.u_id
            protoDataStore.username = userR.u_username
            protoDataStore.questionsSolved = numberTopicAndQuestion[0]
            protoDataStore.topicSolved = numberTopicAndQuestion[1]
            userProtoRepository.updateUserProto(protoDataStore)
            Log.d("signup","$protoDataStore\n$userR")
            state = true
        }
        catch (e : Exception){
            Log.d("signup","$e")
        }
    }

}


