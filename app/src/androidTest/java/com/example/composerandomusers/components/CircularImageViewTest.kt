package com.example.composerandomusers.components

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class CircularImageViewTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_image_load_async(){
        composeTestRule.setContent {
            CircleImageView(
                userImage = "https://randomuser.me/api/portraits/women/52.jpg",
                imageSize = 120
            )
        }

        Thread.sleep(5000)
    }
}