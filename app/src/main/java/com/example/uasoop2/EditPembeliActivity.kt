package com.example.uasoop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.uasoop2.Database.AppRoomDB
import com.example.uasoop2.Database.Constant
import com.example.uasoop2.Database.Pembeli
import kotlinx.android.synthetic.main.activity_edit_pembeli.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPembeliActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var pembeliId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pembeli)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_savePembeli.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.pembeliDao().addPembeli(
                    Pembeli(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
        btn_updatePembeli.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.pembeliDao().updatePembeli(
                    Pembeli(pembeliId, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btn_updatePembeli.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_savePembeli.visibility = View.GONE
                btn_updatePembeli.visibility = View.GONE
                getPembeli()
            }
            Constant.TYPE_UPDATE -> {
                btn_savePembeli.visibility = View.GONE
                getPembeli()
            }
        }
    }

    fun getPembeli() {
        pembeliId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
           val pembelis =  db.pembeliDao().getPembeli( pembeliId )[0]
            txt_merk.setText( pembelis.merk )
            txt_stok.setText( pembelis.stok.toString() )
            txt_harga.setText( pembelis.harga.toString() )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}