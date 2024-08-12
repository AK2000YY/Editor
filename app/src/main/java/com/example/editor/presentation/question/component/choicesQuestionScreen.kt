package com.example.editor.presentation.question.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.editor.presentation.question.QuestionViewModel
import com.example.editor.ui.theme.Orange

@Composable
fun ChoicesQuestionScreen(
    viewModel: QuestionViewModel = hiltViewModel()
) {
    Column (
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ){
        QuestionText(text = viewModel.question.q_question!!)
        Spacer(modifier = Modifier.height(100.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp,
                    Color.Black,
                    RoundedCornerShape(12.dp)
                )
                .padding(18.dp)
        ){
            for(i in viewModel.listOfInput.indices){
                BoxForChoice(
                    i,
                    viewModel
                )
            }
        }
    }
}

@Composable
fun BoxForChoice(
    index : Int,
    viewModel: QuestionViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp, 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .height(50.dp)
                .width(50.dp)
                .background(if(viewModel.isTrue==1 && viewModel.listOfChoice[index].value) Color.Red else if(viewModel.isTrue==2 && viewModel.listOfChoice[index].value) Color.Green  else if(viewModel.listOfChoice[index].value) Orange else Color.DarkGray)
                .clickable {
                    for(i in viewModel.listOfChoice) i.value = false
                    viewModel.listOfChoice[index].value = true
                },
            contentAlignment = Alignment.Center
        ){
            Text(
                text = ('A'+index).toString(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(if(viewModel.isTrue==1 && viewModel.listOfChoice[index].value ) Color.Red else if(viewModel.isTrue==2 && viewModel.listOfChoice[index].value) Color.Green  else if(viewModel.listOfChoice[index].value) Orange else Color.Gray)
                .clickable {
                    for(i in viewModel.listOfChoice) i.value = false
                    viewModel.listOfChoice[index].value = true
                },
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = viewModel.listOfInput[index].value,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(15.dp,0.dp,0.dp,0.dp)
            )
        }
    }
}
