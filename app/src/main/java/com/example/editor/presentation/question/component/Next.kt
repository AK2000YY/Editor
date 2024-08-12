package com.example.editor.presentation.question.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.editor.ui.theme.LightBlue

@Composable
fun BoxScope.Next(
    next:()->Unit,
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .align(Alignment.BottomEnd)
            .height(50.dp)
            .background(
                LightBlue,
                RoundedCornerShape(12.dp)
            )
            .clickable {
                next()
            },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Check Answer",
            style = TextStyle(
                Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
    }
}