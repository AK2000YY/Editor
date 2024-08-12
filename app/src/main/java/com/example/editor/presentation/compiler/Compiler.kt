package com.example.editor.presentation.compiler

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.component.toDp
import com.example.editor.presentation.compiler.component.BottomAppBar
import com.example.editor.presentation.compiler.component.CodeTextField
import com.example.editor.presentation.compiler.component.ExecuteCode
import com.example.editor.presentation.compiler.component.PopupWindow
import com.example.editor.presentation.compiler.component.SuggestionsList
import com.example.editor.presentation.compiler.component.TopBar
import com.example.editor.ui.theme.InsideEditorColor
import com.example.editor.ui.theme.MyBlue
import com.example.editor.ui.theme.MyGray
import com.example.editor.ui.theme.Orange
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun Compiler(
    viewModel: CompilerViewModel = hiltViewModel()
){
    var can by remember {
        mutableStateOf(true)
    }
    var hColumn by remember { mutableFloatStateOf(0f) }
    var pOne by remember { mutableFloatStateOf(0f) }
    var pTwo by remember { mutableFloatStateOf(0f) }
    var pThree by remember { mutableFloatStateOf(0f) }
    var pFour by remember { mutableFloatStateOf(0f) }
    if(viewModel.expansion){
        pTwo = hColumn * 4
        pFour = hColumn * 4.1.toFloat()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged {
                viewModel.size = it
                hColumn = it.height.toFloat() / 10
                if (can) {
                    pOne = hColumn * 1
                    pTwo = hColumn * 7.3.toFloat()
                    pThree = hColumn * 1.2.toFloat()
                    pFour = hColumn * 0.5.toFloat()
                    can = false
                }
            }
            .background(OutsideEditorColor)
    ) {
        TopBar(
            Modifier
                .height(toDp(x = pOne))
                .fillMaxWidth()
                .padding(top = 8.dp, start = 9.dp, end = 9.dp),
            viewModel
        )
        Box(
            modifier = Modifier
                .height(toDp(x = pTwo))
                .padding(horizontal = 18.dp, vertical = 4.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(
                    InsideEditorColor,
                    RoundedCornerShape(15.dp)
                )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .horizontalScroll(rememberScrollState())
            ){
                if (viewModel.lineTops.isNotEmpty()) {
                    Box(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp)) {
                        viewModel.lineTops.forEachIndexed { index, top ->
                            Text(
                                modifier = Modifier
                                    .offset(y = toDp(top))
                                    .align(Alignment.TopEnd),
                                text = "$index -",
                                style = TextStyle(
                                    color = Orange,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
                }
                CodeTextField()
            }

            SuggestionsList()
        }
        BottomAppBar(
            modifier = Modifier
                .height(toDp(x = pThree))
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            viewModel
        )
        Box(
            modifier = Modifier
                .height(toDp(x = pFour))
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                .background(
                    InsideEditorColor,
                    RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                ),
            contentAlignment = Alignment.TopCenter
        ){
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(toDp(x = hColumn*0.8.toFloat())),
                    contentAlignment = Alignment.Center
                ){
                    Box(
                        modifier = Modifier
                            .height(3.dp)
                            .width(60.dp)
                            .background(MyBlue)
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDragStart = { viewModel.expansion = false }
                                ) { change, dragAmount ->
                                    change.consume()
                                    if ((pFour - dragAmount.y < hColumn * 0.8)) {
                                        pTwo = hColumn * 7.3.toFloat()
                                        pFour = hColumn * 0.8.toFloat()
                                    } else if (pTwo + dragAmount.y < hColumn * 4) {
                                        pTwo = hColumn * 4
                                        pFour = hColumn * 4.1.toFloat()
                                    } else {
                                        pTwo += dragAmount.y
                                        pFour -= dragAmount.y
                                    }
                                }
                            }
                    )
                }
                ExecuteCode()
            }
        }
    }
    if(viewModel.windowPopUp != 0 ) PopupWindow(viewModel)
}
