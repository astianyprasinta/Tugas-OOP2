package com.example.crudbaju

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase

class AddModelBajuActivity : AppCompatActivity() {

    private lateinit var tvNama : TextView
    private lateinit var etBaju : EditText
    private lateinit var etJumlah : EditText
    private lateinit var btnBaju : Button
    private lateinit var lvBaju : ListView
    private lateinit var bajuList : MutableList<Modelbaju>
    private lateinit var ref : DatabaseReference


    companion object{
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_model_baju)

        val id =intent.getStringExtra(EXTRA_ID)
        val nama = intent.getStringExtra(EXTRA_NAMA)

        bajuList = mutableListOf()

        ref = FirebaseDatabase.getInstance().getReference("model_baju").child(id.toString())

        tvNama = findViewById(R.id.tv_nama)
        etBaju = findViewById(R.id.et_baju)
        etJumlah = findViewById(R.id.et_jumlah)
        btnBaju = findViewById(R.id.btn_baju)
        lvBaju = findViewById(R.id.lv_baju)

        btnBaju.setOnClickListener{
            saveBaju()
        }
    }

    fun saveBaju(){
        val namaBaju = etBaju.text.toString().trim()
        val jumlahText = etJumlah.text.toString().trim()
        val jumlah = jumlahText.toInt()

        if(namaBaju.isEmpty()){
            etBaju.error = "Baju harus diisi"
            return
        }
        if(jumlahText.isEmpty()){
            etJumlah.error = "Jumlah harus diisi"
            return
        }

        val bajuId = ref.push().key

        val baju = Modelbaju(bajuId!!,namaBaju,jumlah)

        if (bajuId != null) {
            ref.child(bajuId).setValue(baju).addOnCompleteListener{
                Toast.makeText(applicationContext, "ModelBaju berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    bajuList.clear()
                    for(h in p0.children){
                        val modelbaju = h.getValue(Modelbaju::class.java)
                        if (modelbaju != null) {
                            bajuList.add(modelbaju)
                        }
                    }

                    val adapter = ModelBajuAdapter(this@AddModelBajuActivity, R.layout.item_baju, bajuList)
                    lvBaju.adapter = adapter
                }
            }

        })
    }
}
