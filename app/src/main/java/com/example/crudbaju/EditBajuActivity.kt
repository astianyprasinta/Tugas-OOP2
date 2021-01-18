package com.example.crudbaju

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.crudbaju.Database.AppRoomDB
import com.example.crudbaju.Database.Constant
import com.example.crudbaju.Database.Baju
import com.example.crudbaju.R
import kotlinx.android.synthetic.main.activity_edit_baju.*
import kotlinx.android.synthetic.main.activity_edit_baju.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditBajuActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var bajuId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_baju)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_saveBaju.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.bajuDao().addBaju(
                        Baju(0, txt_jenis.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
        btn_updateBaju.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.bajuDao().updateBaju(
                    Baju(bajuId, txt_jenis.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
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
                btn_updateBaju.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_saveBaju.visibility = View.GONE
                btn_updateBaju.visibility = View.GONE
                getBaju()
            }
            Constant.TYPE_UPDATE -> {
                btn_saveBaju.visibility = View.GONE
                getBaju()
            }
        }
    }

    fun getBaju() {
        bajuId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
           val bajus =  db.bajuDao().getBaju( bajuId )[0]
            txt_jenis.setText( bajus.jenis )
            txt_stok.setText( bajus.stok.toString() )
            txt_harga.setText( bajus.harga.toString() )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}