package com.example.editor.presentation.compiler


data class TrieNode(
    val next: IntArray = IntArray(128) { -1 },
    var isEnd: Boolean = false
)

