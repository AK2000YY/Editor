package com.example.editor.presentation.competition.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.editor.navigation.Screen

@Composable
fun Coins(
    numberOfQuestion : Int,
    questionNumber : Int,
    questionTopic : Int,
    alignment : Int,
    icon : ImageVector,
    insideIcon : ImageVector,
    isSolved : Boolean,
    navController : NavHostController
){
    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = if(alignment%2==0) Alignment.CenterStart else Alignment.CenterEnd
        ){
            Box(
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            if(insideIcon==Icons.Rounded.BookmarkBorder)
                                navController.navigate(Screen.TopicScreen.route + questionNumber)
                            else{
                                navController.navigate("${Screen.QuestionScreen.route}$questionNumber/$questionTopic/$numberOfQuestion")
                            }
                        }
                        .size(100.dp),
                    tint = if(isSolved)Color(0xFFFFD700) else Color.White
                    //Color(0xFFFFD700)
                )
                Icon(
                    imageVector = insideIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.Black,
                )
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}