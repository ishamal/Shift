package com.temper.myapplication.views.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.temper.myapplication.R
import kotlinx.android.synthetic.main.components_list_view_item.view.*

@SuppressLint("CustomViewStyleable", "Recycle")
class ListViewItemComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(
    context, attrs, defStyleAttr
) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.components_list_view_item, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.listViewItem, 0, 0
            )

            if (typedArray.hasValue(R.styleable.listViewItem_type)) {
                setJobType(
                    typedArray.getString(
                        R.styleable.listViewItem_type

                    )
                )
            }

            if (typedArray.hasValue(R.styleable.listViewItem_name)) {
                setJobTitle(
                    typedArray.getString(
                        R.styleable.listViewItem_name
                    )
                )
            }

            if (typedArray.hasValue(R.styleable.listViewItem_rate)) {
                setHourlyRate(
                    typedArray.getString(
                        R.styleable.listViewItem_rate
                    )
                )
            }

            if (typedArray.hasValue(R.styleable.listViewItem_time)) {
                setHours(
                    typedArray.getString(
                        R.styleable.listViewItem_time
                    )
                )
            }

            if (typedArray.hasValue(R.styleable.listViewItem_jobImage)) {
                etImageDrawable(typedArray.getDrawable(R.styleable.listViewItem_jobImage))
            }

        }
    }

    fun setImage(bitmap: Bitmap?) {
        shiftProfileImageView.setImageBitmap(bitmap)
    }

    fun etImageDrawable(drawable: Drawable?) {
        shiftProfileImageView.setImageDrawable(drawable)
    }

    fun setHourlyRate(text: String?) {
        hourlyRateTextView.text = text
    }

    fun setJobType(text: String?) {
        titleAndDistance.text = text
    }

    fun setJobTitle(text: String?) {
        jobTitleTextView.text = text
    }

    fun setHours(text: String?) {
        timePeriodTextView.text = text
    }

    fun getImageView() : ImageView {
        return shiftProfileImageView
    }

}