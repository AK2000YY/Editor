package com.example.editor.presentation.profile

import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.editor.SharedViewModel
import com.example.editor.component.ProgressBar
import com.example.editor.data.Response
import com.example.editor.navigation.Screen
import com.example.editor.presentation.profile.component.ContentScreen
import com.example.editor.presentation.profile.component.PopupWindow

@Composable
fun ProfileScreen(
    sharedViewModel:SharedViewModel,
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
){
    if(sharedViewModel.stateOfProfile){
        viewModel.getIdUser()
        sharedViewModel.stateOfProfile = false
    }
    when(viewModel.response){
        Response.Loading -> ProgressBar()
        Response.Success(false) -> ProgressBar()
        Response.Error ->
            Toast.makeText(LocalContext.current,"something is error",Toast.LENGTH_LONG).show()
        else ->{
            ContentScreen(viewModel , navController)
        }
    }
    if(viewModel.isPopup!=0) PopupWindow(viewModel)


    when(viewModel.responseLogout){
        Response.Success(true) -> {
            println(" accccadslkjsadodv  ")
            navController.navigate(Screen.SignInScreen.route){
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
        Response.Loading -> {}
        else -> {
        }
    }
}