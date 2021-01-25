package com.example.crudbaju

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ModelBajuAdapter(val mCtx : Context, val layoutResId : Int, val bajuList :List<Modelbaju> ) : ArrayAdapter<Modelbaju>(mCtx, layoutResId, bajuList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(layoutResId, null)

        val tvNamaIkan = view.findViewById<TextView>(R.id.tv_baju)
        val tvJumlah = view.findViewById<TextView>(R.id.tv_jumlah)

        val ikan = bajuList[position]

        tvNamaIkan.text = ikan.nama
        tvJumlah.text = ikan.jumlah.toString()

        return view

    }
}