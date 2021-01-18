package com.example.crudbaju

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudkoi.Database.AppRoomDB
import com.example.crudkoi.Database.Constant
import com.example.crudkoi.Database.Baju
import com.example.crudkoi.R
import kotlinx.android.synthetic.main.activity_baju.*
import kotlinx.android.synthetic.main.activity_baju.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BajuActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var bajuAdapter: BajuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baju)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadBaju()
    }

    fun loadBaju(){
        CoroutineScope(Dispatchers.IO).launch {
            val allBaju = db.bajuDao().getAllBaju()
            Log.d("BajuActivity", "dbResponse: $allBaju")
            withContext(Dispatchers.Main) {
                bajuAdapter.setData(allBaju)
            }
        }
    }

    fun setupListener() {
        btn_createBaju.setOnClickListener {
           intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        bajuAdapter = BajuAdapter(arrayListOf(), object: BajuAdapter.OnAdapterListener {
            override fun onClick(baju: Baju) {
                intentEdit(baju.id, Constant.TYPE_READ)
            }

            override fun onDelete(baju: Baju) {
                deleteDialog(baju)
            }

            override fun onUpdate(baju: Baju) {
                // edit data
                intentEdit(baju.id, Constant.TYPE_UPDATE)
            }

        })
        list_baju.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = bajuAdapter
        }
    }

    fun intentEdit(bajuId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditBajuActivity::class.java)
                .putExtra("intent_id", bajuId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(baju: Baju) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.bajuDao().deleteBaju(baju)
                    loadBaju()
                }
            }
        }
        alertDialog.show()
    }
}