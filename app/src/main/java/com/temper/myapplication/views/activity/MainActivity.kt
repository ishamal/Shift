package com.temper.myapplication.views.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.temper.myapplication.R
import com.temper.myapplication.Shifts
import com.temper.myapplication.databinding.ActivityMainBinding
import com.temper.myapplication.services.response.JobDto
import com.temper.myapplication.utils.TimeUtil
import com.temper.myapplication.viewModel.MainViewModel
import com.temper.myapplication.viewModel.MainViewModelFactory
import com.temper.myapplication.views.adapter.JobsAdapter
import com.temper.myapplication.views.adapter.JobsClickLister

class MainActivity : AppCompatActivity(), JobsClickLister {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var jobsAdapter: JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Shifts.applicationContext().setCurrentActivity(this)

        mainActivityBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        mainActivityBinding.lifecycleOwner = this
        val view = mainActivityBinding.root
        setContentView(view)
        mainActivityBinding.todayTextView.text = TimeUtil.formatDate()

        initViewModel()
        initRecyclerView()
        getTodayJobs()

        mainActivityBinding.refLayout.setOnRefreshListener {
            getTodayJobs()
        }

        mainActivityBinding.logInButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        mainActivityBinding.signUpButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun getTodayJobs() {
        mainViewModel.getJobs(
            TimeUtil.getCurrentDate("yyyy-MM-dd")
            , mainActivityBinding.progressCircular
        )
    }

    private fun initViewModel() {
        val viewModelFactory = MainViewModelFactory()
        mainViewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel::class.java)
        mainActivityBinding.viewModel = mainViewModel
    }

    private fun initRecyclerView() {
        jobsAdapter = JobsAdapter(this
            , ArrayList()
            , this)
        mainActivityBinding.shiftList.layoutManager = LinearLayoutManager(this)
        mainActivityBinding.shiftList.adapter = jobsAdapter
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