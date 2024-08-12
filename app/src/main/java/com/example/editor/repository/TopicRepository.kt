package com.example.editor.repository

import com.example.editor.data.Topic
import com.example.editor.network.UsersService
import javax.inject.Inject

class TopicRepository @Inject constructor(
    private val usersService: UsersService
) {

    suspend fun getIdTopics() : List<Int> = usersService.getIdTopics()

    suspend fun getTopic(topicId:Int) : Topic = usersService.getTopic(topicId)

}