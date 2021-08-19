package com.temper.myapplication.views.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.temper.myapplication.services.response.JobDto
import com.temper.myapplication.views.viewHolder.JobsViewHolder

class JobsAdapter(
    private val jobsClickLister: JobsClickLister,
    private var jobs: ArrayList<JobDto>,
    private val context: Context,
) : RecyclerView.Adapter<JobsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        return JobsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val job = jobs[position]
        holder.bind(job, jobsClickLister)
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    fun setData ( newJobs : ArrayList<JobDto>) {
        jobs = newJobs
        notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("jobs")
        fun RecyclerView.bindItems(items: ArrayList<JobDto>?) {
            val adapter = adapter as JobsAdapter
            items?.let { adapter.setData(it) }
        }
    }
}

interface JobsClickLister{
    fun onClicked(job : JobDto)
}