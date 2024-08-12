package com.example.editor.presentation.question.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun QuestionText(
    text : String
) {
    Text(
        text = text,
        style = TextStyle(
            Color.White,
            fontSize = 25.sp
        )
    )
}