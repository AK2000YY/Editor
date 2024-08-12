package com.example.editor.repository

import com.example.editor.data.Question
import com.example.editor.network.UsersService
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val usersService: UsersService,
)   {

    suspend fun getNumberOfQuestion(topicId: Int): Int =
        usersService.getQuestions(topicId)

    suspend fun getQuestion(questionNumber: Int, topicId: Int): Question =
        usersService.getQuestion(questionNumber, topicId)

    suspend fun getNumberQuestionTopic(): List<List<Int>> =
        usersService.getNumberQuestionTopic()

}