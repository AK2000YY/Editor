package com.example.editor.presentation.compiler.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.compiler.CompilerViewModel
import com.example.editor.ui.theme.InsideEditorColor
import com.example.editor.ui.theme.OutsideEditorColor

@Composable
fun GetCodes(
    viewModel: CompilerViewModel
) {
    val codeList by viewModel.listOfCodes.collectAsState()
    viewModel.getCodes()
    Column(
        modifier = Modifier
            .background(
                OutsideEditorColor,
                RoundedCornerShape(12.dp)
            )
            .padding(30.dp)
            .widthIn(max = with(LocalDensity.current) {
                viewModel.size.width.toDp() - 40.dp
            }
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        for (i in codeList){
            Box(
                modifier = Modifier
                    .clickable {
                        viewModel.textFieldValue = TextFieldValue(i.c_content.toString())
                        viewModel.windowPopUp=0
                    }
            ){
                let{i.c_title}?.let {
                    let{i.c_date}?.let { it1 ->
                        FileDesign(
                            fileName = it,
                            fileDate = it1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.Gray,
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(4.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun FileDesign(
    fileName:String,
    fileDate:String,
    modifier: Modifier
) {
    var size by remember {
        mutableStateOf(IntSize(0,0))
    }
    Row(
        modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .height(
                    with(LocalDensity.current) {
                        size.height.toDp() + 2.dp
                    }
                )
                .background(
                    InsideEditorColor,
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "C++",
                style = TextStyle(
                    Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 25.sp
                ),
                modifier = Modifier.padding(5.dp)
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            modifier = Modifier
                .onSizeChanged {
                    size = it
                },
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = fileName,
                style = TextStyle(
                    Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 25.sp
                )
            )
            Text(text = fileDate)
        }
    }
}
