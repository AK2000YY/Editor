package com.example.editor.presentation.compiler.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.compiler.CompilerViewModel

@Composable
fun SuggestionsList(
    viewModel: CompilerViewModel = hiltViewModel()
) {
    val suggestion by viewModel.suggestion.collectAsState()
    Box(
        modifier = Modifier
            .offset(
                with(LocalDensity.current) {
                    viewModel.rect.center.x.toDp() + 40.dp
                },
                with(LocalDensity.current) {
                    viewModel.rect.center.y.toDp() + 15.dp
                }
            )
            .background(
                Color.Gray,
                RoundedCornerShape(12.dp)
            )
            .widthIn(100.dp, 200.dp)
    ){

        LazyColumn{
            items(suggestion){item->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            if (viewModel.offset != -1) {
                                viewModel.paste(item)
                                viewModel.textFieldValue = TextFieldValue(
                                    viewModel.text,
                                    TextRange(viewModel.offset + viewModel.plusOffset)
                                )
                                viewModel.update()
                            }
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        text = item,
                        style = TextStyle(
                            color = Color.White,
                            fontStyle = FontStyle.Italic
                        )
                    )
                }
            }
        }
    }
}