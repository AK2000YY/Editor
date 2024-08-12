package com.example.editor.presentation.compiler.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editor.presentation.compiler.ColorsTransformation
import com.example.editor.presentation.compiler.CompilerViewModel
import com.example.editor.ui.theme.InsideEditorColor
import com.example.editor.ui.theme.LightBlue
import com.example.editor.ui.theme.Orange
import com.example.editor.ui.theme.OrangePro

@Composable
fun CodeTextField(
    viewModel: CompilerViewModel = hiltViewModel()
){
    BasicTextField(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(InsideEditorColor),
        value = viewModel.textFieldValue,
        onValueChange = {
            viewModel.textFieldValue = it.copy(it.text)
            Log.d("abdoooo","${viewModel.textFieldValue.selection.start}")
            if(viewModel.textFieldValue.selection.start>0)Log.d("abdoooo","${viewModel.textFieldValue.text[0]}")
            viewModel.update()
        },
        onTextLayout = { result ->
            viewModel.lineTops = Array(result.lineCount) { result.getLineTop(it) }
            if (viewModel.offset != -1) viewModel.rect =
                result.getCursorRect(viewModel.offset)
        },
        textStyle = TextStyle(fontSize = 20.sp),
        visualTransformation = ColorsTransformation(viewModel.inputSearch),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                try {
                    viewModel.formatByPress()
                }catch (e:Exception){
                    Log.d("abd111","$e")
                }
            }
        )
    )
    if(viewModel.error){
        Toast.makeText(LocalContext.current,"error in kerly braket",Toast.LENGTH_LONG).show()
        viewModel.error = false
    }
}

