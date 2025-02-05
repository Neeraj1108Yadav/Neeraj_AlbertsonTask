package com.example.composerandomusers.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit

@Composable
fun DisplayTextView(
    text:String,
    textSize: TextUnit,
    style: TextStyle,
){
    Text(
        text = text,
        fontSize = textSize,
        style = style
    )
}