package com.temper.myapplication.viewModel

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.temper.myapplication.repositories.ShiftRepository
import com.temper.myapplication.services.ShiftService
import com.temper.myapplication.services.response.JobDto
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val shiftRepository: ShiftRepository = ShiftRepository(ShiftService.client)

    val isLoading = ObservableBoolean()

    private val shiftResponse  = MutableLiveData<ArrayList<JobDto>>()
    val shiftLiveData : LiveData<ArrayList<JobDto>> get() = shiftResponse

    fun getJobs(date : String, loadingView : View?) {
        isLoading.set(true)
        viewModelScope.launch {
            val result = shiftRepository.getJobList(date, loadingView)
            if (result != null) {
                isLoading.set(false)
                shiftResponse.postValue(result.data)
            } else {
                isLoading.set(false)
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