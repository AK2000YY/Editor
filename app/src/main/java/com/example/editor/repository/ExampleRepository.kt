package com.example.editor.repository

import com.example.editor.data.Example
import com.example.editor.network.UsersService
import javax.inject.Inject

class ExampleRepository @Inject constructor(
    private val usersService: UsersService
) {
    suspend fun getExamples (topicId:Int) : List<Example> =
        usersService.getExamples(topicId)
}