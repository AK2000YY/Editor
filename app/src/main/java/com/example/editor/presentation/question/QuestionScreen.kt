package com.example.editor.presentation.question

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.editor.SharedViewModel
import com.example.editor.component.ProgressBar
import com.example.editor.data.Response
import com.example.editor.presentation.question.component.BlankQuestionScreen
import com.example.editor.presentation.question.component.ChoicesQuestionScreen
import com.example.editor.presentation.question.component.IsTrue
import com.example.editor.presentation.question.component.Next
import com.example.editor.ui.theme.LightBlue
import com.example.editor.ui.theme.MyGray
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun QuestionScreen(
    questionNumber : Int,
    topicId : Int,
    numberOfQuestion : Int,
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    viewModel : QuestionViewModel = hiltViewModel()
){
    var can by remember { mutableStateOf(true) }
    viewModel.questionNumber = questionNumber
    viewModel.topicId = topicId
    viewModel.numberOfQuestion = numberOfQuestion
    viewModel.getUserId()
    if(viewModel.questionNumber!=-1 && viewModel.topicId!=-1 && viewModel.numberOfQuestion!=-1 && can){
        can = false
        viewModel.getQuestion()
    }
    if(viewModel.response == Response.Success(true)){
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(OutsideEditorColor)
                    .padding(10.dp)
                    .border(
                        2.dp,
                        Color.Black,
                        RoundedCornerShape(12.dp)
                    )
            ){
                if(viewModel.question.q_type == "e"){
                    if(viewModel.can){
                        viewModel.formatInputForTypeE()
                    }
                    if(viewModel.response == Response.Success(true)){
                        BlankQuestionScreen(
                            viewModel
                        )
                    }
                }
                else{
                    if(viewModel.can){
                        viewModel.formatInputForTypeS()
                    }
                    if(viewModel.response == Response.Success(true))
                        ChoicesQuestionScreen(
                            viewModel
                        )
                }
                Next {
                    viewModel.next()
                }
            }
            if(viewModel.isTrue !=0)
                IsTrue(
                    Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .clip(RoundedCornerShape(20.dp,20.dp,0.dp,0.dp))
                        .background(LightBlue),
                    sharedViewModel,
                    navController,
                    viewModel
                )
        }
    }else{
        Box(
            modifier =Modifier
                .fillMaxSize()
        ){
            ProgressBar()
        }
    }
}