package com.example.editor.presentation.competition.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.material.icons.rounded.WbCloudy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.editor.R
import com.example.editor.navigation.Screen
import com.example.editor.presentation.competition.CompetitionViewModel
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun Screen(
    navHostController: NavHostController,
    viewModel: CompetitionViewModel = hiltViewModel()
) {
    val list = viewModel.listQuestionAndTopic
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OutsideEditorColor)
    ){
        Image(
            painter = painterResource(id = R.drawable.forest),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
        ){
            itemsIndexed(list){ index, item ->
                Coins(
                    numberOfQuestion = item.first.third,
                    questionNumber = item.first.first,
                    questionTopic = item.first.second,
                    alignment = index,
                    icon = Icons.Rounded.WbCloudy,
                    insideIcon = if(item.second.first) Icons.Rounded.BookmarkBorder else Icons.Rounded.QuestionMark,
                    isSolved = item.second.second,
                    navController = navHostController
                )
            }
        }
    }
}