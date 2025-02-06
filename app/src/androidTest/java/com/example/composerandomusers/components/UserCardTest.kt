package com.example.composerandomusers.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.composerandomusers.model.Coordinates
import com.example.composerandomusers.model.Street
import com.example.composerandomusers.model.TimeZone
import com.example.composerandomusers.model.User
import com.example.composerandomusers.model.UserDateOfBirth
import com.example.composerandomusers.model.UserLocation
import com.example.composerandomusers.model.UserName
import com.example.composerandomusers.model.UserPicture
import org.junit.Rule
import org.junit.Test

class UserCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val username = UserName("Mr","Nicholas","Hanson")
    private val location = UserLocation(
        street = Street(number = 9085,name = "Albert Road"),
        city = "Newport",
        state = "Bedfordshire",
        country = "United Kingdom",
        postcode = "AO2O 4AP",
        coordinates = Coordinates(-10.7923,-28.0891),
        timezone = TimeZone(
            offset = "+3:00",
            description = "Baghdad, Riyadh, Moscow, St. Petersburg"
        )
    )
    private val userDob = UserDateOfBirth(
        "1967-09-27T19:10:06.578Z",
        57
    )
    private val userLocation = UserPicture(
        large = "https://randomuser.me/api/portraits/men/39.jpg",
        medium = "https://randomuser.me/api/portraits/med/men/39.jpg",
        thumbnail = "https://randomuser.me/api/portraits/thumb/men/39.jpg"
    )

    private val user = User(
        gender = "Male",
        name = username,
        location = location,
        email = "nicholas.hanson@example.com",
        dob = userDob,
        phone = "0114564 081 3175",
        cell = "07477 623317",
        picture = userLocation
    )

    @Test
    fun userCard_displaysCorrectUserData(){
        composeTestRule.setContent {
            UserCard(user=user,onCardTap = {})
        }

        Thread.sleep(5000)

        composeTestRule.onNodeWithText("Nicholas Hanson").assertExists()

        composeTestRule.onNodeWithText("Newport, Bedfordshire,\nUnited Kingdom-AO2O 4AP").assertExists()
    }
}