package com.example.composerandomusers

import app.cash.turbine.turbineScope
import com.example.composerandomusers.listeners.UserFetchListener
import com.example.composerandomusers.model.UserResult
import com.example.composerandomusers.screens.users.UserListViewModel
import com.example.composerandomusers.sealed.NetworkResult
import com.example.composerandomusers.sealed.UserListUiState
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val listener: UserFetchListener = mockk(relaxed = true)
    private lateinit var viewModel: UserListViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        viewModel = UserListViewModel(listener)
    }

    @Test
    fun `getRandomUser should emit Loading and Success State`() = runTest{
        turbineScope{
            val sampleData = Helper.getJsonFile("small_data.json")
            val fakeUsers = Gson().fromJson(sampleData, UserResult::class.java)
            coEvery { listener.getRandomUser(fakeUsers.results.size) } returns flow{
                emit(NetworkResult.Loading)
                delay(1000)
                emit(NetworkResult.Success(fakeUsers))
            }

            val userFlows = viewModel.users.testIn(this)
            viewModel.getRandomUser()
            testDispatcher.scheduler.advanceUntilIdle()

            assertEquals(UserListUiState.Loading,userFlows.awaitItem())
            assertEquals(UserListUiState.onSuccess(fakeUsers.results),userFlows.awaitItem())

            userFlows.cancel()
        }
    }


    @Test
    fun `getRandomUser should emit Loading and Failure State`() = runTest{
        turbineScope{
            val errorMessage = "Failed to fetch user"

            coEvery { listener.getRandomUser(10) } returns flow{
                emit(NetworkResult.Loading)
                delay(1000)
                emit(NetworkResult.Failure(errorMessage))
            }


            viewModel.getRandomUser()
            testDispatcher.scheduler.advanceUntilIdle()

            val userFlow = viewModel.users.testIn(this)
            //val showErrorSnackBar = viewModel.showSnackBar.testIn(this)

            val loadAwait = userFlow.awaitItem()
            val failAwait = userFlow.awaitItem()
            //val snackMsgAwait = showErrorSnackBar.awaitItem()
            assertEquals(UserListUiState.Loading, loadAwait)
            assertEquals(UserListUiState.onFailure(errorMessage), failAwait)
            //assertEquals(errorMessage,snackMsgAwait)

            userFlow.cancel()
            //showErrorSnackBar.cancel()
        }
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}