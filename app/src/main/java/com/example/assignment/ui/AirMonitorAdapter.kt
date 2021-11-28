package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.utils.ApplicationUtils
import com.rtugeek.android.colorseekbar.ColorSeekBar


class AirMonitorAdapter(private val onClick: (SocketUpdate) -> Unit) :
    ListAdapter<SocketUpdate, AirMonitorAdapter.AirMonitorViewHolder>(AirMonitorDiffCallback) {


    class AirMonitorViewHolder(itemView: View, val onClick: (SocketUpdate) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        var selectedList = mutableListOf<SocketUpdate>()
        private val tvCity: TextView = itemView.findViewById(R.id.tvcity)
        private val tvAir: TextView = itemView.findViewById(R.id.tvaqi)
        private val tvTime: TextView = itemView.findViewById(R.id.tvtime)
        private val seekBar: ColorSeekBar = itemView.findViewById(R.id.seekbar)
        private var currentItem: SocketUpdate? = null

        init {
            itemView.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }


       fun getList() : MutableList<SocketUpdate>{
           return selectedList
       }
        /* Bind item data. */
        fun bind(data: SocketUpdate) {
            currentItem = data

            tvCity.text = data.city
            tvAir.text = ApplicationUtils.decimal(data.aqi.toDouble())
            seekBar.setProgress(data.aqi.toDouble().toInt()/5)
            tvTime.text= ApplicationUtils.getDateDifference(data.time.toLong())

        }
    }

    /* Creates and inflates view and return ViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirMonitorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        return AirMonitorViewHolder(view, onClick)
    }

    /* Gets current item and uses it to bind view. */
    override fun onBindViewHolder(holder: AirMonitorViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }
}

object AirMonitorDiffCallback : DiffUtil.ItemCallback<SocketUpdate>() {
    override fun areItemsTheSame(oldItem: SocketUpdate, newItem: SocketUpdate): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SocketUpdate, newItem: SocketUpdate): Boolean {


        return oldItem.city == newItem.city
    }

}
