package com.example.editor.presentation.profile.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserName(
    text : String
) {
    Text(
        text = text,
        style = TextStyle(
            Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        ),
        modifier = Modifier
            .padding(horizontal = 18.dp, vertical = 10.dp)
    )
}