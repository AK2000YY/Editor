package com.example.editor.presentation.compiler.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material.icons.rounded.FolderOpen
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.compiler.CompilerViewModel
import com.example.editor.presentation.compiler.component.IconOfBar
import com.example.editor.presentation.compiler.component.IconOfBarWithInside
import com.example.editor.ui.theme.InsideEditorColor
import com.example.editor.ui.theme.LightBlue
import com.example.editor.ui.theme.MyBlue

@Composable
fun BottomAppBar(
    modifier: Modifier,
    viewModel: CompilerViewModel = hiltViewModel()
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconOfBar(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .clip(CircleShape)
                .size(60.dp)
                .background(
                    InsideEditorColor,
                    CircleShape
                )
                .clickable {
                    viewModel.windowPopUp = 2
                    viewModel.getCodes()
                },
            icon = Icons.Rounded.FolderOpen,
            color = Color.White
        )
        IconOfBarWithInside(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .clip(CircleShape)
                .size(60.dp)
                .background(
                    LightBlue,
                    CircleShape
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                  viewModel.windowPopUp = 3
                },
            icon = Icons.Rounded.PlayArrow,
            outsideColor = MyBlue,
            insideColor = LightBlue
        )
        IconOfBar(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .clip(CircleShape)
                .size(60.dp)
                .background(
                    InsideEditorColor,
                    CircleShape
                )
                .clickable {
                    viewModel.format()
                },
            icon = Icons.Rounded.Refresh,
            color = Color.White
        )
    }
}