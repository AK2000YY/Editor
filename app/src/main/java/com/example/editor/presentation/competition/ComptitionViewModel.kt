package com.example.editor.presentation.competition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.editor.data.Response
import com.example.editor.repository.QuestionRepository
import com.example.editor.repository.QuestionSolvedRepository
import com.example.editor.repository.TopicRepository
import com.example.editor.repository.UserProtoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val topicRepository: TopicRepository,
    private val questionSolvedRepository: QuestionSolvedRepository,
    private val userProtoRepository: UserProtoRepository
) : ViewModel(){

    var response : Response by mutableStateOf(Response.Success(false))
        private set

    private val _listTopic = MutableStateFlow<List<Int>>(listOf())
    private val listTopic : StateFlow<List<Int>> = _listTopic

    private val _listQuestion = MutableStateFlow<List<List<Int>>>(listOf())
    private val listQuestion : StateFlow<List<List<Int>>> = _listQuestion

    private val _listQuestionAndTopic = mutableListOf<Pair<Triple<Int,Int,Int>,Pair<Boolean,Boolean>>>()
    val listQuestionAndTopic: List<Pair<Triple<Int,Int,Int>, Pair<Boolean,Boolean>>> = _listQuestionAndTopic

    var userId by mutableIntStateOf(-1)
    private var isSolved by mutableStateOf(false)

    init {
        getUserId()
    }

    fun getUserId() = viewModelScope.launch {
        response = Response.Loading
        userProtoRepository.getUserProto.collect{
            userId = it.userId!!
            if(userId!=-1) getTopic()
        }
    }

    private fun getTopic() = viewModelScope.launch {
        try {
            response = Response.Loading
            _listTopic.emit(topicRepository.getIdTopics())
            getQuestion()
        }catch (e:Exception){
            response = Response.Error
        }
    }

    private fun getQuestion() = viewModelScope.launch {
        try {
            _listQuestion.emit(questionRepository.getNumberQuestionTopic())
            getContent()
        }catch (e:Exception){
            response = Response.Error
        }
    }

    private fun getContent() = viewModelScope.launch {
        try {
            response = Response.Loading
            _listQuestionAndTopic.clear()
            for(i in listTopic.value.indices){
                isSolved = false
                if(questionSolvedRepository.getTopicAndQuestion(userId,listTopic.value[i], 0)>0)
                    isSolved = true
                _listQuestionAndTopic.add(Pair(Triple(listTopic.value[i],-1,listQuestion.value[i].size), Pair(true,isSolved)))
                for(j in listQuestion.value[i].indices){
                    isSolved = false
                    if(questionSolvedRepository.getTopicAndQuestion(userId,listTopic.value[i], listQuestion.value[i][j])>0)
                        isSolved = true
                    _listQuestionAndTopic.add(Pair(Triple(listQuestion.value[i][j],listTopic.value[i],listQuestion.value[i].size),Pair(false,isSolved)))
                }
            }
            response = Response.Success(true)
        }catch (e:Exception){
            response = Response.Error
            println(e)
        }
    }

}