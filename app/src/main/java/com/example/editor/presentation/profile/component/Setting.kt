package com.example.editor.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.editor.presentation.profile.ProfileViewModel
import com.example.editor.ui.theme.InsideEditorColor

@Composable
fun Setting(
    viewModel: ProfileViewModel
) {
    Column(
        modifier = Modifier
            .padding(top = 70.dp)
    ){
        BoxItem(
            text = "Change your name",
            viewModel = viewModel
        )
        BoxItem(
            text = "Change your password",
            viewModel = viewModel
        )
    }
}

@Composable
fun BoxItem(
    text : String,
    viewModel: ProfileViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 18.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                2.dp,
                Color.Black,
                RoundedCornerShape(12.dp)
            )
            .background(InsideEditorColor)
            .clickable {
                if(text == "Change your name") viewModel.updatePopUp(1)
                if(text == "Change your password") viewModel.updatePopUp(2)
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            style = TextStyle(
                Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 2.dp)
        )
    }
}