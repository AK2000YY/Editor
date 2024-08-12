package com.example.editor.presentation.sign_up

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.component.ItemTextField
import com.example.editor.component.ProgressBar
import com.example.editor.data.Response
import com.example.editor.ui.theme.MyBlue
import com.example.editor.ui.theme.MyGreen
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun SignUpScreen(
    navigateToNavigateBetween : () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val colorList = listOf(MyGreen , MyBlue)
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OutsideEditorColor)
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.photo1),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxSize(),
//            contentScale = ContentScale.FillBounds
//        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Signup",
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = colorList
                    ),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 12.dp)
            )
            for (i in 0..4) {
                ItemTextField(
                    Modifier
                        .fillMaxWidth()
                        .height(85.dp)
                        .padding(vertical = 10.dp, horizontal = 10.dp),
                    text = viewModel.listOfInput[i].value,
                    labelName = viewModel.list[i],
                    placeholder = viewModel.listOfPlaceholder[i].value,
                    error = viewModel.listOfError[i].value,
                    onValueChange = { text ->
                        viewModel.listOfInput[i].value = text
                    }
                )
            }
            Spacer(modifier = Modifier.height(75.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 40.dp)
                    .clickable {
                        if (!viewModel.checkNull()) {
                            Toast.makeText(context, "one of fields are Empty", Toast.LENGTH_LONG).show()
                        } else {
                            viewModel.responseState = Response.Loading
                            viewModel.checkEmail()
                        }
                    }
                    .clip(RoundedCornerShape(15.dp))
                    .background(Brush.linearGradient(colorList)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "SignUp", style = TextStyle(Color.Black, fontSize = 30.sp))
            }
        }
    }
    if(viewModel.state) navigateToNavigateBetween()
    if(viewModel.responseState == Response.Loading)
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            ProgressBar()
        }
    else if(viewModel.responseState == Response.Success(true) && !viewModel.check){
        Toast.makeText(context,"This email is used",Toast.LENGTH_LONG).show()
        viewModel.responseState = Response.Success(false)
    }
    else if(viewModel.responseState == Response.Success(true) && viewModel.check){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            ProgressBar()
        }
        viewModel.signup()
    }
    else if(viewModel.responseState == Response.Error){
        Toast.makeText(context,"Something is error",Toast.LENGTH_LONG).show()
        viewModel.responseState = Response.Success(false)
    }
}