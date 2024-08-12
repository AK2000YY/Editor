package com.example.editor.component

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemTextField(
    modifier : Modifier,
    text : String,
    labelName : String,
    placeholder: String,
    error : Boolean,
    onValueChange : (text : String) -> Unit,
) {
    var state by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = modifier
            .onFocusChanged {
                state = !state
                Log.d("abdo","$state")
            },
        value = text,
        onValueChange ={
            onValueChange(it)
        },
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 17.sp
        ),
        label = {
            Text(text = labelName ,
                style = TextStyle(Color.White,fontSize = 16.sp)
            )
        },
        placeholder = {
            Text(
                text = if(error) placeholder else "Enter your $placeholder",
                style = TextStyle(Color.White,fontSize = 16.sp)
            )
        },
        trailingIcon = {
            if(error){
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = if(state) Color.LightGray.copy(0f) else Color.LightGray.copy(0.3f),
            unfocusedBorderColor = if(error) Color.Red else Color.Gray,
            focusedBorderColor = if(error) Color.Red else Color.LightGray
        ),
        singleLine = false
    )
}