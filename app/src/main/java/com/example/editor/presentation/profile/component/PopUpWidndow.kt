package com.example.editor.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.profile.ProfileViewModel

@Composable
fun PopupWindow(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(0.3f))
            .onSizeChanged {
                viewModel.size = it
            }
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                viewModel.isPopup = 0
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .heightIn(max = with(LocalDensity.current) {
                    viewModel.size.height.toDp() / 2
                }
                )
                .widthIn(max = with(LocalDensity.current) {
                    viewModel.size.width.toDp() - 40.dp
                }
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {}
        ){
            if(viewModel.isPopup == 1) ReName(viewModel)
            if(viewModel.isPopup == 2) RePassword(viewModel)
            if(viewModel.isPopup == 3) {}
        }
    }
}