package com.example.editor.network

import com.example.editor.core.Constants.ADD_CODE
import com.example.editor.core.Constants.CHECK_EMAIL
import com.example.editor.core.Constants.GET_CODES
import com.example.editor.core.Constants.GET_EXAMPLES
import com.example.editor.core.Constants.GET_ID_TOPICS
import com.example.editor.core.Constants.GET_LAST_SOLVED
import com.example.editor.core.Constants.GET_NUMBER_QUESTION_FOR_TOPIC
import com.example.editor.core.Constants.GET_NUMBER_TOPIC_AND_QUESTION
import com.example.editor.core.Constants.GET_RESULT
import com.example.editor.core.Constants.GET_TOPIC
import com.example.editor.core.Constants.GET_TOPIC_QUESTION
import com.example.editor.core.Constants.GET_USER_INFORMATION
import com.example.editor.core.Constants.INSERT_SOLVED_QUESTION
import com.example.editor.core.Constants.NAME
import com.example.editor.core.Constants.QUESTION
import com.example.editor.core.Constants.NUMBER_QUESTION
import com.example.editor.core.Constants.PASSWORD
import com.example.editor.core.Constants.SIGNING
import com.example.editor.core.Constants.SIGNUP
import com.example.editor.data.Code
import com.example.editor.data.Example
import com.example.editor.data.Question
import com.example.editor.data.QuestionSolved
import com.example.editor.data.Topic
import com.example.editor.data.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersService{
    //for user
    @FormUrlEncoded
    @POST(GET_USER_INFORMATION)
    suspend fun getUserInformation(
        @Field("u_id")
        userId : Int
    ) : User

    @FormUrlEncoded
    @POST(CHECK_EMAIL)
    suspend fun checkEmail(
        @Field("u_email")
        email:String
    ) : String

    @FormUrlEncoded
    @POST(SIGNING)
    suspend fun getUsers(
        @Field("u_email")
        email : String,
        @Field("u_password")
        password : String
    ) : User

    @FormUrlEncoded
    @POST(SIGNUP)
    suspend fun insertUsers(
        @Field("u_username")
        userName : String,
        @Field("u_email")
        uEmail : String,
        @Field("u_phone")
        uPhone : String,
        @Field("u_password")
        uPassword : String,
    ) : User

    @FormUrlEncoded
    @POST(NAME)
    suspend fun reName(
        @Field("u_id")
        userId : Int,
        @Field("u_username")
        userName : String
    )

    @FormUrlEncoded
    @POST(PASSWORD)
    suspend fun rePassword(
        @Field("u_id")
        userId : Int,
        @Field("u_password")
        uPassword : String,
        @Field("new_password")
        newPassword : String,
    ) : Int

    //for code
    @FormUrlEncoded
    @POST(GET_RESULT)
    suspend fun getResult(
        @Field("c_code")
        code:String,
        @Field("c_input")
        input:String
    ):String

    @FormUrlEncoded
    @POST(ADD_CODE)
    suspend fun addCodeToServer(
        @Field("u_id")
        idUser : Int,
        @Field("c_title")
        codeTitle : String,
        @Field("c_content")
        content : String
    ) : String

    @FormUrlEncoded
    @POST(GET_CODES)
    suspend fun getCodes(
        @Field("u_id")
        idUser : Int
    ) : List<Code>

    //for Topic and it example and it questions
    @GET(GET_ID_TOPICS)
    suspend fun getIdTopics() : List<Int>

    @FormUrlEncoded
    @POST(GET_EXAMPLES)
    suspend fun getExamples(
        @Field("t_id")
        topicId : Int
    ) : List<Example>

    @FormUrlEncoded
    @POST(GET_TOPIC)
    suspend fun getTopic(
        @Field("t_id")
        topicId : Int
    ) : Topic

    @FormUrlEncoded
    @POST(NUMBER_QUESTION)
    suspend fun getQuestions(
        @Field("t_id")
        topicId : Int
    ) : Int

    @FormUrlEncoded
    @POST(QUESTION)
    suspend fun getQuestion(
        @Field("q_number")
        questionId : Int,
        @Field("t_id")
        topicId : Int
    ) : Question

    @GET(GET_NUMBER_QUESTION_FOR_TOPIC)
    suspend fun getNumberQuestionTopic(
    ) : List<List<Int>>

    @FormUrlEncoded
    @POST(GET_TOPIC_QUESTION)
    suspend fun getTopicAndQuestion(
        @Field("u_id")
        userId : Int,
        @Field("t_id")
        topicId : Int,
        @Field("q_id")
        questionId : Int
    ) : Int

    @FormUrlEncoded
    @POST(GET_NUMBER_TOPIC_AND_QUESTION)
    suspend fun getNumberOfTopicAndQuestion(
        @Field("u_id")
        userId : Int
    ) : List<Int>

    @FormUrlEncoded
    @POST(GET_LAST_SOLVED)
    suspend fun getLastSolvedUser(
        @Field("u_id")
        userId : Int
    ) : QuestionSolved

    @FormUrlEncoded
    @POST(INSERT_SOLVED_QUESTION)
    suspend fun insertSolvedQuestion(
        @Field("u_id")
        userId:Int,
        @Field("q_id")
        questionId:Int,
        @Field("t_id")
        topicId:Int
    )
}