package com.example.editor.repository

import com.example.editor.data.User
import com.example.editor.network.UsersService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val usersService: UsersService
){

    suspend fun getUserInformation(userId:Int) : User =
        usersService.getUserInformation(userId)

    suspend fun checkEmail(email: String) : String =
        usersService.checkEmail(email)

    suspend fun getUsers( email : String,password : String): User =
        usersService.getUsers(email,password)

    suspend fun insertUsers(
        username : String,
        uEmail : String,
        uPhone : String,
        uPassword : String,
    ) : User =
        usersService.insertUsers(username,uEmail,uPhone,uPassword)

    suspend fun updateUserName(userId : Int , userName : String) =
        usersService.reName(userId,userName)

    suspend fun updateUserPassword(userId : Int , password: String , newPassword : String) : Int =
        usersService.rePassword(userId,password,newPassword)

}

