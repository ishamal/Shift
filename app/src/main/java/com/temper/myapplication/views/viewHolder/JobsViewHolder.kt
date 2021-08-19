package com.temper.myapplication.views.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.temper.myapplication.R
import com.temper.myapplication.databinding.ItemListViewBinding
import com.temper.myapplication.services.response.JobDto
import com.temper.myapplication.utils.CurrencyUtil
import com.temper.myapplication.utils.TimeUtil
import com.temper.myapplication.views.adapter.JobsClickLister

class JobsViewHolder(
    private val parent: ViewGroup,
    private val binding: ItemListViewBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_list_view,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(job : JobDto, jobsClickLister: JobsClickLister) {
        binding.job = job

        binding.itemButton.setOnClickListener {
            jobsClickLister.onClicked(job)
        }

        if (job.earnings_per_hour != null && job.earnings_per_hour?.amount != null) {
            binding.listItem.setHourlyRate("${job.earnings_per_hour?.currency?.let {
                CurrencyUtil.getCurrencySymbol(
                    it
                )
            }} ${job.earnings_per_hour?.amount?.let { it}}")
        }

        if (job.job != null) {
            binding.listItem.setJobType("${job.job?.category?.slug}")
            binding.listItem.setJobTitle("${job.job?.title}")
        }

        binding.listItem.setHours("${job.starts_at?.let { TimeUtil.calTime(it) }} - ${job.ends_at?.let {
            TimeUtil.calTime(it) }}")

        Glide.with(binding.listItem.getImageView())
            .load(job.job?.links?.hero_380_image)
            .into(binding.listItem.getImageView())
    }
}