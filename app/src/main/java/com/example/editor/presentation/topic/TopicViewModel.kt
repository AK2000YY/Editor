package com.example.editor.presentation.topic

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.editor.data.Example
import com.example.editor.repository.ExampleRepository
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
class TopicViewModel @Inject constructor(
    private val topicRepository: TopicRepository,
    private val exampleRepository: ExampleRepository,
    private val questionRepository: QuestionRepository,
    private val questionSolvedRepository: QuestionSolvedRepository,
    private val userProtoRepository: UserProtoRepository
) : ViewModel() {

    private val _listExamples = MutableStateFlow<List<Example>>(listOf())
    val listExample : StateFlow<List<Example>> = _listExamples

    private val _listOfContent = MutableStateFlow<List<String>>(listOf())
    val listOfContent : StateFlow<List<String>> = _listOfContent

    var userId by mutableIntStateOf(-1)
    var title by mutableStateOf("")
    var topicId by mutableIntStateOf(-1)
    var numberOFQuestion by mutableIntStateOf(0)
    var canTransportToQuestion by mutableStateOf(false)


    fun getTopic() = viewModelScope.launch{
        try {
            val topic = topicRepository.getTopic(topicId)
            numberOFQuestion = questionRepository.getNumberOfQuestion(topicId)
            _listOfContent.emit(topic.t_content!!.split('`'))
            title = topic.t_name!!
            if(numberOFQuestion>0) canTransportToQuestion = true
            Log.d("12341234","$numberOFQuestion $title ")
        }catch (e:Exception){
            Log.d("12341234","$e")
        }
    }

    fun getExamples() = viewModelScope.launch{
        try {
            _listExamples.emit(exampleRepository.getExamples(topicId))
        }catch (e:Exception){
            println(e)
        }
    }

    fun getUserid() = viewModelScope.launch {
        userProtoRepository.getUserProto.collect{
            userId = it.userId!!
            Log.d("insertTopic","done")
        }
    }

    fun insertTopic() = viewModelScope.launch {
        try {
            questionSolvedRepository.insertSolvedQuestion(userId,0,topicId)
        }catch (_:Exception){
        }
    }

}