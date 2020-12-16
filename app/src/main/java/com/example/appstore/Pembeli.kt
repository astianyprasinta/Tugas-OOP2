package com.UAS.apps

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import com.example.UAS.Database.Pembeli
import java.text.DecimalFormat
import kotlinx.android.synthetic.main.adapter_helm.view.*

@Entity(tableName = "pembeli")
data class Pembeli (
        @PrimaryKey val id_pembeli: Int,
        @ColumnInfo(name = "nama_pembeli") val nama_pembeli: Text?,
        @ColumnInfo(name = "alamat") val alamat : Text?


class Pembeliwrg (private val AllPembeli: ArrayList<Pembeli>, private val listener: OnAdapterListener) : RecyclerView.Adapter<PembeliAdapter.PembeliViewHolder>() {

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
    }

    class PembeliViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Pembeli>) {
        AllPembeli.clear()
        AllPembeli.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(pembeli: Pembeli)
    }
} 
)
