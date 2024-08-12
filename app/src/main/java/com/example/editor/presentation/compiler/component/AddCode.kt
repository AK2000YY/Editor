package com.example.editor.presentation.compiler.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.compiler.CompilerViewModel
import com.example.editor.ui.theme.InsideEditorColor
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun AddCode(
    viewModel: CompilerViewModel
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
            text = viewModel.saveCode,
            style = TextStyle(
                Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(vertical = 4.dp),
        )
        TextField(
            value = viewModel.codeTitle,
            onValueChange = { viewModel.codeTitle = it },
            placeholder = { Text(text = viewModel.placeAdd , style = TextStyle(Color.Black,fontSize = 16.sp)) },
            trailingIcon = {
                if(viewModel.errorAdd){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            },
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
                        viewModel.addCode()
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .background(InsideEditorColor)
            ) {
                Text(
                    text = "add your code",
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
}