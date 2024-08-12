package com.example.editor.presentation.competition

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.editor.SharedViewModel
import com.example.editor.data.Response
import com.example.editor.presentation.competition.component.ErrorScreen
import com.example.editor.presentation.competition.component.LoadingScreen
import com.example.editor.presentation.competition.component.Screen

@Composable
fun CompetitionScreen(
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController,
    viewModel: CompetitionViewModel = hiltViewModel()
){
    if(sharedViewModel.stateOfCompetition){
        viewModel.getUserId()
        sharedViewModel.stateOfCompetition = false
    }
    when(viewModel.response){
        Response.Success(true) -> Screen(navHostController,viewModel)
        Response.Error -> ErrorScreen(viewModel)
        else -> LoadingScreen()
    }
}