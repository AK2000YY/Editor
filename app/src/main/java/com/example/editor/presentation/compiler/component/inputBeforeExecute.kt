package com.example.editor.presentation.compiler.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.compiler.CompilerViewModel
import com.example.editor.ui.theme.InsideEditorColor
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun InputBeforeExecute(
    viewModel: CompilerViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .background(
                OutsideEditorColor,
                RoundedCornerShape(12.dp)
            )
            .padding(30.dp)
            .widthIn(max = with(LocalDensity.current) {
                viewModel.size.width.toDp() - 40.dp
            }
            ),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = "input",
            style = TextStyle(
                Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(vertical = 4.dp),
        )
        TextField(
            value = viewModel.input,
            onValueChange = {
                viewModel.input = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            placeholder = { Text(text = "let it empty if you don't have input") }
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
                        viewModel.runCode()
                        viewModel.windowPopUp = 0
                        viewModel.expansion = true
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .background(InsideEditorColor)
            ) {
                Text(
                    text = "run code",
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