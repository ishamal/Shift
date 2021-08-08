package com.temper.myapplication.views.viewHolder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.temper.myapplication.R
import com.temper.myapplication.services.response.JobDto
import com.temper.myapplication.utils.CurrencyUtil
import com.temper.myapplication.utils.ImageUtil
import com.temper.myapplication.utils.TimeUtil
import com.temper.myapplication.views.adapter.JobsClickLister
import com.temper.myapplication.views.components.ListViewItemComponent

class JobsViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

    private val context: Context = view.context
    private lateinit var jobView : ListViewItemComponent

    fun bind(job : JobDto, jobsClickLister: JobsClickLister) {
        jobView = view.findViewById(R.id.listItem)
        if (job.earnings_per_hour != null && job.earnings_per_hour?.amount != null) {
            jobView.setHourlyRate("${job.earnings_per_hour?.currency?.let {
                CurrencyUtil.getCurrencySymbol(
                    it
                )
            }} ${job.earnings_per_hour?.amount?.let { it}}")
        }

        if (job.job != null) {
            jobView.setJobType("${job.job?.category?.slug}")
            jobView.setJobTitle("${job.job?.title}")
        }

        jobView.setHours("${job.starts_at?.let { TimeUtil.calTime(it) }} - ${job.ends_at?.let {
            TimeUtil.calTime(it) }}")

        Glide.with(jobView.getImageView())
            .load(job.job?.links?.hero_380_image)
            .into(jobView.getImageView())

    }
}