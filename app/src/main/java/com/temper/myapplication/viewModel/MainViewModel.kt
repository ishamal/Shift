package com.temper.myapplication.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.temper.myapplication.repositories.ShiftRepository
import com.temper.myapplication.services.ShiftService
import com.temper.myapplication.services.response.ShiftResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {

    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    private val shiftRepository: ShiftRepository = ShiftRepository(ShiftService.client)

    val shiftResponse  = MutableLiveData<ShiftResponse>()

    fun getJobs(date : String, loadingView : View?) {
        scope.launch {
            val result = shiftRepository.getJobList(date, loadingView)
            if (result != null) {
                shiftResponse.postValue(result)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}