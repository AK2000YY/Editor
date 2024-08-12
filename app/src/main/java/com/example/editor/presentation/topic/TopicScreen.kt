package com.example.editor.presentation.topic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.editor.SharedViewModel
import com.example.editor.presentation.topic.component.Example
import com.example.editor.presentation.topic.component.NextTutorial
import com.example.editor.ui.theme.MyGray
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun TopicScreen(
    id : Int,
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    viewModel: TopicViewModel = hiltViewModel()
) {
    viewModel.topicId = id
    viewModel.getTopic()
    viewModel.getExamples()
    viewModel.getUserid()
    val listOfContent by viewModel.listOfContent.collectAsState()
    val listExample by viewModel.listExample.collectAsState()
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
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = viewModel.title,
                style = TextStyle(
                    Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(modifier = Modifier.height(20.dp))
            for(i in listOfContent.indices){
                Text(
                    text = listOfContent[i],
                    style = TextStyle(
                        Color.White
                    ),
                )
                if(i<listExample.size)
                    Example(
                        title = listExample[i].e_name!!,
                        text = listExample[i].e_content!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                2.dp,
                                Color.Black,
                                RoundedCornerShape(12.dp)
                            )
                    )
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
        NextTutorial(
            viewModel.topicId,
            viewModel.canTransportToQuestion,
            viewModel.numberOFQuestion,
            sharedViewModel,
            navController
        ) { viewModel.insertTopic() }
    }
}