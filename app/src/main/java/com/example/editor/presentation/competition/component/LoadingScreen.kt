package com.example.editor.presentation.competition.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.editor.component.ProgressBar

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        ProgressBar()
    }
}