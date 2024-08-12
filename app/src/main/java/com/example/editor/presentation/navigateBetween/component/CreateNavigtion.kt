package com.example.editor.presentation.navigateBetween.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CreateNavigation(
){
    Row {
        ProfileNavigation(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )

        CompetitionsNavigation(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )

        CompilerNavigation(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }
}