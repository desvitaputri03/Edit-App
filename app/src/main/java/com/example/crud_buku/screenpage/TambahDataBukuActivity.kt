package com.example.crud_buku.screenpage

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_buku.DbHelper.DbHelper
import com.example.crud_buku.databinding.ActivityTambahDataBukuBinding
import com.example.crud_buku.model.ModelBuku

class TambahDataBukuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahDataBukuBinding
    private lateinit var db: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahDataBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DbHelper(this)
        binding.btnTambahData.setOnClickListener {
            val judul = binding.txtInputJudul.text.toString()
            val pengarang = binding.txtInputPengarang.text.toString()
            val genre = binding.txtInputGenre.text.toString()

            if (judul.isNotEmpty() && pengarang.isNotEmpty() && genre.isNotEmpty()) {
                val dataBuku = ModelBuku(0, judul, pengarang, genre)
                db.insertDataBuku(dataBuku)
                finish()
                Toast.makeText(this, "Berhasil Tambah Data Buku", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
