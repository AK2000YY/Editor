package com.example.editor.presentation.compiler.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.component.toDp
import com.example.editor.presentation.compiler.CompilerViewModel
import com.example.editor.ui.theme.MyGray
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun SearchWord(
    width : Float,
    viewModel: CompilerViewModel = hiltViewModel()
){
    val widthColumn by animateFloatAsState(
        targetValue = if(viewModel.canSearch) width
                      else 0f
    )
    Row(
        modifier = Modifier
            .width(toDp(x = widthColumn))
            .background(OutsideEditorColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = viewModel.inputSearch,
            onValueChange = {
                viewModel.inputSearch = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null
                )
            },
            placeholder = {
                  Text(text = "Search in your code")
            },
            modifier = Modifier
                .weight(9f)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable {
                    viewModel.canSearch = false
                },
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}
