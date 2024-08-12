package com.example.editor.repository

import com.example.editor.data.QuestionSolved
import com.example.editor.network.UsersService
import javax.inject.Inject

class QuestionSolvedRepository @Inject constructor(
    private val usersService: UsersService
) {
    suspend fun getNumberOfTopicAndQuestion(userId:Int) : List<Int> =
        usersService.getNumberOfTopicAndQuestion(userId)

    suspend fun getLastSolvedUser(userId: Int) : QuestionSolved =
        usersService.getLastSolvedUser(userId)

    suspend fun insertSolvedQuestion(userId: Int,questionId:Int,topicId:Int){
        usersService.insertSolvedQuestion(userId,questionId,topicId)
    }

    suspend fun getTopicAndQuestion(userId : Int , topicId: Int , questionId: Int) =
        usersService.getTopicAndQuestion(userId,topicId,questionId)
}