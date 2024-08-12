package com.example.editor.presentation.navigateBetween.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.navigateBetween.NavigationViewModel
import com.example.editor.ui.theme.LightBlue
import com.example.editor.ui.theme.MyGray
import com.example.editor.ui.theme.OutsideEditorColor
import com.example.editor.ui.theme.Purple40
import com.example.editor.ui.theme.Purple80

@Composable
fun CompilerNavigation(
    modifier : Modifier,
    viewModel: NavigationViewModel = hiltViewModel()
){
    var size by remember { mutableStateOf(IntSize.Zero) }
    Box(
        modifier = modifier
            .onSizeChanged { size = it },
        contentAlignment = Alignment.Center
        ) {
        Box(
            modifier = Modifier
                .height( with(LocalDensity.current){size.height.toDp()}-15.dp)
                .width( with(LocalDensity.current){size.width.toDp()}-80.dp)
                .background(
                    if(viewModel.change==1) LightBlue
                    else OutsideEditorColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    viewModel.change = 1
                },
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = Icons.Filled.Keyboard,
                contentDescription =null,
                tint = Color.White
            )
        }
    }
}