package com.example.editor.presentation.hello

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.editor.navigation.Screen
import com.example.editor.ui.theme.MyGray
import com.example.editor.ui.theme.OutsideEditorColor
import kotlinx.coroutines.delay

@Composable
fun Hello(
    navController : NavHostController,
    viewModel: HelloViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(OutsideEditorColor),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .graphicsLayer {
                        this.translationY = -35.dp.toPx()
                        this.translationX = 10.dp.toPx()
                        this.rotationY = viewModel.rotate.dp.toPx()
                    }
            )

            Text(
                text = "C",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 75.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .graphicsLayer {
                        this.translationY = viewModel.translate.dp.toPx()
                    }
            )

            Text(
                text = "+",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 75.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .graphicsLayer {
                        this.translationY = -viewModel.translate.dp.toPx()
                    }
            )

            Text(
                text = "+",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 75.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .graphicsLayer {
                        this.translationX = viewModel.translate.dp.toPx()
                    }
            )

        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
        ){
            Text(
                text = "Powered by AK2000YY   ",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }

    LaunchedEffect(true){
        while(viewModel.translate!=0){
            delay(5)
            viewModel.translate--
        }
        while(viewModel.translate==0&&viewModel.rotate<200){
            delay(2)
            viewModel.rotate++
        }
        if(viewModel.rotate == 200){
            navController.popBackStack()
            if(viewModel.state==-1){
                navController.navigate(Screen.SignInScreen.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }else{

                navController.navigate(Screen.MYAPP.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
    }

}
