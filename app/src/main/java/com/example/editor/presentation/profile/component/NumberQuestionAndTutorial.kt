package com.example.editor.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.editor.presentation.profile.ProfileViewModel
import com.example.editor.ui.theme.InsideEditorColor

@Composable
fun NumberQuestionAndTutorial(
    viewModel: ProfileViewModel
) {
    Row (
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth()
    ){
        BoxItem("Tutorial:", viewModel)
        BoxItem("Question:", viewModel)
    }
}

@Composable
fun RowScope.BoxItem(
    type : String,
    viewModel: ProfileViewModel
) {
    Column(
        modifier = Modifier
            .weight(0.5f)
            .height(100.dp)
            .padding(horizontal = 8.dp)
            .border(
                2.dp,
                Color.Black,
                RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(InsideEditorColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = type,
            style = TextStyle(
                Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = if(type == "Tutorial:") viewModel.questionSolved[0].toString() else viewModel.questionSolved[1].toString(),
            style = TextStyle(
                Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}