package com.example.editor.repository

import com.example.editor.data.Code
import com.example.editor.network.UsersService
import javax.inject.Inject

class CodeRepository @Inject constructor(
    private val usersService: UsersService
){
    suspend fun getResults(code:String, input:String) : String{
        return usersService.getResult(code,input)
    }
    suspend fun addCode(idUser:Int,codeTitle:String,code:String) : String {
        return usersService.addCodeToServer(idUser,codeTitle,code)
    }
    suspend fun getCodes(idUser:Int) : List<Code>{
        return usersService.getCodes(idUser)
    }
}