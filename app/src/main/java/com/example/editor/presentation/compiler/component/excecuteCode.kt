package com.example.editor.presentation.compiler.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.compiler.CompilerViewModel

@Composable
fun ExecuteCode(
    viewModel: CompilerViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        Text(
            text = viewModel.resultRun,
            style = TextStyle(
                Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
    }
}