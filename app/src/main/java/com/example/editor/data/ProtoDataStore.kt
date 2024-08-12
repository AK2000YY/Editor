package com.example.editor.data

data class ProtoDataStore(
    var username : String ?= null,
    var userId : Int ?=null,
    var questionsSolved : Int ?=null,
    var topicSolved : Int ?=null
)