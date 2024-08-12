package com.example.editor.presentation.question.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavHostController
import com.example.editor.SharedViewModel
import com.example.editor.presentation.question.QuestionViewModel
import com.example.editor.ui.theme.MyGray

@Composable
fun IsTrue(
    modifier: Modifier,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController,
    viewModel: QuestionViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
    ){
        Box(
            modifier = Modifier
                .weight(1.3f)
                .padding(10.dp,15.dp)
        ){
            Text(
                text = if(viewModel.isTrue==1) "Wrong Answer!" else "Accept Answer!",
                style = TextStyle(
                    color = if(viewModel.isTrue==1) Color.Red else Color.Green,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .padding(10.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MyGray)
                .clickable {
                    sharedViewModel.stateOfProfile = true
                    sharedViewModel.stateOfCompetition = true
                    if(viewModel.isTrue==1) viewModel.isTrue=0
                    else viewModel.addQuestionSolved(navHostController)
                },
            contentAlignment = Alignment.Center
        ){
            Text(
                text = if(viewModel.isTrue==1) "Try Again" else "Next Question",
                style = TextStyle(
                    color = if(viewModel.isTrue==1) Color.Red else Color.Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
    }
}