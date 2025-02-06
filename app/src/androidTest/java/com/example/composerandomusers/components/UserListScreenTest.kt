package com.example.composerandomusers.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composerandomusers.listeners.UserFetchListener
import com.example.composerandomusers.model.UserResult
import com.example.composerandomusers.network.ApiService
import com.example.composerandomusers.repo.FetchRandomUserRepo
import com.example.composerandomusers.screens.users.UserListScreen
import com.example.composerandomusers.sealed.NetworkResult
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.timer

@RunWith(AndroidJUnit4::class)
class UserListScreenTest {

    private val listener: UserFetchListener = mockk(relaxed = true)
    private val fakeRepo:FetchRandomUserRepo = mockk(relaxed = true)
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @get:Rule
    val composeTestRule = createComposeRule()

    /*@Before
    fun setup(){
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }*/

    @Test
    fun floatingButton_performClick() {

        composeTestRule.setContent {
            UserListScreen(
                fetchRandomUserRepo = fakeRepo,
                onSelectUser = {}
            )
        }

        composeTestRule.onNodeWithTag("button_float").performClick()

    }

    @Test
    fun lazyColumn_loading_state(){
        composeTestRule.setContent {
            UserListScreen(
                fetchRandomUserRepo = fakeRepo,
                onSelectUser = {}
            )
        }

        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()

    }

    @Test
    fun lazyColumn_success_state(){
        val sampleData = JSONReader.getJsonFileFromAssets("small_data.json")
        val fakeUsers = Gson().fromJson(sampleData, UserResult::class.java)

        coEvery { listener.getRandomUser(fakeUsers.results.size) } returns flow{
            emit(NetworkResult.Success(fakeUsers))
        }

        composeTestRule.setContent {
            UserListScreen(
                fetchRandomUserRepo = fakeRepo,
                onSelectUser = {}
            )
        }

        composeTestRule.waitUntil(5000){
            composeTestRule.onNodeWithTag("user_list").isDisplayed()
        }

    }
}