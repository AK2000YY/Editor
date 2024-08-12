package com.example.editor.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.editor.SharedViewModel
import com.example.editor.navigation.Screen.SignInScreen
import com.example.editor.navigation.Screen.SignUpScreen
import com.example.editor.navigation.Screen.VerifyEmailScreen
import com.example.editor.navigation.Screen.ForgotPasswordScreen
import com.example.editor.navigation.Screen.MYAPP
import com.example.editor.navigation.Screen.HelloScreen
import com.example.editor.presentation.competition.CompetitionScreen
import com.example.editor.presentation.forget_password.ForgotPasswordScreen
import com.example.editor.presentation.hello.Hello
import com.example.editor.presentation.navigateBetween.NavigateBetween
import com.example.editor.presentation.profile.ProfileScreen
import com.example.editor.presentation.question.QuestionScreen
import com.example.editor.presentation.sign_in.SignInScreen
import com.example.editor.presentation.sign_up.SignUpScreen
import com.example.editor.presentation.topic.TopicScreen
import com.example.editor.presentation.verify_email.VerifyEmailScreen

@Composable
fun NavGraph(
    navController : NavHostController
){
    val sharedViewModel: SharedViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = HelloScreen.route
    ){

        composable(HelloScreen.route){
            Hello(
                navController
            )
        }

//        composable(Screen.CompetitionScreen.route){
//            CompetitionScreen(
//                sharedViewModel,
//                navController
//            )
//        }

//        composable(Screen.ProfileScreen.route){
//            ProfileScreen()
//        }

        composable(SignInScreen.route){
            SignInScreen(
                navigateToNavigateBetween={
                    navController.popBackStack()
                    navController.navigate(MYAPP.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                }
            )
        }

        composable(SignUpScreen.route){
            SignUpScreen(
                navigateToNavigateBetween={
                    navController.popBackStack()
                    navController.navigate(MYAPP.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable(VerifyEmailScreen.route){
            VerifyEmailScreen (
                navigateToMyApp = {
                    navController.navigate(MYAPP.route){
                        popUpTo(navController.graph.id){
                            inclusive=true
                        }
                    }
                }
            )
        }

        composable(ForgotPasswordScreen.route){
            ForgotPasswordScreen()
        }

        composable(MYAPP.route){
            NavigateBetween(
                sharedViewModel,
                navController
            )
        }


        composable(
            "${Screen.QuestionScreen.route}{questionNumber}/{topicId}/{numberOfQuestion}",
            listOf(
                navArgument("questionNumber"){
                    type = NavType.IntType
                },
                navArgument("topicId"){
                    type = NavType.IntType
                },
                navArgument("numberOfQuestion"){
                    type = NavType.IntType
                }
            )
        ){
            println("nav ${it.arguments?.getInt("questionNumber")} ${it.arguments?.getInt("topicId")} ${it.arguments?.getInt("numberOfQuestion")}")

            it.arguments?.let { it1 ->
                QuestionScreen(
                    it1.getInt("questionNumber"),
                    it1.getInt("topicId"),
                    it1.getInt("numberOfQuestion"),
                    sharedViewModel,
                    navController
                )
            }

        }

        composable(
            "${Screen.TopicScreen.route}{id}",
            listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ){
            it.arguments?.let { it1 ->
                TopicScreen(
                    it1.getInt("id"),
                    sharedViewModel,
                    navController
                )
            }
        }


    }
}