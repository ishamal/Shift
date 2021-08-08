package com.temper.myapplication.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.temper.myapplication.databinding.ActivityMainBinding
import com.temper.myapplication.services.response.JobDto
import com.temper.myapplication.utils.TimeUtil
import com.temper.myapplication.viewModel.MainViewModel
import com.temper.myapplication.viewModel.MainViewModelFactory
import com.temper.myapplication.views.adapter.JobsAdapter
import com.temper.myapplication.views.adapter.JobsClickLister

class MainActivity : AppCompatActivity(), JobsClickLister {

    private lateinit var mainActivityBinding :ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel
    private lateinit var jobsAdapter : JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainActivityBinding.root
        setContentView(view)

        mainActivityBinding.todayTextView.text = TimeUtil.formatDate()

        initViewModel()
        initRecyclerView()
        getTodayJobs()
        observers()

        mainActivityBinding.refLayout.setOnRefreshListener {
            getTodayJobs()
        }
    }

    fun getTodayJobs(){
        mainViewModel.getJobs(TimeUtil.getCurrentDate("yyyy-MM-dd"))
    }

    fun initViewModel() {
        val viewModelFactory = MainViewModelFactory()
        mainViewModel =
            ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

    }

    fun initRecyclerView() {
        jobsAdapter = JobsAdapter(this, ArrayList(), this)
        mainActivityBinding.shiftList.layoutManager = LinearLayoutManager(this)
        mainActivityBinding.shiftList.adapter = jobsAdapter
    }

    private fun observers() {
        mainViewModel.shiftResponse.observe(this, {

            Thread(Runnable {
                this.runOnUiThread {
                    mainActivityBinding.refLayout.isRefreshing = false
                }
            }).start()

            jobsAdapter.setData(it.data)
        })
    }

    override fun onClicked(job: JobDto, position: Int) {

    }
}