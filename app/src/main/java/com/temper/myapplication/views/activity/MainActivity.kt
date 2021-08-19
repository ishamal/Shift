package com.temper.myapplication.views.activity

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.temper.myapplication.databinding.ActivityMainBinding
import com.temper.myapplication.services.response.JobDto
import com.temper.myapplication.utils.TimeUtil
import com.temper.myapplication.viewModel.MainViewModel
import com.temper.myapplication.viewModel.MainViewModelFactory
import com.temper.myapplication.views.adapter.JobsAdapter
import com.temper.myapplication.views.adapter.JobsClickLister
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), JobsClickLister {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var jobsAdapter: JobsAdapter

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

    private fun getTodayJobs() {
        mainViewModel.getJobs(
            TimeUtil.getCurrentDate("yyyy-MM-dd"), mainActivityBinding.progressCircular
        )
    }

    private fun initViewModel() {
        val viewModelFactory = MainViewModelFactory()
        mainViewModel =
            ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun initRecyclerView() {
        jobsAdapter = JobsAdapter(this, ArrayList(), this)
        mainActivityBinding.shiftList.layoutManager = LinearLayoutManager(this)
        mainActivityBinding.shiftList.adapter = jobsAdapter
    }

    private fun observers() {
        mainViewModel.shiftLiveData.observe(
            this, {
                GlobalScope.launch (Main){
                    mainActivityBinding.progressCircular.visibility = View.GONE
                    mainActivityBinding.refLayout.isRefreshing = false
                }
//                Thread {
//                    this.runOnUiThread {
//                        mainActivityBinding.progressCircular.visibility = View.GONE
//                        mainActivityBinding.refLayout.isRefreshing = false
//                    }
//                }.start()
                jobsAdapter.setData(it.data)
            }
        )
    }

    override fun onClicked(job: JobDto) {
        val gmmIntentUri = Uri
            .parse("geo:${job.job?.report_at_address?.geo?.lat}, ${job.job?.report_at_address?.geo?.lon}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(packageManager)?.let {
            startActivity(mapIntent)
        }

    }
}