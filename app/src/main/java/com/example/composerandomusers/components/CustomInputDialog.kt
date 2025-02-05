package com.example.composerandomusers.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly

@Composable
fun CustomInputDialog(
    onDismissRequest:(String) -> Unit
){
    val inputRecord = remember { mutableStateOf("") }
    val recordError = remember { mutableStateOf(false) }
    val emptyInput = remember { mutableStateOf(false) }
    val errorMsg = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {onDismissRequest.invoke(inputRecord.value)}
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ){
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Enter number of records to fetch (max 5000).",
                        lineHeight = 20.sp,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(
                            top = 20.dp,
                            start = 15.dp,
                            end = 15.dp
                        )
                    )
                }
                OutlinedTextField(
                    value = inputRecord.value,
                    onValueChange = {newText->
                        emptyInput.value = false
                        recordError.value = false
                        inputRecord.value = newText
                    },
                    modifier = Modifier.padding(15.dp).fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Text(
                    text =  errorMsg.value,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(
                        bottom = 15.dp,
                        start = 15.dp,
                        end = 15.dp
                    )
                        .alpha(if(recordError.value || emptyInput.value) 1f else 0f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = {
                            inputRecord.value = ""
                            onDismissRequest("")
                        },
                        modifier = Modifier.padding(
                            start = 8.dp,
                            bottom = 8.dp,
                            end = 8.dp
                        )
                    ) {
                        Text(
                            text = "Dismiss",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    TextButton(
                        onClick = {
                            if(inputRecord.value.isEmpty() || !inputRecord.value.isDigitsOnly()){
                                errorMsg.value = "Please input number to fetch records!"
                                emptyInput.value = true
                            }else if(inputRecord.value.toInt() > 5000 ){
                                errorMsg.value = "Please fetch record less than 5000."
                                recordError.value = true
                            }else{
                                onDismissRequest(inputRecord.value)
                            }
                        },
                        modifier = Modifier.padding(
                            start = 8.dp,
                            bottom = 8.dp,
                            end = 8.dp
                        )
                    ) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomInputDialogPreview(){
    CustomInputDialog({_ ->})
}