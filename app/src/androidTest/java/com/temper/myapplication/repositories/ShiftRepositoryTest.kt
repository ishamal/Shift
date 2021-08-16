package com.temper.myapplication.repositories

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.temper.myapplication.services.ShiftService
import com.temper.myapplication.services.ShiftServiceClient
import com.temper.myapplication.utils.TimeUtil
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShiftRepositoryTest : TestCase() {

    private lateinit var apiInterface : ShiftServiceClient
    private lateinit var shiftRepository: ShiftRepository


    @Before
    public override fun setUp() {
        super.setUp()
        apiInterface = ShiftService.client
        shiftRepository = ShiftRepository(apiInterface)
    }

    @Test
    fun loadData_success() = runBlocking {
        var result = shiftRepository.getJobList(TimeUtil.getCurrentDate("yyyy-MM-dd"), null)
        assertThat(result != null).isTrue()
    }

}