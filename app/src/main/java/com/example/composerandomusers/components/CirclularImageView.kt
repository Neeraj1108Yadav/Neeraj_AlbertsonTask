package com.example.composerandomusers.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.example.composerandomusers.R

@Composable
fun CircleImageView(
    modifier: Modifier = Modifier,
    userImage:String,
    imageSize:Int,
    padding:Int = 4
){
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(userImage)
            .size(imageSize) // Set the target size to load the image at.
            .build(),
        placeholder = painterResource(id = R.drawable.ic_user),
        error = painterResource(id = R.drawable.ic_user)
    )
    Box(
        modifier = modifier
            .size(imageSize.dp)
            .clip(CircleShape)
            .border(3.dp, Color.Gray, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        val state = painter.state.collectAsState()
        Log.d("State","Painter State => ${state.value}")
        val isPlaceHolderOrError = when(state.value){
            is AsyncImagePainter.State.Loading,
            is AsyncImagePainter.State.Error -> true
            else -> false
        }
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            modifier = Modifier.
            let {baseModifier->
                if(isPlaceHolderOrError){
                    baseModifier.padding(30.dp)
                }else{
                    baseModifier.padding(padding.dp)
                }
            }
                .fillMaxSize()
                .clip(CircleShape),
            contentDescription = "Circle Image"
        )
    }
}

@Preview
@Composable
fun CircleImageViewPreview(){
    CircleImageView(userImage = "", imageSize = 120)
}