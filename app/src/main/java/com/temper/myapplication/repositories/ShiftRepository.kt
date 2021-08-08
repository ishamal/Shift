package com.temper.myapplication.repositories

import androidx.constraintlayout.widget.ConstraintLayout
import com.temper.myapplication.services.ShiftServiceClient
import com.temper.myapplication.services.response.ShiftResponse

class ShiftRepository (private val apiInterface: ShiftServiceClient) : BaseRepository() {

    suspend fun getJobList(date : String, view : ConstraintLayout?) : ShiftResponse?{
        return safeApiCall(
            call = {apiInterface.getJobListAsync(date).await()},
            error = "Error Loading jobs",
            view = view
        )
    }

}