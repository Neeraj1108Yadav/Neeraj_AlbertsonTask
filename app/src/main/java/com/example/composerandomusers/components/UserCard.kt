package com.example.composerandomusers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composerandomusers.model.User
import com.example.composerandomusers.R

@Composable
fun UserCard(
    user: User,
    onCardTap: (User) -> Unit
){
    ElevatedCard(
        onClick = {onCardTap(user)},
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier.padding(10.dp)
            .fillMaxWidth()
            .testTag("UserCard_${user.name.first}_${user.name.last}") // Card Test Tag

    ) {
        Row (
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box{
                CircleImageView(Modifier,user.picture.large,80)
            }

            Box(
                modifier = Modifier.padding(start = 10.dp)
            ){
                Column {
                    UserName("${user.name.first} ${user.name.last}")
                    UserAddress(user.location.city,user.location.state,
                            user.location.country,user.location.postcode)
                }
            }
        }
    }
}



@Composable
fun UserName(name:String){
    Row{
        Image(
            painter = painterResource(id = R.drawable.ic_name),
            contentDescription = "Name",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun UserAddress(
    city:String,
    state:String,
    country:String,
    postCode:String
){
    Row(modifier = Modifier.padding(top = 5.dp)){
        Image(
            painter = painterResource(id = R.drawable.ic_address),
            contentDescription = "Address",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(top = 3.dp)
        )
        Text(
            text = "$city, $state,\n$country-$postCode",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun UserCardPreview(){
    //UserCard(User("","","","","",""),{})
}