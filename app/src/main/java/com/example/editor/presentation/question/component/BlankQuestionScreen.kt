package com.example.editor.presentation.question.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.question.QuestionViewModel

@Composable
fun BlankQuestionScreen(
    viewModel: QuestionViewModel = hiltViewModel()
) {
    Column (
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ){
        QuestionText(text = viewModel.question.q_question!!)
        Spacer(modifier = Modifier.height(30.dp))
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
            for(i in viewModel.splitCode.indices){
                if(viewModel.splitCode[i][0].first == "\n"){
                }else{
                    Row(
                        modifier = Modifier
                            .height(40.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for(j in viewModel.splitCode[i].indices){
                            if(viewModel.splitCode[i][j].second){
                                BasicTextField(
                                    value = viewModel.listOfInput[viewModel.listOfLocation.indexOf(Pair(i,j))].value,
                                    onValueChange = {
                                        viewModel.listOfInput[viewModel.listOfLocation.indexOf(Pair(i,j))].value = it
                                    },
                                    textStyle = TextStyle(
                                        Color.Black,
                                        fontSize = 20.sp,
                                        textAlign = TextAlign.Center
                                    ),
                                    singleLine = true,
                                    modifier = Modifier
                                        .background(
                                            Color.LightGray,
                                            RoundedCornerShape(12.dp)
                                        )
                                        .width(60.dp)
                                        .fillMaxHeight()
                                        .border(
                                            1.dp,
                                            Color.DarkGray,
                                            RoundedCornerShape(12.dp)
                                        )
                                )
                            }else {
                                Text(
                                    text = viewModel.splitCode[i][j].first,
                                    style = TextStyle(
                                        Color.White,
                                        fontSize = 20.sp
                                    )
                                )
                            }

                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

