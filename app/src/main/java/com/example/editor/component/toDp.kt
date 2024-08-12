package com.example.editor.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp


@Composable
fun toDp(x: Float) : Dp {
    return with(LocalDensity.current){
        x.toDp()
    }
}