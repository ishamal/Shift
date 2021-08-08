package com.temper.myapplication.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.temper.myapplication.utils.TimeUtil
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest{

    private lateinit var mainViewModel: MainViewModel


    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        mainViewModel = MainViewModel()
        var today = TimeUtil.getCurrentDate("yyyy-MM-dd")
        mainViewModel.getJobs(today)
    }

    @Test
    fun getJobs_notNullData() {
        mainViewModel.shiftResponse.observeForever(Observer {
            assertNotEquals(it, null)
        })
    }


    @Test
    fun getJobs_notNullDataObj() {
        mainViewModel.shiftResponse.observeForever(Observer {
            assertNotEquals(it.data, null)
        })
    }

}