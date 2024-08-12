package com.example.editor.presentation.compiler.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.compiler.CompilerViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun IconOfBarWithInside(
    viewModel: CompilerViewModel = hiltViewModel(),
    modifier: Modifier,
    icon: ImageVector,
    outsideColor: Color,
    insideColor : Color
) {
    Box(
        modifier = modifier
            .onSizeChanged {
                viewModel.sizeBottomBar = it
            },
        contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier
                .size(
                    height = with(LocalDensity.current){
                        viewModel.sizeBottomBar.height.toDp()-3.dp
                    },
                    width = with(LocalDensity.current){
                        viewModel.sizeBottomBar.width.toDp()-3.dp
                    }
                ),
            imageVector = icon,
            contentDescription = null,
            tint = outsideColor,
        )
        Icon(
            modifier = Modifier
                .size(
                    height = with(LocalDensity.current){
                        viewModel.sizeBottomBar.height.toDp()-15.dp
                    },
                    width = with(LocalDensity.current){
                        viewModel.sizeBottomBar.width.toDp()-15.dp
                    }
                ),
            imageVector = icon,
            contentDescription = null,
            tint = insideColor,
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun IconOfBar(
    viewModel: CompilerViewModel = hiltViewModel(),
    modifier: Modifier,
    icon: ImageVector,
    color: Color
) {
    Box(
        modifier = modifier
            .onSizeChanged {
                viewModel.sizeBottomBar = it
            },
        contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier
                .size(
                    height = with(LocalDensity.current){
                        viewModel.sizeBottomBar.height.toDp()-15.dp
                    },
                    width = with(LocalDensity.current){
                        viewModel.sizeBottomBar.width.toDp()-15.dp
                    }
                ),
            imageVector = icon,
            contentDescription = null,
            tint = color,
        )
    }
}
