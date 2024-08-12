package com.example.editor.presentation.navigateBetween

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.editor.SharedViewModel
import com.example.editor.presentation.competition.CompetitionScreen
import com.example.editor.presentation.compiler.Compiler
import com.example.editor.presentation.navigateBetween.component.CreateNavigation
import com.example.editor.presentation.profile.ProfileScreen
import com.example.editor.ui.theme.MyGray
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun NavigateBetween(
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    viewModel: NavigationViewModel = hiltViewModel()
){
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(9.2f)
                .background(OutsideEditorColor)
        ){
            when (viewModel.change) {
                1 -> Compiler()
                3 -> ProfileScreen(sharedViewModel,navController)
                else -> CompetitionScreen(sharedViewModel,navController)
                /*else -> CompetitionScreen(
                    navController
                )*/
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
                .background(OutsideEditorColor)
        ){
            CreateNavigation()
        }
    }
}