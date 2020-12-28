package com.example.uasoop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uasoop2.Database.Pembeli

import kotlinx.android.synthetic.main.adapter_pembeli.view.*

class PembeliAdapter (private val AllPembeli: ArrayList<Pembeli>, private val listener: OnAdapterListener) : RecyclerView.Adapter<PembeliAdapter.PembeliViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PembeliViewHolder {
        return PembeliViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_pembeli, parent, false)
        )
    }

    override fun getItemCount() = AllPembeli.size

    override fun onBindViewHolder(holder: PembeliViewHolder, position: Int) {
        val pembeli = AllPembeli[position]
        holder.view.text_merk.text = pembeli.merk
        holder.view.text_merk.setOnClickListener {
            listener.onClick(pembeli)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(pembeli)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(pembeli)
        }
    }

    class PembeliViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Pembeli>) {
        AllPembeli.clear()
        AllPembeli.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(pembeli: Pembeli)
        fun onDelete(pembeli: Pembeli)
        fun onUpdate(pembeli: Pembeli)
    }
}