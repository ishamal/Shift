package com.temper.myapplication.services

import com.temper.myapplication.services.response.ShiftResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ShiftServiceClient {

    @Headers("Content-Type: application/json")
    @GET("shifts")
    fun getJobListAsync(@Query("filter[date]") date : String) : Deferred<Response<ShiftResponse>>

}