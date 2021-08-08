package com.temper.myapplication.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.temper.myapplication.R
import com.temper.myapplication.services.response.JobDto
import com.temper.myapplication.views.viewHolder.JobsViewHolder

class JobsAdapter(
    private val jobsClickLister: JobsClickLister,
    private var jobs: ArrayList<JobDto>,
    private val context: Context,
) : RecyclerView.Adapter<JobsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        return JobsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)
        )
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

}

interface JobsClickLister{
    fun onClicked(job : JobDto, position: Int)
}