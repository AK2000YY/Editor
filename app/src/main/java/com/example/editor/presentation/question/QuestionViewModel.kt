package com.example.editor.presentation.question

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.editor.data.Question
import com.example.editor.data.Response
import com.example.editor.navigation.Screen
import com.example.editor.repository.QuestionRepository
import com.example.editor.repository.QuestionSolvedRepository
import com.example.editor.repository.UserProtoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val userProtoRepository: UserProtoRepository,
    private val questionSolvedRepository: QuestionSolvedRepository
) : ViewModel(){

    var response:Response by mutableStateOf(Response.Success(false))
        private set

    var splitCode = mutableListOf<List<Pair<String,Boolean>>>()
    var listOfInput = mutableListOf<MutableState<String>>()
    var listOfChoice = mutableListOf<MutableState<Boolean>>()
    var listOfLocation = mutableListOf<Pair<Int,Int>>()
    var isTrue by mutableIntStateOf(0)

    var userId by mutableIntStateOf(-1)
    var question by mutableStateOf(Question())
    var questionNumber by mutableIntStateOf(-1)
    var topicId by mutableIntStateOf(-1)
    var numberOfQuestion by mutableIntStateOf(-1)
    var can by mutableStateOf(true)

    fun getUserId() = viewModelScope.launch {
        try {
            userProtoRepository.getUserProto.collect{
                userId = it.userId!!
            }
        }catch (e:Exception){
            println(e)
        }
    }

    fun getQuestion() = viewModelScope.launch {
        try {
            response = Response.Loading
            question=questionRepository.getQuestion(questionNumber,topicId)
            response = Response.Success(true)
        }catch (e:Exception){
            println(e)
        }
    }

    fun formatInputForTypeE(){
        if(can){
            response = Response.Loading
            val split = splitCode(question.q_code!!)
            splitCode = split.first.toMutableList()
            listOfLocation = split.second.toMutableList()
            listOfInput = MutableList(split.second.size){mutableStateOf("")}
            can = false
            response = Response.Success(true)
        }
    }

    fun formatInputForTypeS(){
        if(can){
            response = Response.Loading
            val split = splitMultiChoice(question.q_input!!)
            for(i in split)
                listOfInput.add(mutableStateOf(i))
            listOfChoice = MutableList(split.size){ mutableStateOf(false) }
            can = false
            response = Response.Success(true)
        }
    }

    fun next(){
        if(question.q_type == "e") nextForTypeE()
        if(question.q_type == "s") nextForTypeS()
    }

    private fun nextForTypeE(){
        val list = question.q_ans?.split('`') as MutableList<String>
        for(i in listOfInput.indices){
            var input = ""
            for(j in listOfInput[i].value){
                if(j != ' ') input+=j
            }
            if(input != list[i]){
                isTrue = 1
                return
            }
        }
        isTrue = 2
    }

    private fun nextForTypeS(){
        var ans = 1
        for(i in listOfChoice.indices){
            if(listOfChoice[i].value){
                ans+=i
                isTrue = if(ans.toString() == question.q_ans) 2 else 1
                return
            }
        }
        isTrue = 1
    }

    fun addQuestionSolved(navHostController: NavHostController) = viewModelScope.launch {
        try {
            response = Response.Loading
            questionSolvedRepository.insertSolvedQuestion(userId,questionNumber,topicId)
            if(questionNumber==numberOfQuestion){
                navHostController.popBackStack()
                navHostController.navigate(Screen.TopicScreen.route + (topicId+1))
            }
            else{
                navHostController.popBackStack()
                navHostController.navigate("${Screen.QuestionScreen.route}${questionNumber+1}/${topicId}/${numberOfQuestion}")
            }
        }catch (e:Exception){
            println(e)
        }
    }

}

fun splitCode(text : String) : Pair<List<List<Pair<String,Boolean>>>,List<Pair<Int,Int>>>{
    var subText = ""
    val list = ArrayList<List<Pair<String,Boolean>>>()
    val subList = ArrayList<Pair<String,Boolean>>()
    val listLocation = ArrayList<Pair<Int,Int>>()
    for(i in text){
        when (i) {
            '\n' -> {
                if(subText.isNotEmpty()) subList.add(Pair(subText,false))
                list.add(ArrayList(subList.map { it.copy() }))
                list.add(listOf(Pair(i.toString(),false)))
                subList.clear()
                subText = ""
            }
            '`' -> {
                if(subText.isNotEmpty()) subList.add(Pair(subText,false))
                listLocation.add(Pair(list.size,subList.size))
                subList.add(Pair(i.toString(),true))
                subText = ""
            }
            else -> {
                subText+=i
            }
        }
    }
    if(subText.isNotEmpty()) subList.add(Pair(subText,false))
    if(subList.isNotEmpty()) list.add(subList)
    return Pair(list,listLocation)
}

fun splitMultiChoice(text : String) : List<String>{
    var subText = ""
    val list = ArrayList<String>()
    for(i in text)
        when(i){
            '`' -> {
                if(subText != "") list.add(subText)
                subText = ""
            }
            else -> subText+=i
        }
    if(subText!="") list.add(subText)
    return list
}