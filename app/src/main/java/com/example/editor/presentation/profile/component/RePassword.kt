package com.example.editor.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.editor.component.ProgressBar
import com.example.editor.data.Response
import com.example.editor.presentation.profile.ProfileViewModel
import com.example.editor.ui.theme.InsideEditorColor
import com.example.editor.ui.theme.OutsideEditorColor


@Composable
fun RePassword(
    viewModel: ProfileViewModel
){
    Column(
        modifier = Modifier
            .background(
                OutsideEditorColor,
                RoundedCornerShape(12.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
            }
            .clip(RoundedCornerShape(12.dp))
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = viewModel.updatePassword,
            style = TextStyle(
                Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(vertical = 4.dp),
        )
        TextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = { Text(text = "enter your password" , style = TextStyle(Color.Black,fontSize = 16.sp)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = viewModel.newPassword,
            onValueChange = { viewModel.newPassword = it },
            placeholder = { Text(text = "enter new password" , style = TextStyle(Color.Black,fontSize = 16.sp)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier
                    .clickable {
                        if(viewModel.password != "" && viewModel.newPassword!="") viewModel.rePassword()
                        else viewModel.updatePassword = "one of them is empty!!"
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .background(InsideEditorColor)
            ) {
                Text(
                    text = "update password",
                    style = TextStyle(
                        Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(15.dp)
                )
            }
        }
    }
    when(viewModel.responsePopScreen){
        Response.Success(false) -> {}
        Response.Loading -> ProgressBar()
        Response.Success(true) -> viewModel.isPopup = 0
        else ->{
            viewModel.updatePassword = "something is error"
        }
    }
}