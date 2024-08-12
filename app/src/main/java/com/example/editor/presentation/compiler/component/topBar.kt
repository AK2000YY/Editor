package com.example.editor.presentation.compiler.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.compiler.CompilerViewModel

@Composable
fun TopBar(
    modifier: Modifier,
    viewModel: CompilerViewModel = hiltViewModel()
){

    var width by remember { mutableFloatStateOf(0f) }
    Box(
        modifier = modifier.
            onSizeChanged {
                width = it.width.toFloat()
            }
    ){
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxHeight()
                .size(40.dp)
                .clickable {
                    viewModel.canSearch = true
                }
        )
        Text(
            text = viewModel.codeName,
            style = TextStyle(
                Color.White,
                fontSize = 20.sp
            ),
            modifier = Modifier
                .align(Alignment.Center)
        )
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .size(40.dp)
                .clickable {
                    viewModel.windowPopUp=1
                }
        )
        SearchWord(
            width,
            viewModel
        )
    }
}