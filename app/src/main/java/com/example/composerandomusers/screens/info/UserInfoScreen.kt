package com.example.composerandomusers.screens.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composerandomusers.components.BodyTextView
import com.example.composerandomusers.components.CircleImageView
import com.example.composerandomusers.components.TopAppBar
import com.example.composerandomusers.model.User

@Composable
fun UserInfoScreen(
    user: User
){
    Scaffold(
        topBar =  {TopAppBar(title = "User Detail")}
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleImageView(
                modifier = Modifier.padding(15.dp),
                userImage = user.picture.large,
                imageSize = 120
            )
            Text(
                text = "${user.name.first} ${user.name.last}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${user.gender}, ${user.dob.age}",
                style = MaterialTheme.typography.titleSmall
            )
            Box(
                modifier = Modifier.padding(15.dp)
                    .verticalScroll(rememberScrollState())
            ){
                Column {
                    BodyTextView(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        text = user.email
                    )
                    BodyTextView(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Street",
                        text = user.location.street.name
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        BodyTextView(
                            modifier = Modifier.weight(1f),
                            label = "City",
                            text = user.location.city
                        )
                        BodyTextView(
                            modifier = Modifier.weight(1f),
                            label = "State",
                            text = user.location.state
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        BodyTextView(
                            modifier = Modifier.weight(1f),
                            label = "Post Code",
                            text = user.location.postcode
                        )
                        BodyTextView(
                            modifier = Modifier.weight(1f),
                            label = "Country",
                            text = user.location.country
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        BodyTextView(
                            modifier = Modifier.weight(1f),
                            label = "Cell",
                            text = user.cell
                        )
                        BodyTextView(
                            modifier = Modifier.weight(1f),
                            label = "Phone",
                            text = user.phone
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun UserInfoScreenPreview(){
    //UserInfoScreen()
}