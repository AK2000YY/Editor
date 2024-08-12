package com.example.editor.presentation.topic.component

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
import androidx.navigation.NavHostController
import com.example.editor.SharedViewModel
import com.example.editor.navigation.Screen
import com.example.editor.ui.theme.LightBlue

@Composable
fun BoxScope.NextTutorial(
    id : Int,
    canTransportToQuestion : Boolean,
    numberOfQuestion : Int,
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    insertTopic:()->Unit
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
                insertTopic()
                sharedViewModel.stateOfCompetition = true
                sharedViewModel.stateOfProfile = true
                if (canTransportToQuestion) {
                    navController.popBackStack()
                    navController.navigate("${Screen.QuestionScreen.route}1/$id/$numberOfQuestion")
                } else {
                    navController.popBackStack()
                    navController.navigate(Screen.TopicScreen.route + (id + 1))
                }
            },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Next Tutorial",
            style = TextStyle(
                Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
    }
}