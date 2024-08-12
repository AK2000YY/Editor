package com.example.editor.presentation.competition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.competition.CompetitionViewModel
import com.example.editor.ui.theme.InsideEditorColor

@Composable
fun ErrorScreen(
    viewModel: CompetitionViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "some thing is error",
            style = TextStyle(
                Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
        Box(
            modifier = Modifier
                .height(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(InsideEditorColor)
                .clickable {
                    viewModel.getUserId()
                },
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "refresh page",
                style = TextStyle(
                    Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(15.dp)
            )
        }
    }
}