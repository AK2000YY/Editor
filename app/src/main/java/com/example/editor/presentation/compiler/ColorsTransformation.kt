package com.example.editor.presentation.compiler

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import com.example.editor.ui.theme.MyBlue
import com.example.editor.ui.theme.Orange
import com.example.editor.ui.theme.OrangePro

class ColorsTransformation(var input: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            if(input.isEmpty()) buildAnnotatedStringWithColors(text.toString())
            else buildAnnotatedStringWithColorsWithSearch(text.toString(),input),
            OffsetMapping.Identity)
    }
}

//auto coloring
fun buildAnnotatedStringWithColors(text:String): AnnotatedString {
    val list : MutableList<String> = mutableListOf()
    val listBlue = setOf("int","double","float","bool","using","namespace","if","else","while","do","for","return")
    val builder = AnnotatedString.Builder()
    list.addAll(stringToList(text))
    for (word in list) {
        builder.withStyle(style = SpanStyle(
            color = if(word!=" "&&listBlue.contains(word)) MyBlue
            else if(word.length==1&&(word>="{"&&word<="~")||(word>="["&&word<="`")||(word>=":"&&word<="@")||(word>="!"&&word<="/")) Orange
            else if(word!=" "&&(word=="include"||word=="std"||word=="define")) OrangePro
            else Color.White
        )
        ) {
            append(word)
        }
    }

    return builder.toAnnotatedString()
}

//when user searches about specific word
fun buildAnnotatedStringWithColorsWithSearch(text:String,input : String): AnnotatedString {
    var j = 0
    var subTextBackground = ""
    var subText = ""
    val listOfList : MutableList<Pair<MutableList<String>,Boolean>> = mutableListOf()
    val listBlue = setOf("int","double","float","bool","using","namespace","if","else","while","do","for","return")
    val builder = AnnotatedString.Builder()
    for(i in text){
        if(i==input[j]){
            subTextBackground +=i
            j++
            if(j==input.length){
                if(subText.isNotEmpty()){
                    listOfList.add(Pair(stringToList(subText),false))
                    subText = ""
                }
                listOfList.add(Pair(stringToList(subTextBackground),true))
                subTextBackground =""
                j=0
            }
        }else{
            subText += subTextBackground + i
            subTextBackground = ""
            j = 0
        }
    }
    if(subText.isNotEmpty()||subTextBackground.isNotEmpty()) listOfList.add(Pair(stringToList(subText+subTextBackground),false))

    for(i in listOfList){
        if(i.second){
            for(z in i.first){
                builder.withStyle(style = SpanStyle(
                    Color.Black,
                    background = Color.White.copy(0.7f)
                )){
                    append(z)
                }
            }
        }else{
            for(z in i.first){
                builder.withStyle(style = SpanStyle(
                    color = if(z!=" "&&listBlue.contains(z)) MyBlue
                    else if(z.length==1&&(z>="{"&&z<="~")||(z>="["&&z<="`")||(z>=":"&&z<="@")||(z>="!"&&z<="/")) Orange
                    else if(z!=" "&&(z=="include"||z=="std"||z=="define")) OrangePro
                    else Color.White
                )){
                    append(z)
                }
            }
        }
    }

    return builder.toAnnotatedString()
}

//to transform string to list
fun stringToList(text : String) : MutableList<String>{
    val list : MutableList<String> = mutableListOf()
    var subText = ""
    for(i in text){
        if((i in 'a'..'z')||(i in 'A'..'Z')) subText+=i
        else {
            if(subText.isNotEmpty()){
                list.add(subText)
                subText=""
            }
            list.add(i.toString())
        }
    }
    if(subText.isNotEmpty()) list.add(subText)
    return  list
}