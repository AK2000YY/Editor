package com.example.editor.presentation.compiler

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.editor.data.Code
import com.example.editor.repository.CodeRepository
import com.example.editor.repository.UserProtoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.sql.Types.NULL
import javax.inject.Inject

@HiltViewModel
class CompilerViewModel @Inject
constructor(
    private val codeRepository: CodeRepository,
    private val userProtoRepository: UserProtoRepository
): ViewModel() {
    private val _suggestions = MutableStateFlow<List<String>>(listOf())
    val suggestion: StateFlow<List<String>> = _suggestions

    private val _listOfCodes = MutableStateFlow<List<Code>>(listOf())
    val listOfCodes: StateFlow<List<Code>> = _listOfCodes

    private var userId by mutableIntStateOf(0)


    var textFieldValue by mutableStateOf(TextFieldValue("""#include<iostream>

using namespace std;

int main(){
    cout<<"hello world"<<endl;
    return 0;
}""".trimIndent()))

    var lineTops by mutableStateOf(emptyArray<Float>())

    private var subText by mutableStateOf("")

    private val trie = mutableListOf<TrieNode>()
    private val trieFunction = mutableListOf<TrieNode>()

    private val visitedString = mutableListOf<String>()

    //text in basicTextField
    var text by mutableStateOf("")
    private var testText by mutableStateOf("")

    //run code from server
    var input by mutableStateOf("")
    var resultRun by mutableStateOf("")
    var expansion by mutableStateOf(false)

    //position of char
    var offset by mutableIntStateOf(0)

    //
    var plusOffset by mutableIntStateOf(0)

    //corrdinate of cursor
    var rect by mutableStateOf(Rect(0f, 0f, 0f, 0f))

    //for start and text we went to replace
    private var start by mutableIntStateOf(0)

    //for add code and get codes
    var size by mutableStateOf(IntSize.Zero)
    var windowPopUp by mutableIntStateOf(0)
    var codeTitle by mutableStateOf("")
    var errorAdd by mutableStateOf(false)
    var placeAdd by mutableStateOf("Enter your code name")
    var codeName by mutableStateOf("File Name")

    //for top bar
    var sizeBottomBar by mutableStateOf(IntSize.Zero)

    //for format code
    private var prefixByPress by mutableIntStateOf(0)
    var error by mutableStateOf(false)

    //for top bar
    var canSearch by mutableStateOf(false)
    var inputSearch by mutableStateOf("")
    var saveCode by mutableStateOf("save")

    init {
        getUserId()
        offset = -1
        trie.add(TrieNode())
        for (i in data.list) {
            add(i)
        }
        trieFunction.add(TrieNode())
        for(i in dataDote.list){
            addFunction(i)
        }
        _suggestions.value = visitedString
    }

    //run when onValueChange is active
    fun update() {
        offset = textFieldValue.selection.start
        text = textFieldValue.text
        start = offset - 1
        while (start > 1 && text[start] != ' ' && text[start] != '\n' && text[start] != '<' && text[start] != ';'&& text[start] != '(' && text[start] != '.') {
            start--
        }
        if(text[start] == '.'){
            println(text.substring(start, offset))
            subText = text.substring(start+1, offset)
            if (offset > 0) checkFunction(subText )
            return
        }
        subText = text.substring(start+1, offset)
        if (offset > 0) check(subText )
    }

    //paste suggestion text
    fun paste(text: String) {
        plusOffset = text.length - (offset - start) + 1
        this.text = this.text.replaceRange(start + 1, offset, text)
    }

    //add to trie
    private fun add(s: String) {
        var cur = 0
        for (i in s.indices) {
            val index = (s[i] - NULL).code
            if (trie[cur].next[index] == -1) {
                trie.add(TrieNode())
                trie[cur].next[index] = trie.size - 1
            }
            cur = trie[cur].next[index]

            if (i == s.lastIndex) {
                trie[cur].isEnd = true
            }
        }
    }

    private fun addFunction(s: String) {
        var cur = 0
        for (i in s.indices) {
            val index = (s[i] - NULL).code
            if (trieFunction[cur].next[index] == -1) {
                trieFunction.add(TrieNode())
                trieFunction[cur].next[index] = trieFunction.size - 1
            }
            cur = trieFunction[cur].next[index]

            if (i == s.lastIndex) {
                trieFunction[cur].isEnd = true
            }
        }
    }

    //check if find in trie
    private fun check(s: String ): Boolean {
        var cur = 0
        for (i in s.indices) {
            val index = (s[i] - NULL).code
            if (trie[cur].next[index] == -1) {
                _suggestions.value = listOf()
                return false
            }
            cur = trie[cur].next[index]
        }
        visitedString.clear()
        if (s.isNotEmpty()) rec(cur, s)
        _suggestions.value = visitedString
        return trie[cur].isEnd
    }

    private fun checkFunction(s: String): Boolean {
        var cur = 0
        println(s + " is found")
        for (i in s.indices) {
            val index = (s[i] - NULL).code
            if (trieFunction[cur].next[index] == -1) {
                _suggestions.value = listOf()
                return false
            }
            cur = trieFunction[cur].next[index]
        }
        visitedString.clear()
        if (s.isNotEmpty()) recFunction(cur, s)
        _suggestions.value = visitedString
        return trieFunction[cur].isEnd
    }

    //find the strings of suggestion
    private fun rec(x: Int, s: String) {
        for (i in 0 until 128) {
            if (trie[x].next[i] != -1) {
                val xx = trie[x].next[i]
                if (trie[xx].isEnd) {
                    visitedString.add(s + (i.toChar()))
                    rec(xx, s + (i.toChar()))
                } else rec(xx, s + (i.toChar()))
            }
        }
    }

    private fun recFunction(x: Int, s: String) {
        for (i in 0 until 128) {
            if (trieFunction[x].next[i] != -1) {
                val xx = trieFunction[x].next[i]
                if (trieFunction[xx].isEnd) {
                    visitedString.add(s + (i.toChar()))
                    recFunction(xx, s + (i.toChar()))
                } else recFunction(xx, s + (i.toChar()))
            }
        }
    }

    //for add code to server and get codes from server
    private fun getUserId() = viewModelScope.launch {
        userProtoRepository.getUserProto.collect { userId = it.userId!! }
    }

    fun addCode() = viewModelScope.launch {
        try {
            if(userId == 0 || codeTitle == "") throw IllegalArgumentException("not user")
            val s = codeRepository.addCode(
                userId,
                codeTitle,
                textFieldValue.text
            )
            if(s!="accept"||codeTitle==""){
                placeAdd = "the title is token"
                saveCode = "something went wrong"
            }
            else{
                placeAdd = "enter code title"
                codeTitle = ""
                saveCode = "code is saved"
                windowPopUp = 0
            }
        } catch (e: Exception) {
            Log.d("abdotest", "$e")
            placeAdd = "the title is token"
            saveCode = "something went wrong"
        }
    }

    fun getCodes() = viewModelScope.launch {
        try {
            _listOfCodes.emit(codeRepository.getCodes(userId))
            println(listOfCodes.value)
            Log.d("teeest", "${_listOfCodes.value}")
        } catch (e: Exception) {
            Log.d("teeest", "$e")
        }
    }

    //for format code
    fun format() {
        var text = textFieldValue.text
        var indent = 0
        var index = -1
        for(i in text.indices){
            if(i == index){
                index = -1
            }
            if(index!=-1) continue
            if(i+1<text.length&&text.substring(i,i+2)=="do"){
                for(j in i+2 until text.length){
                    if(index!=-1) break
                    else if(text[j]==' ') continue
                    else if(text[j]=='{'){
                        index = j
                        for(z in j+1 until text.length)
                            if(text[z]=='\n') {
                                index = j
                                break
                            }
                            else if(text[z]==' ') continue
                            else{
                                text = text.substring(0,z)+'\n'+text.substring(z)
                                index = z;
                                break
                            }
                    }
                    else break
                }
            }
            if(i+3<text.length&&text.substring(i,i+4)=="for("){
                for(j in i+4 until text.length){
                    if(text[j]==' ') continue
                    if(text[j]==')'){
                        index = j
                        for(z in j+1 until text.length)
                            if(text[z]=='{') {
                                index = j
                                break
                            }
                    }
                }
            }
            if(text[i]==';')
                for(j in i+1 until text.length)
                    if(text[j]==' ') continue
                    else if(text[j]!='\n'){
                        text = text.substring(0,i+1)+'\n'+text.substring(i+1)
                        break
                    }
                    else break
            if(text[i]==')')
                for(j in i+1 until text.length)
                    if(text[j] == ' ') continue
                    else if(text[j]=='{'){
                        for(x in j+1 until text.length)
                            if(text[x] == ' ') continue
                            else if(text[x]== '\n') break
                            else {
                                text = text.substring(0,j+1)+'\n'+text.substring(j+1)
                                break
                            }
                        break
                    }
                    else break
            if(text[i]=='}'){
                for(j in i+1 until text.length){
                    if(index!=-1) break
                    else if(j+4<text.length && text.substring(j,j+5)=="while")
                        index = j+4
                    else if(text[j]=='\n') break
                    else if(text[j]==' ') continue
                    else{
                        text = text.substring(0,i+1)+'\n'+text.substring(i+1)
                        index = j
                    }
                }
            }
        }
        val list = text.split('\n').toMutableList()
        for(i in list.indices){
            list[i] = list[i].trimStart()
        }
        for(i in list.indices){
            if(list[i].isNotEmpty() &&list[i][0]=='}') list[i] = "    ".repeat(maxOf(0,indent-1)) + list[i]
            else list[i] ="    ".repeat(indent) + list[i]
            for(j in list[i]){
                if(j == '{') indent++
                if(j== '}') indent = maxOf(indent-1,0 )
            }
        }
        text = ""
        for(i in list.indices) text+=list[i]+if(i<list.size-1) '\n' else ""
        println(text)
        println(textFieldValue.text)
        textFieldValue = TextFieldValue(text, TextRange(text.length))
    }

    fun formatByPress() {
        offset = textFieldValue.selection.start
        prefixByPress = 0
        for (i in 0 until offset)
            if (textFieldValue.text[i] == '{') prefixByPress++
            else if (textFieldValue.text[i] == '}') prefixByPress--
            else continue
        if (prefixByPress < 0) prefixByPress = 0
        plusOffset = offset + prefixByPress * 4 + 1
        testText = textFieldValue.text.substring(
            0,
            offset
        ) + '\n' + "    ".repeat(prefixByPress) + textFieldValue.text.substring(
            offset,
            textFieldValue.text.length
        )
        textFieldValue = TextFieldValue(testText, TextRange(plusOffset))
    }

    //for run code from server
    fun runCode() = viewModelScope.launch {
        try {
            resultRun = codeRepository.getResults(textFieldValue.text,input)
            println(resultRun)
        }catch (e : Exception){
            Log.d("test","$e")
            resultRun = ""
        }
    }

}