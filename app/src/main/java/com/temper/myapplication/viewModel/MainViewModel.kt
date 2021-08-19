package com.temper.myapplication.viewModel

import android.view.View
import androidx.lifecycle.*
import com.temper.myapplication.repositories.ShiftRepository
import com.temper.myapplication.services.ShiftService
import com.temper.myapplication.services.response.ShiftResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {

    private val shiftRepository: ShiftRepository = ShiftRepository(ShiftService.client)

    private val shiftResponse  = MutableLiveData<ShiftResponse>()
    val shiftLiveData : LiveData<ShiftResponse> get() = shiftResponse

    fun getJobs(date : String, loadingView : View?) {
        viewModelScope.launch {
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