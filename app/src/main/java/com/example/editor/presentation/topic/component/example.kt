package com.example.editor.presentation.topic.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun Example(
    title : String,
    text : String,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ){
        Column {
            Text(
                text = title,
                style = TextStyle(
                    Color.Red
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
            Text(
                text = text,
                style = TextStyle(
                    Color.White
                ),
                modifier = Modifier
                    .border(
                        2.dp,
                        Color.Black,
                        RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp)
                    )
                    .padding(10.dp)
                    .fillMaxWidth()
            )
        }
    }
}