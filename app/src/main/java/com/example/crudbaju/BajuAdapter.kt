package com.example.crudbaju

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudbaju.Database.Baju
import com.example.crudbaju.R
import kotlinx.android.synthetic.main.adapter_baju.view.*
import kotlinx.android.synthetic.main.adapter_baju.view.*

class BajuAdapter (private val  allBaju: ArrayList<Baju>, private val listener: OnAdapterListener) : RecyclerView.Adapter<BajuAdapter.bajuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bajuViewHolder {
        return bajuViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_baju, parent, false)
        )
    }

    override fun getItemCount() = allBaju.size

    override fun onBindViewHolder(holder: bajuViewHolder, position: Int) {
        val baju = allBaju[position]
        holder.view.text_merk.text = baju.jenis
        holder.view.text_merk.setOnClickListener {
            listener.onClick(baju)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(baju)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(baju)
        }
    }

    class bajuViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Baju>) {
        allBaju.clear()
        allBaju.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(baju: Baju)
        fun onDelete(baju: Baju)
        fun onUpdate(baju: Baju)
    }
}