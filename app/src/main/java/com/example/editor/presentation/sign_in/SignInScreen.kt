package com.example.editor.presentation.sign_in

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.R
import com.example.editor.component.ItemTextField
import com.example.editor.component.ProgressBar
import com.example.editor.data.Response
import com.example.editor.ui.theme.MyBlue
import com.example.editor.ui.theme.MyGreen
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun SignInScreen(
    navigateToNavigateBetween : () -> Unit,
    navigateToSignUpScreen : () -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
){

    val colorList = listOf(MyGreen , MyBlue)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OutsideEditorColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Brush.linearGradient(colorList)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.editsquare),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Sign In",
                style = TextStyle(
                    brush = Brush.linearGradient(
                       colorList
                    ),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 12.dp)
            )
            for(i in 0..1){
                ItemTextField(
                    Modifier
                        .fillMaxWidth()
                        .height(85.dp)
                        .padding(vertical = 10.dp, horizontal = 10.dp),
                    text = viewModel.listOfInput[i].value,
                    labelName = viewModel.listOfLabel[i],
                    placeholder = "",
                    error = viewModel.listOfError[i],
                    onValueChange = { text->
                        viewModel.listOfInput[i].value = text
                    }
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 20.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .height(50.dp)
                    .clickable {
                        viewModel.login()
                    }
                    .background(Brush.linearGradient(colorList)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "login",
                    style = TextStyle(
                        Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Row (
                modifier = Modifier
                    .padding(bottom = 30.dp)
            ){
                Text(
                    text = "if you don't have account,",
                    style = TextStyle(
                        Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "press here",
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colorList
                        ),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .clickable {
                            navigateToSignUpScreen()
                        }
                )
            }
        }
    }
    if(viewModel.state) navigateToNavigateBetween()
    if(viewModel.responseState == Response.Loading){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            ProgressBar()
        }
    }
}