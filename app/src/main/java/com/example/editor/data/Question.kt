package com.example.editor.data

data class Question(
    var q_id : Int ?= null,
    var q_number : Int ?= null,
    var t_id : Int ?= null,
    var q_question : String ?= null,
    var q_type : String ?= null,
    var q_code : String ?= null,
    var q_input : String ?= null,
    var q_ans : String ?= null
)
