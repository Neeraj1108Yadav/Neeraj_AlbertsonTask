package com.example.composerandomusers.repo

import com.example.composerandomusers.Helper
import com.example.composerandomusers.network.ApiService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(RobolectricTestRunner::class)
class FetchRandomUserRepoTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Test
    fun `test random user response`() = runTest{
        //Arrange
        val mockData = Helper.getJsonFile("user.json")
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(mockData)
        mockWebServer.enqueue(mockResponse)

        //Act
        val response = apiService.getRandomUser(2)
        mockWebServer.takeRequest()

        //Assert
        assertNotNull(response.body())
        val users = response.body()?.results
        assertEquals(2,users?.size)

        val firstUser = users?.get(0)
        assertEquals("male", firstUser?.gender)
        assertEquals("Nicholas", firstUser?.name?.first)
        assertEquals("Albert Road", firstUser?.location?.street?.name)
    }

    @Test
    fun `test error response`() = runTest {
        // Arrange
        val mockResponse = MockResponse().setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        // Act
        val response = apiService.getRandomUser(10)
        mockWebServer.takeRequest()
        // Assert
        assertFalse(response.isSuccessful)
        assertEquals(404, response.code())
    }

    @Test
    fun `test empty response`() = runTest {
        // Arrange
        val mockData = Helper.getJsonFile("empty_result.json")
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(mockData)
        mockWebServer.enqueue(mockResponse)

        // Act
        val response = apiService.getRandomUser(10)
        mockWebServer.takeRequest()

        // Assert
        val users = response.body()?.results
        assertNotNull(users)
        assertTrue(users.isNullOrEmpty())
    }

    @Test
    fun `test query parameters`() = runTest {
        // Arrange
        val mockData = Helper.getJsonFile("user.json")
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(mockData)

        mockWebServer.enqueue(mockResponse)

        // Act
        apiService.getRandomUser(10)

        // Assert
        val recordedRequest = mockWebServer.takeRequest()
        assertTrue(recordedRequest.path?.contains("results=10") == true)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}