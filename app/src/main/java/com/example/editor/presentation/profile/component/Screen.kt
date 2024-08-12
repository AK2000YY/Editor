package com.example.editor.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.editor.component.ProgressBar
import com.example.editor.data.Response
import com.example.editor.navigation.Screen
import com.example.editor.presentation.profile.ProfileViewModel
import com.example.editor.ui.theme.InsideEditorColor

@Composable
fun ContentScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(75.dp)
                    .width(75.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(75.dp)
                        .width(75.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
            Column {
                UserName(text = viewModel.user.u_username!!)
                DateUser(text = viewModel.user.u_date!!)
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        NumberQuestionAndTutorial(
            viewModel
        )
        Setting(viewModel)
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
                    viewModel.restart()
                },
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = "LogOut",
                style = TextStyle(
                    Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            )
        }
    }

}