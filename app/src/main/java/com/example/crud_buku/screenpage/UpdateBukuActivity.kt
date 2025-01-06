package com.example.crud_buku.screenpage

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_buku.DbHelper.DbHelper
import com.example.crud_buku.R
import com.example.crud_buku.databinding.ActivityUpdateBukuBinding
import com.example.crud_buku.model.ModelBuku

class UpdateBukuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBukuBinding
    private lateinit var db: DbHelper
    private var bukuId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DbHelper(this)

        bukuId = intent.getIntExtra("id_buku", -1)
        if (bukuId == -1) {
            finish()
            return
        }

        val buku = db.getBukuById(bukuId)
        binding.etJudul.setText(buku.judul)
        binding.etPengarang.setText(buku.pengarang)
        binding.etGenre.setText(buku.genre)

        binding.btnEditBuku.setOnClickListener {
            val newJudul = binding.etJudul.text.toString()
            val newPengarang = binding.etPengarang.text.toString()
            val newGenre = binding.etGenre.text.toString()

            if (newJudul.isEmpty() || newPengarang.isEmpty() || newGenre.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val updateBuku = ModelBuku(bukuId, newJudul, newPengarang, newGenre)
            db.updateBuku(updateBuku)
            finish()

            Toast.makeText(this, "Update Buku Berhasil", Toast.LENGTH_LONG).show()
        }
    }
}
