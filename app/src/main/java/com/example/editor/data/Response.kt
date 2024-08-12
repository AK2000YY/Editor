package com.example.editor.data


sealed interface Response {
    data class Success(val check:Boolean) : Response
    data object Error : Response
    data object Loading : Response
}
