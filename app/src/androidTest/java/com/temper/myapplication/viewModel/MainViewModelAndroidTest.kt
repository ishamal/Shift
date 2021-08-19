package com.temper.myapplication.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.temper.myapplication.utils.TimeUtil
import com.temper.myapplication.utils.getOrAwaitValue
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelAndroidTest : TestCase() {

    lateinit var mainViewModel: MainViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Before
    public override fun setUp() {
        super.setUp()
        mainViewModel = MainViewModel()
    }

    @Test
    fun fetchData_success() = runBlocking {
        mainViewModel.getJobs(TimeUtil.getCurrentDate("yyyy-MM-dd"), null)
        val result = mainViewModel.shiftLiveData.getOrAwaitValue()
        Truth.assertThat(result != null).isTrue()
    }

    @Test
    fun fetchData_listNotEmpty() = runBlocking {
        mainViewModel.getJobs(TimeUtil.getCurrentDate("yyyy-MM-dd"), null)
        val result = mainViewModel.shiftLiveData.getOrAwaitValue().size
        Truth.assertThat(result > 0).isTrue()
    }

}